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
 * Created by Jack Donnelly
 */
public class makeTest {

        protected oPod opod;
        protected oPad opad;
        protected oPhone ophone;
        protected Optional<Set<String>> stringset1;
        protected oPad opad2;

    @Before
    public void setUp() {

        this.stringset1 = Optional.of(new LinkedHashSet<>());
        this.opad = new oPad(new SerialNumber(new BigInteger("1010", 2)), this.stringset1);
        this.opod = new oPod(new SerialNumber(new BigInteger("0010", 2)), this.stringset1);
        this.ophone = new oPhone(new SerialNumber(new BigInteger("315", 10)), this.stringset1);
        this.opad2 = new oPad (new SerialNumber(new BigInteger("0010", 2)), this.stringset1);
    }

    @After
    public void tearDown() {
        this.stringset1 = null;
        this.opad = null;
        this.opod = null;
        this.ophone = null;
    }

    /**
     * tests the make method from abstract product
     * @throws Exception
     */
    @Test
    public void testMake() throws Exception{

        oPhone answer1 = new oPhone(new SerialNumber(new BigInteger("315", 10)), this.stringset1);  //the correct answer
        Product comp1 = AbstractProduct.make(ProductType.OPHONE, new SerialNumber(new BigInteger("315", 10)), stringset1);  //whats being tested
        boolean comparer = (comp1 instanceof oPhone);
        assertTrue(comparer);
        assertTrue(answer1.getSerialNumber().getSerialNumber().equals(comp1.getSerialNumber().getSerialNumber()));

        oPad answer2 = new oPad(new SerialNumber(new BigInteger("1010", 2)), this.stringset1); //testing an oPad
        Product comp2 = AbstractProduct.make(ProductType.OPAD, new SerialNumber(new BigInteger("1010", 2)), this.stringset1);
        comparer = (comp2 instanceof oPad);
        assertTrue(comparer);
        assertTrue(answer2.getSerialNumber().getSerialNumber().equals(comp2.getSerialNumber().getSerialNumber()));

        oPod answer3 = new oPod(new SerialNumber(new BigInteger("0010", 2)), this.stringset1); // testing an oPod
        Product comp3 = AbstractProduct.make(ProductType.OPOD, new SerialNumber(new BigInteger("0010", 2)), this.stringset1);
        comparer = (comp3 instanceof oPod);
        assertTrue(comparer);
        assertTrue(answer3.getSerialNumber().getSerialNumber().equals(comp3.getSerialNumber().getSerialNumber()));

        
    }

    /**
     *  More tests for make from AbstractProduct
     *  @throws Exception
     */
    @Test(expected = ProductException.class)  //expecting exception
      public void testExceptionThrows() throws Exception{
        AbstractProduct.make(ProductType.OPOD, new SerialNumber(new BigInteger("00101", 2)), this.stringset1);
    }

    /**
     * More tests for make from AbstractProduct
     * @throws Exception
     */
    @Test(expected = ProductException.class) //expecting exception
      public void testExceptionThrows2() throws Exception{
        AbstractProduct.make(ProductType.OPAD, new SerialNumber(new BigInteger("123", 10)), this.stringset1);
    }
}
