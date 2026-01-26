package work.gaigeshen.tripartite.jiangsu.online.openapi.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;
import work.gaigeshen.tripartite.jiangsu.online.openapi.accesstoken.JiangSuOnlineAccessTokenManager;
import work.gaigeshen.tripartite.jiangsu.online.openapi.config.JiangSuOnlineConfig;
import work.gaigeshen.tripartite.jiangsu.online.openapi.exception.JiangSuOnlineClientCreationException;
import work.gaigeshen.tripartite.jiangsu.online.openapi.interceptor.JiangSuOnlineClientAccessTokenInterceptor;

import java.util.Objects;

import static work.gaigeshen.tripartite.jiangsu.online.openapi.config.JiangSuOnlineConfig.ONLINE_PURCHASE;
import static work.gaigeshen.tripartite.jiangsu.online.openapi.config.JiangSuOnlineConfig.PROVINCE_PURCHASE;


/**
 * @author gaigeshen
 */
public class DefaultJiangSuOnlineClientCreator implements JiangSuOnlineClientCreator {

    private static final Logger log = LoggerFactory.getLogger(DefaultJiangSuOnlineClientCreator.class);

    private final JiangSuOnlineAccessTokenManager accessTokenManager;

    public DefaultJiangSuOnlineClientCreator(JiangSuOnlineAccessTokenManager accessTokenManager) {
        ArgumentValidate.notNull(accessTokenManager, "accessTokenManager cannot be null");
        this.accessTokenManager = accessTokenManager;
    }

    @Override
    public JiangSuOnlineBasicClient create(JiangSuOnlineConfig config) throws JiangSuOnlineClientCreationException {
        ArgumentValidate.notNull(config, "config cannot be null");

        ArgumentValidate.notNull(config, "config cannot be null");
        ArgumentValidate.notNull(config.getType(), "config type cannot be null");

        JiangSuOnlineAccessTokenClient accessTokenClient = JiangSuOnlineAccessTokenClient.create(config);
        JiangSuOnlineClientAccessTokenInterceptor interceptor = new JiangSuOnlineClientAccessTokenInterceptor(accessTokenClient, accessTokenManager);
        log.info("creating his procurement client: {}", config);

        // 省采
        if (Objects.equals(PROVINCE_PURCHASE, config.getType())) {
            return DefaultJiangSuOnlineProvinceClient.create(config, interceptor);
        }
        else if (Objects.equals(ONLINE_PURCHASE, config.getType())) {
            return DefaultJiangSuOnlineOnlineClient.create(config, interceptor);
        }
        throw new JiangSuOnlineClientCreationException("config type [ " + config.getType() + " ] not supported");
    }
}
