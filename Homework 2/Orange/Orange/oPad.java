package Orange;
import Orange.AbstractProduct;
import Orange.ProductType;
import Orange.SerialNumber;
import java.util.*;

/**
 * Created by Jack Donnelly
 */
public class oPad extends AbstractProduct {
    private ProductType type = ProductType.OPAD;

    /**
     * constructor
     * @param serialNumber input serial #
     * @param description input description
     */
    oPad(SerialNumber serialNumber, Optional<Set<String>> description) {super(serialNumber, description);}

    /**
     * Returns the product type
     * @return
     */
    public ProductType getProductType() {return this.type;}

    /**
     * Checks that the serial number is valid
     * @param serialNumber
     * @return true if valid
     */
    public Boolean isValidSerialNumber(SerialNumber serialNumber){
        boolean third = serialNumber.testBit(3);
        boolean even = serialNumber.isEven();
        boolean answer = (third && even);
        return answer;
    }

    public void process(Exchange request, RequestStatus status) throws ProductException{
        throw new ProductException(ProductType.OPAD, serialNumber, ProductException.ErrorCode.UNSUPPORTED_OPERATION);
    }

    public void process(Refund request, RequestStatus status) throws ProductException{
        throw new ProductException(ProductType.OPAD, serialNumber, ProductException.ErrorCode.UNSUPPORTED_OPERATION);

    }
}
