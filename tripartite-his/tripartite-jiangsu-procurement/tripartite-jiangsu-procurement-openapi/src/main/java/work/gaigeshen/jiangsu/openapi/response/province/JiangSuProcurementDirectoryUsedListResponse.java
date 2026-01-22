package work.gaigeshen.jiangsu.openapi.response.province;

import lombok.Data;
import lombok.EqualsAndHashCode;
import work.gaigeshen.jiangsu.openapi.response.AbstractJiangSuProcurementResponse;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author zhouze
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class JiangSuProcurementDirectoryUsedListResponse extends AbstractJiangSuProcurementResponse {

    /**
     * 总页数
     */
    private Integer totalPageCount;

    /**
     * 总记录数
     */
    private Integer totalRecordCount;

    /**
     * 当前页码
     */
    private Integer currentPageNumber;

    /**
     * 采购目录数据列表
     */
    private List<SuccessItem> successList;

    /**
     * 当日接口剩余访问次数
     */
    private Integer todayRemainVisitCount;

    /**
     * 采购目录明细
     */
    @Data
    public static class SuccessItem {

        /**
         * 医疗机构账号
         */
        private String hospitalId;

        /**
         * 采购部门编号
         * 为空时表示机构级目录
         */
        private String departmentID;

        /**
         * 产品ID
         */
        private String goodsID;

        /**
         * 分类标识
         */
        private String sortName;

        /**
         * 一级目录名称
         */
        private String productNameFirst;

        /**
         * 二级目录名称
         */
        private String productNameSecond;

        /**
         * 产品名称
         */
        private String goodsName;

        /**
         * 规格
         */
        private String outlookc;

        /**
         * 型号
         */
        private String goodsType;

        /**
         * 计量单位
         */
        private String unit;

        /**
         * 市标产品码（CN码）
         */
        private String provinceId;

        /**
         * 注册证名称
         */
        private String regcodeName;

        /**
         * 品牌
         */
        private String brand;

        /**
         * 产品来源
         */
        private String source;

        /**
         * 医疗机构设置的采购价格
         */
        private BigDecimal purchasePrice;

        /**
         * 采购限价
         */
        private BigDecimal price;

        /**
         * 投标企业编号
         */
        private String companyIdTb;

        /**
         * 投标企业名称
         */
        private String companyNameTb;

        /**
         * 配送企业编号
         */
        private String companyIdPs;

        /**
         * 配送企业名称
         */
        private String companyNamePs;

        /**
         * 采购类别
         * 如：限价、带量等
         */
        private Integer purchaseType;

        /**
         * 添加时间
         */
        private String addTime;

        /**
         * 最后更新时间
         */
        private String lastUpDateTime;

    }
}
