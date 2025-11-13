package work.gaigeshen.tripartite.nanjing.procurement.openapi.response.province;

import lombok.Data;
import lombok.EqualsAndHashCode;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.response.AbstractNanJingProcurementResponse;

import java.util.Collection;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class NanJingProcurementOrderDetailQueryResponse extends AbstractNanJingProcurementResponse {

    /**
     * 返回结果集合
     */
    private Collection<Item> dataList;

    @Data
    public static class Item {

        /**
         * 医疗机构代码
         */
        private String medinsCode;

        /**
         * 收货地址ID
         */
        private String addrId;

        /**
         * 联系人
         */
        private String conerName;

        /**
         * 联系人电话
         */
        private String conerTel;

        /**
         * 配送企业编码
         */
        private String delventpCode;

        /**
         * 南京平台编码
         */
        private String prodCode;

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
         * 类别
         */
        private String mcsType;

        /**
         * 挂网价（元）
         */
        private String pubonInPric;

        /**
         * 项目名称
         */
        private String itemname;

        /**
         * 订单状态
         */
        private String proTypeStock;

        /**
         * 创建时间
         */
        private Date adddTime;

        /**
         * 订单提交时间
         */
        private Date subTime;

        /**
         * 备注
         */
        private String planDetlMemo;

        /**
         * 配送订单号
         */
        private String discode;

        /**
         * 配送数量
         */
        private String disCnt;

        /**
         * 配送时间
         */
        private Date disTime;

        /**
         * 收货数量
         */
        private String wanCnt;

        /**
         * 收货时间
         */
        private Date wanTime;

        /**
         * 配送状态（1：待发货，2：已发货，3：已收货，4：已作废）
         */
        private String disYN;

        /**
         * 订单明细ID
         */
        private String ordDetlId;

        /**
         * 订单ID
         */
        private String ordId;

        /**
         * 挂网目录ID
         */
        private String pubonInRsltId;

        /**
         * 采购计划code
         */
        private String purcPlanCode;

        /**
         * 订单code
         */
        private String ordCode;

        /**
         * 订单明细状态（1:待发货，2:全部发货，3:部分发货，4:缺货，5:已撤销）
         */
        private String ordDetlStas;
    }
}