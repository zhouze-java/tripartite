package work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters.province;

import lombok.Data;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters.NanJingProcurementInputData;

@Data
public class NanJingProcurementPurchasePlanSubmitInputData implements NanJingProcurementInputData {

    /**
     * 医疗机构代码（同 orgCode）
     */
    private String medinsCode;

    /**
     * 采购订单编号（由创建接口返回的 PurcCode 字段）
     */
    private String PurcCode;

    /**
     * 配送地址ID（默认配送地址ID）
     */
    private String addrId;

    /**
     * 操作状态（2：保存，4：发送）
     */
    private Integer chkStas;
}