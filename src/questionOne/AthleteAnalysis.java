package questionOne;
import java.util.InputMismatchException;
import java.util.Scanner; //imports Scanner class
import java.io.File;
import java.io.FileNotFoundException;

/**
 * F28PA | Software Development A | Coursework
 * 
 * The Coursework specification is provided in Canvas. Please read through it in
 * full before you start work.
 * 
 * @author CHIA ZHENG RONG 
 */
public class AthleteAnalysis {
	public static void main(String[] args) throws FileNotFoundException{
		//Defines the data type of the 7 data arrays
		String[] name = new String[1];
		char[] gender = new char[1];
		int[] age = new int[1];
		double[] height = new double[1];
		double[] weight = new double[1];
		String[] sport = new String[1];
		String[] medal = new String[1];
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Hi, welcome to Athlete Analysis!");
		//Asks user to choose the method of data input 
		System.out.println("Enter '1' to start entering athlete details manually; or" + '\n' + "Enter any whole number to input the athlete details through text files");
		System.out.print('\n' + "Please provide your response: ");
		//Checks if the user input is a whole number, if not, continue looping until user input is whole number
		int inputResponse = 0;
		while (true) {
			//Try-catch block to catch any data type errors while user input, users can only input integers
			try {
				inputResponse = scan.nextInt();
				scan.nextLine();
				break;
			}catch (InputMismatchException e) {
				System.out.println("Error: input format not recognized");
				System.out.print("Please enter a whole number ('1' or '2' only): ");
				scan.nextLine();
			}
		}
	
		//Reads data from the text file if integers other than 1 is entered
		if (inputResponse != 1) {
			Scanner scn = new Scanner(new File("data.txt"));
			//Loop to read the text file
			int count = -1;
			while (scn.hasNextLine()) {
				//Reads the first line (information about the number of lines in the text) of the text file
				if (count == -1) {
					scn.useDelimiter(",");
					int numInput = scn.nextInt();
					//Defines the length of the 7 data arrays according to the number of lines in the text file 
					name = new String[numInput];
					gender = new char[numInput];
					age = new int[numInput];
					height = new double[numInput];
					weight = new double[numInput];
					sport = new String[numInput];
					medal = new String[numInput];
					scn.nextLine();
					count += 1;
					
				}
				//Reads the rest of the 20 lines in the text file containing athlete details and stores those athlete information into the 7 different data arrays 
				else {
					String line = scn.nextLine();
					//Splits the values on the same line and stores these values as string values in the array i
					String[] i = line.split(",");
					//Retrieves String values from the array i and storing each value them into 7 different data arrays, converting value types whenever necassary
					name[count] = i[0];
					gender[count] = i[1].charAt(0);
					age[count] = Integer.parseInt(i[2]);
					height[count] = Double.parseDouble(i[3]);
					weight[count] = Double.parseDouble(i[4]);
					sport[count] = i[5];
					medal[count] = i[6];
					count += 1;
				}
				
			}
			
			
			
		}
		//Gets data through manual user input if integer 1 is entered
		else {
			//Asks user for number of data entries
			System.out.print("How many athletes do you wish to have their imformation keyed into the system: ");
			//Repeats until a positive whole number is entered
			int dataEntryNum;
			while (true) {
				try {
					dataEntryNum = scan.nextInt();
					scan.nextLine();
					if (dataEntryNum >= 1) {
						System.out.println();
						break;
					}
					else {
						System.out.println("Only positive whole numbers are accepted");
						System.out.print("Please enter a POSITIVE whole number: ");
						continue;
					}
				}catch (InputMismatchException e) {
					System.out.println("Error: input format not recognized");
					System.out.print("Please enter a positive WHOLE NUMBER: ");
					scan.nextLine();
				}
			}
			
			//Defines the length of the 7 arrays for data entry
			name = new String[dataEntryNum];
			gender = new char[dataEntryNum];
			age = new int[dataEntryNum];
			height = new double[dataEntryNum];
			weight = new double[dataEntryNum];
			sport = new String[dataEntryNum];
			medal = new String[dataEntryNum];
			
			//Prompts user to enter athlete details
			for (int i = 1; i <= dataEntryNum; i++) {
				System.out.println("Details of athlete " + i + ": ");
				System.out.println();
				//User to enter athlete name
				System.out.print("Plase enter the name of athlete " + i + ": ");
				name[i - 1] = scan.nextLine();
				System.out.println();
				//User to input athlete gender
				System.out.print("Please enter the gender of athlete " + i + " (e.g.: m, f, M, F, male, female): ");
				//Repeats until the correct format of gender is entered, i.e. m, f, M, F, male, female, ignoring case
				while(true) {
					String genderResponse = scan.nextLine();
					if ((genderResponse.toUpperCase()).equals("F") || (genderResponse.toUpperCase()).equals("M") || (genderResponse.toUpperCase()).equals("FEMALE") || (genderResponse.toUpperCase()).equals("MALE")) {
						gender[i - 1] = (genderResponse.toUpperCase()).charAt(0);
						System.out.println();
						break;
					}			
					else {
						System.out.println("Invalid input");
						System.out.print("Please enter the gender of athlete " + i + " again (e.g.: m, f, M, F, male, female): ");
						continue;
					}
				}
				//User to input athlete age
				System.out.print("Please enter the age of athlete " + i + ": ");
				//Repeats until positive whole number is entered
				while (true) {
					try { 
						int ageResponse = scan.nextInt();
						scan.nextLine();
						if (ageResponse >= 1) {
							age[i - 1] = ageResponse;
							System.out.println();
							break;
						}
						else {
							System.out.println("Age entered cannot be 0 or negative integers");
							System.out.print("Please enter the age of athlete " + i + " again (Please enter a POSITIVE whole number): ");
							continue;
						}
					}catch (InputMismatchException e) {
						System.out.println("Error: input format not recognized");
						System.out.print("Please enter the age of athlete " + i + " again (Please enter a positive WHOLE NUMBER): ");
						scan.nextLine();
					}
				}
				//User to input athlete height
				System.out.print("Please enter the height of athlete " + i + ": ");
				//Repeats until positive number is entered
				while (true) {
					try {
						double heightResponse = scan.nextDouble();
						if (heightResponse > 0) {
							height[i - 1] = heightResponse;
							System.out.println();
							break;
						}
						else {
							System.out.println("Height cannot be 0 or negative");
							System.out.print("Please enter the height of athlete " + i + " again (Please enter a POSITIVE number): ");
							continue;
						}
					}catch (InputMismatchException e) {
						System.out.println("Error: input format not recognized");
						System.out.print("Please enter the height of athlete " + i + " again (Please enter a positive NUMBER): ");
						scan.nextLine();
					}
				}
				//User to input athlete weight
				System.out.print("Please enter the weight of athlete " + i + ": ");
				//Repeats until a positive number is entered 
				while (true) {
					try {
						double weightResponse = scan.nextDouble();
						scan.nextLine();
						if (weightResponse > 0) {
							weight[i - 1] = weightResponse;
							System.out.println();
							break;
						}
						else {
							System.out.println("Weight cannot be 0 or negative");
							System.out.print("Please enter the weight of athlete " + i + " again (Please enter a POSITIVE number): ");
							continue;
						}
					}catch (InputMismatchException e) {
						System.out.println("Error: input format not recognized");
						System.out.print("Please enter the weight of athlete " + i + " again (Please enter a positive NUMBER): ");
						scan.nextLine();
					}
				}
				//User to input the athlete sport
				System.out.print("Please enter what kind of sports does athlete " + i + " play: ");
				String sportResponse = scan.nextLine();
				sport[i - 1] = ((sportResponse.substring(0, 1)).toUpperCase() + (sportResponse.substring(1)).toLowerCase());
				System.out.println();
				//User to input the type of medal obtained by athlete
				System.out.print("Please enter the medal type obtained by athlete " + i + " (Gold, Silver or Bronze only): ");
				//Repeats until a 0 or positive whole number is entered
				while (true) {
					String medalResponse = scan.nextLine();
					if ((medalResponse.toUpperCase()).equals("BRONZE") || (medalResponse.toUpperCase()).equals("SILVER") || (medalResponse.toUpperCase()).equals("GOLD")) {
						medal[i - 1] = (medalResponse.substring(0, 1)).toUpperCase() + (medalResponse.substring(1)).toLowerCase();
						System.out.println();
						break;
					}
					else {
						System.out.println("Invalid medal type entered");
						System.out.print("Please enter the medal type obtained by athlete " + i + " again (Gold, Silver or Bronze only): ");
						continue;
					}
					
				}
				
			}
			
		}
		
		//calls out all relevant methods to print out details of athletes in the data set
		table(name, gender, age, height, weight, sport, medal);
		System.out.println();
		genderRatio(gender);
		System.out.println();
		System.out.println("The mean and standard deviation of the age of male athletes are " + mean(age, gender, 'M') + " and " + standardD(age, gender, 'M') + " respectively");
		System.out.println("The mean and standard deviation of the age of female athletes are " + mean(age, gender, 'F') + " and " + standardD(age, gender, 'F') + " respectively" + '\n');
		System.out.println("The mean and standard deviation of the height of male athletes are " + mean(height, gender, 'M') + " and " + standardD(height, gender, 'M') + " respectively");
		System.out.println("The mean and standard deviation of the height of female athletes are " + mean(height, gender, 'F') + " and " + standardD(height, gender, 'F') + " respectively" +'\n');
		System.out.println("The mean and standard deviation of the weight of male athletes are " + mean(weight, gender, 'M') + " and " + standardD(weight, gender, 'M') + " respectively");
		System.out.println("The mean and standard deviation of the weight of female athletes are " + mean(weight, gender, 'F') + " and " + standardD(weight, gender, 'F') + " respectively" + '\n');
		oldestAthlete(gender, name, age);
		System.out.println();
		youngestAthlete(gender, name, age);
		System.out.println();
		uniqueSports(sport);
		System.out.println();
		genderMedalNum(gender, medal);
		System.out.println();
		genderInEachSportMedalNum(gender, sport, medal);
	}
		
