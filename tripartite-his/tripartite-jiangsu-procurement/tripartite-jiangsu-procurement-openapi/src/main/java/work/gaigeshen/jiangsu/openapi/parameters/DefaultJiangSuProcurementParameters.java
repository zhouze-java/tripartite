package work.gaigeshen.jiangsu.openapi.parameters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import work.gaigeshen.tripartite.core.parameter.converter.Parameters;
import work.gaigeshen.tripartite.core.parameter.converter.RawFormJsonParametersConverter;

/**
 * @author zhouze
 */
@Getter
@Setter
@AllArgsConstructor
@Parameters(converter = RawFormJsonParametersConverter.class)
public class DefaultJiangSuProcurementParameters implements JiangSuProcurementParameters {

}
