package work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters.province;

import lombok.Data;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters.NanJingProcurementInputData;

import java.util.Date;

/**
 * 【NJHH016】获取医疗机构应急目录 入参
 */
@Data
public class NanJingProcurementEmergencyCatalogInputData implements NanJingProcurementInputData {

    /**
     * 注册证号 / 备案号
     */
    private String mcsRegno;

    /**
     * 南京平台编码
     */
    private String prodCode;

    /**
     * 国家耗材统一编码
     */
    private String mcsCode;

    /**
     * 产品名称
     */
    private String mcsName;

    /**
     * 生产企业
     */
    private String prodentpName;

    /**
     * 页码
     */
    private Integer current;

    /**
     * 数量（最大 500）
     */
    private Integer size;

    /**
     * 最后更新开始时间
     */
    private String strUpTime;

    /**
     * 最后更新结束时间
     */
    private String endUpTime;
}