	//method for calculating the gender ratio (Male : Female)
	static void genderRatio(char gender[]) {
		//loops through the gender array
		double male = 0;
		double female = 0;
		for (int i = 0; i < gender.length; i++) {
			//Gets the number of males in the array
			if (gender[i] == 'M') 
				male += 1;
			//Gets the number of females in the array
			else
				female += 1;
		}
		//Calculates the gender ratio
		double ratio = male/female;
		//prints out the gender ratio
		System.out.println("The gender ratio of the athletes (Male : Female) is " + ratio);	
	}
	
	//Method for calculating and returning the mean of the integer data of male or female athletes in the data set, gen = 'M' for male and 'F' for female
	static double mean(int[] integer, char[] gender, char gen) {
		//loops through the gender and age arrays
		double maleIntSum = 0;
		double femaleIntSum = 0;
		double male = 0;
		double female = 0;
		for (int i = 0; i < gender.length; i++) {
			//Gets the sum of male integer values and the sum of males in the array
			if (gender[i] == 'M') {
				maleIntSum += integer[i];
				male += 1;
			}
			//Gets the sum of female integer values and the sum of females in the array
			else {
				femaleIntSum += integer[i];
				female += 1;
			}
		}
		
		//Calculates and Returns the mean of the integer data of male or female athletes
		if (gen == 'M') {
			double meanMale = maleIntSum/male;
			return meanMale; 
		}
			
		else {
			double meanFemale = femaleIntSum/female;
			return meanFemale;
		}
			
	}
	
