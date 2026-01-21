package work.gaigeshen.jiangsu.openapi.response.province;

import lombok.Data;
import lombok.EqualsAndHashCode;
import work.gaigeshen.jiangsu.openapi.response.AbstractJiangSuProcurementResponse;

/**
 * 耗材提交采购订单返回结果（H007）
 *
 * @author zhouze
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class JiangSuProcurementOrderSubmitResponse extends AbstractJiangSuProcurementResponse {

    /**
     * 省平台订单编号
     */
    private String orderId;

    /**
     * 当日接口剩余访问次数
     */
    private Integer todayRemainVisitCount;
}