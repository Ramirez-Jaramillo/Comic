package comics;

import java.util.ArrayList;

public class Login {
	private String username;
	private String password;
	private String type;
	private static ArrayList<Person> validAccounts = new ArrayList<>(); 
	static {
		Person newUser = new Person("Sheldon", "Bazinga", "A", "I");
		validAccounts.add(newUser);
		newUser = new Person("user", "user1234", "U", "I");
		validAccounts.add(newUser);
	}
			
	public Login(String user, String pass){
		this.username = user;
	    this.password = pass;
	}
	
	public Person checkAuth(){
		for (Person person : validAccounts) {
			if (person.getUsername().equals(username) && person.getPassword().equals(password)) {
				person.setStatus("A");
				return person;
			}
		}
		
		return null;
	}
	
	public String getUserType() {
		return type;
	}
	
	public static void showUsers() {
		System.out.println("Current Users: ");
		for (Person persons : validAccounts) {
			System.out.println("User: " + persons.getUsername());
		}
	}
	
	public static Person getLoggedUser() {
		for (Person person : validAccounts) {
			if (person.getStatus().equals("A")) {
				return person;
			}
		}
		
		return null;
	}
	
	public static void clearLoggedUser() {
		for (Person person : validAccounts) {
			person.setStatus("I");
		}
	}
	
	public static void createUser(String username, String password) {
		Person newUser = new Person(username, password, "U", "I");
		validAccounts.add(newUser);
	}
	
	public static void removeUser(String userName) throws Exception {
		Person userToRemove = getPerson(userName);
		if (userToRemove.getType()!= "A") {
			validAccounts.remove(userToRemove);
		}
		else {
			throw new Exception("Can't remove Admin");
		}
	}
	
	public static void modifyUser(String userName, String userNewName, String userNewPassword) throws Exception {
		Person userToModify = getPerson(userName);
		if (userToModify.getType()!= "A") {
			userToModify.setUsername(userNewName);
			userToModify.setPassword(userNewPassword);
		}
		else {
			throw new Exception("Can't modify Admin");
		}
	}
	
	public static Person getPerson(String userName) throws Exception {
		for (Person users : validAccounts) {
			if (users.getUsername().equals(userName)) {
				return users;
			}
		}
		
		throw new Exception("User not found");
	}
}
