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
 * @author Jack Donnelly
 */
public class oPadTest {
    protected Optional<Set<String>> stringSet1;
    protected oPad goodSerialNumber;
    protected oPad badSerialNumber;
    protected oPad badSerialNumber2;
            
    @Before
    public void setUp() {
        this.stringSet1 = Optional.of(new LinkedHashSet<>());
        this.goodSerialNumber = new oPad(new SerialNumber(new BigInteger("1010", 2)), this.stringSet1);
        this.badSerialNumber = new oPad(new SerialNumber(new BigInteger("10101", 2)), this.stringSet1);
        this.badSerialNumber2 = new oPad(new SerialNumber(new BigInteger("0110", 2)), this.stringSet1);
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
    public void testGetProductName() {
        setUp();

        String answer = "oPad";
        String str = this.goodSerialNumber.getProductName();
        assertEquals(answer, str);

        tearDown();
    }

    /**
     * getProductType test
     */
    @Test
    public void testGetProductType() {
        setUp();

        ProductType answer = ProductType.OPAD;
        ProductType test = this.goodSerialNumber.getProductType();
        assertEquals(answer, test);

        tearDown();
    }

    /**
     * isValidSerialNumber test
     */
    @Test
    public void testIsValidSerialNumber() {
        setUp();
        
        boolean goodNumb = goodSerialNumber.isValidSerialNumber(this.goodSerialNumber.getSerialNumber());
        assertTrue(goodNumb);

        boolean badNumb = goodSerialNumber.isValidSerialNumber(this.badSerialNumber.getSerialNumber());
        assertFalse(badNumb);

        boolean badNumb2 = goodSerialNumber.isValidSerialNumber(this.badSerialNumber2.getSerialNumber());
        assertFalse(badNumb2);
        
        tearDown();
    }
    
}