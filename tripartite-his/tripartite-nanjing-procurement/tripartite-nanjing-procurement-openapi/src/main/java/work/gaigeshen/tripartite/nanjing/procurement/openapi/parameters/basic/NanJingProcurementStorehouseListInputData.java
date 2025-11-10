package work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters.basic;

import lombok.Data;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters.NanJingProcurementInputData;

/**
 * @author gaigeshen
 */
@Data
public class NanJingProcurementStorehouseListInputData implements NanJingProcurementInputData {

    private String orgCode;

    private Integer current;

    private Integer size;

}
