package work.gaigeshen.jiangsu.openapi.parameters.province;

import lombok.Data;
import lombok.EqualsAndHashCode;
import work.gaigeshen.jiangsu.openapi.parameters.DefaultJiangSuProcurementParameters;
import work.gaigeshen.jiangsu.openapi.parameters.JiangSuProcurementParameters;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class JiangSuOrderDetailGetParameter extends DefaultJiangSuProcurementParameters {

    /**
     * 当前页码，必填
     */
    private Integer currentPageNumber;

    /**
     * 医疗机构编号
     */
    private String hospitalId;

    /**
     * 医疗机构采购部门编号
     */
    private String departmentID;

    /**
     * 省平台订单明细编号集合
     */
    private List<String> list;
}