package Orange;
import Orange.AbstractProduct;
import Orange.ProductType;
import Orange.SerialNumber;
import java.util.Optional;
import java.util.Set;
import java.math.BigInteger;

/**
 * Created by Jack Donnelly
 */
public class oPhone extends AbstractProduct {
    private ProductType type = ProductType.OPHONE;

    /**
     * constructor
     * @param serialNumber input serial #
     * @param description input description
     */
    oPhone(SerialNumber serialNumber, Optional<Set<String>> description) {super(serialNumber, description);}

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
        boolean greaterthancheck;
        String numberinput = "630";
        int lowerBound = 42;

        boolean odd = serialNumber.isOdd();
        int value = serialNumber.gcd(new SerialNumber(new BigInteger(numberinput))).intValue();
        greaterthancheck = (lowerBound < value);
        boolean answer = (greaterthancheck && odd);
        return answer;
    }

    public void process(Exchange request, RequestStatus status) throws ProductException{
        throw new ProductException(ProductType.OPHONE, serialNumber, ProductException.ErrorCode.UNSUPPORTED_OPERATION);
    }

    public void process(Refund request, RequestStatus status) throws ProductException{
        throw new ProductException(ProductType.OPHONE, serialNumber, ProductException.ErrorCode.UNSUPPORTED_OPERATION);

    }
}
