package work.gaigeshen.tripartite.nanjing.procurement.openapi.config;

import lombok.Builder;
import lombok.Data;

/**
 * @author zhouze
 * @date 2025/11/7 10:05
 * @description 南京采购平台接口配置
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
}
