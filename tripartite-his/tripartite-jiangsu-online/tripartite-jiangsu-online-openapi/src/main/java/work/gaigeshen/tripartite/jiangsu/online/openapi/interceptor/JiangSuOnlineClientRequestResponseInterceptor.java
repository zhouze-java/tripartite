package work.gaigeshen.tripartite.jiangsu.online.openapi.interceptor;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import work.gaigeshen.tripartite.core.header.Headers;
import work.gaigeshen.tripartite.core.interceptor.AbstractInterceptor;
import work.gaigeshen.tripartite.core.interceptor.InterceptingException;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;
import work.gaigeshen.tripartite.core.util.json.JsonUtils;
import work.gaigeshen.tripartite.jiangsu.online.openapi.config.JiangSuOnlineConfig;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author gaigeshen
 */
public class JiangSuOnlineClientRequestResponseInterceptor extends AbstractInterceptor {

    private final Logger log = LoggerFactory.getLogger(JiangSuOnlineClientRequestResponseInterceptor.class);

    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
    private static final String ENCODING = StandardCharsets.UTF_8.name();
    private final JiangSuOnlineConfig jiangSuOnlineConfig;

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public JiangSuOnlineClientRequestResponseInterceptor(JiangSuOnlineConfig hisProcurementConfig) {
        ArgumentValidate.notNull(hisProcurementConfig, "hisProcurementConfig cannot be null");
        this.jiangSuOnlineConfig = hisProcurementConfig;
    }

    /**
     * 生成CSB签名
     *
     * @param apiAccessKey API访问密钥
     * @param apiName API名称
     * @param apiTimestamp 时间戳（毫秒级）
     * @param apiVersion API版本
     * @param secretKey 密钥
     * @return Base64编码的签名
     * @throws Exception 加密异常
     */
    public static String generateSignature(String apiAccessKey,
                                           String apiName,
                                           String apiTimestamp,
                                           String apiVersion,
                                           String secretKey) throws Exception {
        // 1. 构建参数字符串
        String paramString = buildParamString(apiAccessKey, apiName, apiTimestamp, apiVersion);

        // 2. 使用HMAC-SHA1加密
        byte[] hmacResult = hmacSHA1Encrypt(paramString, secretKey);

        // 3. Base64编码
        return Base64.getEncoder().encodeToString(hmacResult);
    }

    /**
     * 构建参数字符串
     * 格式：_api_access_key=xxx&_api_name=xxx&_api_timestamp=xxx&_api_version=xxx
     */
    private static String buildParamString(String apiAccessKey,
                                           String apiName,
                                           String apiTimestamp,
                                           String apiVersion) {
        return "_api_access_key=" + apiAccessKey +
                "&_api_name=" + apiName +
                "&_api_timestamp=" + apiTimestamp +
                "&_api_version=" + apiVersion;
    }

    /**
     * HMAC-SHA1加密
     */
    private static byte[] hmacSHA1Encrypt(String encryptText, String encryptKey) throws Exception {
        // 获取密钥字节数组
        byte[] keyBytes = encryptKey.getBytes(ENCODING);

        // 创建SecretKey
        SecretKey secretKey = new SecretKeySpec(keyBytes, HMAC_SHA1_ALGORITHM);

        // 初始化Mac实例
        Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
        mac.init(secretKey);

        // 执行加密
        byte[] textBytes = encryptText.getBytes(ENCODING);
        return mac.doFinal(textBytes);
    }

    @Override
    protected void updateRequest(Request request) throws InterceptingException {
        // 打印原始请求
        try {
            String apiAccessKey = jiangSuOnlineConfig.getApiAccessKey();
            String secretKey = jiangSuOnlineConfig.getSecretKey();
            String apiTimestamp = String.valueOf(System.currentTimeMillis());
            String apiName = "hssServives";
            String apiVersion = "1.0.0";

            String signature = generateSignature(apiAccessKey, apiName, apiTimestamp, apiVersion, secretKey);
            Headers headers = request.headers();
            headers.putValue("_api_timestamp", apiTimestamp);
            headers.putValue("_api_name", apiName);
            headers.putValue("_api_version", apiVersion);
            headers.putValue("_api_access_key", apiAccessKey);
            headers.putValue("_api_signature", signature);
            headers.putValue("Content-Type","application/json");
        } catch (Exception e) {
            throw new InterceptingException("update request signature error", e);
        }
        log.info("JIANGSU ONLINE REQUEST URI: {}", request.url());
        log.info("JIANGSU ONLINE REQUEST METHOD: {}", request.method());
        log.info("JIANGSU ONLINE REQUEST HEADERS: {}", request.headers());
        log.info("JIANGSU ONLINE REQUEST BODY: {}", new String(request.bodyBytes(), StandardCharsets.UTF_8));
    }

    @Override
    protected void validateResponse(Request request, Response response) throws InterceptingException {
        String rawResponse;
        try {
            rawResponse = response.bodyString(StandardCharsets.UTF_8);

            log.info("JIANGSU ONLINE RESPONSE BODY: {}", rawResponse);
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
