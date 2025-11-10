package work.gaigeshen.tripartite.nanjing.procurement.openapi.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author gaigeshen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class NanJingProcurementAccessTokenResponse extends AbstractNanJingProcurementResponse {

    private String accessToken;
}
