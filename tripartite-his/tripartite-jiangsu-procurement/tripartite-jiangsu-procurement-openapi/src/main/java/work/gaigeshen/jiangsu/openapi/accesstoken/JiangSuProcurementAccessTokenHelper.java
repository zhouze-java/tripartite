package work.gaigeshen.jiangsu.openapi.accesstoken;

import org.apache.commons.lang3.StringUtils;
import work.gaigeshen.jiangsu.openapi.config.JiangSuProcurementConfig;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;
import java.util.Date;

/**
 * @author gaigeshen
 */
public class JiangSuProcurementAccessTokenHelper {

    /**
     * 访问令牌默认的过期时间单位秒
     */
    public static final int DEFAULT_EXPIRES_IN_SECONDS = 1800;

    /**
     * 定时刷新提前量（秒）：平台规定剩余超过 5 分钟时不发新 token，故在过期前 3 分钟刷新
     */
    public static final int ACCESS_TOKEN_REFRESH_ADVANCE_SECONDS = 180;

    private JiangSuProcurementAccessTokenHelper() { }

    /**
     * 返回该访问令牌剩余有效时长单位秒
     *
     * @param accessToken 访问令牌不可为空
     * @return 剩余有效时长单位秒
     */
    public static long getRemainingDuration(JiangSuProcurementAccessToken accessToken) {
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
    public static JiangSuProcurementAccessToken createAccessToken(JiangSuProcurementConfig config, String newAccessToken) {
        return createAccessToken(config, newAccessToken, DEFAULT_EXPIRES_IN_SECONDS);
    }

    /**
     * 按平台返回的剩余有效时长创建访问令牌
     *
     * @param expiresInSeconds 平台 {@code expiresIn}（秒），无效时使用 {@link #DEFAULT_EXPIRES_IN_SECONDS}
     */
    public static JiangSuProcurementAccessToken createAccessToken(
            JiangSuProcurementConfig config, String newAccessToken, long expiresInSeconds) {
        ArgumentValidate.notNull(config, "config cannot be null");
        ArgumentValidate.notNull(newAccessToken, "newAccessToken cannot be null");
        long effectiveExpiresIn = expiresInSeconds > 0 ? expiresInSeconds : DEFAULT_EXPIRES_IN_SECONDS;
        long nowEpochSecond = System.currentTimeMillis() / 1000;
        JiangSuProcurementAccessToken.JiangSuProcurementAccessTokenBuilder builder = JiangSuProcurementAccessToken.builder();
        builder.accessToken(newAccessToken);
        builder.account(config.getAccount());
        builder.type(config.getType());
        builder.expiresIn(effectiveExpiresIn);
        builder.expiresTimestamp(nowEpochSecond + effectiveExpiresIn);
        builder.updateTime(new Date());
        return builder.build();
    }

    public static long resolveExpiresInSeconds(Long expiresIn) {
        if (expiresIn == null || expiresIn <= 0) {
            return DEFAULT_EXPIRES_IN_SECONDS;
        }
        return expiresIn;
    }

    /**
     * 返回该访问令牌是否已经过期
     *
     * @param accessToken 访问令牌不可为空
     * @return 是否已经过期
     */
    public static boolean isExpired(JiangSuProcurementAccessToken accessToken) {
        ArgumentValidate.notNull(accessToken, "accessToken cannot be null");
        return accessToken.getExpiresTimestamp() <= System.currentTimeMillis() / 1000;
    }

    /**
     * 返回访问令牌是否有效，有效的访问令牌必需包含访问令牌值
     *
     * @param accessToken 访问令牌不可为空
     * @return 是否有效
     */
    public static boolean isValid(JiangSuProcurementAccessToken accessToken) {
        ArgumentValidate.notNull(accessToken, "accessToken cannot be null");
        return !StringUtils.isAnyBlank(accessToken.getAccessToken(), accessToken.getAccount(), accessToken.getType());
    }
}
