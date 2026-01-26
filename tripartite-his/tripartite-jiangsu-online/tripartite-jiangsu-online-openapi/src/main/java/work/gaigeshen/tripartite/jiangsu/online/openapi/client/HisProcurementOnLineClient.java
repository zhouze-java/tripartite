package work.gaigeshen.tripartite.jiangsu.online.openapi.client;


import work.gaigeshen.tripartite.jiangsu.online.openapi.config.JiangSuOnlineConfig;
import work.gaigeshen.tripartite.jiangsu.online.openapi.exception.JiangSuOnlineClientException;
import work.gaigeshen.tripartite.jiangsu.online.openapi.parameters.online.*;
import work.gaigeshen.tripartite.jiangsu.online.openapi.parameters.DefaultJiangSuOnlineParameters;
import work.gaigeshen.tripartite.jiangsu.online.openapi.response.basic.JiangSuOnlineResponse;

/**
 * 南京两定平台客户端，此客户端只针对网采类型
 *
 * @author gaigeshen
 */
public interface HisProcurementOnLineClient extends JiangSuOnlineBasicClient {

    JiangSuOnlineConfig getJiangSuOnlineConfig();

     /**
      * 网采接口-医院库房耗材每日入库数据
      *
      * @param inputData 请求参数数据部分不能为空
      * @return 响应结果不为空
      * @throws JiangSuOnlineClientException 执行请求或者执行业务发生异常
      */
     default JiangSuOnlineResponse sendDailyInBoundData(JiangSuDailyInBoundData inputData)
             throws JiangSuOnlineClientException {
         return execute(new DefaultJiangSuOnlineParameters(getJiangSuOnlineConfig(), "ELS7004", inputData), JiangSuOnlineResponse.class,
                 getJiangSuOnlineConfig().getServiceUri());
     }

    /**
     * 网采接口-医院库房耗材每日出库数据
     *
     * @param inputData 请求参数数据部分不能为空
     * @return 响应结果不为空
     * @throws JiangSuOnlineClientException 执行请求或者执行业务发生异常
     */
    default JiangSuOnlineResponse sendDailyOutBoundData(JiangSuDailyOutBoundData inputData)
            throws JiangSuOnlineClientException {
        return execute(new DefaultJiangSuOnlineParameters(getJiangSuOnlineConfig(), "ELS7005", inputData), JiangSuOnlineResponse.class,
                getJiangSuOnlineConfig().getServiceUri());
    }

    /**
     * 网采接口-医院库房耗材每日退货数据
     *
     * @param inputData 请求参数数据部分不能为空
     * @return 响应结果不为空
     * @throws JiangSuOnlineClientException 执行请求或者执行业务发生异常
     */
    default JiangSuOnlineResponse sendDailyRefundData(JiangSuDailyRefundData inputData)
            throws JiangSuOnlineClientException {
        return execute(new DefaultJiangSuOnlineParameters(getJiangSuOnlineConfig(), "ELS7007", inputData), JiangSuOnlineResponse.class,
                getJiangSuOnlineConfig().getServiceUri());
    }
}
