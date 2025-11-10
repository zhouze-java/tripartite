package work.gaigeshen.tripartite.nanjing.procurement.openapi.client;

import work.gaigeshen.tripartite.nanjing.procurement.openapi.config.NanJingProcurementConfig;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.exception.NanJingProcurementClientCreationException;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.exception.NanJingProcurementClientNotFoundException;

import java.util.function.Predicate;

/**
 * @author gaigeshen
 */
public interface NanJingProcurementClients {

    default NanJingProcurementBasicClient getClient() throws NanJingProcurementClientNotFoundException {
        return getClient(cfg -> true);
    }

    NanJingProcurementBasicClient getClient(Predicate<NanJingProcurementConfig> predicate)
            throws NanJingProcurementClientNotFoundException;

    NanJingProcurementBasicClient getClientOrCreate(NanJingProcurementConfig config)
            throws NanJingProcurementClientCreationException;
}