	//Method for calculating and returning the mean of the double data of male or female athletes in the data set, gen = 'M' for male and 'F' for female
	static double mean(double[] doubleData, char[] gender, char gen) {
		//loops through the gender and age arrays
		double maleDoubleSum = 0;
		double femaleDoubleSum = 0;
		double male = 0;
		double female = 0;
		for (int i = 0; i < gender.length; i++) {
			//Gets the sum of male double values and the sum of males in the array
			if (gender[i] == 'M') {
				maleDoubleSum += doubleData[i];
				male += 1;
			}
			//Gets the sum of female double values and the sum of females in the array
			else {
				femaleDoubleSum += doubleData[i];
				female += 1;
			}
		}
		
		//Calculates Returns the mean of the double data of male or female athletes
		if (gen == 'M') {
			double meanMale = maleDoubleSum/male;
			return meanMale; 
		}
			
		else {
			double meanFemale = femaleDoubleSum/female;
			return meanFemale;
		}
			
	}
	
	//Method for calculating and returning the Standard Deviation of the integer data of male or female athletes in the data set, gen = 'M' for male and 'F' for female
	static double standardD(int[] integer, char[] gender, char gen) {
		double meanMale = 0;
		double meanFemale = 0;
		//Gets the mean of the male or female athlete's double data 
		if (gen == 'M')
			meanMale = mean(integer, gender, gen);
		else
			meanFemale = mean(integer, gender, gen);
		
		//Loops through the gender array and doubleData array
		double male = 0;
		double female = 0;
		double maleSDSum = 0;
		double femaleSDSum = 0;
		for (int i = 0; i < gender.length; i++) {
			//Gets the sum of the ((male integer value - male integer data mean)^2) for every male integer value and the sum of males in the gender array
			if (gender[i] == 'M') {
				maleSDSum += ((integer[i] - meanMale)*(integer[i] - meanMale));
				male += 1;
			}
				
			//Gets the sum of the ((female integer value - female integer data mean)^2) for every female integer value and the sum of females in the gender array
			else {
				femaleSDSum += ((integer[i] - meanFemale)*(integer[i] - meanFemale));
				female += 1;
			}
		}
		
		//Calculates and returns the standard deviation of male or female integer values
		if (gen == 'M') {
			double maleSD = Math.sqrt(maleSDSum/male);
			return maleSD;
		}
		else {
			double femaleSD = Math.sqrt(femaleSDSum/female);
			return femaleSD;
		}
	}
	
	
	//Method for calculating and returning the Standard Deviation of the double data of male or female athletes in the data set, gen = 'M' for male and 'F' for female
	//Method for calculating and returning the Standard Deviation of the double data of male or female athletes in the data set, gen = 'M' for male and 'F' for female
	static double standardD(double[] doubleData, char[] gender, char gen) {
		double meanMale = 0;
		double meanFemale = 0;
		//Gets the mean of the male or female athlete's double data 
		if (gen == 'M')
			meanMale = mean(doubleData, gender, gen);
		else
			meanFemale = mean(doubleData, gender, gen);
		
		//Loops through the gender array and doubleData array
		double male = 0;
		double female = 0;
		double maleSDSum = 0;
		double femaleSDSum = 0;
		for (int i = 0; i < gender.length; i++) {
			//Gets the sum of the ((male double value - male double data mean)^2) for every male double value and the sum of males in the gender array
			if (gender[i] == 'M') {
				maleSDSum += ((doubleData[i] - meanMale)*(doubleData[i] - meanMale));
				male += 1;
			}
				
			//Gets the sum of the ((female double value - female double data mean)^2) for every female double value and the sum of females in the gender array
			else {
				femaleSDSum += ((doubleData[i] - meanFemale)*(doubleData[i] - meanFemale));
				female += 1;
			}
		}
		
		//Calculates and returns the standard deviation of male or female double values
		if (gen == 'M') {
			double maleSD = Math.sqrt(maleSDSum/male);
			return maleSD;
		}
		else {
			double femaleSD = Math.sqrt(femaleSDSum/female);
			return femaleSD;
		}
	}
	//Method for calculating the mean and standard deviation of the age for each gender
	
