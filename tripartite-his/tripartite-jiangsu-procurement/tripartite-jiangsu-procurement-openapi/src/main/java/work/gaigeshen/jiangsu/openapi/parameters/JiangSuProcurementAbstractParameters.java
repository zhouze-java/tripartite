package work.gaigeshen.jiangsu.openapi.parameters;

import work.gaigeshen.tripartite.core.parameter.converter.JsonParametersConverter;
import work.gaigeshen.tripartite.core.parameter.converter.Parameters;

/**
 * @author zhouze
 */
@Parameters(
        converter = JsonParametersConverter.class,
        customizer = JiangSuProcurementParametersCustomizer.class
)
public abstract class JiangSuProcurementAbstractParameters {

}
