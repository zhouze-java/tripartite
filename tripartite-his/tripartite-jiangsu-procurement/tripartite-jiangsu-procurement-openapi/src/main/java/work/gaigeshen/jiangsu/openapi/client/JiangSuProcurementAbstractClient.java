package work.gaigeshen.jiangsu.openapi.client;

import work.gaigeshen.jiangsu.openapi.config.JiangSuProcurementConfig;
import work.gaigeshen.jiangsu.openapi.exception.JiangSuProcurementClientException;
import work.gaigeshen.jiangsu.openapi.parameters.DefaultJiangSuProcurementAbstractParameters;
import work.gaigeshen.jiangsu.openapi.response.AbstractJiangSuProcurementResponse;
import work.gaigeshen.jiangsu.openapi.response.JiangSuProcurementResponse;
import work.gaigeshen.tripartite.core.WebException;
import work.gaigeshen.tripartite.core.WebExecutor;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;

import java.util.Objects;

/**
 * @author gaigeshen
 */
public abstract class JiangSuProcurementAbstractClient implements JiangSuProcurementBasicClient {

    private final JiangSuProcurementConfig config;

    private final WebExecutor executor;


    protected JiangSuProcurementAbstractClient(JiangSuProcurementConfig config, WebExecutor executor) {
        ArgumentValidate.notNull(config, "config cannot be null");
        ArgumentValidate.notNull(executor, "executor cannot be null");
        this.config = config;
        this.executor = executor;
    }

    public JiangSuProcurementConfig getJiangSuProcurementConfig() {
        return config;
    }

    @Override
    public <R extends JiangSuProcurementResponse> R execute(DefaultJiangSuProcurementAbstractParameters parameters, Class<R> responseClass, String uri) throws JiangSuProcurementClientException {
        ArgumentValidate.notNull(parameters, "parameters cannot be null");
        ArgumentValidate.notNull(responseClass, "responseClass cannot be null");
        ArgumentValidate.notNull(uri, "uri cannot be null");
        try {
            R response = executor.execute(config.getServerHost() + uri, parameters, responseClass);
            return validateResponse(response);
        } catch (WebException e) {
            throw new JiangSuProcurementClientException(e.getMessage(), e);
        }
    }

    protected <R extends JiangSuProcurementResponse> R validateResponse(R response) throws JiangSuProcurementClientException {
        if (Objects.isNull(response)) {
            throw new JiangSuProcurementClientException("could not validate null response");
        }
        if (response instanceof AbstractJiangSuProcurementResponse) {
            AbstractJiangSuProcurementResponse abstractResponse = (AbstractJiangSuProcurementResponse) response;
            if (!Objects.equals(abstractResponse.getReturnCode(), 1)) {
                throw new JiangSuProcurementClientException("[ " + abstractResponse.getReturnCode() + " ] " + abstractResponse.getReturnMsg());
            }
        }
        return response;
    }
}
