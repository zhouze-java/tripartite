package work.gaigeshen.jiangsu.openapi.response.province;

import lombok.Data;
import lombok.EqualsAndHashCode;
import work.gaigeshen.jiangsu.openapi.response.AbstractJiangSuProcurementResponse;
import java.util.List;

/**
 * H011 退货出参
 *
 * @author zhouze
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class JiangSuProcurementReturnAddResponse extends AbstractJiangSuProcurementResponse {

    /**
     * 成功列表
     */
    private List<SuccessReturn> successList;

    /**
     * 错误列表
     */
    private List<ErrorReturn> errorList;

    /**
     * 成功退货信息
     */
    @Data
    public static class SuccessReturn {
        /**
         * 医院退货明细主键 (医院系统退货明细主键)
         */
        private String hospitalReturnId;

        /**
         * 省平台的退货信息唯一标识 (需要保存至医疗机构内部系统)
         */
        private String returnId;
    }

    /**
     * 错误退货信息
     */
    @Data
    public static class ErrorReturn {
        /**
         * 医院退货明细主键 (医院系统退货明细主键)
         */
        private String hospitalReturnId;

        /**
         * 错误原因列表
         */
        private List<ErrorReason> errorReasonList;
    }

    /**
     * 错误原因详情
     */
    @Data
    public static class ErrorReason {
        /**
         * 错误码
         */
        private String errorCode;

        /**
         * 错误原因描述
         */
        private String errorMsg;
    }
}