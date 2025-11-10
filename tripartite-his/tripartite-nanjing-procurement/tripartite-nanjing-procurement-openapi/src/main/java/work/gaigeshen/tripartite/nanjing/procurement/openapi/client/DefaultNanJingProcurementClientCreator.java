package work.gaigeshen.tripartite.nanjing.procurement.openapi.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.accesstoken.NanJingProcurementAccessTokenManager;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.config.NanJingProcurementConfig;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.exception.NanJingProcurementClientCreationException;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.interceptor.NanJingProcurementClientAccessTokenInterceptor;

import java.util.Objects;

import static work.gaigeshen.tripartite.nanjing.procurement.openapi.config.NanJingProcurementConfig.ONLINE_PURCHASE;
import static work.gaigeshen.tripartite.nanjing.procurement.openapi.config.NanJingProcurementConfig.PROVINCE_PURCHASE;


/**
 * @author gaigeshen
 */
public class DefaultNanJingProcurementClientCreator implements NanJingProcurementClientCreator {

    private static final Logger log = LoggerFactory.getLogger(DefaultNanJingProcurementClientCreator.class);

    private final NanJingProcurementAccessTokenManager accessTokenManager;

    public DefaultNanJingProcurementClientCreator(NanJingProcurementAccessTokenManager accessTokenManager) {
        ArgumentValidate.notNull(accessTokenManager, "accessTokenManager cannot be null");
        this.accessTokenManager = accessTokenManager;
    }

    @Override
    public NanJingProcurementBasicClient create(NanJingProcurementConfig config) throws NanJingProcurementClientCreationException {
        ArgumentValidate.notNull(config, "config cannot be null");

        ArgumentValidate.notNull(config, "config cannot be null");
        ArgumentValidate.notNull(config.getType(), "config type cannot be null");

        NanJingProcurementAccessTokenClient accessTokenClient = NanJingProcurementAccessTokenClient.create(config);
        NanJingProcurementClientAccessTokenInterceptor interceptor = new NanJingProcurementClientAccessTokenInterceptor(accessTokenClient, accessTokenManager);
        log.info("creating his procurement client: {}", config);

        // 省采
        if (Objects.equals(PROVINCE_PURCHASE, config.getType())) {
            return DefaultNanJingProcurementProvinceClient.create(config, interceptor);
        }
        else if (Objects.equals(ONLINE_PURCHASE, config.getType())) {
            return DefaultNanJingProcurementOnlineClient.create(config, interceptor);
        }
        throw new NanJingProcurementClientCreationException("config type [ " + config.getType() + " ] not supported");
    }
}
