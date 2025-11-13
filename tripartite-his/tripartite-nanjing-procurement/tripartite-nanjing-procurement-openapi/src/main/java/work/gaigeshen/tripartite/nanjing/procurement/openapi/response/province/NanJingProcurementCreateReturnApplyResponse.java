package work.gaigeshen.tripartite.nanjing.procurement.openapi.response.province;

import lombok.Data;
import lombok.EqualsAndHashCode;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.response.AbstractNanJingProcurementResponse;

/**
 * 【NJHH014】创建提交退货申请 出参
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class NanJingProcurementCreateReturnApplyResponse extends AbstractNanJingProcurementResponse {

    /**
     * 退货订单号
     */
    private String retnCode;
}