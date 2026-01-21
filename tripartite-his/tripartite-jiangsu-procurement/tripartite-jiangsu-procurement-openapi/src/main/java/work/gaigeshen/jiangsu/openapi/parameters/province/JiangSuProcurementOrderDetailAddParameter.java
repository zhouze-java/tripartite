package work.gaigeshen.jiangsu.openapi.parameters.province;

import lombok.Data;
import java.util.List;

import lombok.EqualsAndHashCode;
import work.gaigeshen.jiangsu.openapi.parameters.JiangSuProcurementAbstractParameters;
import work.gaigeshen.jiangsu.openapi.parameters.JiangSuProcurementParameters;

/**
 * 耗材添加采购订单明细入参（H006）
 * 在已创建的采购订单下添加订单明细
 *
 * @author zhouze
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class JiangSuProcurementOrderDetailAddParameter extends JiangSuProcurementAbstractParameters {

    /**
     * 省平台订单编号
     * H005 接口返回的 orderId
     */
    private String orderId;

    /**
     * 医疗机构编号
     * 非必填，老系统冗余字段
     */
    private String hospitalId;

    /**
     * 医疗机构采购部门编号
     * 非必填，老系统冗余字段
     */
    private String departmentID;

    /**
     * 订单明细列表
     * 一次最多 10 条
     */
    private List<OrderDetailItem> list;

    /**
     * 订单明细项
     */
    @Data
    public static class OrderDetailItem {

        /**
         * 医疗机构内部订单明细编号
         * 医院系统内唯一
         */
        private String hospitalOrderDetailId;

        /**
         * 产品ID
         * 通过 H003 接口获取 goodsID
         */
        private String goodsID;

        /**
         * 配送企业编号
         * 通过 H016 接口获取
         */
        private String companyIdPs;

        /**
         * 采购数量
         */
        private Integer purchaseCount;

        /**
         * 采购规格
         * 对应目录中的 outlookc
         */
        private String outlookc;

        /**
         * 采购型号
         * 对应目录中的 goodsType
         */
        private String goodsType;

        /**
         * 订单明细备注
         */
        private String orderDetailRemark;

        /**
         * 自定义订单信息
         * 传递给配送企业的附加信息
         */
        private String orderCustomInfo;

        /**
         * 国家编码流水号
         * 通过 H013 接口获取
         */
        private String codeSeq;

        /**
         * 国家医保编码
         * 27 位国家编码
         * 耗材必填（检测试剂除外）
         */
        private String nationGoodsCode;
    }
}