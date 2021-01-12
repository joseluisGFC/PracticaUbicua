package db;

public class Ciudad 
{
    private int id;
    private String name;
 
    // constructors
    public Ciudad() 
    {
    	this.id = 0;
    	this.name = null;
    }

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
 }
