package Orange;

import java.math.BigInteger;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * @author Jack Donnelly
 */
public class AbstractProductTest {
    protected AbstractProductTester product0; //0 names will have nothing in them for testing
    protected AbstractProductTester product1;
    protected AbstractProductTester product2;
    protected oPod opod0;
    protected oPod opod1;
    protected LinkedHashSet<String> hashset;
    protected Optional<Set<String>> stringset0;
    protected Optional<Set<String>> stringset1;
    protected SerialNumber snumb;
    protected SerialNumber snumb2;
    
    public AbstractProductTest() {
    }
    
    /**
     * Class used to allow testing of an Abstract class
     * which can't be done otherwise
     */
    public class AbstractProductTester extends AbstractProduct {

        public AbstractProductTester(SerialNumber serialNumber, Optional<Set<String>> description) {
            super(serialNumber, description);
        }

        @Override
        public ProductType getProductType() {
            return null;
        }
        public void process(Exchange request, RequestStatus status) throws ProductException{
            throw new ProductException(ProductType.OWATCH, serialNumber, ProductException.ErrorCode.UNSUPPORTED_OPERATION);
        }
        @Override
        public void process(Refund request, RequestStatus status) throws ProductException{
            throw new ProductException(ProductType.OWATCH, serialNumber, ProductException.ErrorCode.UNSUPPORTED_OPERATION);

        }
        public Boolean isValidSerialNumber(SerialNumber serialNumber){
            boolean greaterthancheck;
            String numberinput = "630";
            int upperBound = 42;

            boolean odd = serialNumber.isOdd();
            int value = serialNumber.gcd(new SerialNumber(new BigInteger(numberinput))).intValue();
            greaterthancheck = (value <= upperBound);
            boolean answer = (greaterthancheck && odd);
            return answer;
        }
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.hashset = new LinkedHashSet<String>()
                {{
                    add("Hello");
                    add("World");
                    add("Test");
                    add("Description");}};

        this.stringset1 = Optional.of(this.hashset);
        this.stringset0 = Optional.empty();
        this.snumb = new SerialNumber(new BigInteger("13"));
        this.snumb2 = new SerialNumber(new BigInteger("1300"));
        

        this.product1 = new AbstractProductTester(this.snumb, this.stringset1);  //tests are here
        this.product0 = new AbstractProductTester(this.snumb, this.stringset0);
        this.product2 = new AbstractProductTester(this.snumb2, this.stringset0);
        this.opod1 = new oPod(this.snumb, this.stringset1);
        this.opod0 = new oPod(this.snumb, this.stringset0);
    }
    

    
    @After
    public void tearDown() {
        this.hashset = null;
        this.stringset1 = null;
        this.stringset0 = null;
        this.snumb = null;
        this.snumb2 = null;
        this.product1 = null;
        this.product0 = null;
        this.product2  = null;
    }

    /**
     * getSerialNumber test
     */
    @Test
    public void testGetSerialNumber() {
        setUp();

        SerialNumber answer = this.snumb;
        SerialNumber test = this.product1.getSerialNumber();
        assertEquals(answer, test);

        tearDown();
    }

    /**
     * getDescription test
     */
    @Test
    public void testGetDescription() {
        setUp();

        Optional<Set<String>> answer = this.stringset1;
        Optional<Set<String>> test = this.product1.getDescription();
        assertEquals(answer, test);

        answer = this.stringset0;
        test = this.product0.getDescription();
        assertEquals(answer, test);

        tearDown();
    }

    /**
     * hashCode test
     */
    @Test
    public void testHashCode() {
        setUp();

        int answer = snumb.getSerialNumber().hashCode();
        int test = product1.hashCode();

        assertEquals(answer, test);
    }

    /**
     * getProductName test
     */
    @Test
    public void testGetProductName() {
        setUp();

        String answer = "oPod";
        String test = this.opod1.getProductName();
        assertEquals(answer, test);

        tearDown();
    }

    /**
     * toString test
     */
    /*@Test
    public void testToString() {
        setUp();

        String answer = "oPod, 13, Hello World Test Description ";
        String test = this.opod1.toString();
        assertEquals(answer, test);

        answer = "oPod, 13";
        test = this.opod0.toString();
        assertEquals(answer, test);

        tearDown();
    }*/

    /**
     * equals test
     */
    @Test
    public void testEquals() {
        setUp();

        boolean compare = this.product0.equals(this.product1); //serial numbers match
        assertTrue(compare);

        compare = this.product1.equals(this.product2); // serial numbers do not match
        assertFalse(compare);

        tearDown();
    }
}