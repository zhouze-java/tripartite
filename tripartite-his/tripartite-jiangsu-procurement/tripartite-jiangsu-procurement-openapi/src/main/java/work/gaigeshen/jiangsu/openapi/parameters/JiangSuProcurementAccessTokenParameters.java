package work.gaigeshen.jiangsu.openapi.parameters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhouze
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class JiangSuProcurementAccessTokenParameters extends JiangSuProcurementAbstractParameters {

    /**
     * 应用ID
     */
    private String appId;

    /**
     * 密钥
     */
    private String appSecret;

}
