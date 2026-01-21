package work.gaigeshen.jiangsu.openapi.accesstoken;

import work.gaigeshen.jiangsu.openapi.config.JiangSuProcurementConfig;
import work.gaigeshen.jiangsu.openapi.exception.JiangSuProcurementAccessTokenStoreException;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 访问令牌存储器默认的实现，采用简单的哈希存储
 *
 * @author gaigeshen
 */
public class DefaultJiangSuProcurementAccessTokenStore implements JiangSuProcurementAccessTokenStore {

    private final Map<JiangSuProcurementConfig, JiangSuProcurementAccessToken> internalStore = new ConcurrentHashMap<>();

    @Override
    public boolean save(JiangSuProcurementConfig config, JiangSuProcurementAccessToken accessToken) throws JiangSuProcurementAccessTokenStoreException {
        ArgumentValidate.notNull(config, "config cannot be null");
        ArgumentValidate.notNull(accessToken, "accessToken cannot be null");
        return Objects.isNull(internalStore.put(config, accessToken));
    }

    @Override
    public void delete(JiangSuProcurementConfig config) throws JiangSuProcurementAccessTokenStoreException {
        ArgumentValidate.notNull(config, "config cannot be null");
        internalStore.remove(config);
    }

    @Override
    public JiangSuProcurementAccessToken find(JiangSuProcurementConfig config) throws JiangSuProcurementAccessTokenStoreException {
        ArgumentValidate.notNull(config, "config cannot be null");
        return internalStore.get(config);
    }

    @Override
    public Map<JiangSuProcurementConfig, JiangSuProcurementAccessToken> findAll() throws JiangSuProcurementAccessTokenStoreException {
        return new HashMap<>(internalStore);
    }
}
