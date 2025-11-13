package work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters.province;

import lombok.Data;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters.NanJingProcurementInputData;

import java.util.Date;

/**
 * 【NJHH015】获取退货订单信息 入参
 */
@Data
public class NanJingProcurementReturnOrderInfoInputData implements NanJingProcurementInputData {

    /**
     * 医疗机构编码
     */
    private String medinsCode;

    /**
     * 退货订单号
     */
    private String retnCode;

    /**
     * 医院退货开始时间
     */
    private Date strUpTime;

    /**
     * 医院退货结束时间
     */
    private Date endUpTime;

    /**
     * 页码
     */
    private Integer current;

    /**
     * 每页条数
     */
    private Integer size;
}