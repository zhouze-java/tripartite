package work.gaigeshen.tripartite.nanjing.procurement.openapi.accesstoken;

import work.gaigeshen.tripartite.core.util.ArgumentValidate;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.config.NanJingProcurementConfig;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.exception.NanJingProcurementAccessTokenStoreException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 访问令牌存储器默认的实现，采用简单的哈希存储
 *
 * @author gaigeshen
 */
public class DefaultNanJingProcurementAccessTokenStore implements NanJingProcurementAccessTokenStore {

    private final Map<NanJingProcurementConfig, NanJingProcurementAccessToken> internalStore = new ConcurrentHashMap<>();

    @Override
    public boolean save(NanJingProcurementConfig config, NanJingProcurementAccessToken accessToken) throws NanJingProcurementAccessTokenStoreException {
        ArgumentValidate.notNull(config, "config cannot be null");
        ArgumentValidate.notNull(accessToken, "accessToken cannot be null");
        boolean notExists = !internalStore.containsKey(config);
        internalStore.replaceAll((key, value) -> accessToken);
        internalStore.put(config, accessToken);
        return notExists;
    }

    @Override
    public void delete(NanJingProcurementConfig config) throws NanJingProcurementAccessTokenStoreException {
        ArgumentValidate.notNull(config, "config cannot be null");
        internalStore.remove(config);
    }

    @Override
    public NanJingProcurementAccessToken find(NanJingProcurementConfig config) throws NanJingProcurementAccessTokenStoreException {
        ArgumentValidate.notNull(config, "config cannot be null");
        return internalStore.get(config);
    }

    @Override
    public Map<NanJingProcurementConfig, NanJingProcurementAccessToken> findAll() throws NanJingProcurementAccessTokenStoreException {
        return new HashMap<>(internalStore);
    }
}
