package questionTwo;

public class Ticket {
	//Data fields of Ticket class
	private char rowLetter;
	private int seatNo;
	private boolean isOccupied;
	
	//Constructor for Ticket class
	//Creates an object of class Ticket with rowLetter, seatNo and isOccupied values
	public Ticket(int rowIdx, int colIdx, boolean isOccupied) {
		rowLetter = Venue.rowIndex2Letter(rowIdx);
		seatNo = colIdx;
		this.isOccupied = isOccupied;
	}
	
	//Methods of Ticket class
	//Setter methods
	public char getRowLetter() {
		return rowLetter;
	}
	public int getSeatNo() {
		return seatNo;
	}
	public boolean getIsOccupied() {
		return isOccupied;
	}
	//Setter method for isOccupied
	public void setIsOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}
	@Override
	public String toString() {
		return "";
	}
}