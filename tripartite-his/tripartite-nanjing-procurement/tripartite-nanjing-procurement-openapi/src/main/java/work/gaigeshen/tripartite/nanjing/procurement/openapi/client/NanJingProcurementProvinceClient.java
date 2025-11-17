package work.gaigeshen.tripartite.nanjing.procurement.openapi.client;


import work.gaigeshen.tripartite.nanjing.procurement.openapi.exception.NanJingProcurementClientException;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters.DefaultNanJingProcurementParameters;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters.province.*;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.response.province.*;

/**
 * 南京两定平台客户端，此客户端只针对省采类型
 *
 * @author gaigeshen
 */
public interface NanJingProcurementProvinceClient extends NanJingProcurementBasicClient {

    /**
     * 获取挂网目录
     *
     * @param inputData 请求参数数据部分不能为空
     * @return 响应结果不为空
     * @throws NanJingProcurementClientException 执行请求或者执行业务发生异常
     */
    default NanJingProcurementDirectoryListResponse listDirectories(NanJingProcurementDirectoryListInputData inputData)
            throws NanJingProcurementClientException {
        return execute(new DefaultNanJingProcurementParameters(getNanJingProcurementConfig(), "NJHH002", inputData), NanJingProcurementDirectoryListResponse.class, getNanJingProcurementConfig().getServiceUri());
    }


    /**
     * 勾选本院目录
     *
     * @param inputData 请求参数数据部分不能为空
     * @return 响应结果不为空
     * @throws NanJingProcurementClientException 执行请求或者执行业务发生异常
     */
    default NanJingProcurementDirectoryAddResponse addDirectories(NanJingProcurementDirectoryAddInputData inputData)
            throws NanJingProcurementClientException {
        return execute(new DefaultNanJingProcurementParameters(getNanJingProcurementConfig(), "NJHH004", inputData), NanJingProcurementDirectoryAddResponse.class,
                getNanJingProcurementConfig().getServiceUri());
    }


    /**
     * 获取常用目录
     *
     * @param inputData 请求参数数据部分不能为空
     * @return 响应结果不为空
     * @throws NanJingProcurementClientException 执行请求或者执行业务发生异常
     */
    default NanJingProcurementDirectoryUsedListResponse listUsedDirectories(NanJingProcurementDirectoryUsedListInputData inputData)
            throws NanJingProcurementClientException {
        return execute(new DefaultNanJingProcurementParameters(getNanJingProcurementConfig(), "NJHH005", inputData), NanJingProcurementDirectoryUsedListResponse.class,
                getNanJingProcurementConfig().getServiceUri());
    }

    /**
     * 创建采购订单
     *
     * @param inputData 请求参数数据部分不能为空
     * @return 响应结果不为空
     * @throws NanJingProcurementClientException 执行请求或者执行业务发生异常
     */
    default NanJingProcurementPurchasePlanCreateResponse createPurchaseOrder(NanJingProcurementPurchasePlanCreateInputData inputData)
            throws NanJingProcurementClientException {
        return execute(new DefaultNanJingProcurementParameters(getNanJingProcurementConfig(), "NJHH008", inputData), NanJingProcurementPurchasePlanCreateResponse.class,
                getNanJingProcurementConfig().getServiceUri());
    }

    /**
     * 添加采购订单明细
     *
     * @param inputData 请求参数数据部分不能为空
     * @return 响应结果不为空
     * @throws NanJingProcurementClientException 执行请求或者执行业务发生异常
     */
    default NanJingProcurementPurchasePlanAddDetailResponse addPurchaseOrderDetails(NanJingProcurementPurchasePlanAddDetailInputData inputData)
            throws NanJingProcurementClientException {
        return execute(new DefaultNanJingProcurementParameters(getNanJingProcurementConfig(), "NJHH009", inputData), NanJingProcurementPurchasePlanAddDetailResponse.class,
                getNanJingProcurementConfig().getServiceUri());
    }

    /**
     * 保存或发送采购订单
     *
     * @param inputData 请求参数数据部分不能为空
     * @return 响应结果不为空
     * @throws NanJingProcurementClientException 执行请求或者执行业务发生异常
     */
    default NanJingProcurementPurchasePlanSubmitResponse sendPurchaseOrder(NanJingProcurementPurchasePlanSubmitInputData inputData)
            throws NanJingProcurementClientException {
        return execute(new DefaultNanJingProcurementParameters(getNanJingProcurementConfig(), "NJHH010", inputData), NanJingProcurementPurchasePlanSubmitResponse.class,
                getNanJingProcurementConfig().getServiceUri());
    }

    /**
     * 获取订单明细
     *
     * @param inputData 请求参数数据部分不能为空
     * @return 响应结果不为空
     * @throws NanJingProcurementClientException 执行请求或者执行业务发生异常
     */
    default NanJingProcurementOrderDetailQueryResponse listOrders(NanJingProcurementOrderDetailQueryInputData inputData)
            throws NanJingProcurementClientException {
        return execute(new DefaultNanJingProcurementParameters(getNanJingProcurementConfig(), "NJHH012", inputData), NanJingProcurementOrderDetailQueryResponse.class,
                getNanJingProcurementConfig().getServiceUri());
    }

