package work.gaigeshen.tripartite.his.procurement.openapi.response.mat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import work.gaigeshen.tripartite.his.procurement.openapi.response.AbstractHisProcurementResponse;

import java.math.BigDecimal;

/**
 * @author zhouze
 * @date 2025/11/11 13:37
 * @description TODO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class HisProcurementGroupSuiteMountDirectoriesResponse extends AbstractHisProcurementResponse {

    private String mcsCode;

    private String prodName;

    private String mcsRegno;

    private String mcsRegcertName;

    private String prodSpec;

    private String mcsMol;

    private String zclassCode;

    private String zclassName;

    private String primDirectory;

    private String secondDirectory;

    private String prodentpCode;

    private String prodentpName;

    private String prxyEntpCode;

    private String prxyEntpName;

    private BigDecimal pubonlnPric;

    private String mcsType;

    private String origin;

    private String unt;

    private String pubonlnType;

    private String tenditmId;

    private String tenditmName;

    private String ZTmcsCode;

}
