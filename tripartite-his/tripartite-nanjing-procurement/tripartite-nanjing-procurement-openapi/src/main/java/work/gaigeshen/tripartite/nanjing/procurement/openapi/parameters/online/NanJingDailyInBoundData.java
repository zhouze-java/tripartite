package work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters.online;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters.NanJingProcurementInputData;

import java.math.BigDecimal;

@Data
public class NanJingDailyInBoundData implements NanJingProcurementInputData {
    /**
     * 医院采购管理明细ID
     */
    private String hosp_purc_mgt_detl;

    /**
     * MCS编码
     */
    private String mcs_code;

    /**
     * 省平台产品代码
     */
    private String prov_plaf_prod_code;

    /**
     * 产品名称
     */
    private String prod_name;

    /**
     * 规格
     */
    private String spec;

    /**
     * 型号
     */
    private String mol;

    /**
     * 单位
     */
    private String unt;

    /**
     * 注册证书
     */
    private String regcert;

    /**
     * 生产企业名称
     */
    private String prodentp_name;

    /**
     * 生产企业代码
     */
    private String prodentp_code;

    /**
     * 配送企业名称
     */
    private String delventp_name;

    /**
     * 配送企业代码
     */
    private String delventp_code;

    /**
     * 采购价格
     */
    private BigDecimal purc_pric;

    /**
     * 入库数量
     */
    private Integer stoin_cnt;

    /**
     * 入库时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String stoin_time;

    /**
     * 库房名称
     */
    private String stroom_name;

    /**
     * 省平台产品ID
     */
    private Integer prov_plaf_prod_id;

    /**
     * 是否应急采购
     */
    private String er_flag;

    /**
     * 省平台采购订单明细ID
     */
    private String prov_plaf_purc_ord_det_id;

    /**
     * 南京平台产品代码
     */
    private String nj_plaf_prod_code;

    /**
     * 南京平台产品ID
     */
    private String nj_plaf_prod_id;

    /**
     * 南京平台采购订单明细ID
     */
    private String nj_plaf_purc_ord_det_id;
}
