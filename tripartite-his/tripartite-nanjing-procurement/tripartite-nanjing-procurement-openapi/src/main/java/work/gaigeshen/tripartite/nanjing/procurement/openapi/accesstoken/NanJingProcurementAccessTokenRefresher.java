package work.gaigeshen.tripartite.nanjing.procurement.openapi.accesstoken;

import work.gaigeshen.tripartite.nanjing.procurement.openapi.config.NanJingProcurementConfig;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.exception.NanJingProcurementAccessTokenRefreshException;

/**
 * 访问令牌刷新器
 *
 * @author gaigeshen
 */
public interface NanJingProcurementAccessTokenRefresher {
    /**
     * 刷新访问令牌
     *
     * @param config 配置信息不能为空
     * @param oldAccessToken 旧的访问令牌不能为空
     * @return 新的访问令牌不能为空
     * @throws NanJingProcurementAccessTokenRefreshException 刷新访问令牌失败
     */
    default NanJingProcurementAccessToken refresh(NanJingProcurementConfig config, NanJingProcurementAccessToken oldAccessToken)
            throws NanJingProcurementAccessTokenRefreshException {
        throw new NanJingProcurementAccessTokenRefreshException("Please override this method to refresh access token")
                .setCurrentAccessToken(oldAccessToken)
                .setCanRetry(false);
    }
}
