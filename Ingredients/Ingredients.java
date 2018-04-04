package brewDay;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;


@Entity
@Table(name = "ingredients")
public class Ingredients 
{

    protected Ingredients()
    {

    }

    public Ingredients(double grainMalts, double hops, double sugars, double yeast, double additives) 
    {
        this.grainMalts = grainMalts;
        this.hops = hops;
        this.sugars = sugars;
        this.yeast = yeast;
        this.additives = additives;
        
    }


    /*@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;*/

    @Temporal(TIMESTAMP)
    @Column(name="created_date", nullable = false)
    private Date createdDate;

    @Temporal(TIMESTAMP)
    @Column(name="last_modified_date", nullable = false)
    private Date lastModifiedDate;


    @Column(name="Grain Malts")
    private double grainMalts;

    @Column(name="Hops")
    private double hops;

    @Column(name="Sugars")
    private double sugars;

    @Column(name="Yeast")
    @NotNull
    private double yeast;

    @Column(name="Additives")
    private double additives;


    @PrePersist
    protected void onCreate() 
    {
        lastModifiedDate = createdDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() 
    {
        lastModifiedDate = new Date();
    }


    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
    
    public double getGrainMalts()
	{
		return grainMalts;
	}
	
	public void setGrainMalts(double grainMalts)
	{
		this.grainMalts = grainMalts;
	}
	
	public double getHops()
	{
		return hops;
	}
	
	public void setHops(double hops)
	{
		this.hops = hops;
	}
	
	public double getSugars()
	{
		return sugars;
	}
	
	public void setSugars(double sugars)
	{
		this.sugars = sugars;
	}
	
	public double getYeast()
	{
		return yeast;
	}
	
	public void setYeast(double yeast)
	{
		this.yeast = yeast;
	}
	
	public double getAdditives()
	{
		return additives;
	}
	
	public void setAdditives(double additives)
	{
		this.additives = additives;
	}

    public Date getLastModifiedDate()
    {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) 
    {
        this.lastModifiedDate = lastModifiedDate;
    }


}

