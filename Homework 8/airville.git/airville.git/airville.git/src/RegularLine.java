import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Admin on 11/18/2015.
 */
public class RegularLine {
    protected LinkedList<LineEntity> passengerQ = new LinkedList<>();
    protected LinkedList<AutomatedCounter> assistQ = new LinkedList<>();
    protected ArrayList<CheckIn> counterArray = new ArrayList<>();
    protected double currentMoney = 0;

    /**
     * creates a new line
     */
    public RegularLine(){

    }

    /**
     * adds a counter to the line, can be automated or in person
     * @param counter the counter to add to the line
     */
    public void addCounter(CheckIn counter){
        counterArray.add(counter);
    }

    /**
     * puts a passenger in the line
     * @param passenger
     */
    public void pushPassenger(LineEntity passenger){
        passengerQ.push(passenger);
    }

    /**
     * pops a passenger to a counter
     * @return
     */
    public LineEntity popPassenger(){
       return passengerQ.pollLast();
    }

    /**
     * adds a counter to the needs assistance Q
     * @param aCounter
     */
    public void pushCounter(AutomatedCounter aCounter){
        assistQ.push(aCounter);
    }

    /**
     * gets the current money the line has made
     * @return
     */
    public double getCurrentMoney(){
        double tempMoney = 0;
        double temp = 0;
        for(int i = 0; i < counterArray.size(); i++){
            tempMoney =  counterArray.get(i).getCurrentMoney();
            temp = temp + tempMoney;
        }
        return temp;
    }

    /**
     * pops the counter from the assistance Q when an agent handles it
     * @return
     */
    public AutomatedCounter popCounter(){
        return assistQ.pollLast();
    }
}

