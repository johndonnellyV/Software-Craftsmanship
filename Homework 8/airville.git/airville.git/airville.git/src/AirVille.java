import java.util.ArrayList;

/**
 * Created by Admin on 11/18/2015.
 */
public class AirVille {
    private ArrayList<RegularLine> lines = new ArrayList<>();
    public double currentMoney;
    public int diamonds;

    private ArrayList<Supervisor> supervisors = new ArrayList<>();

    /**
     * empty constructor
     */
    public AirVille(){
    supervisors.add(new Supervisor());
    }

    /**
     * adds a line, and gets things started
     * @param line line object wanted to be added
     */
    public void addLine(RegularLine line){
        lines.add(line);
    }

    /**
     * total money that you have
     * @return
     */
    public double calculateCash(){
        double money = 0;
        for(int i= 0; i < lines.size(); i++){
            money += lines.get(i).getCurrentMoney();
        }
        return money;
    }

    /**
     * adds a supervisor to the game, only works with diamonds
     */
    public void addSupervisor(){
        if (diamonds > 0){
            diamonds--;
            supervisors.add(new Supervisor());
        }
    }

    public int getNumberOfEmployees(){
        return Employee.employeeArray.size();
    }
    public int getNumberOfPassengers(){
        return Passenger.totalPassengers.size();
    }

    public ArrayList<RegularLine> getLines() {
        return lines;
    }

    public void setLines(ArrayList<RegularLine> lines) {
        this.lines = lines;
    }
    public ArrayList<Supervisor> getSupervisors() {
        return supervisors;
    }

    public void setSupervisors(ArrayList<Supervisor> supervisors) {
        this.supervisors = supervisors;
    }
}
