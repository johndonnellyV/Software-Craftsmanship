package Orange;

import org.junit.*;

import java.math.BigInteger;
import java.sql.Ref;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.*;

/**
 * Created by John Donnelly on 9/23/2015.
 */
public class RequestTest {

    protected Refund refund;
    protected Exchange exchange;
    protected Exchange.Builder ebuilder;
    protected Refund.Builder rbuilder;
    protected oPod opod;
    protected oPad opad;
    protected Optional<Set<String>> stringset1;
    protected RequestStatus status;

    @Before
    public void setUp() {

        this.stringset1 = Optional.of(new LinkedHashSet<>());
        this.opad = new oPad(new SerialNumber(new BigInteger("1010", 2)), this.stringset1);
        this.opod = new oPod(new SerialNumber(new BigInteger("0010", 2)), this.stringset1);
        this.ebuilder = new Exchange.Builder();
        this.rbuilder = new Refund.Builder();
        this.refund = Refund.refund;
        this.exchange = Exchange.exchange;
        this.status = new RequestStatus();


    }

    @After
    public void tearDown() {
        this.stringset1 = null;
        this.opad = null;
        this.opod = null;
        this.ebuilder = null;
        this.rbuilder = null;
        this.refund = null;
        this.exchange = null;
    }

    /**
     * tests out the builders as well as refund and exchange
     * and also tests StatusCode and RequestStatus
     * @throws Exception
     */
    @Test
    public void testRefund() throws Exception{
        this.ebuilder = this.ebuilder.addCompatible(new SerialNumber(new BigInteger("1010", 2)));
        Set<SerialNumber> set = new TreeSet();
        set.add(new SerialNumber(new BigInteger("1010", 2)));
        assertTrue(this.ebuilder.getCompatibleProducts().equals(set));

        this.ebuilder = this.ebuilder.addCompatible(new SerialNumber(new BigInteger("1010", 2))).addCompatible(new SerialNumber(new BigInteger("0010", 2)));
        set = new TreeSet();
        set.add(new SerialNumber(new BigInteger("1010", 2)));
        set.add(new SerialNumber(new BigInteger("0010", 2)));
        assertTrue(this.ebuilder.getCompatibleProducts().equals(set));

        BigInteger bigInt = new BigInteger("47");
        rbuilder.setRMA(bigInt);
        assertTrue(this.rbuilder.getRMA().equals(bigInt));
        
        exchange = ebuilder.build();
        assertTrue(ebuilder.getCompatibleProducts().equals(exchange.getCompatibleProducts()));
        
        ebuilder.addCompatible(new SerialNumber(new BigInteger("1011", 2)));
        assertFalse(ebuilder.getCompatibleProducts().equals(exchange.getCompatibleProducts())); //should fail
        System.out.println(exchange.getCompatibleProducts().toString());
        System.out.println(ebuilder.getCompatibleProducts().toString());
        
        refund = rbuilder.build();
        assertTrue(rbuilder.getRMA().equals(refund.getRMA()));
        
        status.setStatusCode(RequestStatus.StatusCode.OK);                      //Request Status Tests
        assertTrue(status.getStatusCode().equals(RequestStatus.StatusCode.OK));
        
        status.setStatusCode(RequestStatus.StatusCode.FAIL);
        assertTrue(status.getStatusCode().equals(RequestStatus.StatusCode.FAIL));
        
        status.setStatusCode(RequestStatus.StatusCode.UNKNOWN);
        assertTrue(status.getStatusCode().equals(RequestStatus.StatusCode.UNKNOWN));
        
        
    }

    /**
     * exception test for refund
     * @throws Exception
     */
    @Test(expected = ProductException.class) //expecting exception
      public void testRequestException() throws Exception{
      this.refund.process(opad, status);
    }

    /**
     * exception test for exchange
     * @throws Exception
     */
    @Test(expected = ProductException.class) //expecting exception
      public void testRequestException2() throws Exception{
      this.exchange.process(opad, status);
    }

}