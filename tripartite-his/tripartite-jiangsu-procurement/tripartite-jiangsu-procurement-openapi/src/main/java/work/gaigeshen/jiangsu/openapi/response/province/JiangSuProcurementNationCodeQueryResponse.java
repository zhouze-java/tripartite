package work.gaigeshen.jiangsu.openapi.response.province;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;
import work.gaigeshen.jiangsu.openapi.response.AbstractJiangSuProcurementResponse;

/**
 * 获取国家编码返回结果（H013）
 *
 * @author zhouze
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class JiangSuProcurementNationCodeQueryResponse extends AbstractJiangSuProcurementResponse {

    /**
     * 国家编码信息列表
     * 一个产品可能对应多条规格/型号
     */
    private List<NationCodeItem> successList;

    /**
     * 当日接口剩余访问次数
     */
    private Integer todayRemainVisitCount;

    /**
     * 当前页数
     */
    private Integer currentPageNumber;

    /**
     * 总记录数
     */
    private Integer totalPageCount;

    /**
     * 总行数
     */
    private Integer totalRecordCount;

    /**
     * 国家编码明细
     */
    @Data
    public static class NationCodeItem {

        /**
         * 国家医保编码
         * 27 位国家统一编码
         */
        private String countryCode;

        /**
         * 国家编码流水号
         * H006 下单必填
         */
        private String codeSeq;

        /**
         * 产品规格
         * H006 的 outlookc
         */
        private String outlookc;

        /**
         * 产品型号
         * H006 的 goodsType
         */
        private String goodsType;
    }
}