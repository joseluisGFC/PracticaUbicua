package db;

public class Sensor 
{
    private int id;
    private String name;
    private String tipo;
    private int valorMin;
    private int valorMax;
    // constructors
    public Sensor() 
    {
    	this.id = 0;
    	this.name = null;
    	this.tipo = null;
    	this.valorMin = 0;
    	this.valorMax = 100;
    }
    public Sensor(int id, String name, String tipo, int valorMin, int valorMax) 
    {
    	this.id = id;
    	this.name = name;
    	this.tipo = tipo;
    	this.valorMin = valorMin;
    	this.valorMax = valorMax;
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

	public int getvalorMin() {
		return this.valorMin;
	}

	public void setvalorMin(int valueMin) {
		this.valorMin = valueMin;
	}
	public int getvalorMax() {
		return this.valorMax;
	}

	public void setvalorMax(int valueMax) {
		this.valorMax = valueMax;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

 }
