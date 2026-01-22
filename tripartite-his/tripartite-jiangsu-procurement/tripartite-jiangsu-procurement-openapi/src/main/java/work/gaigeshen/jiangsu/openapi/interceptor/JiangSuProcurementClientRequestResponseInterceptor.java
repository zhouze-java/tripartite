package work.gaigeshen.jiangsu.openapi.interceptor;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import work.gaigeshen.jiangsu.openapi.config.JiangSuProcurementConfig;
import work.gaigeshen.tripartite.core.interceptor.AbstractInterceptor;
import work.gaigeshen.tripartite.core.interceptor.InterceptingException;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;
import work.gaigeshen.tripartite.core.util.json.JsonUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author gaigeshen
 */
public class JiangSuProcurementClientRequestResponseInterceptor extends AbstractInterceptor {

    private final Logger log = LoggerFactory.getLogger(JiangSuProcurementClientRequestResponseInterceptor.class);

    private final JiangSuProcurementConfig jiangSuProcurementConfig;

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public JiangSuProcurementClientRequestResponseInterceptor(JiangSuProcurementConfig hisProcurementConfig) {
        ArgumentValidate.notNull(hisProcurementConfig, "hisProcurementConfig cannot be null");
        this.jiangSuProcurementConfig = hisProcurementConfig;
    }

    @Override
    protected void updateRequest(Request request) throws InterceptingException {
        // 打印原始请求
        log.info("REQUEST URI: {}", request.url());
        log.info("REQUEST METHOD: {}", request.method());
        log.info("REQUEST HEADERS: {}", request.headers());
        log.info("REQUEST BODY: {}", new String(request.bodyBytes(), StandardCharsets.UTF_8));

        String bodyContent = new String(request.bodyBytes(), StandardCharsets.UTF_8);
        String decode = "";
        try {
            decode= URLDecoder.decode(bodyContent.replaceFirst("params=", ""), "utf-8");
            log.info("REQUEST BODY: {}", decode);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void validateResponse(Request request, Response response) throws InterceptingException {
        String rawResponse;
        try {
            rawResponse = response.bodyString(StandardCharsets.UTF_8);

            log.info("RESPONSE BODY: {}", rawResponse);
        } catch (IOException e) {
            throw new InterceptingException("could not read raw response", e);
        }
        Map<String, Object> decodedResponse = JsonUtils.decodeObject(rawResponse);

        // 处理令牌没有过期的情况
        if ("9".equals(String.valueOf(decodedResponse.get("returnCode")))) {
            decodedResponse.put("returnCode", "1");
        }

        String returnCode = String.valueOf(decodedResponse.get("returnCode"));
        String returnMsg = String.valueOf(decodedResponse.get("returnMsg"));
        if (!Objects.equals(returnCode, "1")) {
            throw new InterceptingException("省平台返回信息异常: " + returnMsg);
        }


        response.changeBody(JsonUtils.encode(decodedResponse));
    }
}
