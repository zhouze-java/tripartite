package work.gaigeshen.tripartite.nanjing.procurement.openapi.client;

import work.gaigeshen.tripartite.core.util.ArgumentValidate;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.config.NanJingProcurementConfig;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.exception.NanJingProcurementClientCreationException;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.exception.NanJingProcurementClientNotFoundException;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

/**
 * @author gaigeshen
 */
public class DefaultNanJingProcurementClients implements NanJingProcurementClients {

    private final Map<NanJingProcurementConfig, NanJingProcurementBasicClient> hisProcurementClients = new ConcurrentHashMap<>();

    private final NanJingProcurementClientCreator nanJingProcurementClientCreator;

    public DefaultNanJingProcurementClients(
            Collection<NanJingProcurementBasicClient> hisProcurementClients,
            NanJingProcurementClientCreator nanJingProcurementClientCreator) {
        ArgumentValidate.notNull(hisProcurementClients, "hisProcurementClients cannot be null");
        ArgumentValidate.notNull(nanJingProcurementClientCreator, "hisProcurementClientCreator cannot be null");
        for (NanJingProcurementBasicClient hisProcurementClient : hisProcurementClients) {
            this.hisProcurementClients.put(hisProcurementClient.getNanJingProcurementConfig(), hisProcurementClient);
        }
        this.nanJingProcurementClientCreator = nanJingProcurementClientCreator;
    }

    @Override
    public NanJingProcurementBasicClient getClient(Predicate<NanJingProcurementConfig> predicate)
            throws NanJingProcurementClientNotFoundException {
        ArgumentValidate.notNull(predicate, "predicate cannot be null");
        NanJingProcurementBasicClient hisProcurementClient = findHisProcurementClient(predicate);
        if (Objects.isNull(hisProcurementClient)) {
            throw new NanJingProcurementClientNotFoundException("could not find his procurement client");
        }
        return hisProcurementClient;
    }

    @Override
    public NanJingProcurementBasicClient getClientOrCreate(NanJingProcurementConfig config)
            throws NanJingProcurementClientCreationException {
        ArgumentValidate.notNull(config, "config cannot be null");
        return hisProcurementClients.computeIfAbsent(config, nanJingProcurementClientCreator::create);
    }

    private NanJingProcurementBasicClient findHisProcurementClient(Predicate<NanJingProcurementConfig> predicate) {
        ArgumentValidate.notNull(predicate, "predicate cannot be null");
        for (Map.Entry<NanJingProcurementConfig, NanJingProcurementBasicClient> entry : hisProcurementClients.entrySet()) {
            if (predicate.test(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }
}
