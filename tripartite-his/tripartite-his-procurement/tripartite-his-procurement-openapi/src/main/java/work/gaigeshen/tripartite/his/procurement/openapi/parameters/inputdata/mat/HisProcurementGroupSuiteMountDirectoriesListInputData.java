package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.mat;

import lombok.Data;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

import java.util.Date;

/**
 * @author zhouze
 */
@Data
public class HisProcurementGroupSuiteMountDirectoriesListInputData implements HisProcurementInputData {

    /**
     * 组套耗材统一编码
     */
    private String ZTmcsCode;

    /**
     * 组套产品名称
     */
    private String prodName;

    /**
     * 最后更新时间起
     */
    private Date strUpTime;

    /**
     * 最后更新时间止
     */
    private Date endUpTime;

}
