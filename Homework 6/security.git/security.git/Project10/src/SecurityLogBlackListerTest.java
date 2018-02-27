import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Admin on 11/4/2015.
 */
public class SecurityLogBlackListerTest {

        public String goodData = "123.12.234.232";;
        public String badData = "1234.31223.1";
        public String[] dataSet = {"123.12.234.232", "1234.31223.1", "dakjshd,", "this is a test"};
        public SecurityLogBlackLister tester = new SecurityLogBlackLister(4);
        public String numb = "14";
        public String domain = "(google.com.en):";


    @Before
    public void setUp() throws Exception{
        String goodData = "123.12.234.232";
        String badData = "1234.31223.1";
        String[] dataSet = new String[4];
        dataSet[0] = goodData;
        dataSet[1] = badData;
        dataSet[2] = "sadhsa";
        dataSet[3] = "this is a test";
    }

    @After
    public void tearDown(){
        goodData = null;
        badData = null;
        dataSet = null;
        tester = null;

    }

    @Test
    public void testprocessLine() {
       String[] result =  tester.processLine(dataSet, dataSet.length);
        assertTrue(result[0].equals(dataSet[0]));
    }

    @Test
    public void testAddToBlackList1() {
        tester.addToBlacklist("132.123.145.156:");
        System.out.println(tester.builder.toString());
        assertTrue(tester.builder.toString().equals("132.123.145.156" + "\\" + "\n"));
    }

    @Test
    public void testAddToBlackList2() {
        tester.addToBlacklist("132.123.145.156", "(www.google.com):");
        assertTrue(tester.builder.toString().equals("www.google.com, " + "132.123.145.156" + "\\" + "\n"));
    }

    @Test
    public void testAddToBlackList3() {
        //improper formatting
        tester.addToBlacklist("132.123.145.156", "www.google.com");
        assertFalse(tester.builder.toString().equals("www.google.com, " + "132.123.145.156" + "\\" + "\n"));
    }

    @Test
    public void testAddToBlackList4() {
        //improper formatting
        tester.addToBlacklist("132.123.145.156:", "www.google.com");
        assertFalse(tester.builder.toString().equals("www.google.com, " + "132.123.145.156" + "\\" + "\n"));
    }

    @Test
    public void testIsValidIP() {
        //is a good case
        assertTrue(tester.isValidIP("132.123.23.198"));
        //has one number too high
        assertFalse(tester.isValidIP("132.342.23.198"));
        //has too many periods
        assertFalse(tester.isValidIP("132.123.23.198.23"));
        //has too few periods
        assertFalse(tester.isValidIP("132.123.23"));
        //tests all 1s as a minimal case
        assertTrue(tester.isValidIP("1.1.1.1"));
        //tests max possible ip allowed
        assertTrue(tester.isValidIP("255.255.255.255"));
        //tests one more than allowed
        assertFalse(tester.isValidIP("256.256.256.256"));

    }

    @Test
         public void testIsValidDomain() {
        //is a good case
        assertTrue(tester.isValidDomain("(www.google.com)"));
        //another good case
        assertTrue(tester.isValidDomain("(www.google.com):"));
        //bad case formatting shouldn't happen
        assertFalse(tester.isValidDomain("www.google.com"));
        //bad case way off
        assertFalse(tester.isValidDomain("Regular sentence"));
        //uses a space fails
        assertFalse(tester.isValidDomain("Regular sentence.exe.com"));
        //uses an underscore fails
        assertFalse(tester.isValidDomain("Regular_sentence.net.com"));

    }

    @Test
    public void testIsValidAttemptNumber() {
        //is a good case 0 is min
        String[] array = {"0", "times", "14", "time", "14f", "time", "14", "right" ,"-2", "times"};
        assertTrue(tester.isValidAttemptNumber(array, 0));
        //good case
        assertTrue(tester.isValidAttemptNumber(array, 2));
        //bad case not a number
        assertFalse(tester.isValidAttemptNumber(array, 4));
        //another bad case corrent number wrong follow
        assertFalse(tester.isValidAttemptNumber(array, 6));
        //bad case negative number
        assertFalse(tester.isValidAttemptNumber(array, 8));

    }
    @Test
    public void testBlackListProcessor1() {
        tester.blackListProcessor(domain, numb, goodData);
        //System.out.println(domain.substring(1, domain.length() - 2) + ", " + goodData + "\\" + "\n");
        System.out.println(tester.builder.toString());
        assertTrue(tester.builder.toString().equals(domain.substring(1, domain.length() - 2) + ", " + goodData + "\\" + "\n"));

    }
    @Test
    public void testBlackListProcessor2() {
        tester.blackListProcessor("", numb, "123.43.21.164:");
        System.out.println("123.43.21.164:");
        System.out.println(tester.builder);
        assertTrue(tester.builder.toString().equals("123.43.21.164" + "\\" + "\n"));


    }
    @Test
    public void testBlackListCheck() {
        tester.blackListCheck(14);
        assertTrue(tester.blackListCheck(14));
        //ties allowed amount
        assertTrue(tester.blackListCheck(5));
        //below amount allowed
        assertFalse(tester.blackListCheck(4));
        //negative case
        assertFalse(tester.blackListCheck(-2));

    }

    @Test
    public void testIsNumeric(){
        //good cases that should pass
        assertTrue(tester.isNumeric("34"));
        assertTrue(tester.isNumeric("1"));
        assertTrue(tester.isNumeric("256"));
        assertTrue(tester.isNumeric("1456"));
        assertTrue(tester.isNumeric("16"));

        //bad cases that should fail
        assertFalse(tester.isNumeric("16F"));
        assertFalse(tester.isNumeric("Fred"));
        assertFalse(tester.isNumeric("16 test"));
        assertFalse(tester.isNumeric("16 16 167"));
        //should fail because validIP parses by periods
        assertFalse(tester.isNumeric("124.35"));


    }
}

