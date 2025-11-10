package work.gaigeshen.tripartite.nanjing.procurement.openapi.client;

import work.gaigeshen.tripartite.core.WebException;
import work.gaigeshen.tripartite.core.WebExecutor;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.config.NanJingProcurementConfig;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.exception.NanJingProcurementClientException;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters.NanJingProcurementParameters;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.response.AbstractNanJingProcurementResponse;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.response.NanJingProcurementResponse;

import java.util.Objects;

/**
 * @author gaigeshen
 */
public abstract class NanJingProcurementAbstractClient implements NanJingProcurementBasicClient {

    private final NanJingProcurementConfig config;

    private final WebExecutor executor;

    protected NanJingProcurementAbstractClient(NanJingProcurementConfig config, WebExecutor executor) {
        ArgumentValidate.notNull(config, "config cannot be null");
        ArgumentValidate.notNull(executor, "executor cannot be null");
        this.config = config;
        this.executor = executor;
    }

    @Override
    public NanJingProcurementConfig getNanJingProcurementConfig() {
        return config;
    }

    @Override
    public <R extends NanJingProcurementResponse> R execute(NanJingProcurementParameters parameters, Class<R> responseClass, String uri)
            throws NanJingProcurementClientException {
        ArgumentValidate.notNull(parameters, "parameters cannot be null");
        ArgumentValidate.notNull(responseClass, "responseClass cannot be null");
        ArgumentValidate.notNull(uri, "uri cannot be null");
        try {
            R response = executor.execute(config.getServerHost() + uri, parameters, responseClass);
            return validateResponse(response);
        } catch (WebException e) {
            throw new NanJingProcurementClientException(e.getMessage(), e);
        }
    }

    protected <R extends NanJingProcurementResponse> R validateResponse(R response) throws NanJingProcurementClientException {
        if (Objects.isNull(response)) {
            throw new NanJingProcurementClientException("could not validate null response");
        }
        if (response instanceof AbstractNanJingProcurementResponse) {
            AbstractNanJingProcurementResponse abstractResponse = (AbstractNanJingProcurementResponse) response;
            if (!Objects.equals(abstractResponse.getReturnCode(), 0)) {
                throw new NanJingProcurementClientException("[ " + abstractResponse.getReturnCode() + " ] " + abstractResponse.getReturnMsg());
            }
        }
        return response;
    }
}
