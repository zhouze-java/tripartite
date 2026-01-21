package work.gaigeshen.jiangsu.openapi.parameters;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zhouze
 */
@Data
@AllArgsConstructor
public class JiangSuProcurementAccessTokenParameters implements JiangSuProcurementParameters {

    /**
     * 应用ID
     */
    private String appId;

    /**
     * 密钥
     */
    private String appSecret;

}
