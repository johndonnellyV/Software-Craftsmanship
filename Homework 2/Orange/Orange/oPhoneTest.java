
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
public class oPhoneTest{
    protected Optional<Set<String>> stringSet1;
    protected oPhone goodSerialNumber;
    protected oPhone badSerialNumber;
    protected oPhone badSerialNumber2;
            
    @Before
    public void setUp(){
        this.stringSet1 = Optional.of(new LinkedHashSet<>());
        this.goodSerialNumber = new oPhone(new SerialNumber(new BigInteger("315", 10)), this.stringSet1);
        this.badSerialNumber = new oPhone(new SerialNumber(new BigInteger("7650", 10)), this.stringSet1); //this and next one are invalid
        this.badSerialNumber2 = new oPhone(new SerialNumber(new BigInteger("73", 10)), this.stringSet1);
    }
    
    @After
    public void tearDown(){
        this.stringSet1 = null;
        this.goodSerialNumber = null;
        this.badSerialNumber = null;
        this.badSerialNumber2  = null;
    }

    /**
     * tests GetProductName
     */
    @Test
    public void testGetProductName(){
        setUp();

        String expected = "oPhone";
        String str = this.goodSerialNumber.getProductName();
        assertEquals(expected, str);

        tearDown();
    }

    /**
     * tests GetProductType
     */
    @Test
    public void testGetProductType(){
        setUp();
        
        ProductType expected = ProductType.OPHONE;
        ProductType test = this.goodSerialNumber.getProductType();
        assertEquals(expected, test);
        
        tearDown();
    }



    /**
     * tests isvalidserialnumber
     */
    @Test
    public void testIsValidSerialNumber(){
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