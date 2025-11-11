package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.mat;

import lombok.Data;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

/**
 * @author zhouze
 * @date 2025/11/11 13:57
 * @description 勾选院内组套目录参数
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
