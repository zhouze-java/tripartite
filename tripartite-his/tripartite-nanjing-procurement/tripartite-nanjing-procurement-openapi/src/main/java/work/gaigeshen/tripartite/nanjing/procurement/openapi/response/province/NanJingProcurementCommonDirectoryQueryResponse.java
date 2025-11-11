package work.gaigeshen.tripartite.nanjing.procurement.openapi.response.province;

import lombok.Data;
import lombok.EqualsAndHashCode;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.response.AbstractNanJingProcurementResponse;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * @author zhouze
 * @date 2025/11/10 15:30
 * @description 获取医疗机构常用目录出参
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class NanJingProcurementCommonDirectoryQueryResponse extends AbstractNanJingProcurementResponse {

    /**
     * 返回结果页数
     */
    private String currentPageNumber;

    /**
     * 返回结果数量（页数）
     */
    private String totalPageCount;

    /**
     * 返回结果数量（记录数）
     */
    private String totalRecordCount;

    /**
     * 返回结果集合
     */
    private Collection<Item> dataList;

    @Data
    public static class Item {

        /**
         * 院内目录ID
         */
        private String hospListId;

        /**
         * 挂网结果ID
         */
        private String pubonlnRsltId;

        /**
         * 南京平台编码
         */
        private String prodCode;

        /**
         * 耗材统一编码
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
         * 注册证名称
         */
        private String regcert;

        /**
         * 生产企业代码
         */
        private String prodentpCode;

        /**
         * 生产企业名称
         */
        private String prodentpName;

        /**
         * 申报企业代码
         */
        private String dclaEntpCode;

        /**
         * 申报企业名称
         */
        private String dclaEntpName;

        /**
         * 默认配送企业CODE
         */
        private String delventpCode;

        /**
         * 默认配送企业名称
         */
        private String delventpName;

        /**
         * 医院采购价
         */
        private BigDecimal hospPurcPric;

        /**
         * 挂网价格
         */
        private BigDecimal pubonlnPric;

        /**
         * 更新时间（格式：YYYY-MM-DD HH:mm:ss）
         */
        private String updtTime;

        /**
         * 规格
         */
        private String mcsSpec;

        /**
         * 型号
         */
        private String mcsMol;

        /**
         * 交易数据来源（1：挂网目录，2：备案采购，4：应急采购，5：联盟采购）
         */
        private String trnsDataSouc;
    }
}