package work.gaigeshen.tripartite.jiangsu.online.openapi.client;

import work.gaigeshen.tripartite.jiangsu.online.openapi.config.JiangSuOnlineConfig;
import work.gaigeshen.tripartite.jiangsu.online.openapi.exception.JiangSuOnlineClientCreationException;
import work.gaigeshen.tripartite.jiangsu.online.openapi.exception.JiangSuOnlineClientNotFoundException;

import java.util.function.Predicate;

/**
 * @author gaigeshen
 */
public interface JiangSuOnlineClients {

    default JiangSuOnlineBasicClient getClient() throws JiangSuOnlineClientNotFoundException {
        return getClient(cfg -> true);
    }

    JiangSuOnlineBasicClient getClient(Predicate<JiangSuOnlineConfig> predicate)
            throws JiangSuOnlineClientNotFoundException;

    JiangSuOnlineBasicClient getClientOrCreate(JiangSuOnlineConfig config)
            throws JiangSuOnlineClientCreationException;
}
