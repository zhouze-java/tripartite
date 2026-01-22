package work.gaigeshen.tripartite.jiangsu.online.openapi.client;

import org.springframework.web.client.RestTemplate;
import work.gaigeshen.tripartite.core.RestTemplateUtils;
import work.gaigeshen.tripartite.core.RestTemplateWebExecutor;
import work.gaigeshen.tripartite.core.WebExecutor;
import work.gaigeshen.tripartite.core.interceptor.AbstractInterceptor;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersMetadataParametersConverter;
import work.gaigeshen.tripartite.jiangsu.online.openapi.config.JiangSuOnlineConfig;

/**
 * @author gaigeshen
 */
class DefaultJiangSuOnlineProvinceClient extends JiangSuOnlineAbstractClient implements JiangSuOnlineProvinceClient {

    DefaultJiangSuOnlineProvinceClient(JiangSuOnlineConfig config, WebExecutor executor) {
        super(config, executor);
    }

    static DefaultJiangSuOnlineProvinceClient create(JiangSuOnlineConfig config, AbstractInterceptor... interceptors) {
        RestTemplate restTemplate = new RestTemplate();
        RestTemplateUtils.configureTimeout(restTemplate, config.getConnectTimeout(), config.getReadTimeout());
        RestTemplateWebExecutor executor = RestTemplateWebExecutor.create(restTemplate);
        executor.setInterceptors(interceptors);
        executor.setParametersConverter(new ParametersMetadataParametersConverter(config));
        return new DefaultJiangSuOnlineProvinceClient(config, executor);
    }

}
