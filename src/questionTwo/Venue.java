package questionTwo;

public class Venue {
	//Data fields of Venue class
	private String id;
	private int noRows;
	private int noCols;
	private Ticket[][] tickets;
	private int numOfTickets;
	private int numOfSeatsOccupied;
	
	//Constructor of Venue class
	public Venue(String id) {
		this.id = id;
		numOfTickets = 0;
		//Creates a Venue object with 7 rows and 7 columns
		if (id.charAt(1) == 'E') {
			noRows = 7;
			noCols = 7;
			//Defines the array size of tickets
			tickets = new Ticket[noRows][noCols];
			//Assigns distinct objects of Ticket class into the all the available location in tickets array
			for (int i = 0; i < noRows; i++) {
				for (int j = 0; j < noCols; j++) {
					tickets[i][j] = new Ticket(i, (j + 1), false);
				}
			}
		}
		//Creates a Venue object with 5 rows and 7 columns
		else if (id.charAt(1) == 'W') {
			noRows = 5;
			noCols = 7;
			//Defines the array size of tickets
			tickets = new Ticket[noRows][noCols];
			//Assigns distinct objects of Ticket class into the all the available location in tickets array
			for (int i = 0; i < noRows; i++) {
				for (int j = 0; j < noCols; j++) {
					tickets[i][j] = new Ticket(i, (j + 1), false);
				}
			}
		}
		//Creates a Venue object with 7 rows and 5 columns
		else if (id.charAt(1) == 'N') {
			noRows = 7;
			noCols = 5;
			//Defines the array size of tickets
			tickets = new Ticket[noRows][noCols];
			//Assigns distinct objects of Ticket class into the all the available location in tickets array
			for (int i = 0; i < noRows; i++) {
				for (int j = 0; j < noCols; j++) {
					tickets[i][j] = new Ticket(i, (j + 1), false);
				}
			}
		}
		//Creates a Venue object with 9 rows and 9 columns
		else {
			noRows = 9;
			noCols = 9;
			//Defines the array size of tickets
			tickets = new Ticket[noRows][noCols];
			//Assigns distinct objects of Ticket class into the all the available location in tickets array
			for (int i = 0; i < noRows; i++) {
				for (int j = 0; j < noCols; j++) {
					tickets[i][j] = new Ticket(i, (j + 1), false);
				}
			}
		}
		numOfSeatsOccupied = 0;
			
	}
		
	
	//Methods of Venue class
	//Getter methods
	public String getID() {
		return id;
	}
	public int getNoRows() {
		return noRows;
	}
	public int getNoCols() {
		return noCols;
	}
	public int getNumOfSeats() {
		return noRows*noCols;
	}
	//Helper getter method to return tickets array
	public Ticket[][] getTickets() {
		return tickets;
	}
	//Method to book a seat 
	public void bookASeat(int rowIdx, int seatNo) {
		tickets[rowIdx][seatNo].setIsOccupied(true);
		numOfSeatsOccupied += 1;
		numOfTickets += 1;
	}
	//Method to check if a seat is occupied
	public boolean checkOccupied(int rowIdx, int seatNo) {
		return tickets[rowIdx][seatNo].getIsOccupied();
	}
	//Returns the layout dimension of the venue, e.g.: 7X7
	public String seatVenueDisplay() {
		return Integer.toString(noRows) + " X " + Integer.toString(noCols);
	}
	//Method to check if venue is full, true if full; false if not full
	public boolean checkIfVenueIsFull() {
		if (numOfSeatsOccupied == getNumOfSeats()) 
			return true;
		else
			return false;
	}
	// Note: this static method is given
	// Converts row letter (char) to index number (int)
	public static int rowLetter2Idx(char letter) {
		return (int) (letter) - 65;
	}

	// Note: this static method is given
	// Converts index number (int) to row letter (char)
	public static char rowIndex2Letter(int idx) {
		return (char) (idx + 'A');
	}
	
	//Helper method to return a String statement about the availability information of a seat
	public String seatAvailabilityMessage(int rowIdx, int seatNo) {
		return tickets[rowIdx][seatNo].toString();
	}
	@Override
	public String toString() {
		//Returns the show details
		return "Screening location: " + id + '\n' + '\n' + "Screening hall layout (Row X Column): " + seatVenueDisplay() + '\n' + '\n' + "Total number of seats in the hall: " + getNumOfSeats() + '\n' + '\n' + "Number of seats left: " + (getNumOfSeats() - numOfSeatsOccupied) + '\n' + '\n';
	}
}