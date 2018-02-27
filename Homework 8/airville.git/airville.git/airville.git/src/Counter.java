import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;
import jdk.nashorn.internal.runtime.regexp.joni.encoding.ObjPtr;

import java.util.Optional;

/**
 * Created by Admin on 11/18/2015.
 */
public class Counter implements CheckIn {
    private double currentMoney = 0;
    private Optional<LineEntity> currentPassenger = Optional.empty();
    private RegularLine line;
    private Optional<Agent> currentAgent = Optional.empty();
    private Optional<Supervisor> supervisor = Optional.empty();
    private double processTime;

    /**
     * creates a counter for a line
     * @param line
     */
    public Counter(RegularLine line){
        line.addCounter(this);
        this.line = line;
    }

    /**
     * calcs the amount of time to process current entity
     * @param passenger
     */
    public void calculateProcessTime(Passenger passenger){
        if (currentPassenger.isPresent()){
            if(supervisor.isPresent()){
                this.processTime = passenger.getProcessTime() * supervisor.get().getMultiplier();
        }
            else{
                this.processTime = passenger.getProcessTime();
            }
        }

    }

    /**
     * takes money from entity and calls next one from the line
     * @param passenger
     */
    public void processPassenger(LineEntity passenger){
        currentPassenger.get().setIsBusy(true);
        if(currentAgent.isPresent()) {
            currentMoney += passenger.getTicketValue();
            currentPassenger.get().setIsBusy(false);
            this.getNextPassenger();
            currentPassenger.empty();
        }
        else{
            System.out.println("no agent is present");
        }
    }

    /**
     * gets the next passenger from the Q
     */
    public void getNextPassenger() {
        try {
            currentPassenger = Optional.of(line.popPassenger());
        }
        catch(NullPointerException e){
            System.out.println("No one is in line");
        }
    }

    public void setSupervisor(Supervisor supervisor){
        this.supervisor = Optional.of(supervisor);
        }
    //how to take away a supervisor
    public void setSupervisor(){
        this.supervisor.empty();
    }
    public double getCurrentMoney(){
        return this.currentMoney;
    }

    public Optional<LineEntity> getCurrentPassenger() {
        return currentPassenger;
    }

    public void setCurrentPassenger(Optional<LineEntity> currentPassenger) {
        this.currentPassenger = currentPassenger;
    }
    public Optional<Supervisor> getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Optional<Supervisor> supervisor) {
        this.supervisor = supervisor;
    }

    public Optional<Agent> getCurrentAgent() {
        return currentAgent;
    }

    public void setCurrentAgent(Optional<Agent> currentAgent) {
        this.currentAgent = currentAgent;
    }
}
