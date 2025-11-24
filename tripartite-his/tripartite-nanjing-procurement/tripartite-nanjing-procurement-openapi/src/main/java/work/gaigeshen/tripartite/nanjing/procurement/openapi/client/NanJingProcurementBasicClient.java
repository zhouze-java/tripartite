package work.gaigeshen.tripartite.nanjing.procurement.openapi.client;


import work.gaigeshen.tripartite.nanjing.procurement.openapi.config.NanJingProcurementConfig;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.exception.NanJingProcurementClientException;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters.NanJingProcurementParameters;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.response.NanJingProcurementResponse;

/**
 * 南京两定平台客户端
 *
 * @author gaigeshen
 */
public interface NanJingProcurementBasicClient {

    /**
     * 返回此客户端的配置
     *
     * @return 此客户端的配置
     */
    NanJingProcurementConfig getNanJingProcurementConfig();


    /**
     * 此方法接受任何请求参数，然后执行请求并返回对应的响应结果
     *
     * @param parameters    请求参数不能为空
     * @param responseClass 响应结果类型不能为空
     * @param uri           该请求对应的服务器地址不能为空
     * @param <R>           表示响应结果类型
     * @return 响应结果不会为空
     * @throws NanJingProcurementClientException 执行请求或者执行业务发生异常
     */
    <R extends NanJingProcurementResponse> R execute(NanJingProcurementParameters parameters, Class<R> responseClass, String uri)
            throws NanJingProcurementClientException;

}
