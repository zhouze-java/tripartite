package work.gaigeshen.jiangsu.openapi.accesstoken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import work.gaigeshen.jiangsu.openapi.config.JiangSuProcurementConfig;
import work.gaigeshen.jiangsu.openapi.exception.JiangSuProcurementAccessTokenManagerException;
import work.gaigeshen.jiangsu.openapi.exception.JiangSuProcurementAccessTokenStoreException;
import work.gaigeshen.jiangsu.openapi.exception.JiangSuProcurementAccessTokenUpdateException;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 访问令牌管理器实现，创建此管理器的时候，会同时为存储器中所有的访问令牌创建并调度更新任务，
 * 这些任务的执行时间早于访问令牌过期时间 {@link JiangSuProcurementAccessTokenHelper#ACCESS_TOKEN_REFRESH_ADVANCE_SECONDS} 秒
 *
 * @author gaigeshen
 */
public class DefaultJiangSuProcurementAccessTokenManager implements JiangSuProcurementAccessTokenManager {

    private static final Logger log = LoggerFactory.getLogger(DefaultJiangSuProcurementAccessTokenManager.class);

    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

    private final JiangSuProcurementAccessTokenStore accessTokenStore;

    private final JiangSuProcurementAccessTokenRefresher accessTokenRefresher;

    /**
     * 创建访问令牌管理器，将会从访问令牌存储器中查询所有的访问令牌，并为这些访问令牌创建并调度更新任务
     *
     * @param accessTokenStore 访问令牌存储器不能为空
     * @param accessTokenRefresher 访问令牌刷新器不能为空
     * @throws JiangSuProcurementAccessTokenManagerException 在为访问令牌创建并调度更新任务的时候发生异常
     */
    public DefaultJiangSuProcurementAccessTokenManager(
            JiangSuProcurementAccessTokenStore accessTokenStore, JiangSuProcurementAccessTokenRefresher accessTokenRefresher)
            throws JiangSuProcurementAccessTokenManagerException {
        ArgumentValidate.notNull(accessTokenStore, "accessTokenStore cannot be null");
        ArgumentValidate.notNull(accessTokenRefresher, "accessTokenRefresher cannot be null");
        this.accessTokenStore = accessTokenStore;
        this.accessTokenRefresher = accessTokenRefresher;
        createAndScheduleUpdateTasks();
    }

    @Override
    public void addNewAccessToken(JiangSuProcurementConfig config, JiangSuProcurementAccessToken accessToken)
            throws JiangSuProcurementAccessTokenManagerException {
        ArgumentValidate.notNull(config, "config cannot be null");
        ArgumentValidate.notNull(accessToken, "accessToken cannot be null");
        if (!JiangSuProcurementAccessTokenHelper.isValid(accessToken)) {
            throw new JiangSuProcurementAccessTokenManagerException("Could not add invalid access token: " + accessToken);
        }
        try {
            if (!accessTokenStore.save(config, accessToken)) return;
        } catch (JiangSuProcurementAccessTokenStoreException e) {
            throw new JiangSuProcurementAccessTokenManagerException("Could not add new access token: " + accessToken, e);
        }
        try {
            createAndScheduleUpdateTask(config, accessToken);
        } catch (Exception e) {
            throw new JiangSuProcurementAccessTokenManagerException(
                    "Could not schedule update task for new access token: " + accessToken, e);
        }
    }

    @Override
    public JiangSuProcurementAccessToken findAccessToken(JiangSuProcurementConfig config) throws JiangSuProcurementAccessTokenManagerException {
        ArgumentValidate.notNull(config, "config cannot be null");
        try {
            return accessTokenStore.find(config);
        } catch (JiangSuProcurementAccessTokenStoreException e) {
            throw new JiangSuProcurementAccessTokenManagerException("Could not find access token: " + config, e);
        }
    }

    @Override
    public synchronized void shutdown() throws JiangSuProcurementAccessTokenManagerException {
        executorService.shutdownNow();
        try {
            if (executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                log.info("access token manager terminated");
            } else {
                log.warn("access token manager termination timeout");
            }
        } catch (InterruptedException e) {
            throw new JiangSuProcurementAccessTokenManagerException("Current thread interrupted while shutting down", e);
        }
    }

    private void createAndScheduleUpdateTasks() throws JiangSuProcurementAccessTokenManagerException {
        try {
            accessTokenStore.findAll().forEach(this::createAndScheduleUpdateTask);
        } catch (Exception e) {
            throw new JiangSuProcurementAccessTokenManagerException("Could not schedule access token update tasks", e);
        }
    }

    private void createAndScheduleUpdateTask(JiangSuProcurementConfig config, JiangSuProcurementAccessToken accessToken) {
        long remainingDuration = JiangSuProcurementAccessTokenHelper.getRemainingDuration(accessToken);
        long delaySeconds = remainingDuration - JiangSuProcurementAccessTokenHelper.ACCESS_TOKEN_REFRESH_ADVANCE_SECONDS;
        createAndScheduleUpdateTask(config, Math.max(0, delaySeconds));
    }

    private void createAndScheduleUpdateTask(JiangSuProcurementConfig config, long delaySeconds) {
        executorService.schedule(createUpdateTask(config), delaySeconds, TimeUnit.SECONDS);
    }

    private JiangSuProcurementAccessTokenUpdateTask createUpdateTask(JiangSuProcurementConfig config) {
        return new JiangSuProcurementAccessTokenUpdateTaskImpl(config);
    }

    /**
     * 访问令牌更新任务实现，使用当前访问令牌管理器关联的存储器
     *
     * @author gaigeshen
     */
    private class JiangSuProcurementAccessTokenUpdateTaskImpl extends AbstractJiangSuProcurementAccessTokenUpdateTask {

        public JiangSuProcurementAccessTokenUpdateTaskImpl(JiangSuProcurementConfig config) {
            setAccessTokenStore(accessTokenStore);
            setAccessTokenUpdateListener(new JiangSuProcurementAccessTokenUpdateListenerImpl());
            setConfig(config);
        }

        @Override
        protected JiangSuProcurementAccessToken executeUpdate(JiangSuProcurementAccessToken currentAccessToken)
                throws JiangSuProcurementAccessTokenUpdateException {
            return accessTokenRefresher.refresh(getConfig(), currentAccessToken);
        }
    }

    /**
     * 访问令牌更新监听器实现，在访问令牌被更新成功之后（获取到新的访问令牌且保存到存储器中成功），将会创建并调度新的访问令牌更新任务，
     *
     * @author gaigeshen
     */
    private class JiangSuProcurementAccessTokenUpdateListenerImpl implements JiangSuProcurementAccessTokenUpdateListener {
        @Override
        public void handleUpdated(JiangSuProcurementConfig config, JiangSuProcurementAccessToken oldAccessToken, JiangSuProcurementAccessToken newAccessToken) {
            log.info("Access token updated, old access token is {}, new access token is {}", oldAccessToken, newAccessToken);
            try {
                createAndScheduleUpdateTask(config, newAccessToken);
            } catch (Exception e) {
                log.warn("Could not schedule access token update task, current access token is " + newAccessToken, e);
            }
        }

        @Override
        public void handleFailed(JiangSuProcurementConfig config, JiangSuProcurementAccessTokenUpdateException ex) {
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
