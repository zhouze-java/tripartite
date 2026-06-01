package work.gaigeshen.jiangsu.openapi.response.province;

import lombok.Data;
import lombok.EqualsAndHashCode;
import work.gaigeshen.jiangsu.openapi.response.AbstractJiangSuProcurementResponse;

import java.util.List;

/**
 * H014 撤单出参
 *
 * @author zhouze
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class JiangSuProcurementOrderCancelResponse extends AbstractJiangSuProcurementResponse {

    /**
     * 错误明细列表（仅在失败时返回）
     */
    private List<ErrorItem> dataList;

    @Data
    public static class ErrorItem {

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
