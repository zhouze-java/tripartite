package work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters.province;

import lombok.Data;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters.NanJingProcurementInputData;

import java.math.BigDecimal;
import java.util.Collection;

@Data
public class NanJingProcurementPurchasePlanAddDetailInputData implements NanJingProcurementInputData {

    /**
     * 医疗机构代码
     */
    private String medinsCode;

    /**
     * 采购订单编号（由创建接口返回的 PurcCode）
     */
    private String PurcCode;

    /**
     * 操作状态（2：提交，-1：暂存）
     */
    private Integer chkStas;

    /**
     * 添加状态（1：新增，2：删除）
     */
    private Integer addorDelStas;

    /**
     * 采购明细集合
     */
    private Collection<Item> List;

    @Data
    public static class Item {

        /**
         * 院内目录ID
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
         * 采购数量（值必须大于0）
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
         * 收货地址ID（单条覆盖默认地址）
         */
        private String addrId;

        /**
         * 医院采购单ID（医院内部订单明细ID）
         */
        private String hospPurcDetlId;

        /**
         * 采购单明细ID（删除时必填）
         */
        private String purcPlanDetId;
    }
}