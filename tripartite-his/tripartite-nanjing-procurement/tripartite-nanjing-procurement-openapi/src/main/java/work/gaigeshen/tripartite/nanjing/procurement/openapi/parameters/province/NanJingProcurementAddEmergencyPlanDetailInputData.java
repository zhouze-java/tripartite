package work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters.province;

import lombok.Data;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters.NanJingProcurementInputData;

import java.math.BigDecimal;
import java.util.List;

@Data
public class NanJingProcurementAddEmergencyPlanDetailInputData implements NanJingProcurementInputData {

    /**
     * 医疗机构代码
     */
    private String medinsCode;

    /**
     * 采购订单编号
     * ZJ9704 接口出参的 PurcCode 字段
     */
    private String PurcCode;

    /**
     * 输入数据集合
     */
    private List<AddEmergencyPlanDetailItem> List;

    /**
     * 操作状态
     * 2：提交  -1：暂存
     */
    private Integer chkStas;

    /**
     * 添加状态
     * 1：新增  2：删除
     */
    private Integer addorDelStas;

    @Data
    public static class AddEmergencyPlanDetailItem {

        /**
         * 院内目录 id
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
         * 采购数量
         * 值必须大于 0
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
         * 收货地址 id
         * 单条优先于默认地址
         */
        private String addrId;

        /**
         * 医院采购单 ID
         * 用户接受返回结果
         */
        private String hospPurcDetlId;

        /**
         * 采购单明细 ID
         * 删除时必填
         */
        private String purcPlanDetId;
    }
}