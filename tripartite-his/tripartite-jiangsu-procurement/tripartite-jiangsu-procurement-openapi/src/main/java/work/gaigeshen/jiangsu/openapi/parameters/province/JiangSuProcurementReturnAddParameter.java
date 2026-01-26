package work.gaigeshen.jiangsu.openapi.parameters.province;

import lombok.Data;
import lombok.EqualsAndHashCode;
import work.gaigeshen.jiangsu.openapi.parameters.DefaultJiangSuProcurementParameters;
import work.gaigeshen.jiangsu.openapi.parameters.JiangSuProcurementParameters;
import java.util.List;

/**
 * H011 退货入参
 * 场景：同招采管理系统【订单管理-->退货】功能
 *
 * @author zhouze
 * @date 2025/05/22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class JiangSuProcurementReturnAddParameter extends DefaultJiangSuProcurementParameters {

    /**
     * 医疗机构编号（非必填，老系统冗余字段可传空）
     */
    private String hospitalId;

    /**
     * 医疗机构采购部门编号（非必填，老系统冗余字段可传空）
     */
    private String departmentID;

    /**
     * 退货明细列表
     */
    private List<ReturnItem> list;

    /**
     * 退货明细项
     */
    @Data
    public static class ReturnItem {
        /**
         * 医院退货明细主键 (医院系统内部退货明细唯一主键)
         */
        private String hospitalReturnId;

        /**
         * 配送明细编号
         */
        private String distributionSerialID;

        /**
         * 退货数量
         */
        private String returnCount;

        /**
         * 退货原因
         */
        private String returnReason;

        /**
         * 自定义退货信息
         */
        private String returnCustomInfo;
    }
}