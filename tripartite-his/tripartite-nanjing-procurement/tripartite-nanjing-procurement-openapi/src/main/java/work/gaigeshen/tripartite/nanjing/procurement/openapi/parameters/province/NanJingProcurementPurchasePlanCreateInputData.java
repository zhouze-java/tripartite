package work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters.province;

import lombok.Data;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters.NanJingProcurementInputData;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * @author zhouze
 */
@Data
public class NanJingProcurementPurchasePlanCreateInputData implements NanJingProcurementInputData {

    /**
     * 医疗机构代码
     */
    private String medinsCode;

    /**
     * 采购总金额，值必须大于0
     */
    private BigDecimal planSumamt;

    /**
     * 收货地址ID（默认地址ID）
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
     * 采购明细集合
     */
    private Collection<Item> list;

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
         * 采购数量，值必须大于0
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
         * 收货地址ID（单条数据可覆盖默认地址）
         */
        private String addrId;

        /**
         * 医院采购单ID（医院内部订单明细ID，用于接收返回结果）
         */
        private String hospPurcDetlId;

        /**
         * 采购计划明细ID
         */
        private String purcPlanDetId;
    }
}