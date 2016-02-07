package comics;

import java.util.Date;

public class Loan {
	private Person user;
	private Comic comic;
	private String status;
	private Date initialDate = new Date();
	
	public Loan(Person user, Comic comic, String status, Date initialDate) {
		this.user = user;
		this.comic = comic;
		this.status = status;
		this.initialDate = initialDate;
	}
	
	public Person getUser() {
		return user;
	}
	public void setUser(Person user) {
		this.user = user;
	}
	public Comic getComic() {
		return comic;
	}
	public void setComic(Comic comic) {
		this.comic = comic;
	}
	public Date getInitialDate() {
		return initialDate;
	}
	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
