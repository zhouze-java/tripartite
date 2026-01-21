package work.gaigeshen.jiangsu.openapi.parameters.province;

import lombok.Data;
import work.gaigeshen.jiangsu.openapi.parameters.JiangSuProcurementParameters;

import java.util.List;

/**
 * @author zhouze
 */
@Data
public class JiangSuProcurementDirectoryUsedListParameter implements JiangSuProcurementParameters {

    /**
     * 当前页码
     * 必填，从 1 开始
     */
    private Integer currentPageNumber;

    /**
     * 医疗机构编号
     */
    private String hospitalId;

    /**
     * 医疗机构采购部门编号
     * 非必填，老系统冗余字段
     */
    private String departmentID;

    /**
     * 查询开始时间
     * 非必填，包含该时间点
     * 格式：yyyy-MM-dd HH:mm:ss
     */
    private String beginTime;

    /**
     * 查询结束时间
     * 非必填，不包含该时间点
     * 格式：yyyy-MM-dd HH:mm:ss
     */
    private String endTime;

    /**
     * 产品ID集合
     * goodsID 列表
     * 与时间区间条件二选一，不能同时为空
     */
    private List<String> list;
}
