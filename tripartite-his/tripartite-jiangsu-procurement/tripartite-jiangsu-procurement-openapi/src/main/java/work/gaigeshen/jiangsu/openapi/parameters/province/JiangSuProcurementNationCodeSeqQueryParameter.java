package work.gaigeshen.jiangsu.openapi.parameters.province;

import lombok.Data;
import lombok.EqualsAndHashCode;
import work.gaigeshen.jiangsu.openapi.parameters.DefaultJiangSuProcurementParameters;
import work.gaigeshen.jiangsu.openapi.parameters.JiangSuProcurementParameters;

/**
 * 国家医保编码流水号查询入参（H013）
 * 用于根据国家医保编码获取对应的流水号
 * 主要用于耗材订单明细下单前校验
 *
 * @author zhouze
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class JiangSuProcurementNationCodeSeqQueryParameter extends DefaultJiangSuProcurementParameters {


    /**
     * 页数
     */
    private Integer currentPageNUmber;

    /**
     * 医疗机构编号
     * 非必填，老系统冗余字段
     */
    private String hospitalId;

    /**
     * 产品ID
     */
    private String goodsId;

}