import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by Admin on 11/18/2015.
 */
public class AirVilleTest {

    public RegularLine line1 = new RegularLine();
    public RegularLine line2 = new RegularLine();
    public RegularLine line3 = new RegularLine();
    public FrequentFlyerLine line4 = new FrequentFlyerLine();
    public Counter counter1 = new Counter(line1);
    public Counter counter2 = new Counter(line1);
    public Counter counter3 = new Counter(line1);
    public Counter counter4 = new Counter(line1);
    public Counter counter5 = new Counter(line2);
    public Counter counter6 = new Counter(line2);
    public AutomatedCounter ac1 = new AutomatedCounter(line3, line4);
    public AutomatedCounter ac2 = new AutomatedCounter(line3, line4);
    public AutomatedCounter ac3 = new AutomatedCounter(line3, line4);
    public AutomatedCounter ac4 = new AutomatedCounter(line3, line4);
    public AutomatedCounter ac5 = new AutomatedCounter(line3, line4);
    public Agent agent1 = new Agent(counter1);
    public Agent agent2 = new Agent(counter2);
    public Agent agent3 = new Agent(counter3);
    public Agent agent4 = new Agent(counter4);
    public Agent agent5 = new Agent(counter5);
    public Agent agent6 = new Agent(counter6);
    public Agent agent7 = new Agent(line3);
    public Passenger passenger1 = new Passenger();
    public Passenger passenger2 = new Passenger();
    public Passenger passenger3 = new Passenger();
    public Passenger passenger4 = new Passenger();
    public FrequentFlyer passenger5 = new FrequentFlyer();
    public FrequentFlyer passenger6 = new FrequentFlyer();
    public BaggagePassenger passenger7 = new BaggagePassenger();
    public RefundPassenger passenger8 = new RefundPassenger();
    public ReroutedPassenger passenger9 = new ReroutedPassenger();
    public Supervisor sup1 = new Supervisor();
    public AirVille testCase = new AirVille();




    @Before
    public void setUp() throws Exception {
        testCase = new AirVille();
        RegularLine line1 = new RegularLine();
        RegularLine line2 = new RegularLine();
        RegularLine line3 = new RegularLine();
        FrequentFlyerLine line4 = new FrequentFlyerLine();
        Counter counter1 = new Counter(line1);
        Counter counter2 = new Counter(line1);
        Counter counter3 = new Counter(line1);
        Counter counter4 = new Counter(line1);
        Counter counter5 = new Counter(line2);
        Counter counter6 = new Counter(line2);
        AutomatedCounter ac1 = new AutomatedCounter(line3, line4);
        AutomatedCounter ac2 = new AutomatedCounter(line3, line4);
        AutomatedCounter ac3 = new AutomatedCounter(line3, line4);
        AutomatedCounter ac4 = new AutomatedCounter(line3, line4);
        AutomatedCounter ac5 = new AutomatedCounter(line3, line4);
        Agent agent1 = new Agent(counter1);
        Agent agent2 = new Agent(counter2);
        Agent agent3 = new Agent(counter3);
        Agent agent4 = new Agent(counter4);
        Agent agent5 = new Agent(counter5);
        Agent agent6 = new Agent(counter6);
        Agent agent7 = new Agent(line3);
        Passenger passenger1 = new Passenger();
        Passenger passenger2 = new Passenger();
        Passenger passenger3 = new Passenger();
        Passenger passenger4 = new Passenger();
        FrequentFlyer passenger5 = new FrequentFlyer();
        FrequentFlyer passenger6 = new FrequentFlyer();
        BaggagePassenger passenger7 = new BaggagePassenger();
        RefundPassenger passenger8 = new RefundPassenger();
        ReroutedPassenger passenger9 = new ReroutedPassenger();
        Supervisor sup1 = new Supervisor();

    }

    @After
    public void tearDown(){
        testCase = null;
        line1 = null;
        line2 = null;
        line3 = null;
        line4 = null;
        sup1 = null;
        counter1 = null;
        counter2 = null;
        counter3 = null;
        counter4 = null;
        counter5 = null;
        counter6 = null;
        ac1 = null;
        ac2 = null;
        ac3 = null;
        ac4 = null;
        ac5 = null;
        agent1 = null;
        agent2 = null;
        agent3 = null;
        agent4 = null;
        agent5 = null;
        agent6 = null;
        agent7 = null;
        passenger1 = null;
        passenger2 = null;
        passenger3 = null;
        passenger4 = null;
        passenger5 = null;
        passenger6 = null;
        passenger7 = null;
        passenger8 = null;
        passenger9 = null;

    }

    @Test
    public void testScanCounter() throws Exception{
        line3.pushPassenger(passenger1);
        ac1.getNextPassenger();
        for(int i = 0; i < 100; i++) {
            ac1.processPassenger(passenger1);
            ac1.processPassenger(passenger1);
        }
        assertTrue(line3.popCounter() != null);
    }
    @Test(expected=NoSuchElementException.class)
    public void testLeaveCounter(){
        agent2.leaveCounter();
        counter1 = (Counter) agent2.currentCounter.get();
    }

    @Test
    public void testcalculateProcesstime() throws Exception {
        line3.pushPassenger(passenger1);
        ac1.getNextPassenger();
        ac1.calculateProcessTime(passenger1);
        assertTrue(ac1.getProcessTime() == passenger1.getProcessTime());
    }
    @Test
    public void testcalculateProcesstime2() throws Exception {
        line3.pushPassenger(passenger1);
        ac1.getNextPassenger();
        ac1.calculateProcessTime(passenger1);
        ac1.supervisor = Optional.of(sup1);

        assertFalse(ac1.getProcessTime() != passenger1.getProcessTime());
    }
    @Test
    public void testprocessPassenger() throws Exception {
        line3.pushPassenger(passenger1);
        ac1.getNextPassenger();
        for(int i = 0; i < 100; i++) {
            ac1.processPassenger(passenger1);
            ac1.processPassenger(passenger1);
        }
        System.out.println(line3.assistQ.size());
        assertTrue(line3.assistQ.size() > 3);
    }

