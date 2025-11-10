package work.gaigeshen.tripartite.nanjing.procurement.openapi.exception;

import work.gaigeshen.tripartite.nanjing.procurement.openapi.accesstoken.NanJingProcurementAccessToken;

/**
 * 刷新访问令牌异常
 *
 * @author gaigeshen
 */
public class NanJingProcurementAccessTokenRefreshException extends NanJingProcurementAccessTokenUpdateException {

    public NanJingProcurementAccessTokenRefreshException(String message) {
        super(message);
    }

    public NanJingProcurementAccessTokenRefreshException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public NanJingProcurementAccessTokenRefreshException setCurrentAccessToken(NanJingProcurementAccessToken currentAccessToken) {
        super.setCurrentAccessToken(currentAccessToken);
        return this;
    }

    @Override
    public NanJingProcurementAccessTokenRefreshException setCanRetry(boolean canRetry) {
        super.setCanRetry(canRetry);
        return this;
    }
}
