package Orange;

/**
 * Created by Jack Donnelly
 */
public class ProductException extends Exception{

    protected ProductType prodtype;
    //protected String prodname;
    protected SerialNumber serialnumb;
    protected ErrorCode errcode;

    ProductException(ProductType productType, SerialNumber serialNumber, ErrorCode errorCode){
        this.prodtype = productType;
        this.serialnumb = serialNumber;
        this.errcode = errorCode;
    }
    ProductType getProductType(){
        return this.prodtype;
    }

    public String getProductName(){
        return this.prodtype.getName();
    }

    public SerialNumber getSerialNumber(){
        return this.serialnumb;
    }

    public ErrorCode getErrorCode(){
        return this.errcode;
    }

    public enum ErrorCode {
        INVALID_SERIAL_NUMBER,
        INVALID_PRODUCT_TYPE,
        UNSUPPORTED_OPERATION;
    }
}
