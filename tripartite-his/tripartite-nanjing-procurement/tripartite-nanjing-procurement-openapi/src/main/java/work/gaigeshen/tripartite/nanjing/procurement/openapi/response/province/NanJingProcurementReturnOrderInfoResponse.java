package work.gaigeshen.tripartite.nanjing.procurement.openapi.response.province;

import lombok.Data;
import lombok.EqualsAndHashCode;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.response.AbstractNanJingProcurementResponse;

import java.util.List;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 【NJHH015】获取退货订单信息 出参
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class NanJingProcurementReturnOrderInfoResponse extends AbstractNanJingProcurementResponse {

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
         * 医疗机构编码
         */
        private String medinsCode;

        /**
         * 退货订单号
         */
        private String retnCode;

        /**
         * 配送企业编码
         */
        private String delventpCode;

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
         * 型号
         */
        private String rgtMol;

        /**
         * 规格
         */
        private String prodSpec;

        /**
         * 产品材质
         */
        private String prodMatl;

        /**
         * 包装
         */
        private String prodPac;

        /**
         * 包装材质
         */
        private String pacMatl;

        /**
         * 生产企业
         */
        private String prodentpName;

        /**
         * 医院采购价（元）
         */
        private String purcpric;

        /**
         * 采购数量
         */
        private String purcCnt;

        /**
         * 配送企业
         */
        private String delventpName;

        /**
         * 代理企业
         */
        private String dclaEntpName;

        /**
         * 挂网价（元）
         */
        private String pubonlnPric;

        /**
         * 退货订单状态
         */
        private String retnChkStas;

        /**
         * 医疗机构名称
         */
        private String medinsName;

        /**
         * 批次号
         */
        private String manuLotnum;

        /**
         * 退货数量
         */
        private Integer retnCnt;

        /**
         * 退货金额
         */
        private BigDecimal retnAmt;

        /**
         * 配送企业拒绝退货原因
         */
        private String delventpFailRea;

        /**
         * 申请退货时间（医疗机构）
         */
        private String medinsRetnTime;

        /**
         * 配送企业通过时间
         */
        private String delventpPassTime;

        /**
         * 配送企业不通过时间
         */
        private String delventpFailTime;

        /**
         * 医疗机构退货原因
         */
        private String medinsRetnRea;

        /**
         * 订单编号
         */
        private String ordCode;
    }
}