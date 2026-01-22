package work.gaigeshen.jiangsu.openapi.client;


import work.gaigeshen.jiangsu.openapi.exception.JiangSuProcurementClientException;
import work.gaigeshen.jiangsu.openapi.parameters.DefaultJiangSuProcurementParameters;
import work.gaigeshen.jiangsu.openapi.parameters.province.JiangSuProcurementDirectoryUsedListParams;
import work.gaigeshen.jiangsu.openapi.response.province.JiangSuProcurementDirectoryUsedListResponse;
import work.gaigeshen.jiangsu.openapi.parameters.province.JiangSuProcurementOrderCreateParameter;
import work.gaigeshen.jiangsu.openapi.response.province.JiangSuProcurementOrderCreateResponse;
import work.gaigeshen.jiangsu.openapi.parameters.province.JiangSuProcurementOrderDetailAddParameter;
import work.gaigeshen.jiangsu.openapi.response.province.JiangSuProcurementOrderDetailAddResponse;
import work.gaigeshen.jiangsu.openapi.parameters.province.JiangSuProcurementOrderSubmitParameter;
import work.gaigeshen.jiangsu.openapi.response.province.JiangSuProcurementOrderSubmitResponse;

/**
 * 江苏两定平台客户端，此客户端只针对省采类型
 *
 * @author gaigeshen
 */
public interface JiangSuProcurementProvinceClient extends JiangSuProcurementBasicClient {

    /**
     * 获取常用目录
     *
     * @param parameter 请求参数数据部分不能为空
     * @return 响应结果不为空
     * @throws JiangSuProcurementClientException 执行请求或者执行业务发生异常
     */
    default JiangSuProcurementDirectoryUsedListResponse listUsedDirectories(JiangSuProcurementDirectoryUsedListParams parameter)
            throws JiangSuProcurementClientException {
        return execute(parameter, JiangSuProcurementDirectoryUsedListResponse.class, "/hospitalProcurecatalog/get");
    }

    // /**
    //  * 新建采购订单
    //  *
    //  * @param parameter 请求参数数据部分不能为空
    //  * @return 创建订单结果
    //  * @throws JiangSuProcurementClientException 执行请求或者执行业务发生异常
    //  */
    // default JiangSuProcurementOrderCreateResponse createOrder(JiangSuProcurementOrderCreateParameter parameter)
    //         throws JiangSuProcurementClientException {
    //     return execute(parameter, JiangSuProcurementOrderCreateResponse.class, "/order/add");
    // }
    //
    // /**
    //  * 添加采购订单明细
    //  *
    //  * @param parameter 请求参数数据部分不能为空
    //  * @return 添加订单明细结果
    //  * @throws JiangSuProcurementClientException 执行请求或者执行业务发生异常
    //  */
    // default JiangSuProcurementOrderDetailAddResponse addOrderDetail(JiangSuProcurementOrderDetailAddParameter parameter)
    //         throws JiangSuProcurementClientException {
    //     return execute(new DefaultJiangSuProcurementParameters(parameter), JiangSuProcurementOrderDetailAddResponse.class, "/orderDetail/add");
    // }
    //
    // /**
    //  * 提交采购订单
    //  *
    //  * @param parameter 请求参数数据部分不能为空
    //  * @return 提交订单结果
    //  * @throws JiangSuProcurementClientException 执行请求或者执行业务发生异常
    //  */
    // default JiangSuProcurementOrderSubmitResponse submitOrder(JiangSuProcurementOrderSubmitParameter parameter)
    //         throws JiangSuProcurementClientException {
    //     return execute(new DefaultJiangSuProcurementParameters(parameter), JiangSuProcurementOrderSubmitResponse.class, "/order/submit");
    // }



}
