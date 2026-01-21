package work.gaigeshen.jiangsu.openapi.exception;

import work.gaigeshen.jiangsu.openapi.accesstoken.JiangSuProcurementAccessToken;

/**
 * 刷新访问令牌异常
 *
 * @author gaigeshen
 */
public class JiangSuProcurementAccessTokenRefreshException extends JiangSuProcurementAccessTokenUpdateException {

    public JiangSuProcurementAccessTokenRefreshException(String message) {
        super(message);
    }

    public JiangSuProcurementAccessTokenRefreshException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public JiangSuProcurementAccessTokenRefreshException setCurrentAccessToken(JiangSuProcurementAccessToken currentAccessToken) {
        super.setCurrentAccessToken(currentAccessToken);
        return this;
    }

    @Override
    public JiangSuProcurementAccessTokenRefreshException setCanRetry(boolean canRetry) {
        super.setCanRetry(canRetry);
        return this;
    }
}
