package work.gaigeshen.jiangsu.openapi.response.province;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.util.List;
import work.gaigeshen.jiangsu.openapi.response.AbstractJiangSuProcurementResponse;

/**
 * 耗材添加采购订单明细返回结果（H006）
 *
 * @author zhouze
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class JiangSuProcurementOrderDetailAddResponse extends AbstractJiangSuProcurementResponse {

    /**
     * 成功的订单明细列表
     */
    private List<SuccessItem> successList;

    /**
     * 失败的订单明细列表
     */
    private List<ErrorItem> errorList;

    /**
     * 当日接口剩余访问次数
     */
    private Integer todayRemainVisitCount;

    /**
     * 成功明细
     */
    @Data
    public static class SuccessItem {

        /**
         * 医院内部订单明细编号
         */
        private String hospitalOrderDetailId;

        /**
         * 省平台订单明细编号
         * 后续 H008、H009 必须使用
         */
        private String orderDetailId;

        /**
         * 订单明细金额
         */
        private BigDecimal orderDetailAmount;
    }

    /**
     * 失败明细
     */
    @Data
    public static class ErrorItem {

        /**
         * 医院内部订单明细编号
         */
        private String hospitalOrderDetailId;

        /**
         * 错误详情列表
         */
        private List<ErrorDetail> errorDetailList;
    }

    /**
     * 错误详情
     */
    @Data
    public static class ErrorDetail {

        /**
         * 错误码
         */
        private String errorcode;

        /**
         * 错误原因说明
         */
        private String errorMsg;
    }
}