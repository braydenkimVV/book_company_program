package sait.bms.manager;

import java.awt.print.Book;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import sait.bms.problemdomain.Books;
import sait.bms.problemdomain.ChildrenBooks;
import sait.bms.problemdomain.CookBooks;
import sait.bms.problemdomain.Paperbacks;
import sait.bms.problemdomain.Periodicals;

/**
 * @author Daniel Porter, Drew Temple-Smith, Brayden Kim
 * @version Feb 10th 2022
 */
public class BooksDriver {
	private ArrayList<Books> bookList = new ArrayList<Books>();
	private File textFile;
	private Scanner textInput;
	private PrintWriter writeFile;
	
	
	public BooksDriver() throws IOException {

		loadBooks();
		mainProgram();
	}

	public void mainProgram() throws IOException {

		int option = menuOption();
		switch (option) {
		case 1:
			checkoutBook();
			mainProgram();
			break;
		case 2:
			findBook();
			mainProgram();
			break;
		case 3:
			displayBooks();
			mainProgram();
			break;
		case 4:
			randomList();
			mainProgram();
			break;
		case 5:
			saveFile();
			break;
		default:
			System.out.println("\nThat's not a valid option.");
			mainProgram();
		}

	}

	public void checkoutBook() {
		System.out.print("Enter ISBN of book: ");
		long ISBN = textInput.nextLong();
		for (Books book : bookList) {
			if (!(book instanceof Periodicals)) {
				if (book.getISBN() == ISBN && book.getAvailable() > 0) {
					book.setAvailable(book.getAvailable() - 1);
					System.out.printf(
							"The book \"%s\" has been checked out.\r\n"
									+ "It can be located using a call number: %s\r\n",
							book.getTitle(), book.getCallNumber());
				} else if (book.getISBN() == ISBN && book.getAvailable() == 0) {
					System.out.println("This book is not available");
					break;
				} else {

				}
			} else if (book.getISBN() == ISBN) {
				System.out.println("This type of book isn't available");
				break;
			}
		}

	}

	public void findBook() {
		System.out.print("Enter title to search for: ");
		String title = textInput.next();
		System.out.println("Matching books: ");
		for (Books book : bookList) {
			if (book.getTitle().toLowerCase().contains(title)) {
				if (book instanceof ChildrenBooks) {
					System.out.println(((ChildrenBooks) book).toString());
				}
				if (book instanceof CookBooks) {
					System.out.println(((CookBooks) book).toString());
				}
				if (book instanceof Paperbacks) {
					System.out.println(((Paperbacks) book).toString());
				}
				if (book instanceof Periodicals) {
					System.out.println(((Periodicals) book).toString());
				}
			}
		}

	}

	public void displayBooks() {

		// ask what type of book
		System.out.printf("\n%s%19s%n", "#", "Type");
		System.out.printf("%d%19s%n", 1, "Children's Book");
		System.out.printf("%d%19s%n", 2, "Cookbooks");
		System.out.printf("%d%19s%n", 3, "Paperbacks");
		System.out.printf("%d%19s%n%n", 4, "Periodicals");
		textInput = new Scanner(System.in); // reset the textInput
		System.out.print("Enter type of book: ");
		int num = textInput.nextInt();
		char type = 'z';

		if (num == 1) {
			System.out.print("Enter a format (P for Picture book, E for Early Readers, or C for Chapter book): ");
			type = textInput.next().charAt(0);

		} else if (num == 2) {

			System.out.print(
					"Enter a diet (D for Diabetic, V for Vegetarian, G for Gluten-free, I for International, or N for None): ");
			type = textInput.next().charAt(0);

		} else if (num == 3) {

			System.out.print(
					"Enter a genre (A for Adventure, D for Drama, E for Education, C for Classic, F for Fantasy, or S for Science Fiction): ");
			type = textInput.next().charAt(0);

		} else if (num == 4) {

			System.out.print(
					"Enter a frequency (D for Daily, W for Weekly, M for Monthly, B for Biweekly, or Q for Quarterly): ");
			type = textInput.next().charAt(0);

		}
		System.out.println("Matching Books:");
		for (Books book : bookList) {
			if (book instanceof ChildrenBooks && ((ChildrenBooks) book).getFormat() == Character.toUpperCase(type)
					&& num == 1) {
				System.out.println(book);
			} else if (book instanceof CookBooks && ((CookBooks) book).getDiet() == Character.toUpperCase(type)
					&& num == 2) {
				System.out.println(book);
			} else if (book instanceof Paperbacks && ((Paperbacks) book).getGenre() == Character.toUpperCase(type)
					&& num == 3) {
				System.out.println(book);
			} else if (book instanceof Periodicals && ((Periodicals) book).getFrequency() == Character.toUpperCase(type)
					&& num == 4) {
				System.out.println(book);
			}
		}
	}

	public void randomList() {

		// get number of books
		System.out.print("Enter number of books: ");
		textInput = new Scanner(System.in);
		int num = textInput.nextInt();
		int[] randomNumbers = new int[num];
		Arrays.fill(randomNumbers, -1); 
		for (int i = 0; i < num; i++) {
			int randomNumber = (int) (Math.random() * (bookList.size() + 1));
			boolean hasNumber = false;
			for (int random : randomNumbers) {
				if (random == randomNumber) {
					hasNumber = true;
				}
			}
			if (!hasNumber) {
				randomNumbers[i] = randomNumber;
			} else {
				i--;
			}
		}

		System.out.println("\nRandom books:");
		for (int random : randomNumbers) {
			System.out.println(bookList.get(random));
		}

	}

