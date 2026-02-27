package work.gaigeshen.tripartite.jiangsu.online.openapi.client;

import work.gaigeshen.tripartite.core.WebException;
import work.gaigeshen.tripartite.core.WebExecutor;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;
import work.gaigeshen.tripartite.jiangsu.online.openapi.config.JiangSuOnlineConfig;
import work.gaigeshen.tripartite.jiangsu.online.openapi.exception.JiangSuOnlineClientException;
import work.gaigeshen.tripartite.jiangsu.online.openapi.parameters.JiangSuOnlineParameters;
import work.gaigeshen.tripartite.jiangsu.online.openapi.response.AbstractJiangSuOnlineResponse;
import work.gaigeshen.tripartite.jiangsu.online.openapi.response.JiangSuOnlineResponse;

import java.util.Objects;

/**
 * @author gaigeshen
 */
public abstract class JiangSuOnlineAbstractClient implements JiangSuOnlineBasicClient {

    private final JiangSuOnlineConfig config;

    private final WebExecutor executor;


    protected JiangSuOnlineAbstractClient(JiangSuOnlineConfig config, WebExecutor executor) {
        ArgumentValidate.notNull(config, "config cannot be null");
        ArgumentValidate.notNull(executor, "executor cannot be null");
        this.config = config;
        this.executor = executor;
    }

    @Override
    public JiangSuOnlineConfig getJiangSuOnlineConfig() {
        return config;
    }

    @Override
    public <R extends JiangSuOnlineResponse> R execute(JiangSuOnlineParameters parameters, Class<R> responseClass, String uri)
            throws JiangSuOnlineClientException {
        ArgumentValidate.notNull(parameters, "parameters cannot be null");
        ArgumentValidate.notNull(responseClass, "responseClass cannot be null");
        ArgumentValidate.notNull(uri, "uri cannot be null");
        try {
            R response = executor.execute(config.getServerHost() + uri, parameters, responseClass);
            return validateResponse(response);
        } catch (WebException e) {
            throw new JiangSuOnlineClientException(e.getMessage(), e);
        }
    }

    protected <R extends JiangSuOnlineResponse> R validateResponse(R response) throws JiangSuOnlineClientException {
        if (Objects.isNull(response)) {
            throw new JiangSuOnlineClientException("could not validate null response");
        }
        if (response instanceof AbstractJiangSuOnlineResponse) {
            AbstractJiangSuOnlineResponse abstractResponse = (AbstractJiangSuOnlineResponse) response;
            if (!Objects.equals(abstractResponse.getReturnCode(), 1)) {
                throw new JiangSuOnlineClientException("[ " + abstractResponse.getReturnCode() + " ] " + abstractResponse.getReturnMsg());
            }
        }
        return response;
    }
}
