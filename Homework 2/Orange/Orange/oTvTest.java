/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class oTvTest
{
    protected Optional<Set<String>> stringSet1;
    protected oTv goodSerialNumber;
    protected oTv badSerialNumber;
    protected oTv badSerialNumber2;

    @Before
    public void setUp()
    {
        this.stringSet1 = Optional.of(new LinkedHashSet<>());
        this.goodSerialNumber = new oTv(new SerialNumber(new BigInteger("913", 10)), this.stringSet1);
        this.badSerialNumber = new oTv(new SerialNumber(new BigInteger("96", 10)), this.stringSet1);
        this.badSerialNumber2 = new oTv(new SerialNumber(new BigInteger("45", 10)), this.stringSet1);
    }

    @After
    public void tearDown()
    {
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

        String answer = "oTv";
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

        ProductType answer = ProductType.OTV;
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

        boolean goodnumb = goodSerialNumber.isValidSerialNumber(this.goodSerialNumber.getSerialNumber());assertTrue(goodnumb); //checking all the serial numbers
        boolean badnumb = goodSerialNumber.isValidSerialNumber(this.badSerialNumber.getSerialNumber());assertFalse(badnumb);
        boolean badnumb2 = goodSerialNumber.isValidSerialNumber(this.badSerialNumber2.getSerialNumber());assertFalse(badnumb2);

        tearDown();
    }

}