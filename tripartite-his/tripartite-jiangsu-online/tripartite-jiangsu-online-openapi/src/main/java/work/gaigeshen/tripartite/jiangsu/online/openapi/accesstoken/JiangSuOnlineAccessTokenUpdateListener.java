package work.gaigeshen.tripartite.jiangsu.online.openapi.accesstoken;

import work.gaigeshen.tripartite.jiangsu.online.openapi.config.JiangSuOnlineConfig;
import work.gaigeshen.tripartite.jiangsu.online.openapi.exception.JiangSuOnlineAccessTokenUpdateException;

/**
 * 访问令牌更新监听器
 *
 * @author gaigeshen
 */
public interface JiangSuOnlineAccessTokenUpdateListener {

    /**
     * 在访问令牌更新成功之后被执行，默认不做任何事情，此方法不要抛出任何异常
     *
     * @param config         配置信息不能为空
     * @param oldAccessToken 旧的访问令牌不能为空
     * @param newAccessToken 新的访问令牌不能为空，且必须有效
     */
    default void handleUpdated(JiangSuOnlineConfig config, JiangSuOnlineAccessToken oldAccessToken, JiangSuOnlineAccessToken newAccessToken) { }

    /**
     * 在访问令牌更新失败之后被执行，默认不做任何事情，此方法不要抛出任何异常
     *
     * @param config    配置信息不能为空
     * @param exception 异常不能为空
     */
    default void handleFailed(JiangSuOnlineConfig config, JiangSuOnlineAccessTokenUpdateException exception) { }

}
