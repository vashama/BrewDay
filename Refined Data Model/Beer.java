package brew_day;

import java.util.Date;

public class Beer {
	private int id;
	private String name;
	private String style;
	private String color;
	private Date last_brew_date;
	private String brewNotes;
	private User user;
	
	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public Date getLast_brew_date() {
		return last_brew_date;
	}

	public void setLast_brew_date(Date last_brew_date) {
		this.last_brew_date = last_brew_date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

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
