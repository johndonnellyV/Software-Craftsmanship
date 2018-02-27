import java.util.LinkedList;

/**
 * Created by Admin on 11/18/2015.
 */
public class Passenger implements LineEntity{
    public boolean isBusy = false;
    public boolean canAuto = true;
    public static LinkedList<Passenger> totalPassengers = new LinkedList<>();
    public double ticketValue;
    private double maxValue = 2000;
    private double minValue = 20;
    private final double minTime = .2;
    private final double maxTime = 1;
    //time for processing
    public double processTime;

    /**
     * creates a new passenger with random process time and ticket value
     */
    public Passenger(){
        totalPassengers.add(this);
        ticketValue = Math.random() * (maxValue - minValue) + minValue;
        processTime = (Math.random() * (maxTime - minTime) + minTime);
    }

    public boolean getCanAuto() {
        return canAuto;
    }

    public static LinkedList<Passenger> getTotalPassengers() {
        return totalPassengers;
    }

    public double getTicketValue() {
        return ticketValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public double getMinValue() {
        return minValue;
    }

    public double getMinTime() {
        return minTime;
    }

    public double getMaxTime() {
        return maxTime;
    }

    public double getProcessTime() {
        return processTime;
    }
    public void setIsBusy(boolean bool){
        this.isBusy = bool;
    }
    public boolean getIsBusy(){
        return this.isBusy;
    }
}
