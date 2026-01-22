package work.gaigeshen.jiangsu.openapi.parameters.province;

import lombok.Data;
import lombok.EqualsAndHashCode;
import work.gaigeshen.jiangsu.openapi.parameters.DefaultJiangSuProcurementParameters;
import work.gaigeshen.jiangsu.openapi.parameters.JiangSuProcurementParametersCustomizer;
import work.gaigeshen.jiangsu.openapi.parameters.JiangSuProcurementParams;
import work.gaigeshen.tripartite.core.parameter.converter.JsonParametersConverter;
import work.gaigeshen.tripartite.core.parameter.converter.Parameters;

/**
 * 耗材提交采购订单入参（H007）
 *
 * 将已创建并添加明细的订单提交至省平台
 *
 * @author zhouze
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class JiangSuProcurementOrderSubmitParameter extends DefaultJiangSuProcurementParameters {

    /**
     * 省平台订单编号
     * H005 接口返回的 orderId
     */
    private String orderId;

    /**
     * 医疗机构编号
     * 非必填，老系统冗余字段
     */
    private String hospitalId;

    /**
     * 医疗机构采购部门编号
     * 非必填，老系统冗余字段
     */
    private String departmentID;

}