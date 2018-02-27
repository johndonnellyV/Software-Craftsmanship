import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Admin on 11/4/2015.
 */
public class SecurityLogBlackLister {
    public static Scanner scanner;
    public static boolean scanNext = false;
    public static int maxAttempts;
    public StringBuilder builder = new StringBuilder();

    public SecurityLogBlackLister(int maxAttempts){
        this.maxAttempts = maxAttempts;
    }

    /**
     * Main method to run the program
     * @param args takes a maxAttempt
     * @throws FileNotFoundException thrown if file can't be located
     */
    public static void main(String[] args) throws FileNotFoundException {
        scanner = new Scanner(System.in);
        SecurityLogBlackLister test = new SecurityLogBlackLister(3);
        if (args.length != 0) {
            if(Integer.parseInt(args[0]) > 0){
                maxAttempts = Integer.parseInt(args[0]);
            }
        }
            test.generateBlacklist();

        scanner.close();
    }

    /**
     * Generates the black list for the file
     */
    public void generateBlacklist(){
        String failedLogin = "Failed logins from:";
        String illegalUser = "Illegal users from:";
        //builder.append("Results: \n");

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals(failedLogin)) {
                line = scanner.nextLine();
                processPossibleFailure(line);
            }
            else if (line.equals(illegalUser)){
                line = scanner.nextLine();
                processPossibleFailure(line);
            }
        }
        System.out.print(builder.toString());
    }

    /**
     * takes in a string that could be valid data
     * @param line string of the line
     */
    public void processPossibleFailure(String line) {
        scanNext = false;
        String IPAddress;
        String domain = "";
        String attemptNumber;
        String[] lineArray = new String[3];

        boolean readingFailures = true;
        String[] parts = line.split("\\s+");
        for(int i = 0; i < lineArray.length; i++) {
            if (parts.length < 1){

            }
            else {
                lineArray = processLine(parts, parts.length);
            }
        }
        IPAddress = lineArray[0];
        if (lineArray[1] == null) {
            domain = "";
        }
        else{
            domain = lineArray[1];
        }
        attemptNumber = lineArray[2];
        blackListProcessor(domain, attemptNumber, IPAddress);
        if (scanner.hasNext()){
            processPossibleFailure(scanner.nextLine());
        }
    }

    /**
     * Processes which version of addToBlacklist should be called
     * @param domain
     * @param attemptNumber
     * @param IPAddress
     */
    public void blackListProcessor(String domain, String attemptNumber, String IPAddress){
        try {
            if (blackListCheck(Integer.parseInt(attemptNumber))) {
                if (domain.equals("")) {
                    addToBlacklist(IPAddress);
                } else {
                    addToBlacklist(IPAddress, domain);
                }
            }
        }
        catch(NumberFormatException e){
            //blank because nothing should be added to the list but nothing bad should happen
        }
    }

    /**
     * checks if the number is high enough to be added to the blacklist
     * @param i
     * @return true if it should be added
     */
    public boolean blackListCheck(int i){
        if (i > maxAttempts){
            return true;
        }
        return false;
    }

    /**
     * goes through the line to see if the data is valid
     * @param parts array of strings that compose the line
     * @param input size of array
     * @return array of domain ip and attempt number
     */
    public String[] processLine(String[] parts, int input) {
        String[] array = new String[3];
            for (int i = 0; i < input; i++) {
                if (isValidIP(parts[i])) {
                    array[0] = parts[i];
                } else if (isValidAttemptNumber(parts, i)) {
                    array[2] = parts[i];
                    scanNext = true;
                } else if (isValidDomain(parts[i])) {
                    array[1] = parts[i];
                }
            }
        return array;
    }

    /**
     * checks if the AttemptNumber syntax is valid
     * @param parts array of strings from the line
     * @param i index to check
     * @return true if the syntax is valid
     */
    public boolean isValidAttemptNumber(String parts[], int i){
        try {
            if (Integer.parseInt(parts[i]) > -1 && (parts[i + 1].equals("times") || parts[i + 1].equals("time"))) {
                return true;
            }
        }
        catch(NumberFormatException e){
            return false;
        }
        return false;
    }

    /**
     * checks if the domain is valid
     * @param domain string to check
     * @return true if it is valid
     */
    public boolean isValidDomain(String domain) {
        String[] partsOfLine = domain.split("\\.", -2);
        if (partsOfLine.length == 1){
            return false;
        }
        for (int i = 0; i < partsOfLine.length - 1; i++) {
            if (!partsOfLine[0].contains("(")){
                return false;
            }
            if (partsOfLine[i].contains(" ") || partsOfLine[i].contains("_")) {
                return false;
            }
        }
        return true;
    }

    /**
     * checks if the IP address is valid
     * @param string ip to check
     * @return true if the IP address is valid
     */
    public boolean isValidIP(String string) {
        String[] parts = string.split("\\.", -1);
        if(parts[parts.length - 1].contains(":")){
            parts[parts.length - 1] = parts[parts.length - 1].substring(0, parts[parts.length - 1].length()-1);
        }
        return parts.length == 4 // 4 parts
                && Arrays.stream(parts)
                .filter(SecurityLogBlackLister::isNumeric) // Only numbers
                .map(Integer::parseInt)
                .filter(i -> i <= 255 && i >= 0) // Must be inside [0, 255]
                .count() == 4; // 4 numerical parts inside [0, 255]
    }

    /**
     * Check that a string consists of only numbers
     * @param string The string to check
     * @return true if string consits of only numbers, false otherwise
     */
    public static boolean isNumeric(String string) {
        for(char c : string.toCharArray()) {
            if(c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    /**
     * adds to blacklist if a domain is present
     * @param IPAddress ip address
     * @param domain domain
     */
    public void addToBlacklist(String IPAddress, String domain){
        builder.append(domain.substring(1, domain.length() - 2) +", "+ IPAddress + "\\" + "\n");
    }

    /**
     * adds to blacklist if a domain is not present
     * @param IPAddress ip address
     */
    public void addToBlacklist(String IPAddress){
        builder.append(IPAddress.substring(0, IPAddress.length() - 1)+ "\\" + "\n");
    }

    /*public class BlackListerTester extends SecurityLogBlackLister {
        public StringBuilder builder = new StringBuilder();
        public BlackListerTester(int input) {
            super(input);
        }
        public void addToBlacklist(String IPAddress, String domain) {
            builder.append(domain.substring(1, domain.length() - 2) + ", " + IPAddress + "\\" + "\n");
        }
        public void addToBlacklist(String IPAddress) {
            builder.append(IPAddress.substring(0, IPAddress.length() - 1) + "\\" + "\n");
        }
        public boolean isNumeric(String string) {
            return SecurityLogBlackLister.this.isNumeric(string);
        }
        public void generateBlacklist() {
            SecurityLogBlackLister.this.generateBlacklist();
        }
        public void processPossibleFailure(String line) {
            SecurityLogBlackLister.this.processPossibleFailure(line);
        }
        public void blackListProcessor(String domain, String attemptNumber, String IPAddress) {
            SecurityLogBlackLister.this.blackListProcessor(domain, attemptNumber, IPAddress);
        }
        public boolean blackListCheck(int i) {
            return SecurityLogBlackLister.this.blackListCheck(i);
        }
        public String[] processLine(String[] parts, int input) {
            return SecurityLogBlackLister.this.processLine(parts, input);
        }
        public boolean isValidAttemptNumber(String parts[], int i) {
            return SecurityLogBlackLister.this.isValidAttemptNumber(parts, i);
        }
        public boolean isValidDomain(String domain) {
            return SecurityLogBlackLister.this.isValidDomain(domain);
        }
        public boolean isValidIP(String string) {
            return SecurityLogBlackLister.this.isValidIP(string);
        }*/
    }