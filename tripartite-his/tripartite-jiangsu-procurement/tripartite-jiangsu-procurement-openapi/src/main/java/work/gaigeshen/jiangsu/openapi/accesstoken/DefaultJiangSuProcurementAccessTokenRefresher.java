package work.gaigeshen.jiangsu.openapi.accesstoken;

import work.gaigeshen.jiangsu.openapi.client.JiangSuProcurementBasicClient;
import work.gaigeshen.jiangsu.openapi.config.JiangSuProcurementConfig;
import work.gaigeshen.jiangsu.openapi.exception.JiangSuProcurementAccessTokenRefreshException;
import work.gaigeshen.jiangsu.openapi.parameters.JiangSuProcurementAccessTokenParameters;
import work.gaigeshen.jiangsu.openapi.response.JiangSuProcurementAccessTokenResponse;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;

/**
 * @author gaigeshen
 */
public class DefaultJiangSuProcurementAccessTokenRefresher implements JiangSuProcurementAccessTokenRefresher {

    private final JiangSuProcurementClientSelector nanJingProcurementClientSelector;

    public DefaultJiangSuProcurementAccessTokenRefresher(JiangSuProcurementClientSelector nanJingProcurementClientSelector) {
        ArgumentValidate.notNull(nanJingProcurementClientSelector, "hisProcurementClientSelector cannot be null");
        this.nanJingProcurementClientSelector = nanJingProcurementClientSelector;
    }

    @Override
    public JiangSuProcurementAccessToken refresh(JiangSuProcurementConfig config, JiangSuProcurementAccessToken oldAccessToken)
            throws JiangSuProcurementAccessTokenRefreshException {
        JiangSuProcurementBasicClient client;
        try {
            client = nanJingProcurementClientSelector.select(config, oldAccessToken);
        } catch (Exception e) {
            throw new JiangSuProcurementAccessTokenRefreshException("could not find his procurement client: " + oldAccessToken);
        }


        JiangSuProcurementAccessTokenParameters inputData = new JiangSuProcurementAccessTokenParameters(
                config.getAppCode(), config.getAuthCode());
        JiangSuProcurementAccessTokenResponse response;
        try {
            response = client.execute(inputData, JiangSuProcurementAccessTokenResponse.class, config.getAccessTokenUri());
        } catch (Exception e) {
            throw new JiangSuProcurementAccessTokenRefreshException("could not refresh access token", e)
                    .setCurrentAccessToken(oldAccessToken).setCanRetry(true);
        }
        return JiangSuProcurementAccessTokenHelper.createAccessToken(config, response.getAccessToken());
    }

    /**
     * @author gaigeshen
     */
    @FunctionalInterface
    public interface JiangSuProcurementClientSelector {
        JiangSuProcurementBasicClient select(JiangSuProcurementConfig config, JiangSuProcurementAccessToken oldAccessToken);
    }
}
