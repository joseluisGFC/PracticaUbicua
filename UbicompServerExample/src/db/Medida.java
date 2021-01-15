package db;

import java.util.Date;

public class Medida 
{
	private int id_contenedor;
	private int id_sensor;
	private int value;
	private Date dateMeasurement;
	
	
	public Medida() {
		super();
		this.id_contenedor = 0;
		this.id_sensor = 0;
		this.value = 0;
		this.dateMeasurement = null;
	}
	
	public int getId_sensor() {
		return id_sensor;
	}
	public void setId_sensor(int id_sensor) {
		this.id_sensor = id_sensor;
	}
	public int getId_contenedor() {
		return id_contenedor;
	}
	public void setId_contenedor(int id_contendor) {
		this.id_contenedor = id_contendor;
	}
	public Date getDateMeasurement() {
		return dateMeasurement;
	}
	public void setDateMeasurement(Date dateMeasurement) {
		this.dateMeasurement = dateMeasurement;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}

}