	//Method to determine the oldest male and female athlete in the data set
	//Method for determining and indicating the name and age of the oldest athlete for each gender
	static void oldestAthlete(char[] gender, String[] name, int[] age) {
		//Loops through the gender and age array 
		int maleAge = 0;
		int femaleAge = 0;
		for (int i = 0; i < gender.length; i++) {
			//Gets the largest age among the males
			if (gender[i] == 'M') {
				if (age[i] >= maleAge) 
					maleAge = age[i];
			}
			//Gets the largest age among the females
			else {
				if (age[i] >= femaleAge)
					femaleAge = age[i];
			}
		}
		
		System.out.println("The oldest male athlete's name(s) and age (Name, Age): ");
		//Loops through the age and gender array again
		int count = 0;
		for (int i = 0; i < gender.length; i++) {
			//Prints out the oldest male athlete's name(s) and age
			if (gender[i] == 'M') {
				if (age[i] == maleAge) {
					count += 1;
					System.out.println("(" + count + ") " + name[i] + ", " + age[i]);
				}
			}
			else 
				continue;
		}
		System.out.println();
		
		System.out.println("The oldest female athlete's name(s) and age (Name, Age): ");
		//Loops through the age and gender arrays again
		count = 0;
		for (int i = 0; i < gender.length; i++) {
			//Prints out the oldest female athlete's name(s) and age
			if (gender[i] == 'F') {
				if (age[i] == femaleAge) {
					count += 1;
					System.out.println("(" + count + ") " + name[i] + ", " + age[i]);
				}
			}
			else 
				continue;
		}
		System.out.println();
	}
	//Method for determining and indicating the name and age of the youngest athlete for each gender
	
