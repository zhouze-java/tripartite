package work.gaigeshen.tripartite.jiangsu.online.openapi.accesstoken;

import work.gaigeshen.tripartite.jiangsu.online.openapi.config.JiangSuOnlineConfig;
import work.gaigeshen.tripartite.jiangsu.online.openapi.exception.JiangSuOnlineAccessTokenUpdateException;

import java.util.Objects;

/**
 * 抽象的访问令牌更新任务，子类只需关注获取新的访问令牌
 *
 * @author gaigeshen
 */
public abstract class AbstractJiangSuOnlineAccessTokenUpdateTask implements JiangSuOnlineAccessTokenUpdateTask {

    private JiangSuOnlineAccessTokenStore accessTokenStore;

    private JiangSuOnlineAccessTokenUpdateListener accessTokenUpdateListener;

    private JiangSuOnlineConfig config;

    /**
     * 请先设置访问令牌存储器再使用此任务
     *
     * @param accessTokenStore 访问令牌存储器
     */
    @Override
    public final void setAccessTokenStore(JiangSuOnlineAccessTokenStore accessTokenStore) {
        this.accessTokenStore = accessTokenStore;
    }

    /**
     * 监听器可以不设置
     *
     * @param accessTokenUpdateListener 访问令牌更新监听器
     */
    @Override
    public final void setAccessTokenUpdateListener(JiangSuOnlineAccessTokenUpdateListener accessTokenUpdateListener) {
        this.accessTokenUpdateListener = accessTokenUpdateListener;
    }

    @Override
    public final void setConfig(JiangSuOnlineConfig config) {
        this.config = config;
    }

    @Override
    public final JiangSuOnlineAccessTokenStore getAccessTokenStore() {
        return accessTokenStore;
    }

    @Override
    public final JiangSuOnlineAccessTokenUpdateListener getAccessTokenUpdateListener() {
        return accessTokenUpdateListener;
    }

    @Override
    public final JiangSuOnlineConfig getConfig() {
        return config;
    }

    @Override
    public final void executeUpdate() {
        JiangSuOnlineAccessTokenStore accessTokenStore = getAccessTokenStore();
        JiangSuOnlineConfig config = getConfig();
        if (Objects.isNull(accessTokenStore) || Objects.isNull(config)) {
            throw new IllegalStateException("access token store or config not found");
        }
        JiangSuOnlineAccessToken currentAccessToken;
        try {
            currentAccessToken = accessTokenStore.find(config);
        } catch (Exception e) {
            handleUpdateFailed(config, null, true, "could not find current access token: " + config);
            return;
        }
        if (Objects.isNull(currentAccessToken)) {
            handleUpdateFailed(config, null, false, "current access token not found:" + config);
            return;
        }
        JiangSuOnlineAccessToken accessToken;
        try {
            accessToken = executeUpdate(currentAccessToken);
        } catch (Exception ex) {
            if (ex instanceof JiangSuOnlineAccessTokenUpdateException) {
                handleUpdateFailed(config, (JiangSuOnlineAccessTokenUpdateException) ex);
            } else {
                handleUpdateFailed(config, currentAccessToken, true, ex.getMessage());
            }
            return;
        }
        if (Objects.isNull(accessToken)) {
            return;
        }
        if (!JiangSuOnlineAccessTokenHelper.isValid(accessToken)) {
            handleUpdateFailed(config, currentAccessToken, true, "new access token is invalid: " + config);
            return;
        }
        try {
            accessTokenStore.save(config, accessToken);
        } catch (Exception e) {
            handleUpdateFailed(config, currentAccessToken, true, "could not save new access token: " + config);
            return;
        }
        JiangSuOnlineAccessTokenUpdateListener listener = getAccessTokenUpdateListener();
        if (Objects.nonNull(listener)) {
            listener.handleUpdated(config, currentAccessToken, accessToken);
        }
    }

    private void handleUpdateFailed(JiangSuOnlineConfig config, JiangSuOnlineAccessToken currentAccessToken,
                                    boolean canRetry, String error) {
        JiangSuOnlineAccessTokenUpdateException exception = new JiangSuOnlineAccessTokenUpdateException(error)
                .setCurrentAccessToken(currentAccessToken).setCanRetry(canRetry);
        handleUpdateFailed(config, exception);
    }

    private void handleUpdateFailed(JiangSuOnlineConfig config, JiangSuOnlineAccessTokenUpdateException ex) {
        JiangSuOnlineAccessTokenUpdateListener listener = getAccessTokenUpdateListener();
        if (Objects.nonNull(listener)) {
            listener.handleFailed(config, ex);
        }
    }

    /**
     * 此方法用于获取新的访问令牌，获取到的访问令牌必须有效
     *
     * @param currentAccessToken 当前的访问令牌不能为空
     * @return 返回的访问令牌必须有效且不能为空，如果为空则会导致当前的任务立即结束，且不会调用监听器的相关方法
     * @throws JiangSuOnlineAccessTokenUpdateException 无法获取新的访问令牌
     * @see JiangSuOnlineAccessTokenHelper#isValid(JiangSuOnlineAccessToken)
     */
    protected abstract JiangSuOnlineAccessToken executeUpdate(JiangSuOnlineAccessToken currentAccessToken) throws JiangSuOnlineAccessTokenUpdateException;
}
