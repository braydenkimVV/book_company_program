package sait.bms.problemdomain;

/**
 * @author Daniel Porter, Drew Temple-Smith, Brayden Kim
 * @version Feb 10th 2022
 */
public class Books {
	private long ISBN;
	private String callNumber;
	private int available;
	private int total;
	private String title;


	public Books(long ISBN, String callNumber, int available, int total, String title) {
		super();
		this.ISBN = ISBN;
		this.callNumber = callNumber;
		this.available = available;
		this.total = total;
		this.title = title;
	}

	public long getISBN() {
		return ISBN;
	}

	public String getCallNumber() {
		return callNumber;
	}

	public int getTotal() {
		return total;
	}

	public String getTitle() {
		return title;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public int getAvailable() {
		return available;
	}

	@Override
	public String toString() {
		return "Books [getISBN()=" + getISBN() + ", getCallNumber()=" + getCallNumber() + ", getTotal()=" + getTotal()
				+ ", getTitle()=" + getTitle() + ", getAvailable()=" + getAvailable() + "]";
	}

}
