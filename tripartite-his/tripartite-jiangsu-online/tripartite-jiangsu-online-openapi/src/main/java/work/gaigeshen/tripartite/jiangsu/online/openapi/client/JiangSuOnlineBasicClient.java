package work.gaigeshen.tripartite.jiangsu.online.openapi.client;


import work.gaigeshen.tripartite.jiangsu.online.openapi.config.JiangSuOnlineConfig;
import work.gaigeshen.tripartite.jiangsu.online.openapi.exception.JiangSuOnlineClientException;
import work.gaigeshen.tripartite.jiangsu.online.openapi.parameters.DefaultJiangSuOnlineParameters;
import work.gaigeshen.tripartite.jiangsu.online.openapi.parameters.JiangSuOnlineParameters;
import work.gaigeshen.tripartite.jiangsu.online.openapi.parameters.basic.JiangSuOnlineStorehouseListInputData;
import work.gaigeshen.tripartite.jiangsu.online.openapi.response.JiangSuOnlineResponse;
import work.gaigeshen.tripartite.jiangsu.online.openapi.response.basic.JiangSuOnlineStorehouseListResponse;

/**
 * 南京两定平台客户端
 *
 * @author gaigeshen
 */
public interface JiangSuOnlineBasicClient {

    /**
     * 返回此客户端的配置
     *
     * @return 此客户端的配置
     */
    JiangSuOnlineConfig getJiangSuOnlineConfig();

    /**
     * 获取库房信息 (医疗机构用于药品和医用耗材 的收货地址/库房地址下载)
     *
     * @param inputData 请求参数数据部分不能为空
     * @return 响应结果不为空
     * @throws JiangSuOnlineClientException 执行请求或者执行业务发生异常
     */
    default JiangSuOnlineStorehouseListResponse listStorehouses(JiangSuOnlineStorehouseListInputData inputData)
            throws JiangSuOnlineClientException {
        return execute(new DefaultJiangSuOnlineParameters(getJiangSuOnlineConfig(), "NJHH001", inputData), JiangSuOnlineStorehouseListResponse.class, getJiangSuOnlineConfig().getServiceUri());
    }



    /**
     * 此方法接受任何请求参数，然后执行请求并返回对应的响应结果
     *
     * @param parameters    请求参数不能为空
     * @param responseClass 响应结果类型不能为空
     * @param uri           该请求对应的服务器地址不能为空
     * @param <R>           表示响应结果类型
     * @return 响应结果不会为空
     * @throws JiangSuOnlineClientException 执行请求或者执行业务发生异常
     */
    <R extends JiangSuOnlineResponse> R execute(JiangSuOnlineParameters parameters, Class<R> responseClass, String uri)
            throws JiangSuOnlineClientException;

}
