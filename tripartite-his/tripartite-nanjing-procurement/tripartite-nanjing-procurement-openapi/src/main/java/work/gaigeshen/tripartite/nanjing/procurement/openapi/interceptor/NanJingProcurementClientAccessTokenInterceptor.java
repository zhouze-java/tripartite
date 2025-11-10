package work.gaigeshen.tripartite.nanjing.procurement.openapi.interceptor;

import work.gaigeshen.tripartite.core.header.Headers;
import work.gaigeshen.tripartite.core.interceptor.InterceptingException;

import work.gaigeshen.tripartite.nanjing.procurement.openapi.accesstoken.NanJingProcurementAccessToken;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.accesstoken.NanJingProcurementAccessTokenHelper;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.accesstoken.NanJingProcurementAccessTokenManager;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.client.NanJingProcurementBasicClient;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.config.NanJingProcurementConfig;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters.NanJingProcurementAccessTokenParameters;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.response.NanJingProcurementAccessTokenResponse;

import java.util.Objects;

/**
 * @author gaigeshen
 */
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
        NanJingProcurementConfig config = nanJingProcurementBasicClient.getNanJingProcurementConfig();
        NanJingProcurementAccessToken accessToken = nanJingProcurementAccessTokenManager.findAccessToken(config);
        Headers headers = request.headers();
        if (Objects.nonNull(accessToken) && !NanJingProcurementAccessTokenHelper.isExpired(accessToken)) {
            headers.putValue("Access-Token", accessToken.getAccessToken());
            return;
        }
        NanJingProcurementAccessTokenParameters parameters = new NanJingProcurementAccessTokenParameters(config.getAppCode(), config.getAuthCode());
        NanJingProcurementAccessTokenResponse response;
        try {
            response = nanJingProcurementBasicClient.execute(parameters, NanJingProcurementAccessTokenResponse.class,
                    config.getAccessTokenUri());
        } catch (Exception e) {
            throw new InterceptingException("could not get new access token", e);
        }
        NanJingProcurementAccessToken newAccessToken = NanJingProcurementAccessTokenHelper.createAccessToken(
                config, response.getAccessToken());
        nanJingProcurementAccessTokenManager.addNewAccessToken(config, newAccessToken);
        headers.putValue("Access-Token", newAccessToken.getAccessToken());
    }
}
