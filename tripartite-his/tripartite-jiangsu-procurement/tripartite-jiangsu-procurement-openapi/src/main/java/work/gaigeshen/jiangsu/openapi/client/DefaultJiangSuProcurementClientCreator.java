package work.gaigeshen.jiangsu.openapi.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import work.gaigeshen.jiangsu.openapi.accesstoken.JiangSuProcurementAccessTokenManager;
import work.gaigeshen.jiangsu.openapi.config.JiangSuProcurementConfig;
import work.gaigeshen.jiangsu.openapi.exception.JiangSuProcurementClientCreationException;
import work.gaigeshen.jiangsu.openapi.interceptor.JiangSuProcurementClientAccessTokenInterceptor;
import work.gaigeshen.jiangsu.openapi.interceptor.JiangSuProcurementClientRateLimitInterceptor;
import work.gaigeshen.tripartite.core.ratelimiter.RateLimiterService;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;

import java.util.Objects;

import static work.gaigeshen.jiangsu.openapi.config.JiangSuProcurementConfig.ONLINE_PURCHASE;
import static work.gaigeshen.jiangsu.openapi.config.JiangSuProcurementConfig.PROVINCE_PURCHASE;


/**
 * @author gaigeshen
 */
public class DefaultJiangSuProcurementClientCreator implements JiangSuProcurementClientCreator {

    private static final Logger log = LoggerFactory.getLogger(DefaultJiangSuProcurementClientCreator.class);

    private final JiangSuProcurementAccessTokenManager accessTokenManager;

    private final RateLimiterService rateLimiterService;

    public DefaultJiangSuProcurementClientCreator(
            JiangSuProcurementAccessTokenManager accessTokenManager, RateLimiterService rateLimiterService) {
        ArgumentValidate.notNull(accessTokenManager, "accessTokenManager cannot be null");
        ArgumentValidate.notNull(rateLimiterService, "rateLimiterService cannot be null");
        this.accessTokenManager = accessTokenManager;
        this.rateLimiterService = rateLimiterService;
    }

    @Override
    public JiangSuProcurementBasicClient create(JiangSuProcurementConfig config) throws JiangSuProcurementClientCreationException {
        ArgumentValidate.notNull(config, "config cannot be null");

        ArgumentValidate.notNull(config, "config cannot be null");
        ArgumentValidate.notNull(config.getType(), "config type cannot be null");

        JiangSuProcurementAccessTokenClient accessTokenClient = JiangSuProcurementAccessTokenClient.create(config);
        JiangSuProcurementClientRateLimitInterceptor rateLimitInterceptor =   new JiangSuProcurementClientRateLimitInterceptor(rateLimiterService);
        JiangSuProcurementClientAccessTokenInterceptor accessTokenInterceptor =
                new JiangSuProcurementClientAccessTokenInterceptor(accessTokenClient, accessTokenManager);
        log.info("creating his procurement client: {}", config);

        // 省采
        if (Objects.equals(PROVINCE_PURCHASE, config.getType())) {
            return DefaultJiangSuProcurementProvinceClient.create(config, rateLimitInterceptor, accessTokenInterceptor);
        }
        else if (Objects.equals(ONLINE_PURCHASE, config.getType())) {
            return DefaultJiangSuProcurementOnlineClient.create(config, rateLimitInterceptor, accessTokenInterceptor);
        }
        throw new JiangSuProcurementClientCreationException("config type [ " + config.getType() + " ] not supported");
    }
}
