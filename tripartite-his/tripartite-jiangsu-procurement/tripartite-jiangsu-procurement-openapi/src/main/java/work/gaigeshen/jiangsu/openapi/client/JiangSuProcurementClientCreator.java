package work.gaigeshen.jiangsu.openapi.client;

import work.gaigeshen.jiangsu.openapi.config.JiangSuProcurementConfig;
import work.gaigeshen.jiangsu.openapi.exception.JiangSuProcurementClientCreationException;

/**
 * @author gaigeshen
 */
public interface JiangSuProcurementClientCreator {

    JiangSuProcurementBasicClient create(JiangSuProcurementConfig config) throws JiangSuProcurementClientCreationException;

}
