package work.gaigeshen.jiangsu.openapi.client;


import work.gaigeshen.jiangsu.openapi.exception.JiangSuProcurementClientException;
import work.gaigeshen.jiangsu.openapi.parameters.province.*;
import work.gaigeshen.jiangsu.openapi.response.province.*;

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

    /**
     * 获取国家码
     *
     * @param parameter 请求参数数据部分不能为空
     * @return 响应结果不为空
     * @throws JiangSuProcurementClientException 执行请求或者执行业务发生异常
     */
    default JiangSuProcurementNationCodeQueryResponse listNationCodeSeq(JiangSuProcurementNationCodeSeqQueryParameter parameter)
            throws JiangSuProcurementClientException {
        return execute(parameter, JiangSuProcurementNationCodeQueryResponse.class, "/hospitalNationGoodsCode/get");
    }

    /**
     * 新建采购订单
     *
     * @param parameter 请求参数数据部分不能为空
     * @return 创建订单结果
     * @throws JiangSuProcurementClientException 执行请求或者执行业务发生异常
     */
    default JiangSuProcurementOrderCreateResponse createOrder(JiangSuProcurementOrderCreateParameter parameter)
            throws JiangSuProcurementClientException {
        return execute(parameter, JiangSuProcurementOrderCreateResponse.class, "/order/add");
    }

    /**
     * 添加采购订单明细
     *
     * @param parameter 请求参数数据部分不能为空
     * @return 添加订单明细结果
     * @throws JiangSuProcurementClientException 执行请求或者执行业务发生异常
     */
    default JiangSuProcurementOrderDetailAddResponse addOrderDetail(JiangSuProcurementOrderDetailAddParameter parameter)
            throws JiangSuProcurementClientException {
        return execute(parameter, JiangSuProcurementOrderDetailAddResponse.class, "/orderdetail/add");
    }

    /**
     * 提交采购订单
     *
     * @param parameter 请求参数数据部分不能为空
     * @return 提交订单结果
     * @throws JiangSuProcurementClientException 执行请求或者执行业务发生异常
     */
    default JiangSuProcurementOrderSubmitResponse submitOrder(JiangSuProcurementOrderSubmitParameter parameter)
            throws JiangSuProcurementClientException {
        return execute(parameter, JiangSuProcurementOrderSubmitResponse.class, "/order/submit");
    }

    /**
     * 获取订单明细信息（耗材）
     *
     * @param parameter 请求参数数据部分不能为空
     * @return 订单明细信息查询结果
     * @throws JiangSuProcurementClientException 执行请求或者执行业务发生异常
     */
    default JiangSuOrderDetailGetResponse getOrderDetail(JiangSuOrderDetailGetParameter parameter)
            throws JiangSuProcurementClientException {
        return execute(parameter, JiangSuOrderDetailGetResponse.class, "/order/detail/get");
    }

    /**
     * 获取配送信息（耗材）
     * <p>
     * 场景：对于状态为已配送待收货且院内无配送信息的订单明细, 可获取相关物流信息。
     * </p>
     *
     * @param parameter 请求参数数据部分不能为空
     * @return 配送信息查询结果
     * @throws JiangSuProcurementClientException 执行请求或者执行业务发生异常
     */
    default JiangSuProcurementDistributionGetResponse getDistribution(JiangSuProcurementDistributionGetParameter parameter)
            throws JiangSuProcurementClientException {
        return execute(parameter, JiangSuProcurementDistributionGetResponse.class, "/distribution/get");
    }


    /**
     * 退货申请（耗材）
     * <p>
     * 场景：同招采管理系统【订单管理-->退货】功能。
     * </p>
     *
     * @param parameter 请求参数数据部分不能为空
     * @return 退货结果（包含成功和失败的明细列表）
     * @throws JiangSuProcurementClientException 执行请求或者执行业务发生异常
     */
    default JiangSuProcurementReturnAddResponse addReturn(JiangSuProcurementReturnAddParameter parameter)
            throws JiangSuProcurementClientException {
        return execute(parameter, JiangSuProcurementReturnAddResponse.class, "/return/addReturn");
    }


}
