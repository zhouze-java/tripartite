package work.gaigeshen.tripartite.nanjing.procurement.openapi.response.province;

import lombok.Data;
import lombok.EqualsAndHashCode;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.response.AbstractNanJingProcurementResponse;

import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class NanJingProcurementAddEmergencyPlanDetailResponse extends AbstractNanJingProcurementResponse {

    /**
     * 采购订单编号
     */
    private String PurcCode;

    /**
     * 采购总金额
     */
    private BigDecimal planSumamt;

    /**
     * 添加时间
     */
    private String PurcAddTime;

    /**
     * 采购订单编码
     */
    private String purcPlanCode;

    /**
     * 订单明细集合
     */
    private List<ResponseItem> dataList;

    @Data
    public static class ResponseItem {

        /**
         * 采购单明细 ID
         */
        private String purcPlanDetId;

        /**
         * 医院采购单 ID
         */
        private String hospPurcDetlId;
    }
}