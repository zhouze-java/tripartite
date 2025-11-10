package work.gaigeshen.tripartite.nanjing.procurement.openapi.client;

import org.springframework.web.client.RestTemplate;
import work.gaigeshen.tripartite.core.RestTemplateUtils;
import work.gaigeshen.tripartite.core.RestTemplateWebExecutor;
import work.gaigeshen.tripartite.core.WebExecutor;
import work.gaigeshen.tripartite.core.interceptor.AbstractInterceptor;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersMetadataParametersConverter;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.config.NanJingProcurementConfig;

/**
 * @author gaigeshen
 */
class DefaultNanJingProcurementProvinceClient extends NanJingProcurementAbstractClient implements NanJingProcurementProvinceClient {

    DefaultNanJingProcurementProvinceClient(NanJingProcurementConfig config, WebExecutor executor) {
        super(config, executor);
    }

    static DefaultNanJingProcurementProvinceClient create(NanJingProcurementConfig config, AbstractInterceptor... interceptors) {
        RestTemplate restTemplate = new RestTemplate();
        RestTemplateUtils.configureTimeout(restTemplate, config.getConnectTimeout(), config.getReadTimeout());
        RestTemplateWebExecutor executor = RestTemplateWebExecutor.create(restTemplate);
        executor.setInterceptors(interceptors);
        executor.setParametersConverter(new ParametersMetadataParametersConverter(config));
        return new DefaultNanJingProcurementProvinceClient(config, executor);
    }

}
