package work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters.province;

import lombok.Data;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters.NanJingProcurementInputData;

import java.util.Collection;

/**
 * @author zhouze
 * @date 2025/11/10 14:21
 * @description TODO
 */
@Data
public class NanJingProcurementDirectoryAddInputData implements NanJingProcurementInputData {

    /**
     * 医疗机构编码
     */
    private String medinsCode;

    /**
     * 挂网结果ID集合
     */
    private Collection<String> list;

}
