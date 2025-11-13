package work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters;

import lombok.Getter;
import lombok.Setter;
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
@Setter
public class DefaultNanJingProcurementParameters implements NanJingProcurementParameters {

    @Parameter(name = "infno")
    private final String interfaceCode;

    /**
     * 定点医药机构编号
     */
    @Parameter(name = "fixmedins_code")
    private String fixmedinsCode;

    /**
     * 定点医药机构名称
     */
    @Parameter(name = "fixmedins_name")
    private String fixmedinsName;

    /**
     * 定点医药机构厂商名称
     */
    @Parameter(name = "fixmedins_soft_fcty")
    private String fixmedinsSoftFcty;

    /**
     * 交易时间
     */
    @Parameter(name = "inf_time")
    private String infTime;

    /**
     * 版本号
     */
    @Parameter(name = "infver")
    private String infver;

    /**
     * 就医地医保区划
     */
    @Parameter(name = "insuplc_admdvs")
    private String insuplcAdmdvs;

    /**
     * 参保地区划
     */
    @Parameter(name = "mdtrtarea_admvs")
    private String mdtrtareaAdmvs;

    /**
     * 接收方系统代码
     */
    @Parameter(name = "recer_sys_code")
    private String recerSysCode;

    /**
     * 设备编号
     */
    @Parameter(name = "dev_no")
    private String devNo;

    /**
     * 设备安全信息
     */
    @Parameter(name = "dev_safe_info")
    private String devSafeInfo;

    /**
     * 加密方式
     */
    @Parameter(name = "enc_type")
    private String encType;

    /**
     * 消息ID
     */
    @Parameter(name = "msgid")
    private String msgId;

    /**
     * 经办人
     */
    @Parameter(name = "opter")
    private String opter;

    /**
     * 经办人名称
     */
    @Parameter(name = "opter_name")
    private String opterName;

    /**
     * 1-经办人；2-自助终端；3- 移动终端
     */
    @Parameter(name = "opter_type")
    private String opterType;

    /**
     * 交易签到流水号
     */
    @Parameter(name = "sign_no")
    private String signNo;

    /**
     * 加密方式
     */
    @Parameter(name = "signtype")
    private String signType;

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
