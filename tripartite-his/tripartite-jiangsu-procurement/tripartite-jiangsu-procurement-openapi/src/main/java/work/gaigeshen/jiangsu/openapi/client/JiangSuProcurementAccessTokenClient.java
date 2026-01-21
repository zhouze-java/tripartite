package work.gaigeshen.jiangsu.openapi.client;

import org.springframework.web.client.RestTemplate;
import work.gaigeshen.jiangsu.openapi.config.JiangSuProcurementConfig;
import work.gaigeshen.jiangsu.openapi.interceptor.JiangSuProcurementClientRequestResponseInterceptor;
import work.gaigeshen.tripartite.core.RestTemplateUtils;
import work.gaigeshen.tripartite.core.RestTemplateWebExecutor;
import work.gaigeshen.tripartite.core.WebExecutor;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersMetadataParametersConverter;

/**
 * @author gaigeshen
 */
class JiangSuProcurementAccessTokenClient extends JiangSuProcurementAbstractClient {

    JiangSuProcurementAccessTokenClient(JiangSuProcurementConfig config, WebExecutor executor) {
        super(config, executor);
    }

    static JiangSuProcurementAccessTokenClient create(JiangSuProcurementConfig config) {
        RestTemplate restTemplate = new RestTemplate();
        RestTemplateUtils.configureTimeout(restTemplate, config.getConnectTimeout(), config.getReadTimeout());
        RestTemplateWebExecutor executor = RestTemplateWebExecutor.create(restTemplate);
        executor.setInterceptors(new JiangSuProcurementClientRequestResponseInterceptor(config));
        executor.setParametersConverter(new ParametersMetadataParametersConverter(config));
        return new JiangSuProcurementAccessTokenClient(config, executor);
    }

}
