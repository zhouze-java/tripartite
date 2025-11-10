package work.gaigeshen.tripartite.nanjing.procurement.openapi.client;

import org.springframework.web.client.RestTemplate;
import work.gaigeshen.tripartite.core.RestTemplateUtils;
import work.gaigeshen.tripartite.core.RestTemplateWebExecutor;
import work.gaigeshen.tripartite.core.WebExecutor;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersMetadataParametersConverter;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.config.NanJingProcurementConfig;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.interceptor.NanJingProcurementClientRequestResponseInterceptor;

/**
 * @author gaigeshen
 */
class NanJingProcurementAccessTokenClient extends NanJingProcurementAbstractClient {

    NanJingProcurementAccessTokenClient(NanJingProcurementConfig config, WebExecutor executor) {
        super(config, executor);
    }

    static NanJingProcurementAccessTokenClient create(NanJingProcurementConfig config) {
        RestTemplate restTemplate = new RestTemplate();
        RestTemplateUtils.configureTimeout(restTemplate, config.getConnectTimeout(), config.getReadTimeout());
        RestTemplateWebExecutor executor = RestTemplateWebExecutor.create(restTemplate);
        executor.setInterceptors(new NanJingProcurementClientRequestResponseInterceptor(config));
        executor.setParametersConverter(new ParametersMetadataParametersConverter(config));
        return new NanJingProcurementAccessTokenClient(config, executor);
    }

}
