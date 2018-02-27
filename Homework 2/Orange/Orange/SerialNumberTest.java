package Orange;
import java.math.BigInteger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Jack Donnelly
 */
public class SerialNumberTest {
    private SerialNumber numbodd;
    private SerialNumber numbeven;

    @Before
    public void setUp() {
        this.numbodd = new SerialNumber(new BigInteger("101101", 2));
        this.numbeven = new SerialNumber(new BigInteger("10100", 2));
    }

    @After
    public void tearDown(){
        this.numbodd = null;
        this.numbeven = null;
    }

    /**
     * tests the getSerialNumber method
     */
    @Test
    public void testGetSerialNumber() {
        
        setUp();

        BigInteger correctanswer = new BigInteger("101101", 2);
        BigInteger test = this.numbodd.getSerialNumber();
        assertEquals(test,correctanswer);

        correctanswer = new BigInteger("10100", 2);
        test = this.numbeven.getSerialNumber();
        assertEquals(test,correctanswer);

        tearDown();
    }

    /**
     * tests the gcd method
     */
    @Test
    public void testGcd() {
        setUp();

        BigInteger correctanswer = new BigInteger("130", 10).gcd(new BigInteger("101101", 2));
        BigInteger test = new BigInteger("130", 10).gcd(this.numbodd.getSerialNumber());
        assertEquals(correctanswer, test);

        tearDown();
    }

    /**
     * tests the mod method
     */
    @Test
    public void testMod() {
        setUp();

        BigInteger correctanswer = new BigInteger("100", 10).gcd(new BigInteger("101101", 2));
        BigInteger test = new BigInteger("100", 10).gcd(this.numbodd.getSerialNumber());
        assertEquals(correctanswer, test);

        tearDown();
    }

    /**
     * tests the testbit method
     */
    @Test
    public void testTestBit() {
        setUp();

        boolean test = this.numbodd.testBit(0);
        assertTrue(test);

        tearDown();
    }

    /**
     * tests iseven method
     */
    @Test
    public void testIsEven() {
        setUp();

        boolean oddinput = this.numbodd.isEven();
        boolean eveninput = this.numbeven.isEven();
        assertTrue(eveninput);
        assertFalse(oddinput);


        tearDown();
    }

    /**
     * tests isodd method
     */
    @Test
    public void testIsOdd() {
        setUp();

        boolean oddinput = this.numbodd.isOdd();
        boolean eveninput = this.numbeven.isOdd();
        assertTrue(oddinput);
        assertFalse(eveninput);


        tearDown();
    }

}