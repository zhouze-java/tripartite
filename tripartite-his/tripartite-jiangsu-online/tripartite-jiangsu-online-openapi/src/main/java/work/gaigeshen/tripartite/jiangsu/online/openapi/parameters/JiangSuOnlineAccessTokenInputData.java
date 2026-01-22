package work.gaigeshen.tripartite.jiangsu.online.openapi.parameters;

import lombok.Getter;
import work.gaigeshen.tripartite.core.parameter.typed.Parameter;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;

/**
 * @author gaigeshen
 */
@Getter
public class JiangSuOnlineAccessTokenInputData implements JiangSuOnlineInputData {

    @Parameter(name = "app_code")
    public final String appCode;

    @Parameter(name = "auth_code")
    public final String authCode;

    public JiangSuOnlineAccessTokenInputData(String appCode, String authCode) {
        ArgumentValidate.notNull(appCode, "appCode cannot be null");
        ArgumentValidate.notNull(authCode, "authCode cannot be null");
        this.appCode = appCode;
        this.authCode = authCode;
    }

}
