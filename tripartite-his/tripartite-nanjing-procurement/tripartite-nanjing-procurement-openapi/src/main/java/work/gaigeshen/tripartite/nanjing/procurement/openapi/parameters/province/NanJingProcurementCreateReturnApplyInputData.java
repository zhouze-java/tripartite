package work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters.province;

import lombok.Data;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.parameters.NanJingProcurementInputData;

import java.util.List;

/**
 * 【NJHH014】创建提交退货申请 入参
 */
@Data
public class NanJingProcurementCreateReturnApplyInputData implements NanJingProcurementInputData {

    /**
     * 医疗机构编码
     */
    private String medinsCode;

    /**
     * 退货申请列表
     */
    private List<ReturnItem> list;

    @Data
    public static class ReturnItem {

        /**
         * 配送明细编码
         */
        private String shpCode;

        /**
         * 退货数量
         */
        private Integer retnCnt;

        /**
         * 退货原因
         */
        private String medinsRetnRea;
    }
}