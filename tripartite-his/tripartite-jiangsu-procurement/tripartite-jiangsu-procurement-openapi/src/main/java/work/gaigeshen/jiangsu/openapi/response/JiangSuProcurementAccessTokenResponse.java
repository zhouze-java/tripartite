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
     * 剩余有效时长（秒）
     */
    private Long expiresIn;

    /**
     * 当前时间
     */
    private String currentTime;

}
