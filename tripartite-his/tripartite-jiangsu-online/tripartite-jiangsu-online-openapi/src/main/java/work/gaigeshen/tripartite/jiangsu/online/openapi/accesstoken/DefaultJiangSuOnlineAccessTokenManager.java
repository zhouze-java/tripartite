package work.gaigeshen.tripartite.jiangsu.online.openapi.accesstoken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;
import work.gaigeshen.tripartite.jiangsu.online.openapi.config.JiangSuOnlineConfig;
import work.gaigeshen.tripartite.jiangsu.online.openapi.exception.JiangSuOnlineAccessTokenManagerException;
import work.gaigeshen.tripartite.jiangsu.online.openapi.exception.JiangSuOnlineAccessTokenStoreException;
import work.gaigeshen.tripartite.jiangsu.online.openapi.exception.JiangSuOnlineAccessTokenUpdateException;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 访问令牌管理器实现，创建此管理器的时候，会同时为存储器中所有的访问令牌创建并调度更新任务，这些任务的执行时间早于访问令牌过期时间十分钟
 *
 * @author gaigeshen
 */
public class DefaultJiangSuOnlineAccessTokenManager implements JiangSuOnlineAccessTokenManager {

    private static final Logger log = LoggerFactory.getLogger(DefaultJiangSuOnlineAccessTokenManager.class);

    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

    private final JiangSuOnlineAccessTokenStore accessTokenStore;

    private final JiangSuOnlineAccessTokenRefresher accessTokenRefresher;

    /**
     * 创建访问令牌管理器，将会从访问令牌存储器中查询所有的访问令牌，并为这些访问令牌创建并调度更新任务
     *
     * @param accessTokenStore 访问令牌存储器不能为空
     * @param accessTokenRefresher 访问令牌刷新器不能为空
     * @throws JiangSuOnlineAccessTokenManagerException 在为访问令牌创建并调度更新任务的时候发生异常
     */
    public DefaultJiangSuOnlineAccessTokenManager(
            JiangSuOnlineAccessTokenStore accessTokenStore, JiangSuOnlineAccessTokenRefresher accessTokenRefresher)
            throws JiangSuOnlineAccessTokenManagerException {
        ArgumentValidate.notNull(accessTokenStore, "accessTokenStore cannot be null");
        ArgumentValidate.notNull(accessTokenRefresher, "accessTokenRefresher cannot be null");
        this.accessTokenStore = accessTokenStore;
        this.accessTokenRefresher = accessTokenRefresher;
        createAndScheduleUpdateTasks();
    }

    @Override
    public void addNewAccessToken(JiangSuOnlineConfig config, JiangSuOnlineAccessToken accessToken)
            throws JiangSuOnlineAccessTokenManagerException {
        ArgumentValidate.notNull(config, "config cannot be null");
        ArgumentValidate.notNull(accessToken, "accessToken cannot be null");
        if (!JiangSuOnlineAccessTokenHelper.isValid(accessToken)) {
            throw new JiangSuOnlineAccessTokenManagerException("Could not add invalid access token: " + accessToken);
        }
        try {
            if (!accessTokenStore.save(config, accessToken)) return;
        } catch (JiangSuOnlineAccessTokenStoreException e) {
            throw new JiangSuOnlineAccessTokenManagerException("Could not add new access token: " + accessToken, e);
        }
        try {
            createAndScheduleUpdateTask(config, accessToken);
        } catch (Exception e) {
            throw new JiangSuOnlineAccessTokenManagerException(
                    "Could not schedule update task for new access token: " + accessToken, e);
        }
    }

    @Override
    public JiangSuOnlineAccessToken findAccessToken(JiangSuOnlineConfig config) throws JiangSuOnlineAccessTokenManagerException {
        ArgumentValidate.notNull(config, "config cannot be null");
        try {
            return accessTokenStore.find(config);
        } catch (JiangSuOnlineAccessTokenStoreException e) {
            throw new JiangSuOnlineAccessTokenManagerException("Could not find access token: " + config, e);
        }
    }

