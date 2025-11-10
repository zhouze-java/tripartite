package work.gaigeshen.tripartite.nanjing.procurement.openapi.exception;

/**
 * @author gaigeshen
 */
public class NanJingProcurementClientException extends RuntimeException {

    public NanJingProcurementClientException(String message) {
        super(message);
    }

    public NanJingProcurementClientException(String message, Throwable cause) {
        super(message, cause);
    }

}