    //
    // /**
    //  * 撤销采购订单
    //  *
    //  * @param inputData 请求参数数据部分不能为空
    //  * @return 响应结果不为空
    //  * @throws HisProcurementClientException 执行请求或者执行业务发生异常
    //  */
    // default HisProcurementPurchaseOrderCancelResponse cancelPurchaseOrder(HisProcurementPurchaseOrderCancelInputData inputData)
    //         throws HisProcurementClientException {
    //     return execute(new DefaultHisProcurementParameters("ZJ9707", inputData), HisProcurementPurchaseOrderCancelResponse.class,
    //             getHisProcurementConfig().getServiceUri());
    // }
    //

    //
    // /**
    //  * 医疗机构收货
    //  *
    //  * @param inputData 请求参数数据部分不能为空
    //  * @return 响应结果不为空
    //  * @throws HisProcurementClientException 执行请求或者执行业务发生异常
    //  */
    // default HisProcurementTakeDeliveryResponse takeDelivery(HisProcurementTakeDeliveryInputData inputData)
    //         throws HisProcurementClientException {
    //     return execute(new DefaultHisProcurementParameters("ZJ9709", inputData), HisProcurementTakeDeliveryResponse.class,
    //             getHisProcurementConfig().getServiceUri());
    // }
    //
    /**
     * 创建提交退货申请
     *
     * @param inputData 请求参数数据部分不能为空
     * @return 响应结果不为空
     * @throws NanJingProcurementClientException 执行请求或者执行业务发生异常
     */
    default NanJingProcurementCreateReturnApplyResponse applyReturn(NanJingProcurementCreateReturnApplyInputData inputData)
            throws NanJingProcurementClientException {
        return execute(new DefaultNanJingProcurementParameters(getNanJingProcurementConfig(), "NJHH014", inputData), NanJingProcurementCreateReturnApplyResponse.class,
                getNanJingProcurementConfig().getServiceUri());
    }

    /**
     * 获取退货订单信息
     *
     * @param inputData 请求参数数据部分不能为空
     * @return 响应结果不为空
     * @throws NanJingProcurementClientException 响应结果不为空
     */
    default NanJingProcurementReturnOrderInfoResponse listReturns(NanJingProcurementReturnOrderInfoInputData inputData)
            throws NanJingProcurementClientException {
        return execute(new DefaultNanJingProcurementParameters(getNanJingProcurementConfig(), "NJHH015", inputData), NanJingProcurementReturnOrderInfoResponse.class,
                getNanJingProcurementConfig().getServiceUri());
    }

    // /**
    //  * 创建结算单
    //  *
    //  * @param inputData 请求参数数据部分不能为空
    //  * @return 响应结果不为空
    //  * @throws HisProcurementClientException 执行请求或者执行业务发生异常
    //  */
    // default HisProcurementStatementCreateResponse createStatement(HisProcurementStatementCreateInputData inputData)
    //         throws HisProcurementClientException {
    //     return execute(new DefaultHisProcurementParameters("ZJ9716", inputData), HisProcurementStatementCreateResponse.class,
    //             getHisProcurementConfig().getServiceUri());
    // }
    //
    // /**
    //  * 添加结算单明细
    //  *
    //  * @param inputData 请求参数数据部分不能为空
    //  * @return 响应结果不为空
    //  * @throws HisProcurementClientException 执行请求或者执行业务发生异常
    //  */
    // default HisProcurementStatementDetailAddResponse addStatementDetails(HisProcurementStatementDetailAddInputData inputData)
    //         throws HisProcurementClientException {
    //     return execute(new DefaultHisProcurementParameters("ZJ9717", inputData), HisProcurementStatementDetailAddResponse.class,
    //             getHisProcurementConfig().getServiceUri());
    // }
    //
    // /**
    //  * 提交结算单
    //  *
    //  * @param inputData 请求参数数据部分不能为空
    //  * @return 响应结果不为空
    //  * @throws HisProcurementClientException 执行请求或者执行业务发生异常
    //  */
    // default HisProcurementStatementSubmitResponse submitStatement(HisProcurementStatementSubmitInputData inputData)
    //         throws HisProcurementClientException {
    //     return execute(new DefaultHisProcurementParameters("ZJ9718", inputData), HisProcurementStatementSubmitResponse.class,
    //             getHisProcurementConfig().getServiceUri());
    // }
    //
    // /**
    //  * 审核结算单
    //  *
    //  * @param inputData 请求参数数据部分不能为空
    //  * @return 响应结果不为空
    //  * @throws HisProcurementClientException 执行请求或者执行业务发生异常
    //  */
    // default HisProcurementStatementExamineResponse examineStatement(HisProcurementStatementExamineInputData inputData)
    //         throws HisProcurementClientException {
    //     return execute(new DefaultHisProcurementParameters("ZJ9719", inputData), HisProcurementStatementExamineResponse.class,
    //             getHisProcurementConfig().getServiceUri());
    // }

}
