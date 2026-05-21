package work.gaigeshen.jiangsu.openapi.client;

import org.springframework.web.client.RestTemplate;
import work.gaigeshen.jiangsu.openapi.config.JiangSuProcurementConfig;
import work.gaigeshen.tripartite.core.RestTemplateUtils;
import work.gaigeshen.tripartite.core.RestTemplateWebExecutor;
import work.gaigeshen.tripartite.core.WebExecutor;
import work.gaigeshen.tripartite.core.interceptor.Interceptor;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersMetadataParametersConverter;

/**
 * @author gaigeshen
 */
class DefaultJiangSuProcurementOnlineClient extends JiangSuProcurementAbstractClient implements HisProcurementOnLineClient {

    DefaultJiangSuProcurementOnlineClient(JiangSuProcurementConfig config, WebExecutor executor) {
        super(config, executor);
    }

    static DefaultJiangSuProcurementOnlineClient create(JiangSuProcurementConfig config, Interceptor... interceptors) {
        RestTemplate restTemplate = new RestTemplate();
        RestTemplateUtils.configureTimeout(restTemplate, config.getConnectTimeout(), config.getReadTimeout());
        RestTemplateWebExecutor executor = RestTemplateWebExecutor.create(restTemplate);
        executor.setInterceptors(interceptors);
        executor.setParametersConverter(new ParametersMetadataParametersConverter(config));
        return new DefaultJiangSuProcurementOnlineClient(config, executor);
    }

}
