package work.gaigeshen.tripartite.jiangsu.online.openapi.accesstoken;

import org.apache.commons.lang3.StringUtils;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;
import work.gaigeshen.tripartite.jiangsu.online.openapi.config.JiangSuOnlineConfig;

import java.util.Date;

/**
 * @author gaigeshen
 */
public class JiangSuOnlineAccessTokenHelper {

    /**
     * 访问令牌默认的过期时间单位秒
     */
    public static final int DEFAULT_EXPIRES_IN_SECONDS = 1800;

    private JiangSuOnlineAccessTokenHelper() { }

    /**
     * 返回该访问令牌剩余有效时长单位秒
     *
     * @param accessToken 访问令牌不可为空
     * @return 剩余有效时长单位秒
     */
    public static long getRemainingDuration(JiangSuOnlineAccessToken accessToken) {
        ArgumentValidate.notNull(accessToken, "accessToken cannot be null");
        if (isExpired(accessToken)) {
            return 0;
        }
        return accessToken.getExpiresTimestamp() - System.currentTimeMillis() / 1000;
    }

    /**
     * 创建新的访问令牌
     *
     * @param config 配置信息不能为空
     * @param newAccessToken 访问令牌内容不能为空
     * @return 新的访问令牌
     */
    public static JiangSuOnlineAccessToken createAccessToken(JiangSuOnlineConfig config, String newAccessToken) {
        ArgumentValidate.notNull(config, "config cannot be null");
        ArgumentValidate.notNull(newAccessToken, "newAccessToken cannot be null");
        JiangSuOnlineAccessToken.JiangSuOnlineAccessTokenBuilder builder = JiangSuOnlineAccessToken.builder();
        builder.accessToken(newAccessToken);
        builder.account(config.getAccount());
        builder.type(config.getType());
        builder.expiresIn(DEFAULT_EXPIRES_IN_SECONDS);
        builder.expiresTimestamp(System.currentTimeMillis() / 1000 + DEFAULT_EXPIRES_IN_SECONDS);
        builder.updateTime(new Date());
        return builder.build();
    }

    /**
     * 返回该访问令牌是否已经过期
     *
     * @param accessToken 访问令牌不可为空
     * @return 是否已经过期
     */
    public static boolean isExpired(JiangSuOnlineAccessToken accessToken) {
        ArgumentValidate.notNull(accessToken, "accessToken cannot be null");
        return accessToken.getExpiresTimestamp() <= System.currentTimeMillis() / 1000;
    }

    /**
     * 返回访问令牌是否有效，有效的访问令牌必需包含访问令牌值
     *
     * @param accessToken 访问令牌不可为空
     * @return 是否有效
     */
    public static boolean isValid(JiangSuOnlineAccessToken accessToken) {
        ArgumentValidate.notNull(accessToken, "accessToken cannot be null");
        return !StringUtils.isAnyBlank(accessToken.getAccessToken(), accessToken.getAccount(), accessToken.getType());
    }
}
