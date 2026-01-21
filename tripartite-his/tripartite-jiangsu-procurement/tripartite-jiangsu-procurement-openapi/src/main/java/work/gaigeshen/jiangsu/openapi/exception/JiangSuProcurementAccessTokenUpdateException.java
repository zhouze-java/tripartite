package work.gaigeshen.jiangsu.openapi.exception;

import work.gaigeshen.jiangsu.openapi.accesstoken.JiangSuProcurementAccessToken;

import java.util.Objects;

/**
 * 访问令牌更新异常
 *
 * @author gaigeshen
 */
public class JiangSuProcurementAccessTokenUpdateException extends JiangSuProcurementAccessTokenException {

    private JiangSuProcurementAccessToken currentAccessToken;

    private boolean canRetry = false;

    public JiangSuProcurementAccessTokenUpdateException(String message) {
        super(message);
    }

    public JiangSuProcurementAccessTokenUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 设置当前的访问令牌，可以视同为更新失败的时候当前的访问令牌
     *
     * @param currentAccessToken 当前的访问令牌
     * @return 返回当前的异常
     */
    public JiangSuProcurementAccessTokenUpdateException setCurrentAccessToken(JiangSuProcurementAccessToken currentAccessToken) {
        this.currentAccessToken = currentAccessToken;
        return this;
    }

    /**
     * 设置是否能够重试
     *
     * @param canRetry 是否能够重试
     * @return 返回当前的异常
     */
    public JiangSuProcurementAccessTokenUpdateException setCanRetry(boolean canRetry) {
        this.canRetry = canRetry;
        return this;
    }

    /**
     * 返回当前的访问令牌
     *
     * @return 当前的访问令牌
     */
    public JiangSuProcurementAccessToken getCurrentAccessToken() {
        return currentAccessToken;
    }

    /**
     * 返回是否存在当前的访问令牌
     *
     * @return 是否存在当前的访问令牌
     */
    public boolean hasCurrentAccessToken() {
        return Objects.nonNull(currentAccessToken);
    }

    /**
     * 返回是否能够重试
     *
     * @return 是否能够重试
     */
    public boolean isCanRetry() {
        return canRetry;
    }
}
