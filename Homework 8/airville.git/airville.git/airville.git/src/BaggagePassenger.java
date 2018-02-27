import java.util.LinkedList;

/**
 * Created by Admin on 11/18/2015.
 */
public class BaggagePassenger extends Passenger {
    private boolean canAuto = false;
    public double ticketValue;
    private double maxValue = 3000;
    private double minValue = 30;
    private final double minTime = .5;
    private final double maxTime = 5;
    //time for processing
    public double processTime;

    /**
     * passenger with heavy baggage that takes longer to process on average
     */
    public BaggagePassenger(){
        totalPassengers.add(this);
        ticketValue = Math.random() * (maxValue - minValue) + minValue;
        processTime = (Math.random() * (maxTime - minTime) + minTime);
    }

    @Override
    public boolean getCanAuto() {
        return canAuto;
    }

    @Override
    public double getTicketValue() {
        return ticketValue;
    }

    @Override
    public double getMaxValue() {
        return maxValue;
    }

    @Override
    public double getMinValue() {
        return minValue;
    }

    @Override
    public double getMinTime() {
        return minTime;
    }

    @Override
    public double getMaxTime() {
        return maxTime;
    }

    @Override
    public double getProcessTime() {
        return processTime;
    }
}
