package work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters.province;

import lombok.Data;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters.NanJingProcurementInputData;

/**
 * @author zhouze
 * @date 2025/11/10 15:30
 * @description 获取医疗机构常用目录入参
 */
@Data
public class NanJingProcurementCommonDirectoryQueryInputData implements NanJingProcurementInputData {

    /**
     * 院内目录ID
     */
    private String hospListId;

    /**
     * 挂网结果ID
     */
    private String pubonlnId;

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
    private String prodName;

    /**
     * 注册证编号
     */
    private String mcsRegno;

    /**
     * 页码
     */
    private String current;

    /**
     * 每次访问数量，不可超过500
     */
    private String size;

    /**
     * 最后更新开始时间（格式：YYYY-MM-DD HH:mm:ss）
     */
    private String strUpTime;

    /**
     * 最后更新结束时间（格式：YYYY-MM-DD HH:mm:ss）
     */
    private String endUpTime;

}