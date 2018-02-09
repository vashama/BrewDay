package brewDay;
import java.util.Date;

public class ingridients 
{
	
	 	private int id;
	 	private String name;
	 	private String type;
	 	private Date lastPurchasedDate;
	 	private double mount;
	 	
	 	public int getId() 
	 	{
	 		return id;
	 	}
	 	
	 	public void setId(int id) 
	 	{
	 		this.id = id;
	 	}
	 	
	 	public String getName() 
	 	{
	 		return name;
	 	}
	 	
	 	public void setName(String name)
	 	{
	 		this.name = name;
	 	}
	 	
	 	public String getType()
	 	{
	 		return type;
	 	}
	 	
	 	public void setType(String type) 
	 	{
	 		this.type = type;
	 	}
	 	
	 	public Date getLastPurchasedDate() 
	 	{
	 		return lastPurchasedDate;
	 	}
	 	
	 	public void setLastPurchasedDate(Date lastPurchasedDate)
	 	{
	 		this.lastPurchasedDate = lastPurchasedDate;
	 	}
	 	
	 	public double getMount() 
	 	{
	 		return mount;
	 	}
	 	
	 	public void setMount(double mount) 
	 	{
	 		this.mount = mount;
	 	}
	 

}
