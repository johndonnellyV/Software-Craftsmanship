/**
 * Created by Admin on 11/18/2015.
 */
public interface LineEntity {
    /**
     * true if busy
     * @return
     */
    public boolean getIsBusy();

    /**
     * sets busy status
     */
    public void setIsBusy(boolean bol);

    /**
     * true if it can use automated machine
     * @return
     */
    public boolean getCanAuto();

    /**
     * gets the value of the ticket
     * @return
     */
    public double getTicketValue();

}
