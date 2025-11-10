package work.gaigeshen.tripartite.nanjing.procurement.openapi.response;

import lombok.Data;

/**
 * @author gaigeshen
 */
@Data
public abstract class AbstractNanJingProcurementResponse implements NanJingProcurementResponse {

    /**
     * 接口返回状态
     */
    private Integer returnCode;

    /**
     * 接口返回信息
     */
    private String returnMsg;

}