    @Override
    public synchronized void shutdown() throws JiangSuOnlineAccessTokenManagerException {
        executorService.shutdownNow();
        try {
            if (executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                log.info("access token manager terminated");
            } else {
                log.warn("access token manager termination timeout");
            }
        } catch (InterruptedException e) {
            throw new JiangSuOnlineAccessTokenManagerException("Current thread interrupted while shutting down", e);
        }
    }

    private void createAndScheduleUpdateTasks() throws JiangSuOnlineAccessTokenManagerException {
        try {
            accessTokenStore.findAll().forEach(this::createAndScheduleUpdateTask);
        } catch (Exception e) {
            throw new JiangSuOnlineAccessTokenManagerException("Could not schedule access token update tasks", e);
        }
    }

    private void createAndScheduleUpdateTask(JiangSuOnlineConfig config, JiangSuOnlineAccessToken accessToken) {
        long remainingDuration = JiangSuOnlineAccessTokenHelper.getRemainingDuration(accessToken);
        createAndScheduleUpdateTask(config, remainingDuration - 600);
    }

    private void createAndScheduleUpdateTask(JiangSuOnlineConfig config, long delaySeconds) {
        executorService.schedule(createUpdateTask(config), delaySeconds, TimeUnit.SECONDS);
    }

    private JiangSuOnlineAccessTokenUpdateTask createUpdateTask(JiangSuOnlineConfig config) {
        return new JiangSuOnlineAccessTokenUpdateTaskImpl(config);
    }

    /**
     * 访问令牌更新任务实现，使用当前访问令牌管理器关联的存储器
     *
     * @author gaigeshen
     */
    private class JiangSuOnlineAccessTokenUpdateTaskImpl extends AbstractJiangSuOnlineAccessTokenUpdateTask {

        public JiangSuOnlineAccessTokenUpdateTaskImpl(JiangSuOnlineConfig config) {
            setAccessTokenStore(accessTokenStore);
            setAccessTokenUpdateListener(new JiangSuOnlineAccessTokenUpdateListenerImpl());
            setConfig(config);
        }

        @Override
        protected JiangSuOnlineAccessToken executeUpdate(JiangSuOnlineAccessToken currentAccessToken)
                throws JiangSuOnlineAccessTokenUpdateException {
            return accessTokenRefresher.refresh(getConfig(), currentAccessToken);
        }
    }

    /**
     * 访问令牌更新监听器实现，在访问令牌被更新成功之后（获取到新的访问令牌且保存到存储器中成功），将会创建并调度新的访问令牌更新任务，
     *
     * @author gaigeshen
     */
    private class JiangSuOnlineAccessTokenUpdateListenerImpl implements JiangSuOnlineAccessTokenUpdateListener {
        @Override
        public void handleUpdated(JiangSuOnlineConfig config, JiangSuOnlineAccessToken oldAccessToken, JiangSuOnlineAccessToken newAccessToken) {
            log.info("Access token updated, old access token is {}, new access token is {}", oldAccessToken, newAccessToken);
            try {
                createAndScheduleUpdateTask(config, newAccessToken);
            } catch (Exception e) {
                log.warn("Could not schedule access token update task, current access token is " + newAccessToken, e);
            }
        }

        @Override
        public void handleFailed(JiangSuOnlineConfig config, JiangSuOnlineAccessTokenUpdateException ex) {
            log.warn("Access token update failed"
                    + (ex.isCanRetry() && ex.hasCurrentAccessToken() ? ", retry again 10 seconds later" : "")
                    + (ex.hasCurrentAccessToken() ? ", current access token is " + ex.getCurrentAccessToken() : ""), ex);
            if (ex.isCanRetry() && ex.hasCurrentAccessToken()) {
                try {
                    createAndScheduleUpdateTask(config, 10);
                } catch (Exception e) {
                    log.warn("Could not reschedule update task, current access token is " + ex.getCurrentAccessToken(), e);
                }
            }
        }
    }
}
