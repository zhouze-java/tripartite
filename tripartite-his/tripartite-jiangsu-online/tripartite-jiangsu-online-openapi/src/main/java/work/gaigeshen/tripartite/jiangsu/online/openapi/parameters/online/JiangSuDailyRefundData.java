package work.gaigeshen.tripartite.jiangsu.online.openapi.parameters.online;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import work.gaigeshen.tripartite.jiangsu.online.openapi.parameters.JiangSuOnlineInputData;

import java.math.BigDecimal;
import java.util.Collection;

@Data
public class JiangSuDailyRefundData implements JiangSuOnlineInputData {

    private Collection<ListItem> data;

    @Data
    public static class ListItem {

        /**
         * 院内退货数据主键
         */
        private String hosp_retn_mgt_detl;

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
         * 退货数量
         */
        private Integer retn_cnt;

        /**
         * 退货时间
         */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private String retn_time;

        /**
         * 库房名称
         */
        private String stroom_name;

        /**
         * 省市平台退货明细 ID
         */
        private String prov_plaf_retn_det_id;

        /**
         * 南京平台产品代码
         */
        private String nj_plaf_prod_code;

        /**
         * 南京平台采购订单明细ID
         */
        private String nj_plaf_retn_det_id;
    }
}
