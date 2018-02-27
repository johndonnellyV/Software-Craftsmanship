package Orange;
import java.util.*;
/**
 * Created by John Donnelly
 */
public class Exchange implements Request{

    protected static Exchange exchange = new Exchange(new Exchange.Builder()); //for testing not needed
    //protected Builder builder;
    private final NavigableSet<SerialNumber> set;

    /**
     * private constructor for exchange
     * @param builder the input builder
     */
    private Exchange(Builder builder){
        //this.builder = builder;
        set = (NavigableSet<SerialNumber>) builder.getCompatibleProducts();
    }

    /**
     * processes the exchange request
     * @param product the input Product
     * @param status the input RequestStatus
     * @throws RequestException exception for the request
     * @throws ProductException exception for the product
     */
    public void process(Product product, RequestStatus status) throws RequestException, ProductException{
        product.process(this, status);
    }

    public NavigableSet<SerialNumber> getCompatibleProducts(){
            return this.set; 
    }

    /**
     * builder inner class which makes the exchange object
     */
    static class Builder{

        private Set<SerialNumber> set = new TreeSet<SerialNumber>();

        /**
         * default constuctor
         */
        Builder(){
         }

        /**
         * constructor that takes a serial number
         * @param set
         */
         Builder(Set<SerialNumber> set){
        this.set = set;
      }
      
        /**
         * adds a compatible product by serial number
         * @param serialNumber ser numb of the product
         * @return a builder object to stack calls of the method if needed
         */
        public Builder addCompatible(SerialNumber serialNumber){
            set.add(serialNumber);
            return this; //can stack calls
        }

        public Set<SerialNumber> getCompatibleProducts(){
          Set<SerialNumber> newSet = new TreeSet<>();
          newSet.addAll(set);
            return newSet;
        }

        /**
         * creates a new exchange object
         * @return
         */
        public Exchange build(){
          NavigableSet<SerialNumber> newset = (NavigableSet<SerialNumber>) this.set;
            return (new Exchange(new Builder(newset)));

        }
    }
}
