package work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters;

import work.gaigeshen.tripartite.core.parameter.Parameters;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersCustomizer;
import work.gaigeshen.tripartite.core.parameter.converter.ParametersCustomizingException;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.config.NanJingProcurementConfig;

/**
 * @author gaigeshen
 */
public class NanJingProcurementParametersCustomizer implements ParametersCustomizer {

    @Override
    public void beforeConvert(Object rawParameters, Object config) throws ParametersCustomizingException {

    }

    @Override
    public void customize(Parameters parameters, Object rawParameters, Object config) throws ParametersCustomizingException {

    }

    @Override
    public boolean supports(Object config) {
        return config instanceof NanJingProcurementConfig;
    }
}
