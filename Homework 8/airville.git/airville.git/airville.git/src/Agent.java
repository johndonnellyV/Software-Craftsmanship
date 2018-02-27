import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Created by Admin on 11/18/2015.
 */
public class Agent extends Employee {
    public Optional<CheckIn> currentCounter = Optional.empty();
    public Optional<RegularLine> line = Optional.empty();
    public boolean isBusy = false;

    /**
     * takes a counter for reg lines
     * @param counter
     */
    public Agent(Counter counter){
        counter.setCurrentAgent(Optional.of(this));
        this.currentCounter = Optional.of(counter);
        employeeArray.add(this);
    }

    /**
     * takes automatic counter line
     * @param line
     */
    public Agent(RegularLine line){
        employeeArray.add(this);
        this.line = Optional.of(line);
    }


    /**
     * move the agent to an automatic counter
     * @param aCounter
     */
    public void assistCounter(AutomatedCounter aCounter){
        aCounter.setCurrentAgent(this);
        aCounter.hasAgent = true;
        this.currentCounter = Optional.of(aCounter);
        isBusy = true;
    }

    /**
     * agent leaves the counter he was at
     */
    public void leaveCounter(){
        currentCounter = Optional.empty();
        isBusy = false;
    }

    /**
     * pops from the assistQ if a line is present
     */
    public void scanCounters(){
        if(line.isPresent()){
            try {
                line.get().popCounter();
            }
            catch(NoSuchElementException e){
                System.out.println("No counters need help");
            }
            }
        }
    }

