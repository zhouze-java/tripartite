package work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters;

import lombok.Getter;
import work.gaigeshen.tripartite.core.parameter.typed.Parameter;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;

/**
 * @author gaigeshen
 */
@Getter
public class NanJingProcurementAccessTokenInputData implements NanJingProcurementInputData {

    @Parameter(name = "app_code")
    public final String appCode;

    @Parameter(name = "auth_code")
    public final String authCode;

    public NanJingProcurementAccessTokenInputData(String appCode, String authCode) {
        ArgumentValidate.notNull(appCode, "appCode cannot be null");
        ArgumentValidate.notNull(authCode, "authCode cannot be null");
        this.appCode = appCode;
        this.authCode = authCode;
    }

}
