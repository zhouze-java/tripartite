package work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters;

import lombok.Getter;
import work.gaigeshen.tripartite.core.parameter.converter.JsonParametersConverter;
import work.gaigeshen.tripartite.core.parameter.converter.Parameters;
import work.gaigeshen.tripartite.core.parameter.typed.Parameter;

import java.util.Objects;

/**
 * @author gaigeshen
 */
@Parameters(
        converter = JsonParametersConverter.class,
        customizer = NanJingProcurementParametersCustomizer.class
)
@Getter
public class DefaultNanJingProcurementParameters implements NanJingProcurementParameters {

    @Parameter(name = "infno")
    private final String interfaceCode;


    @Parameter(name = "input")
    private final InputParameter inputParameter;

    public DefaultNanJingProcurementParameters(String interfaceCode, NanJingProcurementInputData inputData) {

        if (Objects.isNull(interfaceCode)) {
            throw new IllegalArgumentException("interfaceCode cannot be null");
        }
        if (Objects.isNull(inputData)) {
            throw new IllegalArgumentException("inputData cannot be null");
        }
        this.interfaceCode = interfaceCode;
        this.inputParameter = new InputParameter(inputData);
    }

    /**
     * @author gaigeshen
     */
    @Getter
    public static class InputParameter {

        @Parameter(name = "data")
        private final NanJingProcurementInputData inputData;

        public InputParameter(NanJingProcurementInputData inputData) {
            this.inputData = inputData;
        }

    }
}
