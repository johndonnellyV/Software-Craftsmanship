/**
 * Created by Admin on 11/18/2015.
 */
public class Group implements LineEntity {
    private Passenger[] passengers;
    public int groupSize;
    public int processTime;
    public double ticketValue;
    public boolean canAuto = true;
    public boolean isBusy = false;

    private double maxValue = .85;
    private double minValue = .6;

    /**
     * constructor for a group of passengers takes an array of passengers
     * @param passengers the array of passengers passed in
     */
    public Group(Passenger[] passengers){
        this.passengers = passengers;
        this.groupSize = passengers.length;
        double tempValues = 0;
        for (int i = 0; i < passengers.length; i++){
            if (passengers[i].getCanAuto() == false){
                canAuto = false;
            }
            tempValues += passengers[i].getTicketValue();
        }
        this.ticketValue = tempValues;
    }

    /**
     * calculates the processTime by grabbing passenger processtimes and adding them
     */
    public void calculateProcessTime(){
        int tempTime = 0;
        for(int i = 0; i < passengers.length; i++){
            tempTime += passengers[i].processTime;
        }
        this.processTime = (int) (tempTime * (Math.random() * (maxValue - minValue) + minValue));
    }

    /**
     * checks if the group are all frequent flyers
     * @return
     */
   // public boolean freqFlyerCheck(){
        //for(int i = 0; i < passengers.length; i++){

        //}
        //return true;
    //}

    /**
     * checks if the group was worth a diamond
     * @return
     */
    public boolean diamondCheck(){
        if(groupSize > 10 && processTime < 5){
            return true;
        }
        return false;
    }

    /**
     * checks if the group can use automatic counter
     * @return
     */
    public boolean getCanAuto(){
        return canAuto;
    }
    public Passenger[] getPassengers() {
        return passengers;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public int getProcessTime() {
        return processTime;
    }

    public double getTicketValue() {
        return ticketValue;
    }

    public boolean isCanAuto() {
        return canAuto;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public double getMinValue() {
        return minValue;
    }
    public void setIsBusy(boolean bool){
        this.isBusy = bool;
    }
    public boolean getIsBusy(){
        return this.isBusy;
    }
}
