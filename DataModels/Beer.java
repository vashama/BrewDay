package brew_day;

public class Beer {
	private int id;
	private String name;
	private String beerType;
	private String color;
	private String brewNotes;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getBeerType() {
		return beerType;
	}
	
	public void setBeerType(String beerType) {
		this.beerType = beerType;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public String getBrewNotes() {
		return brewNotes;
	}
	
	public void setBrewNotes(String brewNotes) {
		this.brewNotes = brewNotes;
	}
}
