package questionTwo;
import java.util.Scanner;
import java.util.InputMismatchException;
/**
 * F28PA | Software Development A | Coursework
 * 
 * The Coursework specification is provided in Canvas. Please read through it in
 * full before you start work.
 * 
 * @author CHIA ZHENG RONG
 */
public class BookingSoft {
	public static void main(String[] args) throws InterruptedException {
		int index;
		// There are currently 6 shows offered in 6 different venues.
		Show[] shows = new Show[6];
		
		// Uncomment lines 17 to 22 once the constructors have been written.
		shows[0] = new Show("1N", new Film("SING", 1));
		shows[1] = new Show("2W", new Film("THE GRINCH", 2));
		shows[2] = new Show("3E", new Film("BOSS BABY", 3));
		shows[3] = new Show("3S", new Film("YES DAY", 3));
		shows[4] = new Show("1E", new Film("THE KARATE KID", 1));
		shows[5] = new Show("2N", new Film("THE SEA BEAST", 2));
		System.out.println("### Welcome to the Booking System ###\n");
		// DO NOT CHANGE THE ABOVE PART OF THE CODE.
		///////////////////////////////////////////////////////////////////////////////////
		
		while (true) {
			System.out.println("Below are the 6 shows that will be offered tomorrow: " + '\n');
			//Formats the string values as a single formatted string value, so that the printed string looks more organized
			String formattedString = String.format("%-17s %-18s %-21s", "MOVIE NAME", "SCREENING TIME", "SCREENING LOCATION");
			//prints out all the available shows and their screening times as well as locations in an organized way 
			System.out.println(formattedString);
			//Loops through the shows array 
			for (int i = 0; i < shows.length; i++) {
				System.out.println(shows[i].locationInfo());
			}
			
			//Informs the user of which venue is still available for booking or otherwise 
			System.out.println('\n' + "IMPORTANT: " + '\n' + "Please note that: " + '\n');
			//Loops through the shows array and print out all the shows' availability for booking 
			for (int i = 0; i < shows.length; i++) {
				shows[i].printAvailability();
				System.out.println();
			}
			System.out.println();
			Scanner scan = new Scanner(System.in);
			//Asks user for a movie name
			System.out.print("Please enter the name of the movie that you want to watch (ENTER MOVIE NAMES FROM THE LIST ONLY): ");
			//Checks if the user has entered a correct movie name from the list
			while (true) {
				String filmResponse = scan.nextLine();
				if ((filmResponse.toUpperCase()).equals("SING") || (filmResponse.toUpperCase()).equals("THE GRINCH") || (filmResponse.toUpperCase()).equals("BOSS BABY") || (filmResponse.toUpperCase()).equals("YES DAY") || (filmResponse.toUpperCase()).equals("THE KARATE KID") || (filmResponse.toUpperCase()).equals("THE SEA BEAST")) {
					//Assigns an integer ranging from 0 to 5 into index variable according to the corresponding correct movie input by user and break loop
					if ((filmResponse.toUpperCase()).equals("SING")) {
						index = 0;
					}
					else if ((filmResponse.toUpperCase()).equals("THE GRINCH")) {
						index = 1;
					}
					else if ((filmResponse.toUpperCase()).equals("BOSS BABY")) {
						index = 2;
					}
					else if ((filmResponse.toUpperCase()).equals("YES DAY")){
						index = 3;
					}
					else if ((filmResponse.toUpperCase()).equals("THE KARATE KID"))	{
						index = 4;
					}
					else
						index = 5;
					break;
				}
				else {
					//continue looping if wrong movie name is entered
					System.out.print("Invalid movie name, please enter a movie name as seen FROM THE LIST OF MOVIES ABOVE ONLY: ");
					continue;
				}
			}
			
			//Restart the main loop if the venue of the show selected by the user is fully occupied
			if (shows[index].availability()) {
				System.out.print('\n' + "The venue of your selected film is fully occupied, please try selecting other films that are still available (venue not occupied)");
				continue;
			}
			
			//Prints out the details of the selected movie by the user
			System.out.println('\n' + "Details of the movie that you selected: " + '\n' + '\n' + shows[index].toString());
			shows[index].printHall();
			
			//Assigns the maximum Row Letter of the hall into maxLetter variable
			char maxLetter = Venue.rowIndex2Letter((shows[index].rowNum()) - 1);
			
			char responseToCapital = 'A';
			int seatNoResponse = 0;
			while (true) {
				//Shows a more detailed seat layout that indicates occupied seat(s)
				System.out.println('\n' + "Below is the seat layout display showing any occupied seats for the movie of your interest (if any): ");
				shows[index].printHallWithOccupiedSeats();
				
				//Asks user to select a vacant seat in the hall
				System.out.println('\n' + "The seats that are marked with 'XX' inidcates that they have already been occupied. " + '\n' + "Please enter a Row Letter followed by a Seat Number of your preferred seat that you want to book: " + '\n');
				//Asks user to input Row Letter
				System.out.print("Please enter the Row Letter (ONLY 'A'-'" + maxLetter + "') : ");
				
				//Loop to check if the user only enters characters from 'A' to max Row Letter in the hall 
				while (true) {
					String rowResponse = scan.nextLine();
					//Continue looping if user input more than 1 letter
					if (rowResponse.length() > 1) {
						System.out.println("Error: Cannot accept more than 1 letters");
						System.out.print("Please enter only ONE letter (Row Letter of your preferred seat from 'A' to '" + maxLetter + "'): ");
					}
					//Checks if the single letter input by user is a letter from 'A' to the max Row Letter in the hall
					else {
						responseToCapital = Character.toUpperCase(rowResponse.charAt(0));
						//Checks if the single letter input by user is a letter from 'A' to 'Z'
						if (responseToCapital >= 'A' && responseToCapital <= 'Z') {
							//Breaks the loop if the single letter input by user is a letter from 'A' to the max Row Letter in the hall
							if (responseToCapital >= 'A' && responseToCapital <= maxLetter)
								break;
							//Continue looping if the single letter input by user exceeds the max Row Letter in the hall
							else {
								System.out.println("Error: there is no row '" + responseToCapital + "' in the hall");
								System.out.print("Please enter a single letter character (Row Letter of your preferred seat from only 'A' to '" + maxLetter + "'): ");
							}
						}
						//Continue looping if the single letter input by user is not a letter character
						else {
							System.out.println("Error: Cannot accept characters other than letter characters");
							System.out.print("Please enter a single LETTER character (Row Letter of your preferred seat from 'A' to '" + maxLetter + "'): ");
						}
					}
					
				}
				
				//Asks user to input Seat Number
				System.out.print('\n' + "Please enter the Seat Number of your preferred seat (1-" + shows[index].colNum() + "): ");
				
				//Loops to check if the user input is of a correct value type (integer) and ranges between 1 to the maximum seat number in the hall  
				while (true) {
					//Try-catch block to catch any wrong input value type by the user
					try {
						seatNoResponse = scan.nextInt();
						scan.nextLine();
						//Breaks the loop if the integer user input ranges from 1 to the maximum seat number in the hall
						if (seatNoResponse >= 1 && seatNoResponse <= shows[index].colNum()) 
							break;
						//Continue looping if the integer user input is not from range 1 to the maximum seat number in the hall
						else {
							System.out.println("Error: Seat Number cannot be smaller than 1 or cannot exceed the maximum seat number available in the hall");
							System.out.print("Please enter a positive whole number (Seat Number of your preferred choice) ranging only from 1 to " + shows[index].colNum() + ": ");
						}
							
					}catch (InputMismatchException e) {
						System.out.println("Error: input value type not recognized");
						System.out.print("Please enter a positive whole number (Seat Number of your preferred choice) ranging from 1 to " + shows[index].colNum() + ": ");
						scan.nextLine();
					}
				}
				
				//Checks if the selected seat by the user is occupied or not 
				boolean occupied = shows[index].seatCheckAvailability(responseToCapital, seatNoResponse);
				
				//Continue looping if the selected seat by the user is occupied
				if (occupied) {
					System.out.print('\n' + "Sorry! Seat ");
					shows[index].printSeatAvailability(responseToCapital, seatNoResponse);
					System.out.println(", please select a another seat. " + '\n' + '\n' + "To avoid choosing an occupied seat, " + '\n' + "Please look at the detailed seat layout that will be provided below more carefully before you select your seat. ");
				}
				//Breaks the loop and print a booking confirmation message if the selected seat by the user is not occupied (available)
				else {
					System.out.print('\n' + "You got it! Seat ");
					shows[index].printSeatAvailability(responseToCapital, seatNoResponse);
					System.out.println("! Please wait while we book the seat for you....");
					shows[index].buyTicket(responseToCapital, seatNoResponse);
					System.out.println('\n' + "Congratulations! Your booking of seat " + responseToCapital + seatNoResponse + " is successful! " + '\n' + '\n');
					break;
				}
			}
			
			
			int occupied = 0;
			//Loops through the shows array to see how many shows' venues are fully occupied
			for (int i = 0; i < shows.length; i++) {
				//Adds 1 to occupied variable if the show's venue is fully occupied
				if (shows[i].availability() == true) 
					occupied += 1;
				//Continue looping if the show's venue is not fully occupied
				else
					continue;
			}
			
			//Breaks the main loop if all the show's venues are fully occupied, i.e. all 6 shows' venues are fully occupied
			if (occupied == shows.length) {
				System.out.println("All the shows' venues are fully occupied! Thank you for the support!");
				break;
			}
			//Continue looping if not all the show's venues are fully occupied, i.e. not all 6 shows' venues are fully occupied
			else {
				System.out.println("### Welcome to the Booking System ###\n");
				continue;
			}
			
		}
		
		
		
		
		
	}
}
