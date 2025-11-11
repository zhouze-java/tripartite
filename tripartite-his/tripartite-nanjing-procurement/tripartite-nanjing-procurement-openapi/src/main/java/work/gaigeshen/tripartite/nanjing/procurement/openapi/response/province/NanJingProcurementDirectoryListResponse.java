package work.gaigeshen.tripartite.nanjing.procurement.openapi.response.province;

import lombok.Data;
import lombok.EqualsAndHashCode;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.response.AbstractNanJingProcurementResponse;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * @author gaigeshen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class NanJingProcurementDirectoryListResponse extends AbstractNanJingProcurementResponse {

    /**
     * 返回结果页数
     */
    private Integer currentPageNumber;

    /**
     * 页总数
     */
    private Integer totalPageCount;

    /**
     * 返回结果数量
     */
    private Integer totalRecordCount;


    /**
     * 目录数据列表
     */
    private Collection<Item> dataList;

    /**
     * 单条目录项
     */
    @Data
    public static class Item {

        /**
         * 挂网目录id
         */
        private String pubonlnId;

        /**
         * 南京平台编码
         */
        private String mcsCode;

        /**
         * 产品名称
         */
        private String prodName;

        /**
         * 耗材注册证编号
         */
        private String mcsRegno;

        /**
         * 耗材注册证名称
         */
        private String mcsRegcertName;

        /**
         * 规格
         */
        private String prodSpec;

        /**
         * 型号
         */
        private String mcsMol;

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
         * 挂网价格
         */
        private BigDecimal pubonInPric;

        /**
         * 分类名称
         */
        private String sortName;

        /**
         * 转换比
         */
        private Integer convrat;

        /**
         * 计量单位
         */
        private String minuntName;

        /**
         * 包装单位
         */
        private String minpacuntName;

        /**
         * 挂网类型
         */
        private String pubonInType;

        /**
         * 招标项目ID
         */
        private String tenditmId;

        /**
         * 招标项目名称
         */
        private String tenditmName;

        /**
         * 分类采购标识
         */
        private String purcSign;

        /**
         * 采购类别
         */
        private String purcType;

        /**
         * 南京平台临时编码
         */
        private String mcsCodeNj;

        /**
         * 状态标识
         */
        private String pubonInStas;

    }
}
