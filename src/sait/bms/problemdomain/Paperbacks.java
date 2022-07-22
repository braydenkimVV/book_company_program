package sait.bms.problemdomain;

/**
 * @author Daniel Porter, Drew Temple-Smith, Brayden Kim
 * @version Feb 10th 2022
 */
public class Paperbacks extends Books{
	private String author;
	private int year;
	private char genre;
	
	
	public Paperbacks(long ISBN, String callNumber, int available, int total, String title, String author, int year, char genre) {
		super(ISBN, callNumber, available, total, title);
		this.author = author;
		this.year = year;
		this.genre = genre;
		
	}

	public String getAuthor() {
		return author;
	}

	public int getYear() {
		return year;
	}

	public char getGenre() {
		return genre;
	}

	@Override
	public String toString() {
		String genre = "";
		if(Character.toUpperCase(getGenre())=='A') { // changed to toUpperCase. Because you made the format lowercase and compaired it to an uppercase char, they would never be the same, so format remained an empty string.
			genre = "Adventure";
		}else if(Character.toUpperCase(getGenre())=='D') {
			genre = "Drama";	
		}else if(Character.toUpperCase(getGenre())=='E') {
			genre = "Education";
		}else if(Character.toUpperCase(getGenre())=='C') {
			genre = "Classic";
		}else if(Character.toUpperCase(getGenre())=='F') {
			genre = "Fantasy";
		}else if(Character.toUpperCase(getGenre())=='S') {
			genre = "Sicence Fiction";
		}
		// return "Paperbacks [getAuthor()=" + getAuthor() + ", getYear()=" + getYear() + ", getGenre()=" + genre	+ "]";

		// I added this to make it match the layout in the document.
		return String.format("%-17s %d\n%-17s %s\n%-17s %d\n%-17s %d\n%-17s %s\n%-17s %s\n%-17s %d\n%-17s %s\n", "ISBN:", this.getISBN(), "Call Number:", this.getCallNumber(), "Available:", this.getAvailable(), "Total:", this.getTotal(), "Title:", this.getTitle(), "Authors:", this.getAuthor(), "Year:", this.getYear(), "Genre:", genre);
	}
	
	
}
