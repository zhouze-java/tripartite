package work.gaigeshen.jiangsu.openapi.parameters.province;

import lombok.Data;
import lombok.EqualsAndHashCode;
import work.gaigeshen.jiangsu.openapi.parameters.DefaultJiangSuProcurementParameters;

/**
 * H014 撤单入参
 * <p>
 * 场景：订单明细撤单操作。
 * </p>
 *
 * @author zhouze
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class JiangSuProcurementOrderCancelParameter extends DefaultJiangSuProcurementParameters {

    /**
     * 省平台订单明细编号
     */
    private String orderDetailId;

}
