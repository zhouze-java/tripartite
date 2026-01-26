package work.gaigeshen.jiangsu.openapi.parameters.province;

import lombok.Data;
import lombok.EqualsAndHashCode;
import work.gaigeshen.jiangsu.openapi.parameters.DefaultJiangSuProcurementParameters;
import work.gaigeshen.jiangsu.openapi.parameters.JiangSuProcurementParameters;
import java.util.List;

/**
 * H009 获取配送信息入参
 * 场景：对于状态为已配送待收货且院内无配送信息的订单明细，可获取相关物流信息。
 *
 * @author zhouze
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class JiangSuProcurementDistributionGetParameter extends DefaultJiangSuProcurementParameters {

    /**
     * 获取指定页码的数据, 例如"1"、"2"、"3"等
     */
    private String currentPageNumber;

    /**
     * （非必填）医疗机构编号, 老系统冗余字段
     */
    private String hospitalId;

    /**
     * （非必填）医疗机构采购部门编号, 老系统冗余字段可传空
     */
    private String departmentID;

    /**
     * 订单明细编号集合
     */
    private List<String> list;
}