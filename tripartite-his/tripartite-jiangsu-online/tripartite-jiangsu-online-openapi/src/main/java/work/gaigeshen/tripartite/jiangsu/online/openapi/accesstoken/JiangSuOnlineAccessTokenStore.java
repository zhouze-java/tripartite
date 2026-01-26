package work.gaigeshen.tripartite.jiangsu.online.openapi.accesstoken;

import work.gaigeshen.tripartite.jiangsu.online.openapi.config.JiangSuOnlineConfig;
import work.gaigeshen.tripartite.jiangsu.online.openapi.exception.JiangSuOnlineAccessTokenException;

import java.util.Map;

/**
 * 访问令牌存储器
 *
 * @author gaigeshen
 */
public interface JiangSuOnlineAccessTokenStore {

    /**
     * 保存访问令牌，通过返回值确定本次操作的是否为新配置信息的访问令牌
     *
     * @param config 配置信息不能为空
     * @param accessToken 访问令牌不能为空
     * @return 是否为新配置信息的访问令牌
     * @throws JiangSuOnlineAccessTokenException 无法保存或者更新访问令牌
     */
    boolean save(JiangSuOnlineConfig config, JiangSuOnlineAccessToken accessToken) throws JiangSuOnlineAccessTokenException;

    /**
     * 删除访问令牌
     *
     * @param config 配置信息不能为空
     * @throws JiangSuOnlineAccessTokenException 无法删除访问令牌
     */
    void delete(JiangSuOnlineConfig config) throws JiangSuOnlineAccessTokenException;

    /**
     * 查询访问令牌
     *
     * @param config 配置信息不能为空
     * @return 访问令牌可能为空
     * @throws JiangSuOnlineAccessTokenException 无法查询访问令牌
     */
    JiangSuOnlineAccessToken find(JiangSuOnlineConfig config) throws JiangSuOnlineAccessTokenException;

    /**
     * 查询所有的访问令牌
     *
     * @return 所有的访问令牌
     * @throws JiangSuOnlineAccessTokenException 无法查询所有的访问令牌
     */
    Map<JiangSuOnlineConfig, JiangSuOnlineAccessToken> findAll() throws JiangSuOnlineAccessTokenException;
}