	//Method to determine the youngest male and female athlete in the data set
	static void youngestAthlete(char[] gender, String[] name, int[] age) {
		//Loops through the gender and age array 
		int maleAge = Integer.MAX_VALUE;
		int femaleAge = Integer.MAX_VALUE;
		for (int i = 0; i < gender.length; i++) {
			//Gets the smallest age among the males
			if (gender[i] == 'M') {
				if (age[i] <= maleAge) 
					maleAge = age[i];
			}
			//Gets the smallest age among the females
			else {
				if (age[i] <= femaleAge)
					femaleAge = age[i];
			}
		}
		
		System.out.println("The youngest male athlete's name(s) and age (Name, Age): ");
		//Loops through the age and gender array again
		int count = 0;
		for (int i = 0; i < gender.length; i++) {
			//Prints out the youngest male athlete's name(s) and age
			if (gender[i] == 'M') {
				if (age[i] == maleAge) {
					count += 1;
					System.out.println("(" + count + ") " + name[i] + ", " + age[i]);
				}
			}
			else 
				continue;
		}
		System.out.println();
		
		System.out.println("The youngest female athlete's name(s) and age (Name, Age): ");
		//Loops through the age and gender arrays again
		count = 0;
		for (int i = 0; i < gender.length; i++) {
			//Prints out the youngest female athlete's name(s) and age
			if (gender[i] == 'F') {
				if (age[i] == femaleAge) {
					count += 1;
					System.out.println("(" + count + ") " + name[i] + ", " + age[i]);
				}
			}
			else 
				continue;
		}
		System.out.println();
	}
	
