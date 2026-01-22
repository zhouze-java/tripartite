package work.gaigeshen.tripartite.jiangsu.online.openapi.exception;

import work.gaigeshen.tripartite.jiangsu.online.openapi.accesstoken.JiangSuOnlineAccessToken;

/**
 * 刷新访问令牌异常
 *
 * @author gaigeshen
 */
public class JiangSuOnlineAccessTokenRefreshException extends JiangSuOnlineAccessTokenUpdateException {

    public JiangSuOnlineAccessTokenRefreshException(String message) {
        super(message);
    }

    public JiangSuOnlineAccessTokenRefreshException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public JiangSuOnlineAccessTokenRefreshException setCurrentAccessToken(JiangSuOnlineAccessToken currentAccessToken) {
        super.setCurrentAccessToken(currentAccessToken);
        return this;
    }

    @Override
    public JiangSuOnlineAccessTokenRefreshException setCanRetry(boolean canRetry) {
        super.setCanRetry(canRetry);
        return this;
    }
}
