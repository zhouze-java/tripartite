package work.gaigeshen.tripartite.jiangsu.online.openapi.client;

import work.gaigeshen.tripartite.core.util.ArgumentValidate;
import work.gaigeshen.tripartite.jiangsu.online.openapi.config.JiangSuOnlineConfig;
import work.gaigeshen.tripartite.jiangsu.online.openapi.exception.JiangSuOnlineClientCreationException;
import work.gaigeshen.tripartite.jiangsu.online.openapi.exception.JiangSuOnlineClientNotFoundException;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

/**
 * @author gaigeshen
 */
public class DefaultJiangSuOnlineClients implements JiangSuOnlineClients {

    private final Map<JiangSuOnlineConfig, JiangSuOnlineBasicClient> hisProcurementClients = new ConcurrentHashMap<>();

    private final JiangSuOnlineClientCreator jiangSuOnlineClientCreator;

    public DefaultJiangSuOnlineClients(
            Collection<JiangSuOnlineBasicClient> hisProcurementClients,
            JiangSuOnlineClientCreator jiangSuOnlineClientCreator) {
        ArgumentValidate.notNull(hisProcurementClients, "hisProcurementClients cannot be null");
        ArgumentValidate.notNull(jiangSuOnlineClientCreator, "hisProcurementClientCreator cannot be null");
        for (JiangSuOnlineBasicClient hisProcurementClient : hisProcurementClients) {
            this.hisProcurementClients.put(hisProcurementClient.getJiangSuOnlineConfig(), hisProcurementClient);
        }
        this.jiangSuOnlineClientCreator = jiangSuOnlineClientCreator;
    }

    @Override
    public JiangSuOnlineBasicClient getClient(Predicate<JiangSuOnlineConfig> predicate)
            throws JiangSuOnlineClientNotFoundException {
        ArgumentValidate.notNull(predicate, "predicate cannot be null");
        JiangSuOnlineBasicClient hisProcurementClient = findHisProcurementClient(predicate);
        if (Objects.isNull(hisProcurementClient)) {
            throw new JiangSuOnlineClientNotFoundException("could not find his procurement client");
        }
        return hisProcurementClient;
    }

    @Override
    public JiangSuOnlineBasicClient getClientOrCreate(JiangSuOnlineConfig config)
            throws JiangSuOnlineClientCreationException {
        ArgumentValidate.notNull(config, "config cannot be null");
        return hisProcurementClients.computeIfAbsent(config, jiangSuOnlineClientCreator::create);
    }

    private JiangSuOnlineBasicClient findHisProcurementClient(Predicate<JiangSuOnlineConfig> predicate) {
        ArgumentValidate.notNull(predicate, "predicate cannot be null");
        for (Map.Entry<JiangSuOnlineConfig, JiangSuOnlineBasicClient> entry : hisProcurementClients.entrySet()) {
            if (predicate.test(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }
}
