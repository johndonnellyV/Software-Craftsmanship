package Orange;
import java.math.BigInteger;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jack Donnelly
 */
public class oWatchTest{

    protected Optional<Set<String>> stringSet1;
    protected oWatch goodSerialNumber;
    protected oWatch badSerialNumber;
    protected oWatch badSerialNumber2;

    @Before
    public void setUp() {
        this.stringSet1 = Optional.of(new LinkedHashSet<>());
        this.goodSerialNumber = new oWatch(new SerialNumber(new BigInteger("165", 10)), this.stringSet1);
        this.badSerialNumber = new oWatch(new SerialNumber(new BigInteger("146", 10)), this.stringSet1);
        this.badSerialNumber2 = new oWatch(new SerialNumber(new BigInteger("1345677", 10)), this.stringSet1);
    }

    @After
    public void tearDown() {
        this.stringSet1 = null;
        this.goodSerialNumber = null;
        this.badSerialNumber = null;
        this.badSerialNumber2 = null;
    }

    /**
     * tests getProductName
     */
    @Test
    public void testGetProductName() {
        setUp();

        String expected = "oWatch";
        String str = this.goodSerialNumber.getProductName();
        assertEquals(expected, str);

        tearDown();
    }

    /**
     * tests getProductType
     */
    @Test
    public void testGetProductType() {
        setUp();

        ProductType expected = ProductType.OWATCH;
        ProductType test = this.goodSerialNumber.getProductType();
        assertEquals(expected, test);

        tearDown();
    }

    /**
     * tests isValidSerialNumber
     */
    @Test
    public void testIsValidSerialNumber() {
        setUp();

        boolean goodnumb = goodSerialNumber.isValidSerialNumber(this.goodSerialNumber.getSerialNumber());
        assertTrue(goodnumb);

        boolean badnumb = goodSerialNumber.isValidSerialNumber(this.badSerialNumber.getSerialNumber());
        assertFalse(badnumb);

        boolean badnumb2 = goodSerialNumber.isValidSerialNumber(this.badSerialNumber2.getSerialNumber());
        assertFalse(badnumb2);

        tearDown();
    }

}