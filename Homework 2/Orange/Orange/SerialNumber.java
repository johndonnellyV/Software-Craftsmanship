package Orange;

import java.math.BigInteger;

/**
 * Created by Jack Donnelly
 */
public class SerialNumber implements Comparable<SerialNumber>{

    private BigInteger serialNumber;

    SerialNumber(BigInteger serialNumber) {
        this.serialNumber = serialNumber;

    }

    /**
     * Returns the serial number that was stored
     * @return the serial number
     */
    public BigInteger getSerialNumber() {
        return this.serialNumber;
    }
    public BigInteger gcd(SerialNumber input) {
        BigInteger inputserialnumb = input.getSerialNumber();
        BigInteger thisnumb = this.getSerialNumber();

        BigInteger result = thisnumb.gcd(inputserialnumb);
        return result;
    }

    /**
     *
     * @param input second serial number input
     * @return the result of the operation
     */
    public BigInteger mod(SerialNumber input) {
        BigInteger inputnumb = input.getSerialNumber();
        BigInteger thisnumb = this.getSerialNumber();

        BigInteger result = thisnumb.mod(inputnumb);
        return result;
    }

    /**
     *
     * @param bit bit #
     * @return if the bit is set its true
     */
    public boolean testBit(int bit) {
        BigInteger number = this.getSerialNumber(); //uses BigIntegers testbit method to check if
        return number.testBit(bit);                 //the bit is set to true
    }

    /**
     *  checks if it is even
     * @return true if its even
     */
    public boolean isEven() {
        return (!this.getSerialNumber().testBit(0));

    }

    /**
     * checks if its odd
     * @return true if its odd
     */
    public boolean isOdd() {

        return (this.getSerialNumber().testBit(0));
    }

    public int compareTo(SerialNumber input){
        if (this.getSerialNumber().equals(input.getSerialNumber())){
            return 0;
        }
        else if (this.getSerialNumber().compareTo(input.getSerialNumber()) > 0){
            return 1;
        }
        else{
            return -1;
        }
    }
}
