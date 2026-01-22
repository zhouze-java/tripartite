package work.gaigeshen.tripartite.jiangsu.online.openapi.client;

import org.springframework.web.client.RestTemplate;
import work.gaigeshen.tripartite.core.RestTemplateUtils;
import work.gaigeshen.tripartite.core.RestTemplateWebExecutor;
import work.gaigeshen.tripartite.core.WebExecutor;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersMetadataParametersConverter;
import work.gaigeshen.tripartite.jiangsu.online.openapi.config.JiangSuOnlineConfig;
import work.gaigeshen.tripartite.jiangsu.online.openapi.interceptor.JiangSuOnlineClientRequestResponseInterceptor;

/**
 * @author gaigeshen
 */
class JiangSuOnlineAccessTokenClient extends JiangSuOnlineAbstractClient {

    JiangSuOnlineAccessTokenClient(JiangSuOnlineConfig config, WebExecutor executor) {
        super(config, executor);
    }

    static JiangSuOnlineAccessTokenClient create(JiangSuOnlineConfig config) {
        RestTemplate restTemplate = new RestTemplate();
        RestTemplateUtils.configureTimeout(restTemplate, config.getConnectTimeout(), config.getReadTimeout());
        RestTemplateWebExecutor executor = RestTemplateWebExecutor.create(restTemplate);
        executor.setInterceptors(new JiangSuOnlineClientRequestResponseInterceptor(config));
        executor.setParametersConverter(new ParametersMetadataParametersConverter(config));
        return new JiangSuOnlineAccessTokenClient(config, executor);
    }

}
