package work.gaigeshen.tripartite.nanjing.procurement.openapi.client;


import work.gaigeshen.tripartite.nanjing.procurement.openapi.config.NanJingProcurementConfig;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.exception.NanJingProcurementClientException;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters.DefaultNanJingProcurementParameters;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters.online.*;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.response.basic.NanJingOnlineProcurementResponse;

/**
 * 南京两定平台客户端，此客户端只针对网采类型
 *
 * @author gaigeshen
 */
public interface HisProcurementOnLineClient extends NanJingProcurementBasicClient {

    NanJingProcurementConfig getNanJingProcurementConfig();

     /**
      * 网采接口-医院库房耗材每日入库数据
      *
      * @param inputData 请求参数数据部分不能为空
      * @return 响应结果不为空
      * @throws NanJingProcurementClientException 执行请求或者执行业务发生异常
      */
     default NanJingOnlineProcurementResponse sendDailyInBoundData(NanJingDailyInBoundData inputData)
             throws NanJingProcurementClientException {
         return execute(new DefaultNanJingProcurementParameters(getNanJingProcurementConfig(), "ELS7004", inputData), NanJingOnlineProcurementResponse.class,
                 getNanJingProcurementConfig().getServiceUri());
     }

    /**
     * 网采接口-医院库房耗材每日出库数据
     *
     * @param inputData 请求参数数据部分不能为空
     * @return 响应结果不为空
     * @throws NanJingProcurementClientException 执行请求或者执行业务发生异常
     */
    default NanJingOnlineProcurementResponse sendDailyOutBoundData(NanJingDailyOutBoundData inputData)
            throws NanJingProcurementClientException {
        return execute(new DefaultNanJingProcurementParameters(getNanJingProcurementConfig(), "ELS7005", inputData), NanJingOnlineProcurementResponse.class,
                getNanJingProcurementConfig().getServiceUri());
    }

    /**
     * 网采接口-医院库房耗材每日退货数据
     *
     * @param inputData 请求参数数据部分不能为空
     * @return 响应结果不为空
     * @throws NanJingProcurementClientException 执行请求或者执行业务发生异常
     */
    default NanJingOnlineProcurementResponse sendDailyRefundData(NanjingDailyRefundData inputData)
            throws NanJingProcurementClientException {
        return execute(new DefaultNanJingProcurementParameters(getNanJingProcurementConfig(), "ELS7007", inputData), NanJingOnlineProcurementResponse.class,
                getNanJingProcurementConfig().getServiceUri());
    }

    /**
     * 网采接口-医院库房耗材每日盘盈数据
     *
     * @param inputData 请求参数数据部分不能为空
     * @return 响应结果不为空
     * @throws NanJingProcurementClientException 执行请求或者执行业务发生异常
     */
    default NanJingOnlineProcurementResponse sendDailyInventorySurplusData(NanJingDailyInventorySurplusData inputData)
            throws NanJingProcurementClientException {
        return execute(new DefaultNanJingProcurementParameters(getNanJingProcurementConfig(), "ELS7009", inputData), NanJingOnlineProcurementResponse.class,
                getNanJingProcurementConfig().getServiceUri());
    }

    /**
     * 网采接口-医院库房耗材每日盘亏数据
     *
     * @param inputData 请求参数数据部分不能为空
     * @return 响应结果不为空
     * @throws NanJingProcurementClientException 执行请求或者执行业务发生异常
     */
    default NanJingOnlineProcurementResponse sendDailyInventoryLossData(NanJingDailyInventoryLossData inputData)
            throws NanJingProcurementClientException {
        return execute(new DefaultNanJingProcurementParameters(getNanJingProcurementConfig(), "ELS7010", inputData), NanJingOnlineProcurementResponse.class,
                getNanJingProcurementConfig().getServiceUri());
    }
}
