package db;

import java.util.Date;

public class Ruta 
{
	private int id_contenedor;
	private int id_sensor;
	private int value;
	private Date dateMeasurement;
    private Double latitude;
    private Double longitude;
	
	
	public Ruta() {
		super();
		this.id_contenedor = 0;
		this.id_sensor = 0;
		this.value = 0;
		this.dateMeasurement = null;
		this.latitude = 0.0;
		this.longitude = 0.0;
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

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

}