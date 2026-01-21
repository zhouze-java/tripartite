package work.gaigeshen.jiangsu.openapi.client;


import work.gaigeshen.jiangsu.openapi.config.JiangSuProcurementConfig;

/**
 * 南京两定平台客户端，此客户端只针对网采类型
 *
 * @author gaigeshen
 */
public interface HisProcurementOnLineClient extends JiangSuProcurementBasicClient {

    JiangSuProcurementConfig getJiangSuProcurementConfig();

}
