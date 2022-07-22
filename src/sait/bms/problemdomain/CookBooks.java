package sait.bms.problemdomain;


/**
 * @author Daniel Porter, Drew Temple-Smith, Brayden Kim
 * @version Feb 10th 2022
 */
public class CookBooks extends Books {
	private String publisher;
	private char diet;
	
	public CookBooks(long ISBN, String callNumber, int available, int total, String title, String publisher, char diet) {
		super(ISBN, callNumber, available, total, title);
		this.publisher = publisher;
		this.diet = diet;
		
	}

	public String getPublisher() {
		return publisher;
	}

	public char getDiet() {
		return diet;
	}

	@Override
	public String toString() {
		String diet = "";
		if(Character.toUpperCase(getDiet())=='D') { // changed to toUpperCase. Because you made the format lowercase and compaired it to an uppercase char, they would never be the same, so format remained an empty string.
			diet = "Diabetic";
		}else if(Character.toUpperCase(getDiet())=='V') {
			diet = "Vegetarian";
		}else if(Character.toUpperCase(getDiet())=='G') {
			diet = "Gluten-free";
		}else if(Character.toUpperCase(getDiet())=='I') {
			diet = "International";
		}else if(Character.toUpperCase(getDiet())=='N'){
			diet = "None";
		}
		
		// return "CookBooks [getPublisher()=" + getPublisher() + ", getDiet()=" + diet + "]";

		// I added this to make it match the layout in the document.
		return String.format("%-17s %d\n%-17s %s\n%-17s %d\n%-17s %d\n%-17s %s\n%-17s %s\n%-17s %s\n", "ISBN:", this.getISBN(), "Call Number:", this.getCallNumber(), "Available:", this.getAvailable(), "Total:", this.getTotal(), "Title:", this.getTitle(), "Publisher:", this.getPublisher(), "Diet:", diet);
	}
	
	
}

