package work.gaigeshen.tripartite.nanjing.procurement.openapi.accesstoken;

import work.gaigeshen.tripartite.core.util.ArgumentValidate;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.client.NanJingProcurementBasicClient;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.config.NanJingProcurementConfig;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.exception.NanJingProcurementAccessTokenRefreshException;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters.DefaultNanJingProcurementParameters;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters.NanJingProcurementAccessTokenInputData;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.response.NanJingProcurementAccessTokenResponse;

/**
 * @author gaigeshen
 */
public class DefaultNanJingProcurementAccessTokenRefresher implements NanJingProcurementAccessTokenRefresher {

    private final NanJingProcurementClientSelector nanJingProcurementClientSelector;

    public DefaultNanJingProcurementAccessTokenRefresher(NanJingProcurementClientSelector nanJingProcurementClientSelector) {
        ArgumentValidate.notNull(nanJingProcurementClientSelector, "hisProcurementClientSelector cannot be null");
        this.nanJingProcurementClientSelector = nanJingProcurementClientSelector;
    }

    @Override
    public NanJingProcurementAccessToken refresh(NanJingProcurementConfig config, NanJingProcurementAccessToken oldAccessToken)
            throws NanJingProcurementAccessTokenRefreshException {
        NanJingProcurementBasicClient client;
        try {
            client = nanJingProcurementClientSelector.select(config, oldAccessToken);
        } catch (Exception e) {
            throw new NanJingProcurementAccessTokenRefreshException("could not find his procurement client: " + oldAccessToken);
        }


        NanJingProcurementAccessTokenInputData inputData = new NanJingProcurementAccessTokenInputData(
                config.getAppCode(), config.getAuthCode());
        NanJingProcurementAccessTokenResponse response;
        try {
            response = client.execute(new DefaultNanJingProcurementParameters(config, "ELS7001", inputData), NanJingProcurementAccessTokenResponse.class, config.getAccessTokenUri());
        } catch (Exception e) {
            throw new NanJingProcurementAccessTokenRefreshException("could not refresh access token", e)
                    .setCurrentAccessToken(oldAccessToken).setCanRetry(true);
        }
        return NanJingProcurementAccessTokenHelper.createAccessToken(config, response.getAccessToken());
    }

    /**
     * @author gaigeshen
     */
    @FunctionalInterface
    public interface NanJingProcurementClientSelector {
        NanJingProcurementBasicClient select(NanJingProcurementConfig config, NanJingProcurementAccessToken oldAccessToken);
    }
}
