package sait.bms.problemdomain;

/**
 * @author Daniel Porter, Drew Temple-Smith, Brayden Kim
 * @version Feb 10th 2022
 */
public class Periodicals extends Books {
	private char frequency;
	
	
	public Periodicals(long ISBN, String callNumber, int available, int total, String title, char frequency) {
		super(ISBN, callNumber, available, total, title);
		this.frequency = frequency;
	}

	public char getFrequency() {
		return frequency;
	}

	@Override
	public String toString() {
		String frequency = "";
		if(Character.toUpperCase(getFrequency())=='D') { // changed to toUpperCase. Because you made the format lowercase and compaired it to an uppercase char, they would never be the same, so format remained an empty string.
			frequency = "Daily";
		}else if(Character.toUpperCase(getFrequency())=='W') {
			frequency = "Weekly";
		}else if(Character.toUpperCase(getFrequency())=='M') {
			frequency = "Monthly";
		}else if(Character.toUpperCase(getFrequency())=='B') {
			frequency = "Bimonthly";
		}else if(Character.toUpperCase(getFrequency())=='Q') {
			frequency = "Quarterly";
		}
		// return "Periodicals [getFrequency()=" + frequency + "]";

		// I added this to make it match the layout in the document.
		return String.format("%-17s %d\n%-17s %s\n%-17s %d\n%-17s %d\n%-17s %s\n%-17s %s\n", "ISBN:", this.getISBN(), "Call Number:", this.getCallNumber(), "Available:", this.getAvailable(), "Total:", this.getTotal(), "Title:", this.getTitle(), "Frequency:", frequency);
	}
	
	
}
