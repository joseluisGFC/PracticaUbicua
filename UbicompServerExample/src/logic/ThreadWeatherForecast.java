package logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import db.ConectionDDBB;
import db.Measurement;

/**
 * ES:Hilo que obtiene la previsión del tiempo de wunderground.com/api para insertar la previsión en la base de datos
 * EN:This thread will obtain the weather forecast from wunderground.com/api to insert data obtained in the data base
 */
public class ThreadWeatherForecast extends Thread
{
	// Period at which the Thread will read data from the JSON is 60 minutes
	private int PERIOD = 1000 * 60 * 60;	
	private static String APIkey = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX";

	
	public ThreadWeatherForecast()
	{
		start();
	}
	
	@Override
	public void run()
	{
		while (true)
		{
			
			
		}

	}

	/**
	 * Gets the API's URL page
	 * 
	 * @return URL
	 * @throws IOException
	 */
	/*
	private static URL getURLCity(String city, String countryCode) throws IOException
	{
		String url = "https://api.openweathermap.org/data/2.5/forecast?q=" + getCity() + "," + countryCode + "&appid=" + APIkey;
		Log.log.debug("URL={}", url);
		return new URL(url);
	}*/
	
	/**
	 * Gets the API's URL page
	 * 
	 * @return URL
	 * @throws IOException
	 */
	/*
	private static URL getURLGPS(String d, String e) throws IOException
	{
		String url = "https://api.openweathermap.org/data/2.5/forecast?lat=" +d + "&lon=" + e + "&appid=" + APIkey;
		Log.log.info("URL={}", url);
		return new URL(url);
	}*/

	/**
	 * ES: Crea un nuevo WeatherObtainer 
	 * EN: Creates a new WeatherObtainer
	 */	
	public static String getCity()
	{
		String city = "Guadalajara"; 
		
		try
		{
			//ES:Recorrer todas las ciudades de la base de datos
			//EN:Get all the cities stored inthe database
			ConectionDDBB conectiondb = new ConectionDDBB();
			Connection con = conectiondb.obtainConnection(true);
			PreparedStatement ps = ConectionDDBB.GetCities(con);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				city = rs.getString("nombre");
			}
			Log.log.debug("Closing data base connection");
			conectiondb.closeConnection(con);
		} catch (SQLException e)
		{
			Log.log.error("SQL error when evaluating the generic collection sequence: {}", e);
		} catch (NullPointerException e)
		{
			Log.log.error("Nullpointer error when evaluatinf the generic collection sequence: {}", e);
		} catch (Exception e)
		{
			Log.log.error("Error when evaluating the generic collection sequence: {}", e);
		}
		return city;
	}

	
//TODO cambiar
	
	
	public static double obtainWeatherString(int id_contenedor, int id_sensor)
	{
		
		
		double weather = 0;
		
		try
		{
			
			ConectionDDBB conectiondb = new ConectionDDBB();
			Connection con = conectiondb.obtainConnection(true);
			PreparedStatement ps = ConectionDDBB.GetLastValueStationSensor(con);
			ps.setInt(1, id_contenedor);
			ps.setInt(2, id_sensor);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				weather = rs.getDouble("valor");
			}
			Log.log.debug("Closing data base connection");
			conectiondb.closeConnection(con);
		} catch (SQLException e)
		{
			Log.log.error("SQL error when evaluating the generic collection sequence: {}", e);
		} catch (NullPointerException e)
		{
			Log.log.error("Nullpointer error when evaluatinf the generic collection sequence: {}", e);
		} catch (Exception e)
		{
			Log.log.error("Error when evaluating the generic collection sequence: {}", e);
		}
		
		
		return weather;
	}
}