	//Method to calculate and print out details about the number of unique sports in the sport array of the data set
	//Method to calculate the number of unique sports in the data and print it out 
	static void uniqueSports(String[] sport) {
		//Creates an array with the same length as the number of data entries
		String[] uniqueSport = new String[sport.length];
		//Loops through the sport array and assigns each unique sports a location in the uniqueSport array
		uniqueSport[0] = sport[0];
		int count = 0;
		for (String i : sport) {
			if (i.equals(uniqueSport[count])) 
				continue;
			else {
				//Loops through the unqiueSport array and checks if there is an existing unique sports in the array for a unique sports
				int counter = 0;
				for (String j : uniqueSport) {
					if (j == null) 
						counter += 1;
					else {
						if (j.equals(i)) 
							break;
						else 
							counter += 1;
					}
					
				}
				//Assigns a new location in the uniqueSport array for a unique sports that did not exist in the array
				if (counter == uniqueSport.length) {
					count += 1;
					uniqueSport[count] = i;
				}
				
			}
		}
		//Loops through the uniqueSport array and counts the number of unique sports in the array
		int uniqueCounter = 0;
		for (String i : uniqueSport) {
			if (i != null) 
				uniqueCounter += 1;
			else
				continue;
		}
		//Prints out the number of unique sports that are available in the data
		System.out.println("There are " + uniqueCounter + " unique sports availabale in the data");
	}
	
	//Method to create and return the array containing all the unique sports in the sport array of the data set
	//Method to create and return an array that contains only unique sports in it
	static String[] uniqueSportsArray(String[] sport) {
		//Creates an array with the same length as the number of data entries
		String[] uniqueSport = new String[sport.length];
		//Loops through the sport array and assigns each unique sports a location in the uniqueSport array
		uniqueSport[0] = sport[0];
		int count = 0;
		for (String i : sport) {
			if (i.equals(uniqueSport[count])) 
				continue;
			else {
				//Loops through the unqiueSport array and checks if there is an existing unique sports in the array for a unique sports
				int counter = 0;
				for (String j : uniqueSport) {
					if (j == null) 
						counter += 1;
					else {
						if (j.equals(i)) 
							break;
						else 
							counter += 1;
					}
					
				}
				//Assigns a new location in the uniqueSport array for a unique sports that did not exist in the array
				if (counter == uniqueSport.length) {
					count += 1;
					uniqueSport[count] = i;
				}
				
			}
		}
		//Loops through the uniqueSport array and counts the number of unique sports in the array
		int uniqueCounter = 0;
		for (String i : uniqueSport) {
			if (i != null) 
				uniqueCounter += 1;
			else
				continue;
		}
		//Creates a new array "uniqueSportUpdated" containing only unique sports in it 
		String[] uniqueSportUpdated = new String[uniqueCounter];
		for (int i = 0; i < uniqueSportUpdated.length; i++) {
			uniqueSportUpdated[i] = uniqueSport[i];
		}
		//Returns the uniqueSportUpdated array 
		return uniqueSportUpdated;
	}
	//Method to how many medals each gender gets in all sports for each medal type
	
	//Method to calculate the number of medals each gender gets in all sports for each medal type
	static void genderMedalNum(char[] gender, String[] medal) {
		//Loops through the gender and medal arrays
		int maleGold = 0;
		int femaleGold = 0;
		int maleSilver = 0;
		int femaleSilver = 0;
		int maleBronze = 0;
		int femaleBronze = 0;
		for (int i = 0; i < gender.length; i++) {
			//Gets the number of gold, silver and bronze medals respectively obtained by male athletes
			if (gender[i] == 'M') {
				if (medal[i].equals("Gold")) {
					maleGold += 1;
				}
				else if (medal[i].equals("Silver")) {
					maleSilver += 1;
				}
				else {
					maleBronze += 1;
				}
			}
			//Gets the number of gold silver and bronze medals respectively obtained by female athletes
			else {
				if (medal[i].equals("Gold")) {
					femaleGold += 1;
				}
				else if (medal[i].equals("Silver")) {
					femaleSilver += 1;
				}
				else {
					femaleBronze += 1;
				}
			}
				
		}
		//Prints out the number of gold, silver and bronze medals obtained by male and female athletes respectively 
		System.out.println("There are " + maleGold + " gold medal(s), " + maleSilver + " silver medal(s), " + maleBronze + " bronze medal(s) obtained by male athletes in the data in all sports");
		System.out.println("There are " + femaleGold + " gold medal(s), " + femaleSilver + " silver medal(s), " + femaleBronze + " bronze medal(s) obtained by female athletes in the data in all sports");
	}
	//Method to calculate how many medals each gender gets in each sport for each medal type
	
