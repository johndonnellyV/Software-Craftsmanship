package Orange;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import javax.swing.text.html.Option;
import java.math.BigInteger;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author Jack Donnelly
 */
public class ProductExceptionTest {

    protected ProductException testExceptionoPod;
    protected ProductException testExceptionoPhone;
    protected SerialNumber serialNumbTester;
    protected SerialNumber serialNumbTester2;


    @Before
    public void setUp() throws Exception {
        serialNumbTester = new SerialNumber(new BigInteger("0010", 2));
        serialNumbTester2 = new SerialNumber(new BigInteger("315", 10));
        testExceptionoPod = new ProductException(ProductType.OPOD, serialNumbTester, ProductException.ErrorCode.INVALID_SERIAL_NUMBER);
        testExceptionoPhone = new ProductException(ProductType.OPHONE, serialNumbTester2, ProductException.ErrorCode.INVALID_SERIAL_NUMBER);

    }

    @After
    public void tearDown() throws Exception {
        serialNumbTester = null;
        serialNumbTester2 = null;
        testExceptionoPod = null;
        testExceptionoPhone = null;

    }

    /**
     * tests the getProductType method
     * @throws Exception
     */
    @Test
    public void testGetProducttype() throws Exception {
        ProductType prodtypeexpected = ProductType.OPOD;
        ProductType prodtypetest = testExceptionoPod.getProductType();
        assertEquals(prodtypeexpected, prodtypetest);
        prodtypeexpected = ProductType.OPHONE;
        prodtypetest = testExceptionoPhone.getProductType();
        assertEquals(prodtypeexpected, prodtypetest);
    }

    /**
     * tests the getProductName method
     * @throws Exception
     */
    @Test
    public void testGetProductName() throws Exception {
        String expected = "oPod";
        String actual = testExceptionoPod.getProductName();
        assertEquals(expected, actual);
        expected = "oPhone";
        actual = testExceptionoPhone.getProductName();
        assertEquals(expected, actual);
        

    }

    /**
     * tests the getSerialNumber method
     * @throws Exception
     */
    @Test
    public void testGetSerialNumber() throws Exception {
        SerialNumber expected = serialNumbTester;
        SerialNumber actual = testExceptionoPod.getSerialNumber();
        assertEquals(expected, actual);
        expected = serialNumbTester2;
        actual = testExceptionoPhone.getSerialNumber();
        assertEquals(expected, actual);
    }

    /**
     * tests the getErrorCode method
     * @throws Exception
     */
    @Test
    public void testGetErrorCode() throws Exception {
        ProductException.ErrorCode expected = ProductException.ErrorCode.INVALID_SERIAL_NUMBER;
        ProductException.ErrorCode actual = testExceptionoPod.getErrorCode();
        assertEquals(expected, actual);
        expected = ProductException.ErrorCode.INVALID_SERIAL_NUMBER;
        actual = testExceptionoPhone.getErrorCode();
        assertEquals(expected, actual);
    }
}