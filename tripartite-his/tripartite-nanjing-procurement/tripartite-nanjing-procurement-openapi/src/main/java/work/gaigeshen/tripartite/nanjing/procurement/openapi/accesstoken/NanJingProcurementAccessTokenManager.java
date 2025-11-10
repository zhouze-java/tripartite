package work.gaigeshen.tripartite.nanjing.procurement.openapi.accesstoken;


import work.gaigeshen.tripartite.nanjing.procurement.openapi.config.NanJingProcurementConfig;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.exception.NanJingProcurementAccessTokenManagerException;

/**
 * 访问令牌管理器，维护所有访问令牌的更新
 *
 * @author gaigeshen
 */
public interface NanJingProcurementAccessTokenManager {

    /**
     * 添加新的访问令牌，如果该访问令牌所属的配置信息已经存在访问令牌，则会将此新的访问令牌替换掉旧的
     *
     * @param config 配置信息不能为空
     * @param accessToken 待添加的访问令牌不能为空
     * @throws NanJingProcurementAccessTokenManagerException 无法添加访问令牌
     */
    void addNewAccessToken(NanJingProcurementConfig config, NanJingProcurementAccessToken accessToken) throws NanJingProcurementAccessTokenManagerException;

    /**
     * 查询访问令牌
     *
     * @param config 配置信息不能为空
     * @return 访问令牌可能为空
     * @throws NanJingProcurementAccessTokenManagerException 无法查询访问令牌
     */
    NanJingProcurementAccessToken findAccessToken(NanJingProcurementConfig config) throws NanJingProcurementAccessTokenManagerException;

    /**
     * 关闭此访问令牌管理器，此方法被调用之后将会停止所有访问令牌的更新，但应该不影响查询访问令牌
     *
     * @throws NanJingProcurementAccessTokenManagerException 关闭的时候发生异常
     */
    void shutdown() throws NanJingProcurementAccessTokenManagerException;

}
