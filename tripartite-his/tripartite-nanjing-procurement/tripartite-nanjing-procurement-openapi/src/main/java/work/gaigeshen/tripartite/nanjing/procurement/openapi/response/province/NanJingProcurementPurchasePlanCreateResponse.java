package work.gaigeshen.tripartite.nanjing.procurement.openapi.response.province;

import lombok.Data;
import lombok.EqualsAndHashCode;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.response.AbstractNanJingProcurementResponse;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 * @author zhouze
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class NanJingProcurementPurchasePlanCreateResponse extends AbstractNanJingProcurementResponse {

    /**
     * 采购计划单编号（计划单唯一编号）
     */
    private String purcCode;

    /**
     * 采购订单创建时间（格式：yyyy-MM-dd HH:mm:ss）
     */
    private Date purcAddTime;

    /**
     * 采购计划单编码
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
         * 采购计划单明细ID
         */
        private String purcPlanDetId;

        /**
         * 医院采购单ID（医院内部订单明细ID，用于接收返回结果）
         */
        private String hospPurcDetlId;
    }
}