package work.gaigeshen.jiangsu.openapi.client;

import org.springframework.web.client.RestTemplate;
import work.gaigeshen.jiangsu.openapi.config.JiangSuProcurementConfig;
import work.gaigeshen.tripartite.core.RestTemplateUtils;
import work.gaigeshen.tripartite.core.RestTemplateWebExecutor;
import work.gaigeshen.tripartite.core.WebExecutor;
import work.gaigeshen.tripartite.core.interceptor.Interceptor;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersMetadataParametersConverter;
import work.gaigeshen.tripartite.core.parameter.converter.RawFormJsonParametersConverter;

/**
 * @author gaigeshen
 */
class DefaultJiangSuProcurementProvinceClient extends JiangSuProcurementAbstractClient implements JiangSuProcurementProvinceClient {

    DefaultJiangSuProcurementProvinceClient(JiangSuProcurementConfig config, WebExecutor executor) {
        super(config, executor);
    }

    static DefaultJiangSuProcurementProvinceClient create(JiangSuProcurementConfig config, Interceptor... interceptors) {
        RestTemplate restTemplate = new RestTemplate();
        RestTemplateUtils.configureTimeout(restTemplate, config.getConnectTimeout(), config.getReadTimeout());
        RestTemplateWebExecutor executor = RestTemplateWebExecutor.create(restTemplate);
        executor.setInterceptors(interceptors);
        executor.setParametersConverter(new RawFormJsonParametersConverter());
        return new DefaultJiangSuProcurementProvinceClient(config, executor);
    }
}
