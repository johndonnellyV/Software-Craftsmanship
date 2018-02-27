/**
 * Created by Admin on 11/18/2015.
 */
public class FrequentFlyerLine extends RegularLine{

    public FrequentFlyerLine(){

    }
    public void pushPassenger(Passenger passenger){
        System.out.println("Not a frequent flyer");
    }

    public void pushPassenger(FrequentFlyer passenger){
        passengerQ.add(passenger);
    }
    public FrequentFlyer popPassenger(){
        return (FrequentFlyer) passengerQ.getLast();
    }
}
