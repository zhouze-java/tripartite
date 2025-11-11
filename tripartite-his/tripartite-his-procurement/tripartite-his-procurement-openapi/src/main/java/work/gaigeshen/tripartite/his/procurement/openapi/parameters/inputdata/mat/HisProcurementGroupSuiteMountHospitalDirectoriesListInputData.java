package work.gaigeshen.tripartite.his.procurement.openapi.parameters.inputdata.mat;

import lombok.Data;
import work.gaigeshen.tripartite.his.procurement.openapi.parameters.HisProcurementInputData;

/**
 * @author zhouze
 * @date 2025/11/11 13:50
 * @description 院内组套目录输入参数
 */
@Data
public class HisProcurementGroupSuiteMountHospitalDirectoriesListInputData implements HisProcurementInputData {

    /**
     * 产品系统类型
     */
    private String prodSysType;

    /**
     * 关节系统类别
     */
    private String jointSysName;

    /**
     * 产品系统名称
     */
    private String prodSysName;

    /**
     * 是否中选系统
     */
    private String checkSystem;

    /**
     * 申报企业
     */
    private String dclaEntpName;

    /**
     * 医疗器械注册人
     */
    private String medicalDeviceRegistr;

    /**
     * 项目ID
     */
    private String tenditmId;
}
