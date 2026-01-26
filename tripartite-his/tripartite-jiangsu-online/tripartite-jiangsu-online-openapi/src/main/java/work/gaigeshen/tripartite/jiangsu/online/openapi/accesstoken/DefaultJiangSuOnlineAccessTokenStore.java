package work.gaigeshen.tripartite.jiangsu.online.openapi.accesstoken;

import work.gaigeshen.tripartite.core.util.ArgumentValidate;
import work.gaigeshen.tripartite.jiangsu.online.openapi.config.JiangSuOnlineConfig;
import work.gaigeshen.tripartite.jiangsu.online.openapi.exception.JiangSuOnlineAccessTokenStoreException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 访问令牌存储器默认的实现，采用简单的哈希存储
 *
 * @author gaigeshen
 */
public class DefaultJiangSuOnlineAccessTokenStore implements JiangSuOnlineAccessTokenStore {

    private final Map<JiangSuOnlineConfig, JiangSuOnlineAccessToken> internalStore = new ConcurrentHashMap<>();

    @Override
    public boolean save(JiangSuOnlineConfig config, JiangSuOnlineAccessToken accessToken) throws JiangSuOnlineAccessTokenStoreException {
        ArgumentValidate.notNull(config, "config cannot be null");
        ArgumentValidate.notNull(accessToken, "accessToken cannot be null");
        return Objects.isNull(internalStore.put(config, accessToken));
    }

    @Override
    public void delete(JiangSuOnlineConfig config) throws JiangSuOnlineAccessTokenStoreException {
        ArgumentValidate.notNull(config, "config cannot be null");
        internalStore.remove(config);
    }

    @Override
    public JiangSuOnlineAccessToken find(JiangSuOnlineConfig config) throws JiangSuOnlineAccessTokenStoreException {
        ArgumentValidate.notNull(config, "config cannot be null");
        return internalStore.get(config);
    }

    @Override
    public Map<JiangSuOnlineConfig, JiangSuOnlineAccessToken> findAll() throws JiangSuOnlineAccessTokenStoreException {
        return new HashMap<>(internalStore);
    }
}
