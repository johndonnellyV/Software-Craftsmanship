package Orange;

import java.math.BigInteger;

/**
 * Created by John Donnelly
 */
public class RequestStatus {
    /**
     * Enum StatusCode represents the status code of the status
     */
    public enum StatusCode{
        UNKNOWN,
        OK,
        FAIL;
    }

    private StatusCode statusCode;
    private BigInteger result;

    /**
     * constructor for Request Status
     * sets the code to UNKNOWN by default
     */
    RequestStatus(){
        this.statusCode = StatusCode.UNKNOWN;
        result = null;
    }
    /*simple getters and setters*/
    public StatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public BigInteger getResult() {
        return result;
    }

    public void setResult(BigInteger result) {
        this.result = result;
    }
}
