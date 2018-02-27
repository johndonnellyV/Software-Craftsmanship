package Orange;

import java.math.BigInteger;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 
 * @author Jack Donnelly
 */
public class oPodTest {
    protected Optional<Set<String>> stringSet1;
    protected oPod goodSerialNumber;
    protected oPod badSerialNumber;
    protected oPod badSerialNumber2;


    @Before
    public void setUp() {
        this.stringSet1 = Optional.of(new LinkedHashSet<>());
        this.goodSerialNumber = new oPod(new SerialNumber(new BigInteger("0010", 2)), this.stringSet1); //valid serial, the rest aren't
        this.badSerialNumber = new oPod(new SerialNumber(new BigInteger("1101", 2)), this.stringSet1);
        this.badSerialNumber2 = new oPod(new SerialNumber(new BigInteger("1101001001", 2)), this.stringSet1);

    }

    @After
    public void tearDown() {
        this.stringSet1 = null;
        this.goodSerialNumber = null;
        this.badSerialNumber = null;
        this.badSerialNumber2 = null;
    }


    /**
     * getProductName test
     */
    @Test
    public void testGetProductName()
    {
        setUp();

        String answer = "oPod";
        String str = this.goodSerialNumber.getProductName();
        assertEquals(answer, str);

        tearDown();
    }

    /**
     * getProductType test
     */
    @Test
    public void testGetProductType()
    {
        setUp();

        ProductType answer = ProductType.OPOD;
        ProductType test = this.goodSerialNumber.getProductType();
        assertEquals(answer, test);

        tearDown();
    }


    /**
     * isValidSerialNumber test
     */
    @Test
    public void testIsValidSerialNumber()
    {
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