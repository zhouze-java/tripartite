package work.gaigeshen.tripartite.nanjing.procurement.openapi.response.province;

import lombok.Data;
import lombok.EqualsAndHashCode;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.response.AbstractNanJingProcurementResponse;

import java.util.Collection;

/**
 * @author zhouze
 * @date 2025/11/10 14:21
 * @description TODO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class NanJingProcurementDirectoryAddResponse extends AbstractNanJingProcurementResponse {

    /**
     * 返回结果页数
     */
    private Integer currentPageNumber;

    /**
     * 返回结果数量
     */
    private Integer totalPageCount;

    /**
     * 返回结果数量
     */
    private Integer totalRecordCount;

    /**
     * 返回结果集合
     */
    private Collection<Item> dataList;

    @Data
    public static class Item {

        /**
         * 挂网id
         */
        private String pubonInId;

        /**
         * 院内目录ID
         */
        private String hospListId;

    }
}
