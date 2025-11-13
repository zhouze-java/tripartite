package work.gaigeshen.tripartite.nanjing.procurement.openapi.response.province;

import lombok.Data;
import lombok.EqualsAndHashCode;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.response.AbstractNanJingProcurementResponse;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class NanJingProcurementPurchasePlanAddDetailResponse extends AbstractNanJingProcurementResponse {

    /**
     * 采购订单编号
     */
    private String purcCode;

    /**
     * 添加时间（yyyy-MM-dd HH:mm:ss）
     */
    private Date purcAddTime;

    /**
     * 采购订单编码
     */
    private String purcPlanCode;

    /**
     * 采购订单金额
     */
    private BigDecimal planSumamt;

    /**
     * 订单明细集合
     */
    private Collection<Item> dataList;

    @Data
    public static class Item {

        /**
         * 采购单明细ID
         */
        private String purcPlanDetId;

        /**
         * 医院采购单ID（医院内部订单明细ID）
         */
        private String hospPurcDetlId;
    }
}