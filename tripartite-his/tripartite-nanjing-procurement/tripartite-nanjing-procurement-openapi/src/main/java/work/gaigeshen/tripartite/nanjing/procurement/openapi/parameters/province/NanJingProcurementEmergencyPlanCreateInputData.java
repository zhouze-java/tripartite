package work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters.province;

import lombok.Data;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters.NanJingProcurementInputData;

import java.math.BigDecimal;
import java.util.List;

/**
 * 【NJHH017】创建应急采购计划 入参
 */
@Data
public class NanJingProcurementEmergencyPlanCreateInputData implements NanJingProcurementInputData {

    /**
     * 医疗机构代码
     */
    private String medinsCode;

    /**
     * 采购总金额（必须 > 0）
     */
    private BigDecimal planSumamt;

    /**
     * 默认收货地址 ID
     */
    private String addrId;

    /**
     * 联系人
     */
    private String conerName;

    /**
     * 联系人电话
     */
    private String conerTel;

    /**
     * 操作状态（2：保存，-1：暂存）
     */
    private Integer chkStas;

    /**
     * 采购单备注
     */
    private String planMemo;

    /**
     * 订单明细集合
     */
    private List<PlanDetailItem> list;

    @Data
    public static class PlanDetailItem {

        /**
         * 院内目录 ID
         */
        private String hospListId;

        /**
         * 配送企业编码
         */
        private String delventpCode;

        /**
         * 配送企业名称
         */
        private String delventpName;

        /**
         * 采购数量（必须 > 0）
         */
        private BigDecimal purcCnt;

        /**
         * 医院采购价
         */
        private BigDecimal hospPurcPric;

        /**
         * 采购详情备注
         */
        private String planDetlMemo;

        /**
         * 明细的收货地址 ID（若不填，使用默认 addrId）
         */
        private String addrId;

        /**
         * 医院采购单 ID（透传返回）
         */
        private String hospPurcDetlId;

        /**
         * 采购计划单明细 ID（一般为空，透传用）
         */
        private String purcPlanDetId;
    }
}