	//Method to calculate the number of medals each gender gets in each sport for each medal type,
	static void genderInEachSportMedalNum(char[] gender, String[] sport, String[] medal) {
		//Creates an array "uniqueSport" that stores only the unique sports available 
		String[] uniqueSport = uniqueSportsArray(sport);
		//Loops through the uniqueSport array
		for (String i : uniqueSport) {
			int maleGold = 0;
			int femaleGold = 0;
			int maleSilver = 0;
			int femaleSilver = 0;
			int maleBronze = 0;
			int femaleBronze = 0;
			//Loops through the sport, gender and medal array 
			for (int j = 0; j < sport.length; j++) {
				//Checks if a value in the sport array matches the current uniqueSport array value i
				if (sport[j].equals(i)){
					//Checks if the the athlete is a male 
					if (gender[j] == 'M') {
						//Checks if the medal obtained by the male athlete is a gold, silver or bronze
						if (medal[j].equals("Gold")) 
							maleGold += 1;
						else if (medal[j].equals("Silver")) 
							maleSilver += 1;
						else
							maleBronze += 1;
					}
					//Checks is the athlete is a female 
					else {
						//Checks if the medal obtained by the female athlete is a gold, silver or bronze
						if (medal[j].equals("Gold")) 
							femaleGold += 1;
						else if (medal[j].equals("Silver")) 
							femaleSilver += 1;
						else
							femaleBronze += 1;
					}
				}
				//Continue loop if a value in the sport array doesn't match the current uniqueSport array value i
				else
					continue;
			}
			//Prints out the each medal obtained by each gender in each sport
			System.out.println("The medals obtained by male athletes in " + i + " are: " + '\n' + maleGold + " gold medal(s); " + '\n' + maleSilver + " silver medal(s); " + '\n' + maleBronze + " bronze medal(s)" + '\n');
			System.out.println("The medals obtained by female athletes in " + i + " are: " + '\n' + femaleGold + " gold medal(s); " + '\n' + femaleSilver + " silver medal(s); " + '\n' + femaleBronze + " bronze medal(s)" + '\n');
		}
	}
	//Method for printing out a table 
	
