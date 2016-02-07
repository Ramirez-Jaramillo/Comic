package comics;

public class Comic {
	private String name;
	private int number;
	private int year;
	private int copies;
	private Genre genre;
	
	public void createComic(String comicName, int comicNumber, int comicYear, int comicCopies, int comicGenre) {
		// Set variables to object attributes
		this.name = comicName;
		this.number = comicNumber;
		this.year = comicYear;
		this.copies = comicCopies;
		this.genre = Catalog.getGenre(comicGenre);
    	Catalog.addComic(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getCopies() {
		return copies;
	}

	public void setCopies(int copies) {
		this.copies = copies;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
}
