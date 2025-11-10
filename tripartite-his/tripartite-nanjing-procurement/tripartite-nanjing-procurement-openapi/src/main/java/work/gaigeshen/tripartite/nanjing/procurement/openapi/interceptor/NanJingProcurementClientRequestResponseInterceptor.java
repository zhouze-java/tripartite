package work.gaigeshen.tripartite.nanjing.procurement.openapi.interceptor;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import work.gaigeshen.tripartite.core.interceptor.AbstractInterceptor;
import work.gaigeshen.tripartite.core.interceptor.InterceptingException;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;
import work.gaigeshen.tripartite.core.util.json.JsonUtils;
import work.gaigeshen.tripartite.nanjing.procurement.openapi.config.NanJingProcurementConfig;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.util.Map;
import java.util.Objects;

/**
 * @author gaigeshen
 */
public class NanJingProcurementClientRequestResponseInterceptor extends AbstractInterceptor {

    private final NanJingProcurementConfig nanJingProcurementConfig;

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public NanJingProcurementClientRequestResponseInterceptor(NanJingProcurementConfig hisProcurementConfig) {
        ArgumentValidate.notNull(hisProcurementConfig, "hisProcurementConfig cannot be null");
        this.nanJingProcurementConfig = hisProcurementConfig;
    }

    @Override
    protected void updateRequest(Request request) throws InterceptingException {
        // long timestamp = System.currentTimeMillis() / 1000;
        // String bodyContent = new String(request.bodyBytes(), StandardCharsets.UTF_8);
        // String account = nanJingProcurementConfig.getAccount();
        // byte[] secretBytes = nanJingProcurementConfig.getSecret().getBytes(StandardCharsets.UTF_8);
        // try {
        //     SecretKey secretKey = new SecretKeySpec(secretBytes, "HmacSm3");
        //     Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        //     mac.init(secretKey);
        //     mac.update((timestamp + "\n").getBytes(StandardCharsets.UTF_8));
        //     mac.update(bodyContent.getBytes(StandardCharsets.UTF_8));
        //     byte[] digest = mac.doFinal();
        //     String digestResult = Hex.toHexString(digest).toLowerCase();
        //     Headers headers = request.headers();
        //     headers.putValue("x-ca-key", account);
        //     headers.putValue("x-ca-signature", timestamp + ":" + digestResult);
        // } catch (Exception e) {
        //     throw new InterceptingException("update request signature error", e);
        // }
    }

    @Override
    protected void validateResponse(Request request, Response response) throws InterceptingException {
        String rawResponse;
        try {
            rawResponse = response.bodyString(StandardCharsets.UTF_8);
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
        response.changeBody(JsonUtils.encode(output.get("data")));
    }
}
