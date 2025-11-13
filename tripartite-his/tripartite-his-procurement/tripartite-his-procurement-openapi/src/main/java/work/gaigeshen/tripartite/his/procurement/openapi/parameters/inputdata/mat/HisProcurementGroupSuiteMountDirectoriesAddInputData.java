package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.mat;

import lombok.Data;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

/**
 * @author zhouze
 */
@Data
public class HisProcurementGroupSuiteMountDirectoriesAddInputData implements HisProcurementInputData {

    /**
     * 医疗机构编码
     */
    private String medinsCode;

    /**
     * 组套耗材统一编码
     */
    private String ZTmcsCode;
}
