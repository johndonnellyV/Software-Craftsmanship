package Orange;

/**
 * Created by John Donnelly
 */
public class RequestException extends Exception{

    protected Product product;
    protected RequestStatus status;
    protected ErrorType errorType;

    /**
     * creates a Request Exception object
     * @param product input product
     * @param status input requestStatus
     * @param errorType input ErrorType
     */
    RequestException(Product product, RequestStatus status, ErrorType errorType){
        this.product = product;
        this.status = status;
        this.errorType = errorType;
    }

    Product getProduct(){
        return this.product;
    }
    RequestStatus getStatusRequest(){
        return this.status;
    }

    ErrorType getErrorType(){
        return this.errorType;
    }

    /**
     * Enum ErrorType represents the type of Exception
     */
    public enum ErrorType{
        INVALID_REQUEST,
        INVALID_EXCHANGE,
        INVALID_REFUND;
    }

}
