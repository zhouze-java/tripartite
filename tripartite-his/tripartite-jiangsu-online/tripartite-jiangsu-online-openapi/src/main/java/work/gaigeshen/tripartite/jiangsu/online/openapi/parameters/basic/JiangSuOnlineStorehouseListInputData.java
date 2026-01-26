package work.gaigeshen.tripartite.jiangsu.online.openapi.parameters.basic;

import lombok.Data;
import work.gaigeshen.tripartite.jiangsu.online.openapi.parameters.JiangSuOnlineInputData;

/**
 * @author gaigeshen
 */
@Data
public class JiangSuOnlineStorehouseListInputData implements JiangSuOnlineInputData {

    private String orgCode;

    private Integer current;

    private Integer size;

}
