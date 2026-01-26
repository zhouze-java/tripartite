package work.gaigeshen.tripartite.jiangsu.online.openapi.interceptor;

import lombok.extern.slf4j.Slf4j;
import work.gaigeshen.tripartite.core.interceptor.InterceptingException;
import work.gaigeshen.tripartite.core.util.json.JsonUtils;
import work.gaigeshen.tripartite.jiangsu.online.openapi.accesstoken.JiangSuOnlineAccessToken;
import work.gaigeshen.tripartite.jiangsu.online.openapi.accesstoken.JiangSuOnlineAccessTokenHelper;
import work.gaigeshen.tripartite.jiangsu.online.openapi.accesstoken.JiangSuOnlineAccessTokenManager;
import work.gaigeshen.tripartite.jiangsu.online.openapi.client.JiangSuOnlineBasicClient;
import work.gaigeshen.tripartite.jiangsu.online.openapi.config.JiangSuOnlineConfig;
import work.gaigeshen.tripartite.jiangsu.online.openapi.parameters.DefaultJiangSuOnlineParameters;
import work.gaigeshen.tripartite.jiangsu.online.openapi.parameters.JiangSuOnlineAccessTokenInputData;
import work.gaigeshen.tripartite.jiangsu.online.openapi.response.JiangSuOnlineAccessTokenResponse;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author gaigeshen
 */
@Slf4j
public class JiangSuOnlineClientAccessTokenInterceptor extends JiangSuOnlineClientRequestResponseInterceptor {

    private final JiangSuOnlineBasicClient nanJingProcurementBasicClient;

    private final JiangSuOnlineAccessTokenManager nanJingProcurementAccessTokenManager;

    public JiangSuOnlineClientAccessTokenInterceptor(JiangSuOnlineBasicClient client,
                                                     JiangSuOnlineAccessTokenManager accessTokenManager) {
        super(client.getJiangSuOnlineConfig());
        this.nanJingProcurementBasicClient = client;
        this.nanJingProcurementAccessTokenManager = accessTokenManager;
    }

    @Override
    protected void updateRequest(Request request) throws InterceptingException {
        super.updateRequest(request);

        String bodyContent = new String(request.bodyBytes(), StandardCharsets.UTF_8);
        Map<String, Object> bodyMap = JsonUtils.decodeObject(bodyContent);

        JiangSuOnlineConfig config = nanJingProcurementBasicClient.getJiangSuOnlineConfig();
        JiangSuOnlineAccessToken accessToken = nanJingProcurementAccessTokenManager.findAccessToken(config);

        if (Objects.nonNull(accessToken) && !JiangSuOnlineAccessTokenHelper.isExpired(accessToken)) {
            setRequestToken(bodyMap, accessToken);
        } else {
            JiangSuOnlineAccessTokenInputData inputData = new JiangSuOnlineAccessTokenInputData(config.getAppCode(), config.getAuthCode());
            JiangSuOnlineAccessTokenResponse response;
            try {
                response = nanJingProcurementBasicClient.execute(
                        new DefaultJiangSuOnlineParameters(config, "ELS7001", inputData),
                        JiangSuOnlineAccessTokenResponse.class,
                        config.getAccessTokenUri());
            } catch (Exception e) {
                throw new InterceptingException("could not get new access token", e);
            }
            JiangSuOnlineAccessToken newAccessToken = JiangSuOnlineAccessTokenHelper.createAccessToken(config, response.getAccessToken());
            nanJingProcurementAccessTokenManager.addNewAccessToken(config, newAccessToken);

            setRequestToken(bodyMap, newAccessToken);
        }

        String encode = JsonUtils.encode(bodyMap);
        request.body(encode.getBytes(StandardCharsets.UTF_8));
    }

    private static void setRequestToken(Map<String, Object> bodyMap, JiangSuOnlineAccessToken newAccessToken) {
        Map<String, Object> inputMap = (Map<String, Object>) bodyMap.get("input");
        if (inputMap == null) {
            inputMap = new HashMap<>();
            bodyMap.put("input", inputMap);
        }
        inputMap.put("access_token", newAccessToken.getAccessToken());
    }
}
