/**
 * Created by Admin on 11/18/2015.
 */
public interface CheckIn {
    /**
     * take money from the passenger
     * @param passenger
     */
    public void processPassenger(LineEntity passenger);

    /**
     * get the next passenger from the Q
     */
    public void getNextPassenger();

    /**
     * set a supervisor to this desk
     * @param supervisor
     */
    public void setSupervisor(Supervisor supervisor);

    /**
     * get the current money of the class
     * @return
     */
    public double getCurrentMoney();
}
