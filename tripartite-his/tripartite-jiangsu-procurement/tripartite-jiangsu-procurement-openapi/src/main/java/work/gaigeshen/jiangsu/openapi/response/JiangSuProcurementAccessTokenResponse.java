package work.gaigeshen.jiangsu.openapi.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhouze
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class JiangSuProcurementAccessTokenResponse extends AbstractJiangSuProcurementResponse{

    /**
     * accessToken
     */
    private String accessToken;

    /**
     * 失效时间
     */
    private String expiresIn;

    /**
     * 当前时间
     */
    private String currentTime;

}