	public int menuOption() {
		System.out.println("\nWelcome in ABC Book Company: How May We Assit You?");
		System.out.printf("%d%19s%n", 1, "Checkout Book");
		System.out.printf("%d%25s%n", 2, "Find Books By Title");
		System.out.printf("%d%27s%n", 3, "Display Books by Type");
		System.out.printf("%d%30s%n", 4, "Produce Random Book List");
		System.out.printf("%d%17s%n", 5, "Save & Exit");
		System.out.print("Enter the option: ");
		textInput = new Scanner(System.in);
		int num = textInput.nextInt();
		return num;

	}

	public void loadBooks() throws IOException {
		File textFile = new File("res/books.txt");
		Scanner scan = new Scanner(textFile);
		while (scan.hasNext()) {
			String line = scan.nextLine();
			String[] fields = line.split(";");

			int lastDigit = (int) (Long.parseLong(fields[0]) % 10);

			Books book;

			if (lastDigit == 0 || lastDigit == 1) { // changed && to ||
				book = new ChildrenBooks(Long.parseLong(fields[0]), fields[1], Integer.parseInt(fields[2]),
						Integer.parseInt(fields[3]), fields[4], fields[5], fields[6].charAt(0));
			} else if (lastDigit == 2 || lastDigit == 3) {
				book = new CookBooks(Long.parseLong(fields[0]), fields[1], Integer.parseInt(fields[2]),
						Integer.parseInt(fields[3]), fields[4], fields[5], fields[6].charAt(0));
			} else if (lastDigit == 4 || lastDigit == 5 || lastDigit == 6 || lastDigit == 7) {
				book = new Paperbacks(Long.parseLong(fields[0]), fields[1], Integer.parseInt(fields[2]),
						Integer.parseInt(fields[3]), fields[4], fields[5], Integer.parseInt(fields[6]),
						fields[7].charAt(0));
			} else {
				book = new Periodicals(Long.parseLong(fields[0]), fields[1], Integer.parseInt(fields[2]),
						Integer.parseInt(fields[3]), fields[4], fields[5].charAt(0));
			}
			bookList.add(book);
		}
	}

	private void saveFile() throws FileNotFoundException {
		textInput.close(); 

		Books book;
		textFile = new File("res/books.txt");
		writeFile = new PrintWriter(textFile);

		for (int i = 0; i < bookList.size(); i++) {

			book = bookList.get(i);

			if (i != bookList.size() - 1) {

				if (book instanceof ChildrenBooks) {
					writeFile.println(book.getISBN() + ";" + book.getCallNumber() + ";" + book.getAvailable() + ";"
							+ book.getTotal() + ";" + book.getTitle() + ";" + ((ChildrenBooks) book).getAuthor() + ";"
							+ ((ChildrenBooks) book).getFormat());
				} else if (book instanceof CookBooks) {
					writeFile.println(book.getISBN() + ";" + book.getCallNumber() + ";" + book.getAvailable() + ";"
							+ book.getTotal() + ";" + book.getTitle() + ";" + ((CookBooks) book).getPublisher() + ";"
							+ ((CookBooks) book).getDiet());
				} else if (book instanceof Paperbacks) {
					writeFile.println(book.getISBN() + ";" + book.getCallNumber() + ";" + book.getAvailable() + ";"
							+ book.getTotal() + ";" + book.getTitle() + ";" + ((Paperbacks) book).getAuthor() + ";"
							+ ((Paperbacks) book).getYear() + ";" + ((Paperbacks) book).getGenre());
				} else if (book instanceof Periodicals) {
					writeFile.println(book.getISBN() + ";" + book.getCallNumber() + ";" + book.getAvailable() + ";"
							+ book.getTotal() + ";" + book.getTitle() + ";" + ((Periodicals) book).getFrequency());
				}

			} else {
				if (book instanceof ChildrenBooks) {
					writeFile.print(book.getISBN() + ";" + book.getCallNumber() + ";" + book.getAvailable() + ";"
							+ book.getTotal() + ";" + book.getTitle() + ";" + ((ChildrenBooks) book).getAuthor() + ";"
							+ ((ChildrenBooks) book).getFormat());
				} else if (book instanceof CookBooks) {
					writeFile.print(book.getISBN() + ";" + book.getCallNumber() + ";" + book.getAvailable() + ";"
							+ book.getTotal() + ";" + book.getTitle() + ";" + ((CookBooks) book).getPublisher() + ";"
							+ ((CookBooks) book).getDiet());
				} else if (book instanceof Paperbacks) {
					writeFile.print(book.getISBN() + ";" + book.getCallNumber() + ";" + book.getAvailable() + ";"
							+ book.getTotal() + ";" + book.getTitle() + ";" + ((Paperbacks) book).getAuthor() + ";"
							+ ((Paperbacks) book).getYear() + ";" + ((Paperbacks) book).getGenre());
				} else if (book instanceof Periodicals) {
					writeFile.print(book.getISBN() + ";" + book.getCallNumber() + ";" + book.getAvailable() + ";"
							+ book.getTotal() + ";" + book.getTitle() + ";" + ((Periodicals) book).getFrequency());
				}
			}

		}

		writeFile.close();

	}
}