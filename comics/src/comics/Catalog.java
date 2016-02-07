package comics;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Catalog {
	private static ArrayList<Comic> comicComics = new ArrayList<>();
	private static ArrayList<Genre> comicGenres = new ArrayList<Genre>();
	private static ArrayList<Loan> comicLoans = new ArrayList<>();
	
	static{
		Genre defaultGenre = new Genre("Science Fiction");
		comicGenres.add(defaultGenre);
		defaultGenre = new Genre("Superheroes");
		comicGenres.add(defaultGenre);
	}
	
	public static void addComic(Comic objComic) {
		comicComics.add(objComic);
	}

	public static void addGenre(Genre objGenre) {
		comicGenres.add(objGenre);
	}
	
	public static void addLoan(Loan objLoan) {
		comicLoans.add(objLoan);
	}

	public static void showComics() {
		System.out.println("Current Comics: ");
		int counter = 1;
		for (Comic comics : comicComics) {
			System.out.println(counter + ". " + "Name: " + comics.getName() + ". Number: " + comics.getNumber() + ". Year: " + comics.getYear()
					+ ". Copies: " + comics.getCopies() + ". Genre: " + comics.getGenre().getName());
			counter++;
			clearConsole();
		}
	}

	public static void showGenres() {
		int counter = 1;
		System.out.println("Current Genres: ");
		for (Genre genres : comicGenres) {
			System.out.println(counter + ". " + genres.getName());
			counter++;
			clearConsole();
		}
	}

	public static Genre getGenre(int position) {
		return comicGenres.get(position - 1);
	}

	public static void removeComic(String comicName, int comicNumber) throws Exception {
		Comic comicToModify = getComic(comicName, comicNumber);
		comicComics.remove(comicToModify);
	}

	public static void modifyComic(Comic comicToModify, String comicName, int comicNumber, int comicYear, int comicCopies) {
		comicToModify.setName(comicName);
		comicToModify.setNumber(comicNumber);
		comicToModify.setYear(comicYear);
		comicToModify.setCopies(comicCopies);
	}

	public static Comic getComic(String comicName, int comicNumber) throws Exception {
		for (Comic comics : comicComics) {
			if (comics.getName().equals(comicName) && comics.getNumber() == comicNumber) {
				return comics;
			}
		}
		
		throw new Exception("Comic not found");
	}
	
	public static int getComicCopiesLoaned(String comicName, int comicNumber) {
		int counter = 0;
		for (Loan loans : comicLoans) {
			if (loans.getComic().getName().equals(comicName) && loans.getComic().getNumber() == comicNumber && loans.getStatus()=="Approved") {
				counter++;
			}
		}
		return counter;
	}
	
	public static void createLoan(Comic comicToLoan) {
		Person userWhoLoan = Login.getLoggedUser();
		Date currentDate = new Date();
		Loan newLoan = new Loan(userWhoLoan, comicToLoan, "Pending", currentDate);
		addLoan(newLoan);
	}
	
	public static void showLoans() {
		System.out.println("Current Loans: ");
		int position = 1;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		for (Loan loans : comicLoans) {
			System.out.println("ID: " + position + ". User: " + loans.getUser().getUsername() + ". Comic: " + loans.getComic().getName() + ". Number: " + loans.getComic().getNumber() + ". Status: " + loans.getStatus()
					+ ". Initial Date: " + dateFormat.format(loans.getInitialDate()));
			clearConsole();
			position++;
		}
	}
	
	public static Loan getLoan(int position) {
		return comicLoans.get(position - 1);
	}
	
	public static void approveLoan(int position) throws Exception {
		Loan loanToApprove = getLoan(position);
		int comicCopiesAvailable =  loanToApprove.getComic().getCopies() - getComicCopiesLoaned(loanToApprove.getComic().getName(), loanToApprove.getComic().getNumber());
		if (comicCopiesAvailable > 0) {
			loanToApprove.setStatus("Approved");
		}
		else {
			System.out.println("No copies of this comic available to loan");
		}
	}
	
	public static void registerDevolution(int position) throws Exception {
		Loan loanToFinish = getLoan(position);
		if(loanToFinish.getStatus() == "Approved") {
			loanToFinish.setStatus("Returned");	
		}
		else {
			System.out.println("Can't return an unapproved loan");
		}
	}
	
	public static void rejectLoan(int position) throws Exception {
		Loan loanToReject = getLoan(position);
		if(loanToReject.getStatus() == "Pending") {
			loanToReject.setStatus("Rejected");	
		}
		else {
			System.out.println("Can't reject an unpending loan");
		}
	}
	
	public static void createGenre(String genreName) {
		Genre newGenre = new Genre(genreName);
		comicGenres.add(newGenre);
	}
	
	public static void removeGenre(String genreName) throws Exception {
		Genre genreToRemove = getGenre(genreName);
		comicGenres.remove(genreToRemove);
	}
	
	public static void modifyGenre(String genreName, String genreNewName) throws Exception {
		Genre genreToModify = getGenre(genreName);
		genreToModify.setName(genreNewName);
	}
	
	public static Genre getGenre(String genreName) throws Exception {
		for (Genre genres : comicGenres) {
			if (genres.getName().equals(genreName)) {
				return genres;
			}
		}
		
		throw new Exception("Genre not found");
	}
	
	public static void showUserLoans(String userName) {
		System.out.println("Your Loans:");
		int position = 1;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		for (Loan loans: comicLoans) {
			if(loans.getUser().getUsername().equals(userName)) {
				System.out.println("ID: " + position + ". Comic: " + loans.getComic().getName() + ". Number: " + loans.getComic().getNumber() + ". Status: " + loans.getStatus()
				+ ". Initial Date: " + dateFormat.format(loans.getInitialDate()));
				clearConsole();
				position++;
			}
		}
	}
	
	public static void clearConsole() {
		System.out.println("------------------------------------------------------");
		/*for (int i = 0; i < 100; i++) {
			System.out.println("\b");
		}*/
	}
}