package work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters.province;

import lombok.Data;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters.NanJingProcurementInputData;

import java.util.Date;

@Data
public class NanJingProcurementOrderDetailQueryInputData implements NanJingProcurementInputData {

    /**
     * 医疗机构编码
     */
    private String medinsCode;

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 注册证编号
     */
    private String mcsRegno;

    /**
     * 最后更新时间 起（yyyy-MM-dd HH:mm:ss）
     */
    private Date strUpTime;

    /**
     * 最后更新时间 止（yyyy-MM-dd HH:mm:ss）
     */
    private Date endUpTime;

    /**
     * 每页条数
     */
    private Integer size;

    /**
     * 页码
     */
    private Integer current;

    /**
     * 订单明细ID
     */
    private String ordDetlId;

    /**
     * 订单ID
     */
    private String ordId;

    /**
     * 采购计划单编号
     */
    private String purcCode;
}