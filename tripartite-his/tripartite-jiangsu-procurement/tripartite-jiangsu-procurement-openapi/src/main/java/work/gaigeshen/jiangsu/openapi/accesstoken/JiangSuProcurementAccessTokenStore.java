package work.gaigeshen.jiangsu.openapi.accesstoken;

import work.gaigeshen.jiangsu.openapi.config.JiangSuProcurementConfig;
import work.gaigeshen.jiangsu.openapi.exception.JiangSuProcurementAccessTokenException;

import java.util.Map;

/**
 * 访问令牌存储器
 *
 * @author gaigeshen
 */
public interface JiangSuProcurementAccessTokenStore {

    /**
     * 保存访问令牌，通过返回值确定本次操作的是否为新配置信息的访问令牌
     *
     * @param config 配置信息不能为空
     * @param accessToken 访问令牌不能为空
     * @return 是否为新配置信息的访问令牌
     * @throws JiangSuProcurementAccessTokenException 无法保存或者更新访问令牌
     */
    boolean save(JiangSuProcurementConfig config, JiangSuProcurementAccessToken accessToken) throws JiangSuProcurementAccessTokenException;

    /**
     * 删除访问令牌
     *
     * @param config 配置信息不能为空
     * @throws JiangSuProcurementAccessTokenException 无法删除访问令牌
     */
    void delete(JiangSuProcurementConfig config) throws JiangSuProcurementAccessTokenException;

    /**
     * 查询访问令牌
     *
     * @param config 配置信息不能为空
     * @return 访问令牌可能为空
     * @throws JiangSuProcurementAccessTokenException 无法查询访问令牌
     */
    JiangSuProcurementAccessToken find(JiangSuProcurementConfig config) throws JiangSuProcurementAccessTokenException;

    /**
     * 查询所有的访问令牌
     *
     * @return 所有的访问令牌
     * @throws JiangSuProcurementAccessTokenException 无法查询所有的访问令牌
     */
    Map<JiangSuProcurementConfig, JiangSuProcurementAccessToken> findAll() throws JiangSuProcurementAccessTokenException;
}
