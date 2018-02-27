package Orange;

import java.math.BigInteger;
import java.util.BitSet;
import java.util.Set;

/**
 * Created by John Donnelly
 */
public class Refund implements Request{
    protected static Refund refund = new Refund(new Refund.Builder());
    protected Refund.Builder builder;

    /**
     * private constructor of refund
     * @param builder input builder
     */
    private Refund(Builder builder){
      this.builder = builder;
    }

    /**
     * Processes the refund request
     * @param product input product
     * @param status input status
     * @throws RequestException error for the request
     * @throws ProductException error for the product
     */
    public void process(Product product, RequestStatus status) throws RequestException, ProductException{
        product.process(this, status);
    }

    public BigInteger getRMA(){
        return this.builder.rma;
    }

    /**
     * Builder inner class
     */
    static class Builder{

        protected BigInteger rma;

        /**
         * Setter with return type to allow stacked calls
         * @param rma input BigInteger
         * @return builder object
         */
        public Builder setRMA(BigInteger rma){
            this.rma = rma;
            return (this);
        }

        public BigInteger getRMA(){
            return this.rma;
        }

        /**
         * Creates a Refund object
         * @return a new refund object
         */
        public Refund build(){
            return (new Refund(this));

        }
    }
}
