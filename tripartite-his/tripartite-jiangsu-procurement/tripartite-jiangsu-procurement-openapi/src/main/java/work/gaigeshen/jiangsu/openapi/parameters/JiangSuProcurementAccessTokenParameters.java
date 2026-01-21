package work.gaigeshen.jiangsu.openapi.parameters;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zhouze
 * @date 2026/1/20 14:08
 * @description 江苏省采获取token
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
