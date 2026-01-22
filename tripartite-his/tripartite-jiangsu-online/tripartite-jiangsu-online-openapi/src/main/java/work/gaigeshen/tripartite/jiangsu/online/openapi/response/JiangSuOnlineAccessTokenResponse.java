package work.gaigeshen.tripartite.jiangsu.online.openapi.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author gaigeshen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class JiangSuOnlineAccessTokenResponse extends AbstractJiangSuOnlineResponse {

    private String accessToken;
}
