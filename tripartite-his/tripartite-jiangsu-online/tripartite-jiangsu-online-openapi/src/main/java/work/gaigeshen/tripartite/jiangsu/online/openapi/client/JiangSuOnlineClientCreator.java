package work.gaigeshen.tripartite.jiangsu.online.openapi.client;

import work.gaigeshen.tripartite.jiangsu.online.openapi.config.JiangSuOnlineConfig;
import work.gaigeshen.tripartite.jiangsu.online.openapi.exception.JiangSuOnlineClientCreationException;

/**
 * @author gaigeshen
 */
public interface JiangSuOnlineClientCreator {

    JiangSuOnlineBasicClient create(JiangSuOnlineConfig config) throws JiangSuOnlineClientCreationException;

}
