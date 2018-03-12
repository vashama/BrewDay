package brew_day;

import java.sql.Time;
import java.util.Date;

public class Recipe {
	private int id;
	private String name;
	private Date createdDate;
	private String batchSize;
	private double ABV;
	private double IBU;
	private String instructions;
	private Time boilTime;
	private String brewType;
	private String beerName;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(String batchSize) {
		this.batchSize = batchSize;
	}

	public double getABV() {
		return ABV;
	}

	public void setABV(double aBV) {
		ABV = aBV;
	}

	public String getBeerName() {
		return beerName;
	}

	public void setBeerName(String beerName) {
		this.beerName = beerName;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public String getInstructions() {
		return instructions;
	}
	
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	
	public double getIBU() {
		return IBU;
	}
	
	public void setIBU(double iBU) {
		IBU = iBU;
	}
	
	public Time getBoilTime() {
		return boilTime;
	}
	
	public void setBoilTime(Time boilTime) {
		this.boilTime = boilTime;
	}
	
	public String getBrewType() {
		return brewType;
	}
	
	public void setBrewType(String brewType) {
		this.brewType = brewType;
	}
}
