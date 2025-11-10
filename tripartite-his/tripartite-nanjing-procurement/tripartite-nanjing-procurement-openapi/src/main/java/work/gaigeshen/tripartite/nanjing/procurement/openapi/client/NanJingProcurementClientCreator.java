package work.gaigeshen.tripartite.nanjing.procurement.openapi.client;

import work.gaigeshen.tripartite.nanjing.procurement.openapi.config.NanJingProcurementConfig;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.exception.NanJingProcurementClientCreationException;

/**
 * @author gaigeshen
 */
public interface NanJingProcurementClientCreator {

    NanJingProcurementBasicClient create(NanJingProcurementConfig config) throws NanJingProcurementClientCreationException;

}
