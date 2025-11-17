package work.gaigeshen.tripartite.nanjing.procurement.spring.boot.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.accesstoken.*;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.client.*;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.config.NanJingProcurementConfig;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author gaigeshen
 */
@EnableConfigurationProperties({NanJingProcurementProperties.class})
@ConditionalOnClass({NanJingProcurementBasicClient.class})
@Configuration
public class NanJingProcurementAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(NanJingProcurementAutoConfiguration.class);

    private final NanJingProcurementProperties nanJingProcurementProperties;

    public NanJingProcurementAutoConfiguration(NanJingProcurementProperties nanJingProcurementProperties) {
        this.nanJingProcurementProperties = nanJingProcurementProperties;
    }

    @Bean
    public NanJingProcurementClients hisProcurementClients() {
        NanJingProcurementAccessTokenManager accessTokenManager = hisProcurementAccessTokenManager();
        NanJingProcurementClientCreator hisProcurementClientCreator = new DefaultNanJingProcurementClientCreator(accessTokenManager);
        Collection<NanJingProcurementBasicClient> hisProcurementClients = new ArrayList<>();
        for (NanJingProcurementProperties.Client client : nanJingProcurementProperties.getClients()) {
            NanJingProcurementConfig config = NanJingProcurementConfig.builder()
                    .connectTimeout(client.getConnectTimeout())
                    .readTimeout(client.getReadTimeout())
                    .serverHost(client.getServerHost())
                    .accessTokenUri(client.getAccessTokenUri())
                    .serviceUri(client.getServiceUri())
                    .account(client.getAccount())
                    .type(client.getType())
                    .appCode(client.getAppCode())
                    .authCode(client.getAuthCode())
                    .build();
            NanJingProcurementBasicClient procurementClient = hisProcurementClientCreator.create(config);
            hisProcurementClients.add(procurementClient);
            log.info("loaded his procurement client: {}", config);
        }
        return new DefaultNanJingProcurementClients(hisProcurementClients, hisProcurementClientCreator);
    }

    @Bean(destroyMethod = "shutdown")
    public NanJingProcurementAccessTokenManager hisProcurementAccessTokenManager() {
        return new DefaultNanJingProcurementAccessTokenManager(
                hisProcurementAccessTokenStore(), hisProcurementAccessTokenRefresher()
        );
    }

    @Bean
    public NanJingProcurementAccessTokenRefresher hisProcurementAccessTokenRefresher() {
        return new DefaultNanJingProcurementAccessTokenRefresher(
                (cfg, oat) -> hisProcurementClients().getClientOrCreate(cfg));
    }

    @Bean
    public NanJingProcurementAccessTokenStore hisProcurementAccessTokenStore() {
        return new DefaultNanJingProcurementAccessTokenStore();
    }
}
