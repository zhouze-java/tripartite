package work.gaigeshen.tripartite.nanjing.procurement.openapi.response.province;

import lombok.Data;
import lombok.EqualsAndHashCode;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.response.AbstractNanJingProcurementResponse;

import java.math.BigDecimal;
import java.util.List;

/**
 * 【NJHH016】获取医疗机构应急目录 出参
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class NanJingProcurementEmergencyCatalogResponse extends AbstractNanJingProcurementResponse {

    /**
     * 返回结果页数
     */
    private String currentPageNumber;

    /**
     * 返回结果数量
     */
    private String totalPageCount;

    /**
     * 返回记录总数
     */
    private String totalRecordCount;

    /**
     * 返回结果集合
     */
    private List<DataItem> dataList;

    @Data
    public static class DataItem {

        /**
         * 主键
         */
        private String id;

        /**
         * 临时编码
         */
        private String mcsCode;

        /**
         * 南京专区编码
         */
        private String prodCode;

        /**
         * 国家医保耗材编码
         */
        private String medMcsCode;

        /**
         * 注册证号 / 备案号
         */
        private String mcsRegno;

        /**
         * 产品名称
         */
        private String mcsName;

        /**
         * 生产企业
         */
        private String prodentpName;

        /**
         * 投标企业
         */
        private String prxyEntpName;

        /**
         * 采购价格
         */
        private BigDecimal purcPric;

        /**
         * 产地（1国产，2进口）
         */
        private Integer origin;

        /**
         * 产品材质
         */
        private String mcsMatl;

        /**
         * 包装材质
         */
        private String pacMatl;

        /**
         * 规格
         */
        private String mcsSpec;

        /**
         * 型号
         */
        private String mcsMol;

        /**
         * 转换比
         */
        private BigDecimal convrat;

        /**
         * 计量单位
         */
        private String countUnt;

        /**
         * 包装单位
         */
        private String pacUnt;

        /**
         * 申报时间
         */
        private String crteTime;
    }
}