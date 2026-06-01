package work.gaigeshen.jiangsu.openapi.interceptor;

import lombok.extern.slf4j.Slf4j;
import work.gaigeshen.jiangsu.openapi.accesstoken.JiangSuProcurementAccessToken;
import work.gaigeshen.jiangsu.openapi.accesstoken.JiangSuProcurementAccessTokenHelper;
import work.gaigeshen.jiangsu.openapi.accesstoken.JiangSuProcurementAccessTokenManager;
import work.gaigeshen.jiangsu.openapi.client.JiangSuProcurementBasicClient;
import work.gaigeshen.jiangsu.openapi.config.JiangSuProcurementConfig;

import work.gaigeshen.jiangsu.openapi.parameters.DefaultJiangSuProcurementAccessTokenParameters;
import work.gaigeshen.jiangsu.openapi.parameters.DefaultJiangSuProcurementParameters;
import work.gaigeshen.jiangsu.openapi.response.JiangSuProcurementAccessTokenResponse;
import work.gaigeshen.tripartite.core.interceptor.InterceptingException;
import work.gaigeshen.tripartite.core.util.json.JsonUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

/**
 * @author gaigeshen
 */
@Slf4j
public class JiangSuProcurementClientAccessTokenInterceptor extends JiangSuProcurementClientRequestResponseInterceptor {

    private final JiangSuProcurementBasicClient jiangSuProcurementBasicClient;

    private final JiangSuProcurementAccessTokenManager jiangSuProcurementAccessTokenManager;

    public JiangSuProcurementClientAccessTokenInterceptor(JiangSuProcurementBasicClient client,
                                                          JiangSuProcurementAccessTokenManager accessTokenManager) {
        super(client.getJiangSuProcurementConfig());
        this.jiangSuProcurementBasicClient = client;
        this.jiangSuProcurementAccessTokenManager = accessTokenManager;
    }

    @Override
    protected void updateRequest(Request request) throws InterceptingException {
        super.updateRequest(request);

        String bodyContent = new String(request.bodyBytes(), StandardCharsets.UTF_8);
        String decode = "";
        try {
           decode= URLDecoder.decode(bodyContent.replaceFirst("params=", ""), "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        Map<String, Object> bodyMap = JsonUtils.decodeObject(decode);

        JiangSuProcurementConfig config = jiangSuProcurementBasicClient.getJiangSuProcurementConfig();
        JiangSuProcurementAccessToken accessToken = jiangSuProcurementAccessTokenManager.findAccessToken(config);

        if (Objects.nonNull(accessToken) && !JiangSuProcurementAccessTokenHelper.isExpired(accessToken)) {
            setRequestToken(bodyMap, accessToken);
        } else {
            DefaultJiangSuProcurementAccessTokenParameters inputData = new DefaultJiangSuProcurementAccessTokenParameters(config.getAppCode(), config.getAuthCode());
            JiangSuProcurementAccessTokenResponse response;
            try {
                response = jiangSuProcurementBasicClient.execute(inputData, JiangSuProcurementAccessTokenResponse.class, config.getAccessTokenUri());
            } catch (Exception e) {
                throw new InterceptingException("could not get new access token", e);
            }
            long expiresInSeconds = JiangSuProcurementAccessTokenHelper.resolveExpiresInSeconds(response.getExpiresIn());
            JiangSuProcurementAccessToken newAccessToken = JiangSuProcurementAccessTokenHelper.createAccessToken(
                    config, response.getAccessToken(), expiresInSeconds);
            jiangSuProcurementAccessTokenManager.addNewAccessToken(config, newAccessToken);

            setRequestToken(bodyMap, newAccessToken);
        }

        String encode = JsonUtils.encode(bodyMap);
        String encodeBody = "params=" + encode;
        log.info("encode body:{}", encodeBody);
        request.body(encodeBody.getBytes(StandardCharsets.UTF_8));
    }

    private static void setRequestToken(Map<String, Object> bodyMap, JiangSuProcurementAccessToken newAccessToken) {
        bodyMap.put("accessToken", newAccessToken.getAccessToken());
    }
}
