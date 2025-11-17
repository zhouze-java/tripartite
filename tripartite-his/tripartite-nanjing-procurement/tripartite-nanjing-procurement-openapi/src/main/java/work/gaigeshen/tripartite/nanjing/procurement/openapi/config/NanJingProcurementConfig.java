package work.gaigeshen.tripartite.nanjing.procurement.openapi.config;

import lombok.Builder;
import lombok.Data;

/**
 * @author zhouze
 */
@Data
@Builder
public class NanJingProcurementConfig {

    /**
     * 省采
     */
    public final static String PROVINCE_PURCHASE = "provincePurchase";

    /**
     * 网采
     */
    public final static String ONLINE_PURCHASE = "onlinePurchase";

    /**
     * 接口地址
     */
    private final String serverHost;

    private final String serviceUri;

    /**
     * 1 招采, 2 网采
     */
    private final String type;

    /**
     * 机构码
     */
    private final String appCode;

    /**
     * 机构授权码
     */
    private final String authCode;

    /**
     * 连接超时时间
     */
    private final Integer connectTimeout;

    /**
     * 读取超时时间
     */
    private final Integer readTimeout;

    private final String accessTokenUri;

    private final String account;

    /**
     * 两定的机构医药编号
     */
    private final String hospitalCode;

    /**
     * 两定的医药机构名称
     */
    private final String hospitalName;
}
