package work.gaigeshen.jiangsu.openapi.accesstoken;

import work.gaigeshen.jiangsu.openapi.config.JiangSuProcurementConfig;
import work.gaigeshen.jiangsu.openapi.exception.JiangSuProcurementAccessTokenRefreshException;

/**
 * 访问令牌刷新器
 *
 * @author gaigeshen
 */
public interface JiangSuProcurementAccessTokenRefresher {
    /**
     * 刷新访问令牌
     *
     * @param config 配置信息不能为空
     * @param oldAccessToken 旧的访问令牌不能为空
     * @return 新的访问令牌不能为空
     * @throws JiangSuProcurementAccessTokenRefreshException 刷新访问令牌失败
     */
    default JiangSuProcurementAccessToken refresh(JiangSuProcurementConfig config, JiangSuProcurementAccessToken oldAccessToken)
            throws JiangSuProcurementAccessTokenRefreshException {
        throw new JiangSuProcurementAccessTokenRefreshException("Please override this method to refresh access token")
                .setCurrentAccessToken(oldAccessToken)
                .setCanRetry(false);
    }
}
