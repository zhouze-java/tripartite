package work.gaigeshen.jiangsu.openapi.response.province;

import lombok.Data;
import lombok.EqualsAndHashCode;
import work.gaigeshen.jiangsu.openapi.response.AbstractJiangSuProcurementResponse;

import java.util.List;

/**
 * H010 入库 - 返回结果
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class JiangSuProcurementStorageAddResponse extends AbstractJiangSuProcurementResponse {

    /**
     * 错误明细列表 (仅在部分或全部失败时返回)
     */
    private List<DisError> disErrorList;

    @Data
    public static class DisError {

        private String distributionSerialID;

        /**
         * 错误信息列表
         */
        private List<ErrorDetail> errorDetailList;
    }

    @Data
    public static class ErrorDetail {
        /**
         * 错误码
         */
        private String errorCode;

        /**
         * 错误信息
         */
        private String errorMsg;
    }
}