package work.gaigeshen.jiangsu.openapi.response.province;

import lombok.Data;
import lombok.EqualsAndHashCode;
import work.gaigeshen.jiangsu.openapi.response.AbstractJiangSuProcurementResponse;
import java.util.List;

/**
 * H009 获取配送信息出参
 *
 * @author zhouze
 * @date 2025/05/22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class JiangSuProcurementDistributionGetResponse extends AbstractJiangSuProcurementResponse {

    /**
     * 当前页码（为对应的参数值, 无论结果返回是否有值）
     */
    private String currentPageNumber;

    /**
     * 总页数
     */
    private String totalPageCount;

    /**
     * 总行数
     */
    private String totalRecordCount;

    /**
     * 当日接口剩余访问次数
     */
    private String todayRemainVisitCount;

    /**
     * 成功获取的配送明细列表
     */
    private List<SuccessDistribution> successList;

    /**
     * 配送明细项
     */
    @Data
    public static class SuccessDistribution {

        /**
         * 订单明细编号
         */
        private String orderDetailID;

        /**
         * 配送明细编号
         */
        private String distributionSerialID;

        /**
         * 发票号
         */
        private String invoiceID;

        /**
         * 批号
         */
        private String batchRecordID;

        /**
         * 配送数量
         */
        private String distributeCount;

        /**
         * 配送时间
         */
        private String distributeTime;

        /**
         * 入库数量 (已收货数量)
         */
        private String warehouseCount;

        /**
         * 入库状态: 待发货(1) 缺货(2)拒绝发货(3)已发货(4)拒绝收货(5)部分收货(6)全部收货(7)自主撤单(8)
         */
        private String warehouseState;

        /**
         * 入库时间 (收货时间)
         */
        private String warehouseTime;
    }
}