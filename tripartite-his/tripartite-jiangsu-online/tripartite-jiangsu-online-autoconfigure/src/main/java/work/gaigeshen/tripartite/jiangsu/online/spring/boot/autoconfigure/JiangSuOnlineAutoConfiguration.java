package work.gaigeshen.tripartite.jiangsu.online.spring.boot.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import work.gaigeshen.tripartite.jiangsu.online.openapi.accesstoken.*;
import work.gaigeshen.tripartite.jiangsu.online.openapi.client.*;
import work.gaigeshen.tripartite.jiangsu.online.openapi.config.JiangSuOnlineConfig;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author gaigeshen
 */
@EnableConfigurationProperties({JiangSuOnlineProperties.class})
@ConditionalOnClass({JiangSuOnlineBasicClient.class})
@Configuration
public class JiangSuOnlineAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(JiangSuOnlineAutoConfiguration.class);

    private final JiangSuOnlineProperties jiangSuOnlineProperties;

    public JiangSuOnlineAutoConfiguration(JiangSuOnlineProperties jiangSuOnlineProperties) {
        this.jiangSuOnlineProperties = jiangSuOnlineProperties;
    }

    @Bean
    public JiangSuOnlineClients nanJingProcurementClients() {
        JiangSuOnlineAccessTokenManager accessTokenManager = nanJingProcurementAccessTokenManager();
        JiangSuOnlineClientCreator hisProcurementClientCreator = new DefaultJiangSuOnlineClientCreator(accessTokenManager);
        Collection<JiangSuOnlineBasicClient> hisProcurementClients = new ArrayList<>();
        for (JiangSuOnlineProperties.Client client : jiangSuOnlineProperties.getClients()) {
            JiangSuOnlineConfig config = JiangSuOnlineConfig.builder()
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
                    .apiAccessKey(client.getApiAccessKey())
                    .secretKey(client.getSecretKey())
                    .build();
            JiangSuOnlineBasicClient procurementClient = hisProcurementClientCreator.create(config);
            hisProcurementClients.add(procurementClient);
            log.info("loaded his procurement client: {}", config);
        }
        return new DefaultJiangSuOnlineClients(hisProcurementClients, hisProcurementClientCreator);
    }

    @Bean(destroyMethod = "shutdown")
    public JiangSuOnlineAccessTokenManager nanJingProcurementAccessTokenManager() {
        return new DefaultJiangSuOnlineAccessTokenManager(
                nanJingProcurementAccessTokenStore(), nanJingProcurementAccessTokenRefresher()
        );
    }

    @Bean
    public JiangSuOnlineAccessTokenRefresher nanJingProcurementAccessTokenRefresher() {
        return new DefaultJiangSuOnlineAccessTokenRefresher(
                (cfg, oat) -> nanJingProcurementClients().getClientOrCreate(cfg));
    }

    @Bean
    public JiangSuOnlineAccessTokenStore nanJingProcurementAccessTokenStore() {
        return new DefaultJiangSuOnlineAccessTokenStore();
    }
}
