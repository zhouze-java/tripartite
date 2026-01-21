package work.gaigeshen.jiangsu.openapi.client;


import work.gaigeshen.jiangsu.openapi.config.JiangSuProcurementConfig;
import work.gaigeshen.jiangsu.openapi.exception.JiangSuProcurementClientException;
import work.gaigeshen.jiangsu.openapi.parameters.DefaultJiangSuProcurementAbstractParameters;
import work.gaigeshen.jiangsu.openapi.response.JiangSuProcurementResponse;

/**
 * 江苏两定平台客户端
 *
 * @author gaigeshen
 */
public interface JiangSuProcurementBasicClient {

    /**
     * 返回此客户端的配置
     *
     * @return 此客户端的配置
     */
    JiangSuProcurementConfig getJiangSuProcurementConfig();

    // /**
    //  * 获取库房信息 (医疗机构用于药品和医用耗材 的收货地址/库房地址下载)
    //  *
    //  * @param inputData 请求参数数据部分不能为空
    //  * @return 响应结果不为空
    //  * @throws JiangSuProcurementClientException 执行请求或者执行业务发生异常
    //  */
    // default NanJingProcurementStorehouseListResponse listStorehouses(NanJingProcurementStorehouseListInputData inputData)
    //         throws JiangSuProcurementClientException {
    //     return execute(new DefaultJiangSuProcurementParameters(getJiangSuProcurementConfig(), "NJHH001", inputData), NanJingProcurementStorehouseListResponse.class, getJiangSuProcurementConfig().getServiceUri());
    // }



    /**
     * 此方法接受任何请求参数，然后执行请求并返回对应的响应结果
     *
     * @param parameters    请求参数不能为空
     * @param responseClass 响应结果类型不能为空
     * @param uri           该请求对应的服务器地址不能为空
     * @param <R>           表示响应结果类型
     * @return 响应结果不会为空
     * @throws JiangSuProcurementClientException 执行请求或者执行业务发生异常
     */
    <R extends JiangSuProcurementResponse> R execute(DefaultJiangSuProcurementAbstractParameters parameters, Class<R> responseClass, String uri)
            throws JiangSuProcurementClientException;

}
