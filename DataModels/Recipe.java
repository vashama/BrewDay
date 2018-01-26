package brew_day;

import java.sql.Time;
import java.util.Date;

public class Recipe {
	private int id;
	private Date createdDate;
	private double batchSize;
	private double ABV;
	private String instructions;
	private double IBU;
	private Time boilTime;
	private String brewType;
	
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
	
	public double getBatchSize() {
		return batchSize;
	}
	
	public void setBatchSize(double batchSize) {
		this.batchSize = batchSize;
	}
	
	public double getABV() {
		return ABV;
	}
	public void setABV(double aBV) {
		ABV = aBV;
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
