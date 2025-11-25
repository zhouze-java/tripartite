package work.gaigeshen.tripartite.nanjing.procurement.openapi.response.province;

import lombok.Data;
import lombok.EqualsAndHashCode;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.response.AbstractNanJingProcurementResponse;

import java.util.Collection;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class NanJingProcurementPurchasePlanSubmitResponse extends AbstractNanJingProcurementResponse {

    /**
     * 采购订单编号
     */
    private String purcCode;

    /**
     * 采购订单提交时间（yyyy-MM-dd HH:mm:ss）
     * 如果是发送订单，代表订单发送时间
     */
    private String purcAddTime;

    /**
     * 采购订单编码
     */
    private String purcPlanCode;

    /**
     * 发送的订单集合
     */
    private Collection<OrdItem> ordIdList;

    /**
     * 发送订单明细集合
     */
    private Collection<OrdDetailItem> ordDetlIdList;

    @Data
    public static class OrdItem {

        /**
         * 订单ID
         */
        private String ordId;

        /**
         * 订单编号
         */
        private String ordCode;
    }

    @Data
    public static class OrdDetailItem {

        /**
         * 订单ID
         */
        private String ordId;

        /**
         * 订单编号
         */
        private String ordCode;

        /**
         * 采购计划ID
         */
        private String purcPlanDetId;

        /**
         * 订单明细ID
         */
        private String ordDetlId;
    }
}