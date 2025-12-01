package work.gaigeshen.tripartite.nanjing.procurement.openapi.response.province;

import lombok.Data;
import lombok.EqualsAndHashCode;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.response.AbstractNanJingProcurementResponse;

import java.math.BigDecimal;
import java.util.List;

/**
 * 【NJHH017】创建应急采购计划 出参
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class NanJingProcurementEmergencyPlanCreateResponse extends AbstractNanJingProcurementResponse {


    /**
     * 采购计划单编号（唯一编号）
     */
    private String PurcCode;

    /**
     * 采购订单创建时间
     */
    private String PurcAddTime;

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
    private List<DataItem> dataList;

    @Data
    public static class DataItem {

        /**
         * 采购计划单明细 ID
         */
        private String purcPlanDetId;

        /**
         * 医院采购单 ID（透传返回）
         */
        private String hospPurcDetlId;

        /**
         * 返回标识
         */
        private String returnCode;

        /**
         * 返回信息
         */
        private String returnMsg;
    }
}