package work.gaigeshen.tripartite.jiangsu.online.openapi.interceptor;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import work.gaigeshen.tripartite.core.interceptor.AbstractInterceptor;
import work.gaigeshen.tripartite.core.interceptor.InterceptingException;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;
import work.gaigeshen.tripartite.core.util.json.JsonUtils;
import work.gaigeshen.tripartite.jiangsu.online.openapi.config.JiangSuOnlineConfig;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author gaigeshen
 */
public class JiangSuOnlineClientRequestResponseInterceptor extends AbstractInterceptor {

    private final Logger log = LoggerFactory.getLogger(JiangSuOnlineClientRequestResponseInterceptor.class);

    private final JiangSuOnlineConfig jiangSuOnlineConfig;

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public JiangSuOnlineClientRequestResponseInterceptor(JiangSuOnlineConfig hisProcurementConfig) {
        ArgumentValidate.notNull(hisProcurementConfig, "hisProcurementConfig cannot be null");
        this.jiangSuOnlineConfig = hisProcurementConfig;
    }

    @Override
    protected void updateRequest(Request request) throws InterceptingException {
        // 打印原始请求
        log.info("REQUEST URI: {}", request.url());
        log.info("REQUEST METHOD: {}", request.method());
        log.info("REQUEST HEADERS: {}", request.headers());
        log.info("REQUEST BODY: {}", new String(request.bodyBytes(), StandardCharsets.UTF_8));
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
        String infcode = (String) decodedResponse.get("infcode");
        if (!Objects.equals("0", infcode)) {
            throw new InterceptingException(rawResponse);
        }
        Map<?, ?> output = (Map<?, ?>) decodedResponse.get("output");
        if (Objects.isNull(output) || !output.containsKey("data")) {
            throw new InterceptingException("response output or output data not found: " + rawResponse);
        }
        Map<?, ?> dataRaw = (Map<?, ?>) output.get("data");
        Map<String, Object> data = new HashMap<>();
        for (Map.Entry<?, ?> entry : dataRaw.entrySet()) {
            if (entry.getKey() instanceof String) {
                data.put((String) entry.getKey(), entry.getValue());
            }
        }

        // 处理令牌没有过期的情况
        if ("9".equals(String.valueOf(data.get("return_code")))) {
            data.put("return_code", "1");
        }

        response.changeBody(JsonUtils.encode(data));
    }
}
