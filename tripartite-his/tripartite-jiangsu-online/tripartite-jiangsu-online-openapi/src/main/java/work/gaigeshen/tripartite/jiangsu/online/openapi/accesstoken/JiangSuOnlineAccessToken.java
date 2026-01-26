package work.gaigeshen.tripartite.jiangsu.online.openapi.accesstoken;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author gaigeshen
 */
@Builder
@Data
public class JiangSuOnlineAccessToken {

    private final String accessToken;

    private final String account;

    /**
     * 类型 PROVINCE_PURCHASE/ONLINE_PURCHASE
     */
    private final String type;

    /**
     * 有效期时长单位秒
     */
    private final long expiresIn;

    /**
     * 过期时间点单位秒
     */
    private final long expiresTimestamp;

    /**
     * 有效期时长单位秒
     */
    private final long expireTime;

    /**
     * 过期时间点单位秒
     */
    private final long expiresTimestamp1;

    /**
     * 更新时间
     */
    private final Date updateTime;

}
