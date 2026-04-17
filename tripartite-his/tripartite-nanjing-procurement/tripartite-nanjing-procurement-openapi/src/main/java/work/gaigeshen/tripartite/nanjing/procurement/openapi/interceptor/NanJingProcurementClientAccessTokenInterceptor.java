package work.gaigeshen.tripartite.nanjing.procurement.openapi.interceptor;

import lombok.extern.slf4j.Slf4j;
import work.gaigeshen.tripartite.core.interceptor.InterceptingException;
import work.gaigeshen.tripartite.core.util.json.JsonUtils;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.accesstoken.NanJingProcurementAccessToken;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.accesstoken.NanJingProcurementAccessTokenHelper;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.accesstoken.NanJingProcurementAccessTokenManager;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.client.NanJingProcurementBasicClient;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.config.NanJingProcurementConfig;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters.DefaultNanJingProcurementParameters;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters.NanJingProcurementAccessTokenInputData;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.response.NanJingProcurementAccessTokenResponse;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author gaigeshen
 */
@Slf4j
public class NanJingProcurementClientAccessTokenInterceptor extends NanJingProcurementClientRequestResponseInterceptor {

    private final NanJingProcurementBasicClient nanJingProcurementBasicClient;

    private final NanJingProcurementAccessTokenManager nanJingProcurementAccessTokenManager;

    public NanJingProcurementClientAccessTokenInterceptor(NanJingProcurementBasicClient client,
                                                          NanJingProcurementAccessTokenManager accessTokenManager) {
        super(client.getNanJingProcurementConfig());
        this.nanJingProcurementBasicClient = client;
        this.nanJingProcurementAccessTokenManager = accessTokenManager;
    }

    @Override
    protected void updateRequest(Request request) throws InterceptingException {
        super.updateRequest(request);

        String bodyContent = new String(request.bodyBytes(), StandardCharsets.UTF_8);
        Map<String, Object> bodyMap = JsonUtils.decodeObject(bodyContent);

        NanJingProcurementConfig config = nanJingProcurementBasicClient.getNanJingProcurementConfig();
        NanJingProcurementAccessToken accessToken = nanJingProcurementAccessTokenManager.findAccessToken(config);

        if (Objects.nonNull(accessToken) && !NanJingProcurementAccessTokenHelper.isExpired(accessToken)) {
            setRequestToken(bodyMap, accessToken);
        } else {
            NanJingProcurementAccessTokenInputData inputData = new NanJingProcurementAccessTokenInputData(config.getAppCode(), config.getAuthCode());
            NanJingProcurementAccessTokenResponse response;
            try {
                response = nanJingProcurementBasicClient.execute(
                        new DefaultNanJingProcurementParameters(config, "ELS7001", inputData),
                        NanJingProcurementAccessTokenResponse.class,
                        config.getAccessTokenUri());
            } catch (Exception e) {
                throw new InterceptingException("could not get new access token", e);
            }
            NanJingProcurementAccessToken newAccessToken = NanJingProcurementAccessTokenHelper.createAccessToken(config, response.getAccessToken());
            nanJingProcurementAccessTokenManager.addNewAccessToken(config, newAccessToken);

            setRequestToken(bodyMap, newAccessToken);
        }

        String encode = JsonUtils.encode(bodyMap);
        log.info("encode body:{}", encode);
        request.body(encode.getBytes(StandardCharsets.UTF_8));
    }

    private static void setRequestToken(Map<String, Object> bodyMap, NanJingProcurementAccessToken newAccessToken) {
        Map<String, Object> inputMap = (Map<String, Object>) bodyMap.get("input");
        if (inputMap == null) {
            inputMap = new HashMap<>();
            bodyMap.put("input", inputMap);
        }
        inputMap.put("access_token", newAccessToken.getAccessToken());
    }
}
