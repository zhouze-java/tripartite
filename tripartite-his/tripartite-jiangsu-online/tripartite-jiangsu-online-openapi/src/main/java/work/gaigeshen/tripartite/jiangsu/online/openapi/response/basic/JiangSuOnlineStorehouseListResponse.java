package work.gaigeshen.tripartite.jiangsu.online.openapi.response.basic;

import lombok.Data;
import lombok.EqualsAndHashCode;
import work.gaigeshen.tripartite.jiangsu.online.openapi.response.AbstractJiangSuOnlineResponse;

import java.util.Collection;

/**
 * @author gaigeshen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class JiangSuOnlineStorehouseListResponse extends AbstractJiangSuOnlineResponse {

    private Integer currentPageNumber;

    private Integer totalPageCount;

    private Integer totalRecordCount;

    private Collection<ListItem> dataList;

    @Data
    public static class ListItem {

        /**
         * 库房ID
         */
        private String addrId;

        /**
         * 机构代码
         */
        private String entpCode;

        /**
         * 省名称
         */
        private String prov;

        /**
         * 市名称
         */
        private String city;

        /**
         * 详细地址
         */
        private String addr;

        /**
         * 联系人名称
         */
        private String conerName;

        /**
         * 联系人电话
         */
        private String conerTel;

        /**
         * 库房收货地址
         */
        private String stroomName;

        /**
         * 是否默认地址
         */
        private String defFlag;

        /**
         * 是否默认耗材地址
         */
        private String mcsFlag;
    }
}
