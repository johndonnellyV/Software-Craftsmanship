//top class for employees
Abstract Class Employee

private List for number of employees present
boolean isWorking field

Class Agent extends Employee

field isBusy
field autoSection = null;//group of automatic counters if applicable

constructor can take array of Automatic Counters or a counter
	set them equal to the fields

scanCounters
	if the array is not null
	pop the next assistance request from the assistance Q
	make sure its not null

Class Supervisor extends Employee
field currentCounter
field speedMultiplier
field isBusy

assistCounter(counter)
	set counters field for hasSupervisor to true
	also sets currentCounter
	isBusy is true

leaveCounter()
	currentCounter = null;
	sets the counters hasSupervisor to false
	isBusy is false


//top class for passengers
Class Passenger
field canAuto
field static LinkedList for number of passengers present
field for intended ticket purchase value

constructor adds 1 to the number of passengers field also calcs canAuto change

Class FrequentFlyer extends Passsenger
canAuto is true
constructor adds 1 to the number of passengers field 
Class BaggagePassenger extends Passenger
canAuto is false
constructor adds 1 to the number of passengers field 
Class ReroutedPassenger extends Passenger
canAuto is false
constructor adds 1 to the number of passengers field

Class RefundPassenger extends Passenger 
canAuto is false
constructor adds 1 to the number of passengers field

Interface Line
field for a Queue
pushPassenger
popPassenger
pushCounter
popCounter
Class FrequentFlyerLine extends Line
field for its counter array

constructor takes array of counters it can use
push
	checks to make sure they are a frequent flyer or group of them
pop

Class RegularLine extends Line
field for its counter array
field for requests Queue
constructor takes array of counters it can use

pushPassenger
	will accept passengers and groups
popPassenger

pushCounter
popCounter

Interface CheckIn

method processPassenger for processing a customer takes a passenger or group

getNextPassenger

Class AutomatedCounter implements CheckIn
assistance needed flag
field isOccupied
field needsAgent
field line
field hasSupervisor
field for normal line

constructor needs a normal line in case a passenger needs to be added to it
	set current line and an alternate line

void processPassenger
	//takes a passenger from the Queue from automatedLine
	if canAuto
		check if a stop is gonna happen (some probability)
			if it does call requestAgent
		else isOccupied becomes false
		//set Occupied to false from agent
		add the money from the passengers field to the total
		call getNextPassenger
	else
		add the passenger to a normal line Queue
void requestAgent
	add this to Counter Queue in Line

getNextPassenger
	pop from the Q of the line and take

Class Counter implements Checkin
field currentMoney
field isOccupied
field line
field currentAgent
field time for current guest
field hasSupervisor

constructor takes the line its for
	added to the array of counters in the line
	line field set

processPassenger
	add the money from the passenger to the total
	set isOccupied to false
	call getNextPassenger
	
checkSupervisor
	if hasSupervisor is true
		cut the amount of time for processing

getNextPassenger
	pop from the Q of the line and take


Class AirVille
field arraylist for Lines 
field currentCash
field diamonds
constructor

addLine
	makes a line

addPassenger

addAgent
addSupervisor
	check if diamond is available

addAutomaticCounter
addCounter 

calculateCash
scan all lines counters

getNumberOfAgents

getNumberOfSupervisors

getNumberOfPassengers

Class Group

field for group size
field for group members (Array)
field for group completion time 


constructor takes an array of passengers
	members of the group equal the length of the array
	calls setCompletionTime

FreqFlyerCheck
	checks if all members are frequent flyers

setCompletionTime
	Calculates a discount based on the number of members
	higher discount for more people

diamondCheck
	if the size is over 10 and process under 5 min
		add a diamond to the AirVille instance


Airville is the main class
Two types of employees supervisor and agent 
multiple passenger types but regular passengers work as well
Line is the interface above both inperson line and frequent flyer line
no frequent flyer line is present for the automatic line but they can go through it if they want
group is groups of passengers, only gets in frequent flyer line if all of them are frequent
Checkin is above counter and AutomaticCounter as an interface as it should never exist on its own

Lines are created first, and then counters are added to a line
counters then can have agents assigned to them or if automatic their vicinity
has info on whether or not employees are currently busy
