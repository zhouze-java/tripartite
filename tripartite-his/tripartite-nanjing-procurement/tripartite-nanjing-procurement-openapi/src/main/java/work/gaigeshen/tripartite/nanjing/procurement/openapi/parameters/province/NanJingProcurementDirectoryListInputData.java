package work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters.province;

import lombok.Data;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters.NanJingProcurementInputData;

import java.util.Date;

/**
 * @author zhouze
 * @date 2025/11/10 13:27
 * @description TODO
 */
@Data
public class NanJingProcurementDirectoryListInputData implements NanJingProcurementInputData {

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 注册证编号
     */
    private String mcsRegno;

    /**
     * 挂网结果ID
     */
    private String pubonInId;

    /**
     * 最后更新时间 起
     */
    private Date strUpTime;

    /**
     * 最后更新时间 止
     */
    private Date endUpTime;

    /**
     * 创建起始时间
     */
    private Date strCrTime;

    /**
     * 创建截止时间
     */
    private Date endCrTime;

    /**
     * 页码
     */
    private Integer current;

    /**
     * 数量
     */
    private Integer size;

}
