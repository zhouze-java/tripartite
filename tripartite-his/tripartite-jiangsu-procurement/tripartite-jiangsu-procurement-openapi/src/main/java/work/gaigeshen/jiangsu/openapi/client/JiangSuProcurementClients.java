package work.gaigeshen.jiangsu.openapi.client;


import work.gaigeshen.jiangsu.openapi.config.JiangSuProcurementConfig;
import work.gaigeshen.jiangsu.openapi.exception.JiangSuProcurementClientCreationException;
import work.gaigeshen.jiangsu.openapi.exception.JiangSuProcurementClientNotFoundException;

import java.util.function.Predicate;

/**
 * @author gaigeshen
 */
public interface JiangSuProcurementClients {

    default JiangSuProcurementBasicClient getClient() throws JiangSuProcurementClientNotFoundException {
        return getClient(cfg -> true);
    }

    JiangSuProcurementBasicClient getClient(Predicate<JiangSuProcurementConfig> predicate)
            throws JiangSuProcurementClientNotFoundException;

    JiangSuProcurementBasicClient getClientOrCreate(JiangSuProcurementConfig config)
            throws JiangSuProcurementClientCreationException;
}
