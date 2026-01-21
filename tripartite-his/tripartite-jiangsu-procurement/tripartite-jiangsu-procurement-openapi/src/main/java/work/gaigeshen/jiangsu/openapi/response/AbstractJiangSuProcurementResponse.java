package work.gaigeshen.jiangsu.openapi.response;

import lombok.Data;

/**
 * @author gaigeshen
 */
@Data
public abstract class AbstractJiangSuProcurementResponse implements JiangSuProcurementResponse {

    /**
     * 接口返回状态
     */
    private Integer returnCode;

    /**
     * 接口返回信息
     */
    private String returnMsg;

}
