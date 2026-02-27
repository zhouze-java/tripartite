package work.gaigeshen.tripartite.jiangsu.online.openapi.response;

import lombok.Data;

/**
 * @author gaigeshen
 */
@Data
public abstract class AbstractJiangSuOnlineResponse implements JiangSuOnlineResponse {

    /**
     * 接口返回状态
     */
    private Integer returnCode;

    /**
     * 接口返回信息
     */
    private String returnMsg;

}
