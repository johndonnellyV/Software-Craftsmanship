package Orange; /**
 * Created by Jack Donnelly
 */
import Orange.SerialNumber;

import java.util.*;

/**
 * Interface product is at the top of the hierarchy, methods to be used in child
 * classes for specific products
 */
public interface Product {

    public SerialNumber getSerialNumber();



    public ProductType getProductType();



    public String getProductName();



    public Optional<Set<String>> getDescription();


    public void process(Exchange request, RequestStatus status) throws ProductException;

    public void process(Refund request, RequestStatus status) throws ProductException;

    public Boolean isValidSerialNumber(SerialNumber serialNumber);
}
