package work.gaigeshen.jiangsu.openapi.response.province;

import lombok.Data;
import lombok.EqualsAndHashCode;
import work.gaigeshen.jiangsu.openapi.response.AbstractJiangSuProcurementResponse;

/**
 * 耗材新建采购订单返回结果（H005）
 *
 * @author zhouze
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class JiangSuProcurementOrderCreateResponse extends AbstractJiangSuProcurementResponse {

    /**
     * 医院内部订单编号
     */
    private String hospitalOrderId;

    /**
     * 省平台订单编号
     * 后续 H006、H007 必须使用
     */
    private String orderId;

    /**
     * 当日接口剩余访问次数
     */
    private Integer todayRemainVisitCount;
}