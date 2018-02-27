package Orange;
import Orange.ProductException.ErrorCode;
import Orange.SerialNumber;

import java.lang.reflect.Constructor;
import java.util.*;
import java.math.BigInteger;
import java.util.stream.Stream;
import java.lang.Class;
import java.lang.*;


/**
 * Created by Jack Donnelly
 */
public abstract class AbstractProduct implements Product {

    protected final SerialNumber serialNumber;
    protected final Optional<Set<String>> description;
    protected SerialNumber makeSerialNumber;
    protected  Optional<Set<String>> makeDescription;

    /**
     * Constructor for the class
     *
     * @param serialNumber
     * @param description
     */
    AbstractProduct(SerialNumber serialNumber, Optional<Set<String>> description) {
        this.serialNumber = serialNumber;
        this.description = description;
    }

    /**
     * getter for serial number
     * @return SerialNumber object
     */
    public SerialNumber getSerialNumber() {
        return this.serialNumber;
    }

    /**
     * getter for the product name
     * will work in child classes
     * @return string of the name
     */
    public String getProductName() {
        return this.getProductType().getName();
    }

    /**
     * @return
     */
    public Optional<Set<String>> getDescription() {
        return this.description;
    }

    /**
     * gets the hashcode of the serial number
     * @return the hashcode
     */
    public int hashCode() {
        BigInteger numb = this.getSerialNumber().getSerialNumber();
        return numb.hashCode();
    }

    /**
     * Checks whether the two are equal or not
     * @param obj the input product
     * @return if they are equal it returns true
     */
    public boolean equals(Object obj) {

        // checks to make sure its a product and not something else or null
        if (obj == null || !(obj instanceof AbstractProduct)) {
            return false;
        } else {
            AbstractProduct product = (AbstractProduct) obj;
            return compareSerialNumbers(product.getSerialNumber());
        }
    }

    public static Product make(ProductType productType, SerialNumber serialNumber, Optional<Set<String>>
            description) throws ProductException{

        HashMap<ProductType, Product> holder = AbstractProduct.mapHashMap(serialNumber, description);

        Product creation = holder.get(productType);

        if(!creation.isValidSerialNumber(serialNumber)){
            throw new ProductException(productType, serialNumber, ErrorCode.INVALID_SERIAL_NUMBER);
        }
        else{
            return creation;
        }
    }
    public static HashMap<ProductType, Product> mapHashMap(SerialNumber serialNumber, Optional<Set<String>> description){
        HashMap<ProductType, Product> hashMap = new HashMap<>();

        hashMap.put(ProductType.OPAD, new oPad(serialNumber, description));
        hashMap.put(ProductType.OPOD, new oPod(serialNumber, description));
        hashMap.put(ProductType.OPHONE, new oPhone(serialNumber, description));
        hashMap.put(ProductType.OWATCH, new oWatch(serialNumber, description));
        hashMap.put(ProductType.OTV, new oTv(serialNumber, description));

        return hashMap;
    }
    /**
     * compares two serial numbers
     * @param numb the input serial numbers
     * @return true if they are equal
     */
    private boolean compareSerialNumbers(SerialNumber numb) {
        int zero = 0;
        BigInteger n1 = this.getSerialNumber().getSerialNumber();
        BigInteger n2 = numb.getSerialNumber();
        int check = n1.compareTo(n2);
        if (check == zero) {
            return true;
        } else
            return false;
    }

    //Not working... you're probably doing something stupid
    //errors are quite odd and sporatic
   /* @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        //StringBuilder tester;
        String productName = this.getProductName();
        str = str.append(productName + " ");

        String serialNumberer = this.getSerialNumber().getSerialNumber().toString();
        str = str.append(serialNumberer + " ");
        
        Optional<Set<String>> set1 = this.getDescription();
        if(set1.isPresent()) {
            Set<String> set2 = set1.get();
            str.append(" ");
            str.append(stringSetToString(set2,str));
            return str.toString();
        }

        
        return "Something went wrong";
    }
    //also not working, problem probably here?  Other test streams worked...
    /**
     * Uses a stream to convert a set of strings to a string
     * @param input the input string set
     * @return the new string
     */
    /*private String stringSetToString(Set<String> input, StringBuilder adder) {
        Stream<String> stream1 = input.stream();
        stream1.map(this::formatter).forEach(adder::append);
        
        return adder.toString();
    }
    //formats things, should work fine
    private String formatter(String input) {
        String str = input.substring(0,1).toUpperCase() + input.substring(1);
        str = str + " ";
        return str;
    }
*/
}