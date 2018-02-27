import java.util.ArrayList;

/**
 * Created by Admin on 11/18/2015.
 */
public abstract class Employee {
    public static ArrayList<Employee> employeeArray = new ArrayList<>();
    public boolean isBusy;
    public CheckIn currentCounter;

    /**
     * leaves the counter entity is currently at
     * implies all must have an assistcounter but they are handled
     * differently depending on what object is passed in
     */
    public abstract void leaveCounter();

}
