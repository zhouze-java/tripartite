package work.gaigeshen.tripartite.jiangsu.online.openapi.accesstoken;

import work.gaigeshen.tripartite.jiangsu.online.openapi.config.JiangSuOnlineConfig;
import work.gaigeshen.tripartite.jiangsu.online.openapi.exception.JiangSuOnlineAccessTokenRefreshException;

/**
 * 访问令牌刷新器
 *
 * @author gaigeshen
 */
public interface JiangSuOnlineAccessTokenRefresher {
    /**
     * 刷新访问令牌
     *
     * @param config 配置信息不能为空
     * @param oldAccessToken 旧的访问令牌不能为空
     * @return 新的访问令牌不能为空
     * @throws JiangSuOnlineAccessTokenRefreshException 刷新访问令牌失败
     */
    default JiangSuOnlineAccessToken refresh(JiangSuOnlineConfig config, JiangSuOnlineAccessToken oldAccessToken)
            throws JiangSuOnlineAccessTokenRefreshException {
        throw new JiangSuOnlineAccessTokenRefreshException("Please override this method to refresh access token")
                .setCurrentAccessToken(oldAccessToken)
                .setCanRetry(false);
    }
}
