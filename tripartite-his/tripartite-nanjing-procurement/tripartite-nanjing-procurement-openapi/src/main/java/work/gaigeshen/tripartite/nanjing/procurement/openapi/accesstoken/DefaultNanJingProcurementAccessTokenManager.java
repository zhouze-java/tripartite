package work.gaigeshen.tripartite.nanjing.procurement.openapi.accesstoken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.config.NanJingProcurementConfig;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.exception.HisProcurementAccessTokenStoreException;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.exception.NanJingProcurementAccessTokenManagerException;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.exception.NanJingProcurementAccessTokenUpdateException;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 访问令牌管理器实现，创建此管理器的时候，会同时为存储器中所有的访问令牌创建并调度更新任务，这些任务的执行时间早于访问令牌过期时间十分钟
 *
 * @author gaigeshen
 */
public class DefaultNanJingProcurementAccessTokenManager implements NanJingProcurementAccessTokenManager {

    private static final Logger log = LoggerFactory.getLogger(DefaultNanJingProcurementAccessTokenManager.class);

    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

    private final NanJingProcurementAccessTokenStore accessTokenStore;

    private final NanJingProcurementAccessTokenRefresher accessTokenRefresher;

    /**
     * 创建访问令牌管理器，将会从访问令牌存储器中查询所有的访问令牌，并为这些访问令牌创建并调度更新任务
     *
     * @param accessTokenStore 访问令牌存储器不能为空
     * @param accessTokenRefresher 访问令牌刷新器不能为空
     * @throws NanJingProcurementAccessTokenManagerException 在为访问令牌创建并调度更新任务的时候发生异常
     */
    public DefaultNanJingProcurementAccessTokenManager(
            NanJingProcurementAccessTokenStore accessTokenStore, NanJingProcurementAccessTokenRefresher accessTokenRefresher)
            throws NanJingProcurementAccessTokenManagerException {
        ArgumentValidate.notNull(accessTokenStore, "accessTokenStore cannot be null");
        ArgumentValidate.notNull(accessTokenRefresher, "accessTokenRefresher cannot be null");
        this.accessTokenStore = accessTokenStore;
        this.accessTokenRefresher = accessTokenRefresher;
        createAndScheduleUpdateTasks();
    }

    @Override
    public void addNewAccessToken(NanJingProcurementConfig config, NanJingProcurementAccessToken accessToken)
            throws NanJingProcurementAccessTokenManagerException {
        ArgumentValidate.notNull(config, "config cannot be null");
        ArgumentValidate.notNull(accessToken, "accessToken cannot be null");
        if (!NanJingProcurementAccessTokenHelper.isValid(accessToken)) {
            throw new NanJingProcurementAccessTokenManagerException("Could not add invalid access token: " + accessToken);
        }
        try {
            if (!accessTokenStore.save(config, accessToken)) return;
        } catch (HisProcurementAccessTokenStoreException e) {
            throw new NanJingProcurementAccessTokenManagerException("Could not add new access token: " + accessToken, e);
        }
        try {
            createAndScheduleUpdateTask(config, accessToken);
        } catch (Exception e) {
            throw new NanJingProcurementAccessTokenManagerException(
                    "Could not schedule update task for new access token: " + accessToken, e);
        }
    }

    @Override
    public NanJingProcurementAccessToken findAccessToken(NanJingProcurementConfig config) throws NanJingProcurementAccessTokenManagerException {
        ArgumentValidate.notNull(config, "config cannot be null");
        try {
            return accessTokenStore.find(config);
        } catch (HisProcurementAccessTokenStoreException e) {
            throw new NanJingProcurementAccessTokenManagerException("Could not find access token: " + config, e);
        }
    }

    @Override
    public synchronized void shutdown() throws NanJingProcurementAccessTokenManagerException {
        executorService.shutdownNow();
        try {
            if (executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                log.info("access token manager terminated");
            } else {
                log.warn("access token manager termination timeout");
            }
        } catch (InterruptedException e) {
            throw new NanJingProcurementAccessTokenManagerException("Current thread interrupted while shutting down", e);
        }
    }

    private void createAndScheduleUpdateTasks() throws NanJingProcurementAccessTokenManagerException {
        try {
            accessTokenStore.findAll().forEach(this::createAndScheduleUpdateTask);
        } catch (Exception e) {
            throw new NanJingProcurementAccessTokenManagerException("Could not schedule access token update tasks", e);
        }
    }

    private void createAndScheduleUpdateTask(NanJingProcurementConfig config, NanJingProcurementAccessToken accessToken) {
        long remainingDuration = NanJingProcurementAccessTokenHelper.getRemainingDuration(accessToken);
        createAndScheduleUpdateTask(config, remainingDuration - 600);
    }

    private void createAndScheduleUpdateTask(NanJingProcurementConfig config, long delaySeconds) {
        executorService.schedule(createUpdateTask(config), delaySeconds, TimeUnit.SECONDS);
    }

    private NanJingProcurementAccessTokenUpdateTask createUpdateTask(NanJingProcurementConfig config) {
        return new NanJingProcurementAccessTokenUpdateTaskImpl(config);
    }

    /**
     * 访问令牌更新任务实现，使用当前访问令牌管理器关联的存储器
     *
     * @author gaigeshen
     */
    private class NanJingProcurementAccessTokenUpdateTaskImpl extends AbstractNanJingProcurementAccessTokenUpdateTask {

        public NanJingProcurementAccessTokenUpdateTaskImpl(NanJingProcurementConfig config) {
            setAccessTokenStore(accessTokenStore);
            setAccessTokenUpdateListener(new NanJingProcurementAccessTokenUpdateListenerImpl());
            setConfig(config);
        }

        @Override
        protected NanJingProcurementAccessToken executeUpdate(NanJingProcurementAccessToken currentAccessToken)
                throws NanJingProcurementAccessTokenUpdateException {
            return accessTokenRefresher.refresh(getConfig(), currentAccessToken);
        }
    }

    /**
     * 访问令牌更新监听器实现，在访问令牌被更新成功之后（获取到新的访问令牌且保存到存储器中成功），将会创建并调度新的访问令牌更新任务，
     *
     * @author gaigeshen
     */
    private class NanJingProcurementAccessTokenUpdateListenerImpl implements NanJingProcurementAccessTokenUpdateListener {
        @Override
        public void handleUpdated(NanJingProcurementConfig config, NanJingProcurementAccessToken oldAccessToken, NanJingProcurementAccessToken newAccessToken) {
            log.info("Access token updated, old access token is {}, new access token is {}", oldAccessToken, newAccessToken);
            try {
                createAndScheduleUpdateTask(config, newAccessToken);
            } catch (Exception e) {
                log.warn("Could not schedule access token update task, current access token is " + newAccessToken, e);
            }
        }

        @Override
        public void handleFailed(NanJingProcurementConfig config, NanJingProcurementAccessTokenUpdateException ex) {
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
