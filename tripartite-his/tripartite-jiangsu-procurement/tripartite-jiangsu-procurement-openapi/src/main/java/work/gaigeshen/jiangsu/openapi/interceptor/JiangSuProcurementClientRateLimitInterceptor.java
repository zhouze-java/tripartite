package work.gaigeshen.jiangsu.openapi.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import work.gaigeshen.tripartite.core.WebExecutionException;
import work.gaigeshen.tripartite.core.interceptor.InterceptingException;
import work.gaigeshen.tripartite.core.interceptor.Interceptor;
import work.gaigeshen.tripartite.core.ratelimiter.RateLimiterService;
import work.gaigeshen.tripartite.core.util.ArgumentValidate;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 江苏省采平台订单相关接口限流拦截器，对指定 URI 按路径各自维持每秒 1 次调用。
 * <p>
 * 实际请求 URL 带有平台前缀（如 /hcinterface/v1/hospitalInterface/order/add），因此按 path 是否包含接口后缀匹配。
 * </p>
 *
 * @author gaigeshen
 */
public class JiangSuProcurementClientRateLimitInterceptor implements Interceptor {

    private static final Logger log = LoggerFactory.getLogger(JiangSuProcurementClientRateLimitInterceptor.class);

    /**
     * 较长路径优先，避免未来扩展路径时的误匹配
     */
    private static final List<String> RATE_LIMITED_URIS = Collections.unmodifiableList(Arrays.asList(
            "/orderdetail/add",
            "/order/submit",
            "/order/add",
            "/storage/add"
    ));

    private final RateLimiterService rateLimiterService;

    public JiangSuProcurementClientRateLimitInterceptor(RateLimiterService rateLimiterService) {
        ArgumentValidate.notNull(rateLimiterService, "rateLimiterService cannot be null");
        this.rateLimiterService = rateLimiterService;
    }

    @Override
    public Response intercept(Request request, Chain chain) throws InterceptingException, WebExecutionException {
        String rateLimitKey = resolveRateLimitKey(request.url());
        if (rateLimitKey != null) {
            log.debug("acquiring rate limit permit for uri: {}, request url: {}", rateLimitKey, request.url());
            rateLimiterService.acquire(rateLimitKey);
        }
        return chain.intercept(request);
    }

    /**
     * 从完整请求 URL 中解析是否命中限流接口，命中则返回用于限流的 key（接口后缀）
     */
    static String resolveRateLimitKey(String url) {
        String path = resolvePath(url);
        for (String limitedUri : RATE_LIMITED_URIS) {
            if (path.contains(limitedUri)) {
                return limitedUri;
            }
        }
        return null;
    }

    static String resolvePath(String url) {
        ArgumentValidate.notNull(url, "url cannot be null");
        URI uri = URI.create(url);
        String path = uri.getPath();
        return path != null ? path : "";
    }
}
