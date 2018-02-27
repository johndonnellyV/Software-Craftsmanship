import java.util.Optional;

/**
 * Created by Admin on 11/18/2015.
 */
public class AutomatedCounter implements CheckIn {
    public boolean needsAssistance = false;
    public double currentMoney = 0;

    private Optional<LineEntity> currentPassenger = Optional.empty();
    private RegularLine line;
    private RegularLine altLine;

    public void setCurrentAgent(Agent currentAgent) {
        this.currentAgent = currentAgent;
    }

    private Agent currentAgent;
    public Optional<Supervisor> supervisor = Optional.empty();
    private double processTime;
    public boolean hasAgent = false;

    /**
     * constructor takes a line and an altline to send non auto customers to
     * @param line
     * @param altLine
     */
    public AutomatedCounter(RegularLine line, RegularLine altLine){
        line.addCounter(this);
        this.line = line;
        this.altLine = altLine;

    }

    /**
     * calculates the process time based on if a supervisor is needed
     * @param passenger
     */
    public void calculateProcessTime(Passenger passenger){
        if (currentPassenger.isPresent()){
            if(supervisor.isPresent() == true){
                this.processTime = passenger.getProcessTime() * supervisor.get().getMultiplier();
            }
            else{
                this.processTime = passenger.getProcessTime();
            }
        }
    }

    /**
     * takes the money from the current passenger or group and calls the next one
     * @param passenger input entity
     */
    public void processPassenger(LineEntity passenger){
        try {
            if (currentPassenger.get().getCanAuto()) {
                currentPassenger.get().setIsBusy(true);
                double stopNumb = Math.random() * (.99 - .01) + .01;
                if (stopNumb > .2) {
                    requestAgent();
                } else {
                    currentMoney += passenger.getTicketValue();
                    currentPassenger.get().setIsBusy(false);
                    currentPassenger.empty();
                    getNextPassenger();
                    hasAgent = false;
                }
            } else {
                altLine.pushPassenger(currentPassenger.get());
                currentPassenger.empty();
                getNextPassenger();
            }
        }
        catch(NullPointerException e){
            System.out.println("cant process no passenger");
        }
    }

    /**
     * adds this counter to the assitance Q
     */
    public void requestAgent(){
        line.pushCounter(this);
    }

    /**
     * gets the next passenger from the line
     */
    public void getNextPassenger() {
        try {
            currentPassenger = Optional.of(line.popPassenger());
        }
        catch(NullPointerException e){
            System.out.println("No one is in line");
        }
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = Optional.of(supervisor);
    }

    public double getCurrentMoney() {
        return currentMoney;
    }

    public Optional<LineEntity> getCurrentPassenger() {
        return currentPassenger;
    }

    public RegularLine getLine() {
        return line;
    }

    public Agent getCurrentAgent() {
        return currentAgent;
    }

    public void setCurrentPassenger(Optional<LineEntity> currentPassenger) {
        this.currentPassenger = currentPassenger;
    }
    public Optional<Supervisor> getSupervisor() {
        return supervisor;
    }

    public double getProcessTime() {
        return processTime;
    }
}