	//Method to print out a table containing all the athletes' basic details in the data set
	static void table(String[] name, char[] gender, int[] age, double[] height, double[] weight, String[] sport, String[] medal) {
		//Determines if the name lengths in the name array is longer than 4, if yes, assigns the new longest name length into nameLength
		int nameLength = 4;
		for (String i : name) {
			if (i.length() > nameLength) 
				nameLength = i.length();
			else
				continue;
		}
		//Determines if the age lengths in the age array is longer than 3, if yes, assigns the new longest age length into ageLength
		int ageLength = 3;
		for (int i : age) {
			if ((Integer.toString(i)).length() > ageLength)
				ageLength = (Integer.toString(i)).length();
			else
				continue;
		}
		//Determines if the height lengths in the length array is longer than 3, if yes, assigns the new longest height length into heightLength
		int heightLength = 6;
		for (double i : height) {
			if ((Double.toString(i)).length() > heightLength) {
				heightLength = (Double.toString(i)).length();
			}
			else
				continue;
		}
		//Determines if the weight lengths in the weight array is longer than 3, if yes, assigns the new longest weight length into weightLength
		int weightLength = 6;
		for (double i : weight) {
			if ((Double.toString(i)).length() > weightLength) {
				weightLength = (Double.toString(i)).length();
			}
			else
				continue;
		}
		//Determines if the sport lengths in the sport array is longer than 3, if yes, assigns the new longest sport length into sportLength
		int sportLength = 5;
		for (String i : sport) {
			if (i.length() > sportLength)
				sportLength = i.length();
			else
				continue;
		}
		//Determines if the medal lengths in the medal array is longer than 3, if yes, assigns the new longest medal length into medalLength
		int medalLength = 5;
		for (String i : medal) {
			if (i.length() > medalLength)
				medalLength = i.length();
			else
				continue;
		}
		//Formats the string value so that it is left aligned within a nameLength-character field 
		String formatName = String.format("%-" + nameLength + "s", "Name");
		//Formats the string value so that it is left aligned within 6-character field 
		String formatGender = String.format("%-6s", "Gender");
		//Formats the string value so that it is left aligned within an ageLength-character field 
		String formatAge = String.format("%-" + ageLength + "s", "Age");
		//Formats the string value so that it is left aligned within a heightLength-character field 
		String formatHeight = String.format("%-" + heightLength + "s", "Height");
		//Formats the string value so that it is left aligned within a weightLength-character field 
		String formatWeight = String.format("%-" + weightLength + "s",  "Weight");
		//Formats the string value so that it is left aligned within a sportLength-character field 
		String formatSport = String.format("%-" + sportLength + "s",  "Sport");
		//Formats the string value so that it is left aligned within a medalLength-character field 
		String formatMedal = String.format("%-" + medalLength + "s",  "Medal");
		System.out.println("The details of the athletes are as follows: ");
		System.out.println();
		//Prints out the string values as a table format
		System.out.println("|" + formatName + "|" + formatGender + "|" + formatAge + "|" + formatHeight + "|" + formatWeight + "|" + formatSport + "|" + formatMedal + "|");
		//Formats the string value so that it is left aligned within a nameLength-character field 
		formatName = String.format("%-" + nameLength + "s", " ");
		//Formats the string value so that it is left aligned within 6-character field 
		formatGender = String.format("%-6s", " ");
		//Formats the string value so that it is left aligned within an ageLength-character field 
		formatAge = String.format("%-" + ageLength + "s", " ");
		//Formats the string value so that it is left aligned within a heightLength-character field 
		formatHeight = String.format("%-" + heightLength + "s", " ");
		//Formats the string value so that it is left aligned within a weightLength-character field 
		formatWeight = String.format("%-" + weightLength + "s",  " ");
		//Formats the string value so that it is left aligned within a sportLength-character field 
		formatSport = String.format("%-" + sportLength + "s",  " ");
		//Formats the string value so that it is left aligned within a medalLength-character field 
		formatMedal = String.format("%-" + medalLength + "s",  " ");
		//Prints out the string values as a table format
		System.out.println("|" + formatName + "|" + formatGender + "|" + formatAge + "|" + formatHeight + "|" + formatWeight + "|" + formatSport + "|" + formatMedal + "|");
		//Loops through all the arrays storing the athlete's details 
		for (int i = 0; i < gender.length; i++) {
			//Formats the string value so that it is left aligned within a nameLength-character field 
			String formName = String.format("%-" + nameLength + "s", name[i]);
			//Formats the character value so that it is left aligned within 6-character field 
			String formGender = String.format("%-6c", gender[i]);
			//Formats the integer value so that it is left aligned within an ageLength-character field 
			String formAge = String.format("%-" + ageLength + "d", age[i]);
			//Formats the double value in 2 decimal places so that it is left aligned within a heightLength-character field 
			String formHeight = String.format("%-" + heightLength + ".2f", height[i]);
			//Formats the double value in 2 decimal places so that it is left aligned within a weightLength-character field 
			String formWeight = String.format("%-" + weightLength + ".2f",  weight[i]);
			//Formats the string value so that it is left aligned within a sportLength-character field 
			String formSport = String.format("%-" + sportLength + "s",  sport[i]);
			//Formats the string value so that it is left aligned within a medalLength-character field 
			String formMedal = String.format("%-" + medalLength + "s",  medal[i]);
			//Prints out the values found within the same index location of the arrays in a table format
			System.out.println("|" + formName + "|" + formGender + "|" + formAge + "|" + formHeight + "|" + formWeight + "|" + formSport + "|" + formMedal + "|");
		}
	}
}