package work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters;

import lombok.Getter;
import lombok.Setter;
import work.gaigeshen.tripartite.core.parameter.converter.JsonParametersConverter;
import work.gaigeshen.tripartite.core.parameter.converter.Parameters;
import work.gaigeshen.tripartite.core.parameter.typed.Parameter;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.config.NanJingProcurementConfig;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

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

    public DefaultNanJingProcurementParameters(NanJingProcurementConfig nanJingProcurementConfig, String interfaceCode, NanJingProcurementInputData inputData) {

        if (Objects.isNull(interfaceCode)) {
            throw new IllegalArgumentException("interfaceCode cannot be null");
        }
        if (Objects.isNull(inputData)) {
            throw new IllegalArgumentException("inputData cannot be null");
        }
        this.interfaceCode = interfaceCode;
        this.inputParameter = new InputParameter(inputData);

        this.fixmedinsCode = nanJingProcurementConfig.getHospitalCode();
        this.fixmedinsName = nanJingProcurementConfig.getHospitalName();

        requireDefaultValue(nanJingProcurementConfig);

        otherDefaultValue();
    }

    private void otherDefaultValue() {
        this.devNo = "";
        this.devSafeInfo = "";
        this.signType = "";
        this.signNo = "";
    }

    private void requireDefaultValue(NanJingProcurementConfig nanJingProcurementConfig) {


        this.msgId = MsgIdGenerator.generate(getFixmedinsCode());
        this.mdtrtareaAdmvs = "320118";
        this.insuplcAdmdvs = "320118";
        if (Objects.equals(nanJingProcurementConfig.getType(), NanJingProcurementConfig.PROVINCE_PURCHASE)) {
            this.recerSysCode = "DHCC";
        }
        if (Objects.equals(nanJingProcurementConfig.getType(), NanJingProcurementConfig.ONLINE_PURCHASE)) {
            this.recerSysCode = "UNKONW";
        }

        this.infver = "V1.0";
        this.opterType = "3";
        this.opter = "spd";
        this.opterName = "spd";
        this.infTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        this.fixmedinsSoftFcty = "医贝云服（杭州）科技有限公司";

        this.encType = "";

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




    public static class MsgIdGenerator {

        private static final AtomicInteger SEQ = new AtomicInteger(0);
        private static final int MAX_SEQ = 9999;
        private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");

        public static String generate(String orgCode) {
            if (orgCode == null || orgCode.length() != 12) {
                throw new IllegalArgumentException("机构编号必须为12位");
            }
            int seq = SEQ.getAndIncrement();
            if (seq > MAX_SEQ) {
                SEQ.set(0);
                seq = SEQ.getAndIncrement();
            }
            String time = FORMAT.format(new Date());
            return orgCode + time + String.format("%04d", seq);
        }
    }
}
