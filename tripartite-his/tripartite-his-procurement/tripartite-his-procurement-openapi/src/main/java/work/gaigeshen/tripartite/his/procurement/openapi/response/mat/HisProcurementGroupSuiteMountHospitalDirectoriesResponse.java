package work.gaigeshen.tripartite.his.procurement.openapi.response.mat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import work.gaigeshen.tripartite.his.procurement.openapi.response.AbstractHisProcurementResponse;

/**
 * @author zhouze
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class HisProcurementGroupSuiteMountHospitalDirectoriesResponse extends AbstractHisProcurementResponse {

    private String hospListId;

    private String prodSysName;

    private Integer checkSystem;

    private Integer isServ;

    private String jointSysType;

    private String tenditmId;

    private String tenditmName;

    private String prodSysType;

    private String prodSysNo;

    private String medicalDeviceRegistrant;

    private String dclaEntpName;

    private String delventpCode;

    private String delventpName;

}
