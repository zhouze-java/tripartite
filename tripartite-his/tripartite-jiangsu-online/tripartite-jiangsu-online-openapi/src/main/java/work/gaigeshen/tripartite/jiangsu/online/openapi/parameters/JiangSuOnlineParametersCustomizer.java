package work.gaigeshen.tripartite.jiangsu.online.openapi.parameters;

import work.gaigeshen.tripartite.core.parameter.Parameters;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersCustomizer;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersCustomizingException;
import work.gaigeshen.tripartite.jiangsu.online.openapi.config.JiangSuOnlineConfig;

/**
 * @author gaigeshen
 */
public class JiangSuOnlineParametersCustomizer implements ParametersCustomizer {

    @Override
    public void beforeConvert(Object rawParameters, Object config) throws ParametersCustomizingException {

    }

    @Override
    public void customize(Parameters parameters, Object rawParameters, Object config) throws ParametersCustomizingException {

    }

    @Override
    public boolean supports(Object config) {
        return config instanceof JiangSuOnlineConfig;
    }
}
