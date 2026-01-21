package work.gaigeshen.jiangsu.openapi.client;

import work.gaigeshen.jiangsu.openapi.config.JiangSuProcurementConfig;
import work.gaigeshen.jiangsu.openapi.exception.JiangSuProcurementClientCreationException;
import work.gaigeshen.jiangsu.openapi.exception.JiangSuProcurementClientNotFoundException;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

/**
 * @author gaigeshen
 */
public class DefaultJiangSuProcurementClients implements JiangSuProcurementClients {

    private final Map<JiangSuProcurementConfig, JiangSuProcurementBasicClient> hisProcurementClients = new ConcurrentHashMap<>();

    private final JiangSuProcurementClientCreator jiangSuProcurementClientCreator;

    public DefaultJiangSuProcurementClients(
            Collection<JiangSuProcurementBasicClient> hisProcurementClients,
            JiangSuProcurementClientCreator jiangSuProcurementClientCreator) {
        ArgumentValidate.notNull(hisProcurementClients, "hisProcurementClients cannot be null");
        ArgumentValidate.notNull(jiangSuProcurementClientCreator, "hisProcurementClientCreator cannot be null");
        for (JiangSuProcurementBasicClient hisProcurementClient : hisProcurementClients) {
            this.hisProcurementClients.put(hisProcurementClient.getJiangSuProcurementConfig(), hisProcurementClient);
        }
        this.jiangSuProcurementClientCreator = jiangSuProcurementClientCreator;
    }

    @Override
    public JiangSuProcurementBasicClient getClient(Predicate<JiangSuProcurementConfig> predicate)
            throws JiangSuProcurementClientNotFoundException {
        ArgumentValidate.notNull(predicate, "predicate cannot be null");
        JiangSuProcurementBasicClient hisProcurementClient = findHisProcurementClient(predicate);
        if (Objects.isNull(hisProcurementClient)) {
            throw new JiangSuProcurementClientNotFoundException("could not find his procurement client");
        }
        return hisProcurementClient;
    }

    @Override
    public JiangSuProcurementBasicClient getClientOrCreate(JiangSuProcurementConfig config)
            throws JiangSuProcurementClientCreationException {
        ArgumentValidate.notNull(config, "config cannot be null");
        return hisProcurementClients.computeIfAbsent(config, jiangSuProcurementClientCreator::create);
    }

    private JiangSuProcurementBasicClient findHisProcurementClient(Predicate<JiangSuProcurementConfig> predicate) {
        ArgumentValidate.notNull(predicate, "predicate cannot be null");
        for (Map.Entry<JiangSuProcurementConfig, JiangSuProcurementBasicClient> entry : hisProcurementClients.entrySet()) {
            if (predicate.test(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }
}
