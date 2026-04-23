package work.gaigeshen.jiangsu.openapi.parameters.province;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import work.gaigeshen.jiangsu.openapi.parameters.DefaultJiangSuProcurementParameters;

import java.util.List;
import java.math.BigDecimal;

/**
 * H010 入库 - 请求参数
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class JiangSuProcurementStorageAddParameter extends DefaultJiangSuProcurementParameters {

    /**
     * （非必填）医疗机构编号, 可传空
     */
    private String hospitalId;

    /**
     * （非必填）医疗机构采购部门编号, 老系统冗余字段可传空
     */
    @JsonProperty("departmentID")
    private String departmentId;

    /**
     * 入库明细列表
     */
    private List<StorageDetail> list;

    @Data
    public static class StorageDetail {
        /**
         * 配送明细编号
         */
        private String distributionSerialID;

        /**
         * 发票号
         */
        private String invoiceID;

        /**
         * 批号
         */
        private String batchRecordID;

        /**·
         */
        private Integer warehouseCount;
    }
}