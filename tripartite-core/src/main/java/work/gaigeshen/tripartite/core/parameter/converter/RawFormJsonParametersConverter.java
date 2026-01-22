package work.gaigeshen.tripartite.core.parameter.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import work.gaigeshen.tripartite.core.parameter.DefaultParameters;
import work.gaigeshen.tripartite.core.parameter.Parameters;
import work.gaigeshen.tripartite.core.parameter.typed.StringParameter;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;

/**
 * @author zhouze
 */
public class RawFormJsonParametersConverter implements ParametersConverter {

    public static final RawFormJsonParametersConverter INSTANCE = new RawFormJsonParametersConverter();

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public Parameters convert(Object parameters) throws ParametersConversionException {
        ArgumentValidate.notNull(parameters, "parameters cannot be null");
        try {
            String json = objectMapper.writeValueAsString(parameters);
            Parameters p = new DefaultParameters(Parameters.Type.RAW_FORM_JSON);
            p.put(new StringParameter("params", json));
            return p;
        } catch (JsonProcessingException e) {
            throw new ParametersConversionException("convert raw form json failed", e);
        }
    }
}
