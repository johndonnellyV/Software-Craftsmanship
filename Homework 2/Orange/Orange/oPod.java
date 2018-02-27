package Orange;

import Orange.SerialNumber;
import java.math.BigInteger;
import java.util.*;
/**
 * Created by Jack Donnelly
 */
public class oPod extends AbstractProduct {

    private ProductType type = ProductType.OPOD;

    /**
     * constructor
     * @param serialNumber input serial #
     * @param description input description
     */
    oPod(SerialNumber serialNumber, Optional<Set<String>> description) {super(serialNumber, description);}

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
        int bitTest = 3;
        boolean third = serialNumber.testBit(bitTest);
        boolean even = serialNumber.isEven();
        boolean answer = (!third && even);
        return answer;
    }

    public void process(Exchange request, RequestStatus status) throws ProductException{
        throw new ProductException(ProductType.OPOD, serialNumber, ProductException.ErrorCode.UNSUPPORTED_OPERATION);
    }

    public void process(Refund request, RequestStatus status) throws ProductException{
        throw new ProductException(ProductType.OPOD, serialNumber, ProductException.ErrorCode.UNSUPPORTED_OPERATION);

    }
}