    @Test
    public void testRequestAgent() throws Exception {
        ac1.requestAgent();
        assertTrue(line3.popCounter().equals(ac1));

        ac2.requestAgent();
        assertTrue(line3.popCounter().equals(ac2));

        ac3.requestAgent();
        assertTrue(line3.popCounter().equals(ac3));

        ac4.requestAgent();
        assertTrue(line3.popCounter().equals(ac4));

        ac5.requestAgent();
        assertTrue(line3.popCounter().equals(ac5));

        ac5.requestAgent();
        assertFalse(line3.popCounter().equals(ac1));
    }

    @Test(expected=NullPointerException.class)
    public void testprocessPassengerCounter() throws Exception {
        line1.pushPassenger(passenger2);
        counter1.getNextPassenger();
        counter1.processPassenger(passenger2);
        if(line1.popCounter().equals(new NoSuchElementException())){

        }

    }

    @Test
    public void testpushPassenger() throws Exception {
        line1.pushPassenger(passenger2);
        assertTrue(line1.popPassenger().equals(passenger2));

        line2.pushPassenger(passenger1);
        line2.pushPassenger(passenger2);
        line2.pushPassenger(passenger1);
        line2.pushPassenger(passenger1);
        line2.pushPassenger(passenger1);


        assertTrue(line2.popPassenger().equals(passenger1));
        assertTrue(line2.popPassenger().equals(passenger2));
        assertTrue(line2.popPassenger().equals(passenger1));
        assertTrue(line2.popPassenger().equals(passenger1));
        assertTrue(line2.popPassenger().equals(passenger1));

        line3.pushPassenger(passenger5);
        line3.pushPassenger(passenger6);
        line3.pushPassenger(passenger5);
        line3.pushPassenger(passenger2);
        line3.pushPassenger(passenger1);

        assertTrue(line3.popPassenger().equals(passenger5));
        assertTrue(line3.popPassenger().equals(passenger6));
        assertTrue(line3.popPassenger().equals(passenger5));
        assertTrue(line3.popPassenger().equals(passenger2));
        assertTrue(line3.popPassenger().equals(passenger1));

        line3.pushPassenger(passenger5);
        line3.pushPassenger(passenger6);
        line3.pushPassenger(passenger5);
        line3.pushPassenger(passenger2);
        line3.pushPassenger(passenger1);

        assertTrue(line3.popPassenger().equals(passenger5));
        assertTrue(line3.popPassenger().equals(passenger6));
        assertFalse(line3.popPassenger().equals(passenger6));
        assertFalse(line3.popPassenger().equals(passenger1));
        assertTrue(line3.popPassenger().equals(passenger1));
    }

    @Test
    public void testGetCurrentMoney() throws Exception {
        line3.addCounter(ac1);
        line3.addCounter(ac2);
        line3.addCounter(ac3);
        line3.addCounter(ac4);
        ac1.currentMoney = 45;
        ac2.currentMoney = 60;
        assertTrue(line3.getCurrentMoney() == 210);
    }

    @Test
         public void testGetMultiplier() throws Exception {
        assertTrue(sup1.getMultiplier() < 1);
    }
    @Test
    public void testGetMultiplier2() throws Exception {
        assertTrue(sup1.getMultiplier() < 1);
    }
    @Test
    public void testGetMultiplier3() throws Exception {
        assertTrue(sup1.getMultiplier() < 1);
    }
    @Test
    public void testGetMultiplier4() throws Exception {
        assertTrue(sup1.getMultiplier() < 1);
    }
    @Test
    public void testGetMultiplier5() throws Exception {
        assertTrue(sup1.getMultiplier() < 1);
    }

    @Test
    public void testStressTest() throws Exception{
        testCase.addLine(line1);
        testCase.addLine(line3);
        for(int i = 0;i < 100; i++){
            line1.pushPassenger(new Passenger());
            line1.pushPassenger(new BaggagePassenger());
            line1.pushPassenger(new RefundPassenger());
            //remember these take money away
            line1.pushPassenger(new ReroutedPassenger());
            line3.pushPassenger(new Passenger());
        }
        counter1.getNextPassenger();
        ac1.getNextPassenger();
        for(int k = 0; k < 50; k++){
            counter1.processPassenger(counter1.getCurrentPassenger().get());
            ac1.processPassenger(ac1.getCurrentPassenger().get());
        }
        //checks to make sure money is generating properly
        System.out.println(counter1.getCurrentMoney());
        assertTrue(counter1.getCurrentMoney() > 50);
        //checks to make sure money is generating properly
        //This one will be about 1/5 because 1/5 can go through
        System.out.println(ac1.getCurrentMoney());
        assertTrue(ac1.getCurrentMoney() > 50);
        //makes sure one supervisor is present
        assertTrue(testCase.getSupervisors().get(0) != null);

        //checks a second is not
        assertTrue(testCase.getSupervisors().size() == 1);
        testCase.addSupervisor();
        assertTrue(testCase.getSupervisors().size() == 1);

        //adds a second now that a diamond is present
        testCase.diamonds = 1;
        testCase.addSupervisor();
        assertTrue(testCase.getSupervisors().size() == 2);

        //should be around 825
        assertTrue(testCase.getNumberOfPassengers() > 800);
        System.out.println(testCase.getNumberOfEmployees());
        //should be 19
        assertTrue(testCase.getNumberOfEmployees() > 18);


    }
}