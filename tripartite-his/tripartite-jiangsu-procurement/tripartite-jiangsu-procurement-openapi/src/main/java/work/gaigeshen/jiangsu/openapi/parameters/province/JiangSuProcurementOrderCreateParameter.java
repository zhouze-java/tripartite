package work.gaigeshen.jiangsu.openapi.parameters.province;

import lombok.Data;
import work.gaigeshen.jiangsu.openapi.parameters.JiangSuProcurementParameters;

/**
 * 耗材新建采购订单入参（H005）
 * 对应招采平台【订单管理-新建采购订单】
 *
 * @author zhouze
 */
@Data
public class JiangSuProcurementOrderCreateParameter implements JiangSuProcurementParameters {
    /**
     * 医院订单类型
     * 0：正常订单
     * 2：补单
     */
    private Integer orderType;

    /**
     * 医疗机构编号
     */
    private String hospitalId;

    /**
     * 医疗机构采购部门编号
     * 非必填，老系统冗余字段
     */
    private String departmentID;

    /**
     * 医院内部订单编号
     * 医疗机构系统内唯一
     */
    private String hospitalOrderId;

    /**
     * 库房地址
     * 目录管理-库房管理中维护的详细地址
     * 必填
     */
    private String distributeAddress;

    /**
     * 医院订单备注
     */
    private String remarks;
}