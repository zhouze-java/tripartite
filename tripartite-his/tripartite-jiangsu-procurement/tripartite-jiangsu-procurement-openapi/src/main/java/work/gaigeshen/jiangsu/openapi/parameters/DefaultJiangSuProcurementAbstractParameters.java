package work.gaigeshen.jiangsu.openapi.parameters;

import lombok.Getter;
import lombok.Setter;
import work.gaigeshen.tripartite.core.parameter.converter.JsonParametersConverter;
import work.gaigeshen.tripartite.core.parameter.converter.Parameters;

/**
 * @author zhouze
 */
@Parameters(
        converter = JsonParametersConverter.class,
        customizer = JiangSuProcurementParametersCustomizer.class
)
@Getter
@Setter
public class DefaultJiangSuProcurementAbstractParameters {

}
