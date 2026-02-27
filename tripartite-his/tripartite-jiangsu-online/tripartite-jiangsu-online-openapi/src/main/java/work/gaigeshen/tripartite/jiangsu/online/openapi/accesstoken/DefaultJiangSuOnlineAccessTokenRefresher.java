package work.gaigeshen.tripartite.jiangsu.online.openapi.accesstoken;

import work.gaigeshen.tripartite.core.util.ArgumentValidate;
import work.gaigeshen.tripartite.jiangsu.online.openapi.client.JiangSuOnlineBasicClient;
import work.gaigeshen.tripartite.jiangsu.online.openapi.config.JiangSuOnlineConfig;
import work.gaigeshen.tripartite.jiangsu.online.openapi.exception.JiangSuOnlineAccessTokenRefreshException;
import work.gaigeshen.tripartite.jiangsu.online.openapi.parameters.DefaultJiangSuOnlineParameters;
import work.gaigeshen.tripartite.jiangsu.online.openapi.parameters.DefaultJiangSuOnlineTokenParameters;
import work.gaigeshen.tripartite.jiangsu.online.openapi.parameters.JiangSuOnlineAccessTokenInputData;
import work.gaigeshen.tripartite.jiangsu.online.openapi.response.JiangSuOnlineAccessTokenResponse;

/**
 * @author gaigeshen
 */
public class DefaultJiangSuOnlineAccessTokenRefresher implements JiangSuOnlineAccessTokenRefresher {

    private final JiangSuOnlineClientSelector nanJingProcurementClientSelector;

    public DefaultJiangSuOnlineAccessTokenRefresher(JiangSuOnlineClientSelector nanJingProcurementClientSelector) {
        ArgumentValidate.notNull(nanJingProcurementClientSelector, "hisProcurementClientSelector cannot be null");
        this.nanJingProcurementClientSelector = nanJingProcurementClientSelector;
    }

    @Override
    public JiangSuOnlineAccessToken refresh(JiangSuOnlineConfig config, JiangSuOnlineAccessToken oldAccessToken)
            throws JiangSuOnlineAccessTokenRefreshException {
        JiangSuOnlineBasicClient client;
        try {
            client = nanJingProcurementClientSelector.select(config, oldAccessToken);
        } catch (Exception e) {
            throw new JiangSuOnlineAccessTokenRefreshException("could not find his procurement client: " + oldAccessToken);
        }


        JiangSuOnlineAccessTokenInputData inputData = new JiangSuOnlineAccessTokenInputData(
                config.getAppCode(), config.getAuthCode());
        JiangSuOnlineAccessTokenResponse response;
        try {
            response = client.execute(new DefaultJiangSuOnlineTokenParameters(config, "ELS7001", inputData), JiangSuOnlineAccessTokenResponse.class, config.getAccessTokenUri());
        } catch (Exception e) {
            throw new JiangSuOnlineAccessTokenRefreshException("could not refresh access token", e)
                    .setCurrentAccessToken(oldAccessToken).setCanRetry(true);
        }
        return JiangSuOnlineAccessTokenHelper.createAccessToken(config, response.getAccessToken());
    }

    /**
     * @author gaigeshen
     */
    @FunctionalInterface
    public interface JiangSuOnlineClientSelector {
        JiangSuOnlineBasicClient select(JiangSuOnlineConfig config, JiangSuOnlineAccessToken oldAccessToken);
    }
}
