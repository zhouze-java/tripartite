package work.gaigeshen.tripartite.nanjing.procurement.openapi.accesstoken;

import work.gaigeshen.tripartite.nanjing.procurement.openapi.config.NanJingProcurementConfig;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.exception.NanJingProcurementAccessTokenUpdateException;

/**
 * 访问令牌更新监听器
 *
 * @author gaigeshen
 */
public interface NanJingProcurementAccessTokenUpdateListener {

    /**
     * 在访问令牌更新成功之后被执行，默认不做任何事情，此方法不要抛出任何异常
     *
     * @param config         配置信息不能为空
     * @param oldAccessToken 旧的访问令牌不能为空
     * @param newAccessToken 新的访问令牌不能为空，且必须有效
     */
    default void handleUpdated(NanJingProcurementConfig config, NanJingProcurementAccessToken oldAccessToken, NanJingProcurementAccessToken newAccessToken) { }

    /**
     * 在访问令牌更新失败之后被执行，默认不做任何事情，此方法不要抛出任何异常
     *
     * @param config    配置信息不能为空
     * @param exception 异常不能为空
     */
    default void handleFailed(NanJingProcurementConfig config, NanJingProcurementAccessTokenUpdateException exception) { }

}
