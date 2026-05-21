package work.gaigeshen.jiangsu.spring.boot.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import work.gaigeshen.jiangsu.openapi.accesstoken.*;
import work.gaigeshen.jiangsu.openapi.client.*;
import work.gaigeshen.jiangsu.openapi.config.JiangSuProcurementConfig;
import work.gaigeshen.tripartite.core.ratelimiter.RateLimiterService;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author gaigeshen
 */
@EnableConfigurationProperties({JiangSuProcurementProperties.class})
@ConditionalOnClass({JiangSuProcurementBasicClient.class})
@Configuration
public class JiangSuProcurementAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(JiangSuProcurementAutoConfiguration.class);

    private final JiangSuProcurementProperties jiangSuProcurementProperties;

    public JiangSuProcurementAutoConfiguration(JiangSuProcurementProperties jiangSuProcurementProperties) {
        this.jiangSuProcurementProperties = jiangSuProcurementProperties;
    }

    @Bean
    public JiangSuProcurementClients nanJingProcurementClients() {
        JiangSuProcurementAccessTokenManager accessTokenManager = jiangSuProcurementAccessTokenManager();
        JiangSuProcurementClientCreator hisProcurementClientCreator =
                new DefaultJiangSuProcurementClientCreator(accessTokenManager, jiangSuProcurementRateLimiterService());
        Collection<JiangSuProcurementBasicClient> hisProcurementClients = new ArrayList<>();
        for (JiangSuProcurementProperties.Client client : jiangSuProcurementProperties.getClients()) {
            JiangSuProcurementConfig config = JiangSuProcurementConfig.builder()
                    .connectTimeout(client.getConnectTimeout())
                    .readTimeout(client.getReadTimeout())
                    .serverHost(client.getServerHost())
                    .accessTokenUri(client.getAccessTokenUri())
                    .serviceUri(client.getServiceUri())
                    .account(client.getAccount())
                    .type(client.getType())
                    .appCode(client.getAppCode())
                    .authCode(client.getAuthCode())
                    .hospitalName(client.getHospitalName())
                    .hospitalCode(client.getHospitalCode())
                    .build();
            JiangSuProcurementBasicClient procurementClient = hisProcurementClientCreator.create(config);
            hisProcurementClients.add(procurementClient);
            log.info("loaded his procurement client: {}", config);
        }
        return new DefaultJiangSuProcurementClients(hisProcurementClients, hisProcurementClientCreator);
    }

    @Bean(destroyMethod = "shutdown")
    public JiangSuProcurementAccessTokenManager jiangSuProcurementAccessTokenManager() {
        return new DefaultJiangSuProcurementAccessTokenManager(
                jiangSuProcurementAccessTokenStore(), jiangSuProcurementAccessTokenRefresher()
        );
    }

    @Bean
    public JiangSuProcurementAccessTokenRefresher jiangSuProcurementAccessTokenRefresher() {
        return new DefaultJiangSuProcurementAccessTokenRefresher(
                (cfg, oat) -> nanJingProcurementClients().getClientOrCreate(cfg));
    }

    @Bean
    public JiangSuProcurementAccessTokenStore jiangSuProcurementAccessTokenStore() {
        return new DefaultJiangSuProcurementAccessTokenStore();
    }

    /**
     * 省采订单接口限流（/order/add、/orderdetail/add、/order/submit），各 URI 独立每秒 1 次
     */
    @Bean
    public RateLimiterService jiangSuProcurementRateLimiterService() {
        return RateLimiterService.create(1.0);
    }
}
