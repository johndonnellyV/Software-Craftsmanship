package Orange;

/**
 * Created by John Donnelly
 */
public interface Request {
    /**
     * method to be inherited by all child classes
     * @param product input product
     * @param status input requeststatus
     * @throws Exception throws exceptions
     */
    public void process(Product product, RequestStatus status) throws Exception;

}

