/**
 * Created by Admin on 11/18/2015.
 */
public class RefundPassenger extends Passenger {
    private boolean canAuto = false;
    public double ticketValue;
    private double maxValue = -30;
    private double minValue = -3000;
    private final double minTime = .2;
    private final double maxTime = 2;
    //time for processing
    public double processTime;

    public RefundPassenger(){
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
