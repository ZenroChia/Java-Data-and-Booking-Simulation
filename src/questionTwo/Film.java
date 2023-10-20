package questionTwo;

public class Film {
	//Data fields for Film class
	protected String filmTitle;
	protected int sessionTime;
	
	//Constructor of Film class
	public Film(String title, int sessionTime) {
		this.filmTitle = title;
		this.sessionTime = sessionTime;
	}
	
	//Methods of Film class
	//Method to return film screening session as proper time
	public String getSession() {
		if (sessionTime == 1) 
			return "Afternoon (1pm)";
		else if (sessionTime == 2) 
			return "Evening (5pm)";
		else
			return "Night (9pm)";
	}
	//Method to get and return film title
	public String getTitle() {
		return filmTitle;
	}

}