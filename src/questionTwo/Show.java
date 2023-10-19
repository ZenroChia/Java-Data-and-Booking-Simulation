package questionTwo;

public class Show {
	//Data fields of Show class
	private Film filmName;
	private Venue venueID;
	
	//Constructor for Show class
	public Show(String VenueID, Film filmName) {
		venueID = new Venue(VenueID);
		this.filmName = filmName;
	}
	
	//Methods of Show class
	//Method to buy ticket that calls the methods of Venue class to get the job done
	public void buyTicket(char row, int seatNo) {
		int rowIdx = Venue.rowLetter2Idx(row);
		venueID.bookASeat(rowIdx, (seatNo - 1));
	}
	
	//Check if a selected seat is available, false: available; true: not available
	public boolean seatCheckAvailability(char row, int seatNo) {
		int rowIdx = Venue.rowLetter2Idx(row);
		return venueID.checkOccupied(rowIdx, (seatNo - 1));
	}
	
	//Prints out the layout of the hall with their respective seat location
	public void printHall() {
		int row = venueID.getNoRows();
		int col = venueID.getNoCols();
		//Loops through the tickets array from the Venue class
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				//Converts the rowLetter and seatNo of the current Ticket class object into String and integer
				String rowLetter = Character.toString(((venueID.getTickets())[i][j]).getRowLetter());
				int seatNo = ((venueID.getTickets())[i][j]).getSeatNo();
				//Prints out the current rowLetter and seatNo combined with a space after that 
				System.out.print(rowLetter + seatNo + " ");
			}
			System.out.println();
		}
		
	}
	
	//Prints out a more detailed version of the seats layout with indications to occupied seats
	public void printHallWithOccupiedSeats() {
		int row = venueID.getNoRows();
		int col = venueID.getNoCols();
		//Loops through the tickets array from the Venue class
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (((venueID.getTickets())[i][j]).getIsOccupied()) {
					System.out.print("XX ");
				}
				else {
					//Converts the rowLetter and seatNo of the current Ticket class object into String and integer
					String rowLetter = Character.toString(((venueID.getTickets())[i][j]).getRowLetter());
					int seatNo = ((venueID.getTickets())[i][j]).getSeatNo();
					//Prints out the current rowLetter and seatNo combined with a space after that 
					System.out.print(rowLetter + seatNo + " ");
				}
				
			}
			System.out.println();
		}
	}
	
	//Prints if a venue is fully occupied or not 
	public void printAvailability() {
		boolean full = venueID.checkIfVenueIsFull();
		if (full) {
			System.out.println("The venue for film '" + filmName.getTitle() + "' is FULLY occupied, please DO NOT select this film. ");
		}
		else
			System.out.println("The venue for film '" + filmName.getTitle() + "' is NOT FULLY occupied, please FEEL FREE to select this film. ");
	}
	
	//Helper methods to check if a venue is fully occupied or not, true if fully occupied; false if not fully occupied
	public boolean availability() {
		return venueID.checkIfVenueIsFull();
	}
	
	//Gets and returns the venue location and its screening information 
	public String locationInfo() {
		//Formats the string values and return them as a formatted string value
		String format = String.format("%-17s %-18s %-21s", filmName.getTitle(), filmName.getSession(), venueID.getID());
		return format;
	}
	
	//Helper methods to get the number of rows and number of columns of the venue hall
	public int rowNum() {
		return venueID.getNoRows();
	}
	
	public int colNum() {
		return venueID.getNoCols();
	}
	
	@Override
	public String toString() {
		//returns the show details
		String format = String.format("%-17s %-18s %-21s", filmName.getTitle(), filmName.getSession(), locationInfo());
		return "Film name: " + filmName.getTitle() + '\n' + '\n' + "Screening time: " + filmName.getSession() + '\n' + '\n' + venueID.toString() + "Seat layout display: ";
	}

}