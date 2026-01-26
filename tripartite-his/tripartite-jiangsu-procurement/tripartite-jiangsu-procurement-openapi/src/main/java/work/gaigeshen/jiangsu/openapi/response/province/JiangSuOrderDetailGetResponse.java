package work.gaigeshen.jiangsu.openapi.response.province;

import lombok.Data;
import lombok.EqualsAndHashCode;
import work.gaigeshen.jiangsu.openapi.response.AbstractJiangSuProcurementResponse;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class JiangSuOrderDetailGetResponse extends AbstractJiangSuProcurementResponse {

    /**
     * 当前页码
     */
    private Integer currentPageNumber;

    /**
     * 总页数
     */
    private Integer totalPageCount;

    /**
     * 总记录数
     */
    private Integer totalRecordCount;

    /**
     * 订单明细状态信息列表
     */
    private List<OrderDetailInfo> successList;

    @Data
    public static class OrderDetailInfo {

        /**
         * 省平台订单编号
         */
        private String orderId;

        /**
         * 订单提交时间
         */
        private String submiTime;

        /**
         * 订单明细备注
         */
        private String orderDetailRemark;

        /**
         * 省平台订单明细编号
         */
        private String orderDetailID;

        /**
         * 拒绝原因
         */
        private String refuseReason;

        /**
         * 订单明细状态
         * 1-待发货
         * 2-缺货
         * 3-拒绝发货
         * 4-已发货
         * 5-拒绝收货
         * 6-部分收货
         * 7-全部收货
         * 8-自主撤单
         */
        private Integer orderDetailState;
    }
}