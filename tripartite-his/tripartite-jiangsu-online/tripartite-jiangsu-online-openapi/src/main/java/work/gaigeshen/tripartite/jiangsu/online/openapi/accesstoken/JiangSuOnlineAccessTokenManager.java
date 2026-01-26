package work.gaigeshen.tripartite.jiangsu.online.openapi.accesstoken;


import work.gaigeshen.tripartite.jiangsu.online.openapi.config.JiangSuOnlineConfig;
import work.gaigeshen.tripartite.jiangsu.online.openapi.exception.JiangSuOnlineAccessTokenManagerException;

/**
 * 访问令牌管理器，维护所有访问令牌的更新
 *
 * @author gaigeshen
 */
public interface JiangSuOnlineAccessTokenManager {

    /**
     * 添加新的访问令牌，如果该访问令牌所属的配置信息已经存在访问令牌，则会将此新的访问令牌替换掉旧的
     *
     * @param config 配置信息不能为空
     * @param accessToken 待添加的访问令牌不能为空
     * @throws JiangSuOnlineAccessTokenManagerException 无法添加访问令牌
     */
    void addNewAccessToken(JiangSuOnlineConfig config, JiangSuOnlineAccessToken accessToken) throws JiangSuOnlineAccessTokenManagerException;

    /**
     * 查询访问令牌
     *
     * @param config 配置信息不能为空
     * @return 访问令牌可能为空
     * @throws JiangSuOnlineAccessTokenManagerException 无法查询访问令牌
     */
    JiangSuOnlineAccessToken findAccessToken(JiangSuOnlineConfig config) throws JiangSuOnlineAccessTokenManagerException;

    /**
     * 关闭此访问令牌管理器，此方法被调用之后将会停止所有访问令牌的更新，但应该不影响查询访问令牌
     *
     * @throws JiangSuOnlineAccessTokenManagerException 关闭的时候发生异常
     */
    void shutdown() throws JiangSuOnlineAccessTokenManagerException;

}
