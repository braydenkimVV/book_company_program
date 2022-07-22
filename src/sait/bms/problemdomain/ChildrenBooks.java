package sait.bms.problemdomain;

/**
 * @author Daniel Porter, Drew Temple-Smith, Brayden Kim
 * @version Feb 10th 2022
 */
public class ChildrenBooks extends Books {
	private String author;
	private char format;
	
	
	public ChildrenBooks(long ISBN, String callNumber, int available, int total, String title, String author, char format) {
		super(ISBN, callNumber, available, total, title);
		this.author = author;
		this.format = format;
		
	}

	public String getAuthor() {
		return author;
	}

	public char getFormat() {
		return format;
	}

	@Override
	public String toString() {
		String format = "";
		if(Character.toUpperCase(getFormat()) =='P') { // changed to toUpperCase. Because you made the format lowercase and compaired it to an uppercase char, they would never be the same, so format remained an empty string.
			format = "Picture book";
		}else if(Character.toUpperCase(getFormat())=='E') {
			format = "Early Readers";
		}else if(Character.toUpperCase(getFormat())=='C') {
			format = "Chapter book";	
		}
		
		// return "ChildrenBooks [getAuthor()=" + getAuthor() + ", getFormat()=" + format + "]";

		// I added this to make it match the layout in the document.
		return String.format("%-17s %d\n%-17s %s\n%-17s %d\n%-17s %d\n%-17s %s\n%-17s %s\n%-17s %s\n", "ISBN:", this.getISBN(), "Call Number:", this.getCallNumber(), "Available:", this.getAvailable(), "Total:", this.getTotal(), "Title:", this.getTitle(), "Authors:", this.getAuthor(), "Format:", format);
	}
	
	

}

