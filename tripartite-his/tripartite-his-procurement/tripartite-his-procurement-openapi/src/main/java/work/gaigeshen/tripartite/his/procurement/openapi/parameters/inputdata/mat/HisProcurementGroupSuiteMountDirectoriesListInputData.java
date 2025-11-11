package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.mat;

import lombok.Data;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

import java.util.Date;

/**
 * @author zhouze
 * @date 2025/11/11 13:35
 * @description 组套挂网目录输入参数
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
