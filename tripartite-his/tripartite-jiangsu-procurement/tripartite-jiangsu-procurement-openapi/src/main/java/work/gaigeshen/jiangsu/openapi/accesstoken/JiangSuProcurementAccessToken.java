package work.gaigeshen.jiangsu.openapi.accesstoken;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author gaigeshen
 */
@Builder
@Data
public class JiangSuProcurementAccessToken {

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
     * 更新时间
     */
    private final Date updateTime;

}
