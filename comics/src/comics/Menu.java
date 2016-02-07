package comics;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
	private String userType;
	private ArrayList<String> menuOptions = new ArrayList<>();
	
	public Menu(String type){
		userType = type;
		switch(userType) {
	    	case "A": //Admin
	    		menuOptions.add("1. Create Comic");
	    		menuOptions.add("2. Delete Comic");
	    		menuOptions.add("3. Modify Comic");
	    		menuOptions.add("4. Show Comics");
	    		menuOptions.add("5. Show Loans");
	    		menuOptions.add("6. Approve Loan");
	    		menuOptions.add("7. Register Devolution");
	    		menuOptions.add("8. Reject Loan");
	    		menuOptions.add("9. Create User");
	    		menuOptions.add("10. Delete User");
	    		menuOptions.add("11. Modify User");
	    		menuOptions.add("12. Show Users");
	    		menuOptions.add("13. Create Genre");
	    		menuOptions.add("14. Delete Genre");
	    		menuOptions.add("15. Modify Genre");
	    		menuOptions.add("16. Show Genres");
	    		menuOptions.add("17. Log Out");
	    		break;
	    	case "U": //User
	    		menuOptions.add("1. View Comics");
	    		menuOptions.add("2. Loan Comic");
	    		menuOptions.add("3. View My Loans");
	    		menuOptions.add("4. Log Out");
	    		break;
	    	default: // No user
	    		menuOptions.add("1. Log In");
	    		menuOptions.add("2. Exit");
	    		break;
		}
	}
	
	public ArrayList<String> getMenuOptions() {
		return menuOptions;
	}
	
	public void showMenuOptions() {
		Scanner input = new Scanner(System.in);
		int menuOption = 0;
		ArrayList<String> menuOptions = this.getMenuOptions();
		
		String[] menuItems = new String[menuOptions.size()];
		System.out.println("******* Welcome to the Sheldon Cooper Comic Catalog *******");
		System.out.println("Type an option to begin:");
    	for (int i = 0; i < menuItems.length; i++) {
    		System.out.println(menuOptions.get(i).toString());
    	}
    	try {
    		menuOption = input.nextInt();
        	this.goToOption(menuOption);	
    	}
    	catch (Exception e) {
    		System.out.println("Enter a valid option");
    		this.showMenuOptions();
    	}
    	
	}
	
	public boolean goToOption(int menuOption) {
		Scanner input = new Scanner(System.in);
		if (userType.equals("A")){
			switch(menuOption) {
				case 1:
					// Get comic info to be created
					String comicName = "";
					int comicNumber = 0;
					int comicYear = 0;
					int comicCopies = 0;
					int comicGenre = 0;
					Catalog.clearConsole();
					System.out.println("Create a New Comic");
					System.out.println("Comic Name: ");
					comicName = input.nextLine();
					try {
						System.out.println("Comic Number: ");
						comicNumber = new Integer (input.nextLine());
						System.out.println("Comic Year: ");
						comicYear = new Integer (input.nextLine());
						System.out.println("Comic Copies: ");
						comicCopies = new Integer (input.nextLine());
						System.out.println("Comic Genre: ");
						Catalog.showGenres();
						comicGenre = new Integer (input.nextLine());
						// Create Comic Object
						Comic newComic = new Comic();
						newComic.createComic(comicName, comicNumber, comicYear, comicCopies, comicGenre);
						// Show message and back to menu
						System.out.println("Comic created");
					} catch (NumberFormatException e) {
						System.out.println("You didn't enter a valid number");
						this.goToOption(menuOption);
					}
					catch (Exception e) {
						System.out.println(e.getMessage());
					}
					Catalog.clearConsole();
					this.showMenuOptions();
					break;
				case 2:
					// Get comic info to be deleted
					String comicNameToRemove = "";
					int comicNumberToRemove = 0;
					Catalog.clearConsole();
					System.out.println("Delete a comic");
					System.out.println("Comic Name: ");
					comicNameToRemove = input.nextLine();
					try {
						System.out.println("Comic Number: ");
						comicNumberToRemove = new Integer(input.nextLine());
						Catalog.removeComic(comicNameToRemove, comicNumberToRemove);
						System.out.println("Comic Deleted");
					} catch (NumberFormatException e) {
						System.out.println("You didn't enter a valid number");
						this.goToOption(menuOption);
					}
					catch (Exception e) {
						System.out.println(e.getMessage());
					}
					Catalog.clearConsole();
					this.showMenuOptions();
					break;
				case 3:
					// Get comic info to be modified
					String comicNameToModify = "";
					int comicNumberToModify = 0;
					String comicNewName = "";
					int comicNewNumber = 0;
					int comicNewYear = 0;
					int comicNewCopies = 0;
					int comicNewGenre = 0;
					System.out.println("Comic Name: ");
					comicNameToModify = input.nextLine();
					System.out.println("Comic Number: ");
					try {
						comicNumberToModify = new Integer(input.nextLine());
						Comic comicToModify;
						comicToModify = Catalog.getComic(comicNameToModify, comicNumberToModify);
						System.out.println("Comic New Name: ");
						comicNewName = input.nextLine();
						System.out.println("Comic New Number: ");
						comicNewNumber = new Integer (input.nextLine());
						System.out.println("Comic New Year: ");
						comicNewYear = new Integer (input.nextLine());
						System.out.println("Comic New Copies: ");
						comicNewCopies = new Integer (input.nextLine());
						Catalog.modifyComic(comicToModify, comicNewName, comicNewNumber, comicNewYear, comicNewCopies);
						System.out.println("Comic modified");
					} 
					catch (NumberFormatException e) {
						System.out.println("You didn't enter a valid number");
						this.goToOption(menuOption);
					}
					catch (Exception e) {
						System.out.println(e.getMessage());
					}
					Catalog.clearConsole();
					this.showMenuOptions();
					break;
				case 4:
					Catalog.clearConsole();
					Catalog.showComics();
					Catalog.clearConsole();
					this.showMenuOptions();
					break;
				case 5:
					Catalog.clearConsole();
					Catalog.showLoans();
					Catalog.clearConsole();
					this.showMenuOptions();
					break;
				case 6:
					Catalog.clearConsole();
					Catalog.showLoans();
					int loanToApprove = 0;
					System.out.println("Approve a Loan");
					System.out.println("Loan Number: ");
					try {
						loanToApprove = new Integer(input.nextLine());
						Catalog.approveLoan(loanToApprove);
						System.out.println("Loan approved");
					}
					catch (NumberFormatException e) {
						System.out.println("You didn't enter a valid number");
						this.goToOption(menuOption);
					}
					catch (Exception e) {
						System.out.println(e.getMessage());
					}
					Catalog.clearConsole();
					this.showMenuOptions();
					break;
				case 7:
					Catalog.clearConsole();
					Catalog.showLoans();
					int loanToFinish = 0;
					System.out.println("Register a Devolution");
					System.out.println("Loan Number to Finish: ");
					try {
						loanToFinish = new Integer(input.nextLine());
						Catalog.registerDevolution(loanToFinish);
						System.out.println("Devolution registered. Loan Finished");
					}
					catch (NumberFormatException e) {
						System.out.println("You didn't enter a valid number");
						this.goToOption(menuOption);
					}
					catch (Exception e) {
						System.out.println(e.getMessage());
					}
					Catalog.clearConsole();
					this.showMenuOptions();
					break;
				case 8:
					Catalog.clearConsole();
					Catalog.showLoans();
					int loanToReject = 0;
					System.out.println("Reject a Loan");
					System.out.println("Loan Number to Reject: ");
					try {
						loanToReject = new Integer(input.nextLine());
						Catalog.rejectLoan(loanToReject);
						System.out.println("Loan Rejected");
					}
					catch (NumberFormatException e) {
						System.out.println("You didn't enter a valid number");
						this.goToOption(menuOption);
					}
					catch (Exception e) {
						System.out.println(e.getMessage());
					}
					Catalog.clearConsole();
					this.showMenuOptions();
					break;
				case 9:
					// Get user info to be created
					String newUserName = "";
					String newUserPassword = "";
					Catalog.clearConsole();
					System.out.println("Create a New User");
					System.out.println("User Name: ");
					newUserName = input.nextLine();
					System.out.println("User Password: ");
					newUserPassword = input.nextLine();
					Login.createUser(newUserName, newUserPassword);
					System.out.println("User Created");
					Catalog.clearConsole();
					this.showMenuOptions();
					break;
				case 10:
					// Get user info to be deleted
					String userNameToRemove = "";
					Catalog.clearConsole();
					System.out.println("Remove an User");
					System.out.println("User Name: ");
					userNameToRemove = input.nextLine();
					try {
						Login.removeUser(userNameToRemove);
						System.out.println("User Deleted");
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					Catalog.clearConsole();
					this.showMenuOptions();
					break;
				case 11:
					// Get user info to be modified
					String userNameToModify = "";
					Catalog.clearConsole();
					System.out.println("Modify an User");
					System.out.println("User Name: ");
					userNameToModify = input.nextLine();
					String userNewName = "";
					String userNewPassword = "";
					System.out.println("User New Name: ");
					userNewName = input.nextLine();
					System.out.println("User New Password: ");
					userNewPassword = input.nextLine();
					try {
						Login.modifyUser(userNameToModify, userNewName, userNewPassword);
						System.out.println("User Modified");
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					Catalog.clearConsole();
					this.showMenuOptions();
					break;
				case 12:
					Catalog.clearConsole();
					Login.showUsers();
					Catalog.clearConsole();
					this.showMenuOptions();
					break;
				case 13:
					// Get genre info to be created
					String newGenreName = "";
					Catalog.clearConsole();
					System.out.println("Crete a New Genre");
					System.out.println("Genre Name: ");
					newGenreName = input.nextLine();
					Catalog.createGenre(newGenreName);
					System.out.println("Genre Created");
					Catalog.clearConsole();
					this.showMenuOptions();
					break;
				case 14:
					// Get genre info to be deleted
					String genreNameToRemove = "";
					Catalog.clearConsole();
					System.out.println("Remove a Genre");
					System.out.println("Genre Name: ");
					genreNameToRemove = input.nextLine();
					try {
						Catalog.removeGenre(genreNameToRemove);
						System.out.println("Genre Deleted");
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					Catalog.clearConsole();
					this.showMenuOptions();
					break;
				case 15:
					// Get genre info to be modified
					String genreNameToModify = "";
					Catalog.clearConsole();
					System.out.println("Modify a Genre");
					System.out.println("Genre Name: ");
					genreNameToModify = input.nextLine();
					String genreNewName = "";
					System.out.println("Genre New Name: ");
					genreNewName = input.nextLine();
					try {
						Catalog.modifyGenre(genreNameToModify, genreNewName);
						System.out.println("Genre Modified");
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					Catalog.clearConsole();
					this.showMenuOptions();
					break;
				case 16:
					Catalog.clearConsole();
					Catalog.showGenres();
					Catalog.clearConsole();
					this.showMenuOptions();
					break;
				case 17:
					Menu newMenu = new Menu("Z");
					newMenu.showMenuOptions();
					break;
				default:
					System.out.println("Invalid Option");
					this.showMenuOptions();
					break;
			}
		}
		else if(userType.equals("U")) {
			switch(menuOption) {
				case 1:
					Catalog.clearConsole();
					System.out.println("This is the Comic Catalog");
					Catalog.showComics();
					Catalog.clearConsole();
					this.showMenuOptions();
					break;
				case 2:
					Catalog.clearConsole();
					Catalog.showComics();
					// Get comic info for loan
					String comicName = "";
					int comicNumber = 0;
					System.out.println("Request a Loan");
					System.out.println("Comic Name: ");
					comicName = input.nextLine();
					try {
						System.out.println("Comic Number: ");
						comicNumber = new Integer(input.nextLine());
						Comic comicToLoan;
						comicToLoan = Catalog.getComic(comicName, comicNumber);
						if ((comicToLoan.getCopies()-Catalog.getComicCopiesLoaned(comicName, comicNumber)) > 0) {
							Catalog.createLoan(comicToLoan);
							System.out.println("Loan Requested. Pending for Approval");
						}
						else {
							System.out.println("There are no copies available for this comic");
						}
					} catch (NumberFormatException e) {
						System.out.println("You didn't enter a valid number");
						this.goToOption(menuOption);
					}
					catch (Exception e) {
						System.out.println(e.getMessage());
					}
					Catalog.clearConsole();
					this.showMenuOptions();
					break;
				case 3:
					Person currentUser = Login.getLoggedUser();
					Catalog.clearConsole();
					System.out.println("This is Your Loan History");
					Catalog.showUserLoans(currentUser.getUsername());
					Catalog.clearConsole();
					this.showMenuOptions();
					break;
				case 4:
					Menu newMenu = new Menu("Z");
					newMenu.showMenuOptions();
					break;
				default:
					System.out.println("You didn't Type a Valid Option");
					this.showMenuOptions();
					break;
			}
		}
		else {
			switch(menuOption) {
				case 1:
					login();
					break;
				case 2:
					System.exit(0);
					break;
				default:
					System.exit(0);
					break;
			}
		}
		return true;
	}

	private void login() {
		Scanner input = new Scanner(System.in);
	    String username = "";
	    String password = "";

	    Catalog.clearConsole();
	    System.out.println("The Sheldon Cooper Comic Catalog");
	    System.out.println("Login");

	    // Read username and password
	    System.out.print("Username: ");
	    username = input.nextLine();
	    System.out.print("Password: ");
	    password = input.nextLine();
	    // Check login
	    Login userLoged = new Login(username, password);
	    userLoged.clearLoggedUser();

	    Person loggedUser = userLoged.checkAuth();
	    // If valid user, check user type and show proper menu
	    if(loggedUser != null) {
	    	Menu userMenu = new Menu(loggedUser.getType());
	    	userMenu.showMenuOptions();
	    }
	    else {
	    	System.out.println("This is not a Valid User");
	    	Menu userMenu = new Menu("Z");
		    userMenu.showMenuOptions();
	    }

	}
}
