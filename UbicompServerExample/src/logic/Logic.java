package logic;

import java.util.ArrayList;

import db.Ciudad;
import db.ConectionDDBB;

import db.SensorType;
import db.Contenedores;
import db.Topics;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Logic 
{
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 
	 * @return The list of all the stations stored in the db
	 */
	public static ArrayList<Contenedores> getStationsFromDB()
	{
		ArrayList<Contenedores> stations = new ArrayList<Contenedores>();
		
		ConectionDDBB conector = new ConectionDDBB();
		Connection con = null;
		Contenedores station = new Contenedores();
		try
		{
			con = conector.obtainConnection(true);
			Log.log.debug("Database Connected");
			
			PreparedStatement ps = ConectionDDBB.GetStations(con);
			Log.log.info("Query=> {}", ps.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				
				station.setId(rs.getInt("id"));
				station.setName(rs.getString("id"));
				station.setLatitude(rs.getDouble("latitud"));
				station.setLongitude(rs.getDouble("longuitud"));
				stations.add(station);
				station = new Contenedores();
			}	
			
		} catch (SQLException e)
		{
			Log.log.error("Error: {}", e);
			stations = new ArrayList<Contenedores>();
		} catch (NullPointerException e)
		{
			Log.log.error("Error: {}", e);
			stations = new ArrayList<Contenedores>();
		} catch (Exception e)
		{
			Log.log.error("Error:{}", e);
			stations = new ArrayList<Contenedores>();
		} finally
		{
			conector.closeConnection(con);
			
		}
		return stations;
		
	}
	public static ArrayList<Ciudad> getCitiesFromDB()
	{
		ArrayList<Ciudad> cities = new ArrayList<Ciudad>();
		
		ConectionDDBB conector = new ConectionDDBB();
		Connection con = null;
		try
		{
			con = conector.obtainConnection(true);
			Log.log.debug("Database Connected");
			
			PreparedStatement ps = ConectionDDBB.GetCities(con);
			Log.log.info("Query=> {}", ps.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				Ciudad city = new Ciudad();
				city.setId(rs.getInt("id_ciudad"));
				city.setName(rs.getString("nombre"));
				cities.add(city);
			}	
		} catch (SQLException e)
		{
			Log.log.error("Error: {}", e);
			cities = new ArrayList<Ciudad>();
		} catch (NullPointerException e)
		{
			Log.log.error("Error: {}", e);
			cities = new ArrayList<Ciudad>();
		} catch (Exception e)
		{
			Log.log.error("Error:{}", e);
			cities = new ArrayList<Ciudad>();
		} finally
		{
			conector.closeConnection(con);
		}
		return cities;
	}
	
	/**
	 * 
	 * @return The list of all the stations stored in the db of a city
	 */
	public static ArrayList<Contenedores> getStationsFromCity(int cityId)
	{
		ArrayList<Contenedores> stations = new ArrayList<Contenedores>();
		
		ConectionDDBB conector = new ConectionDDBB();
		Connection con = null;
		try
		{
			con = conector.obtainConnection(true);
			Log.log.debug("Database Connected");
			
			PreparedStatement ps = ConectionDDBB.GetStations(con);
			ps.setInt(1, cityId);
			Log.log.info("Query=> {}", ps.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				Contenedores station = new Contenedores();
				station.setId(rs.getInt("id"));
				station.setName(rs.getString("nombre"));
				station.setLatitude(rs.getDouble("latitud"));
				station.setLongitude(rs.getDouble("longuitud"));
				stations.add(station);
			}	
		} catch (SQLException e)
		{
			Log.log.error("Error: {}", e);
			stations = new ArrayList<Contenedores>();
		} catch (NullPointerException e)
		{
			Log.log.error("Error: {}", e);
			stations = new ArrayList<Contenedores>();
		} catch (Exception e)
		{
			Log.log.error("Error:{}", e);
			stations = new ArrayList<Contenedores>();
		} finally
		{
			conector.closeConnection(con);
		}
		return stations;
	}
	

	
	/**
	 * 
	 * @param idStation ID of the station to search
	 * @return The list of sensors of a Installation
	 */
	public static ArrayList<SensorType> getStationSensorsFromDB(int idStation)
	{
		ArrayList<SensorType> sensors = new ArrayList<SensorType>();	
		
		ConectionDDBB conector = new ConectionDDBB();
		Connection con = null;
		try
		{
			con = conector.obtainConnection(true);
			Log.log.debug("Database Connected");
			
			PreparedStatement ps = ConectionDDBB.GetStationSensors(con);
			ps.setInt(1, idStation);
			Log.log.info("Query=> {}", ps.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				SensorType sensor = new SensorType();
				sensor.setId(rs.getInt("id"));
				sensor.setName(rs.getString("nombre"));
				sensor.setUnits(rs.getString("valor_alerta")); /////////////////////////////////////////////
				if(rs.getInt("id")>0)
				{
					sensor.setAvailable(1);
					//EN:Search the last value of the station
					PreparedStatement ps_value = ConectionDDBB.GetLastValueStationSensor(con);
					ps_value.setInt(1, idStation);
					ps_value.setInt(2, rs.getInt("id"));
					Log.log.info("Query=> {}", ps_value.toString());
					ResultSet rs_value = ps_value.executeQuery();
					if (rs_value.next())
					{
						sensor.setValue(rs_value.getInt("valor_alerta"));
						if(sensor.getValue()<rs.getInt("valor_min"))
						{
							sensor.setLabel(0); //Low value
						}else
						{
							if(sensor.getValue()>rs.getInt("valor_max"))
							{
								sensor.setLabel(2); //High value
							}else
							{
								sensor.setLabel(1);	 //Medium value
							}
						}
					}
				}else
				{
					sensor.setAvailable(0);
					//TODO buscar la media de la ciudad para dar un valor aproximado
				}
				sensors.add(sensor);
			}	
		} catch (SQLException e)
		{
			Log.log.error("Error: {}", e);
			sensors = new ArrayList<SensorType>();
		} catch (NullPointerException e)
		{
			Log.log.error("Error: {}", e);
			sensors = new ArrayList<SensorType>();
		} catch (Exception e)
		{
			Log.log.error("Error:{}", e);
			sensors = new ArrayList<SensorType>();
		} finally
		{
			conector.closeConnection(con);
		}
		return sensors;
	}
	
	

	
	/**
	 * 	
	 * @param idStation
	 * @return Arraylist with the measurements
	 */
	
	
	
	public static void storeNewMeasurement(Topics newTopic)
	{
		ConectionDDBB conector = new ConectionDDBB();
		Connection con = null;
		try
		{
			con = conector.obtainConnection(true);
			Log.log.debug("Database Connected");
			
			PreparedStatement ps = ConectionDDBB.InsertnewMeasurement(con);
			ps.setString(1, newTopic.getIdStation());
			ps.setString(2, newTopic.getIdSensor());
	        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			ps.setString(3, sdf.format(timestamp));
			ps.setString(4, newTopic.getValue());
			ps.setString(5, newTopic.getIdStation());
			ps.setString(6, newTopic.getIdSensor());
			ps.setString(7, sdf.format(timestamp));
			ps.setString(8, newTopic.getValue());
			Log.log.info("Query to store Measurement=> {}", ps.toString());
			ps.executeUpdate();
		} catch (SQLException e)
		{
			Log.log.error("Error: {}", e);
		} catch (NullPointerException e)
		{
			Log.log.error("Error: {}", e);
		} catch (Exception e)
		{
			Log.log.error("Error:{}", e);
		} finally
		{
			conector.closeConnection(con);
		}
	}
	
	public static double getWeatherForecast(int idStation)
	{
		double forecast  = 0;
		
		ConectionDDBB conector = new ConectionDDBB();
		Connection con = null;
		try
		{
			con = conector.obtainConnection(true);
			Log.log.debug("Database Connected");
			
			PreparedStatement ps = ConectionDDBB.GetInfoFromStation(con);
			ps.setInt(1, idStation);
			Log.log.info("Query=> {}", ps.toString());
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				int id_contenedor=rs.getInt("id_contenedor");
				int id_sensor=rs.getInt("id_sensor");
				
				forecast = ThreadWeatherForecast.obtainWeatherString(id_contenedor,id_sensor); 
				
			}	
		} catch (SQLException e)
		{
			Log.log.error("Error: {}", e);
			forecast  = 0;
		} catch (NullPointerException e)
		{
			Log.log.error("Error: {}", e);
			forecast  = 0;
		} catch (Exception e)
		{
			Log.log.error("Error:{}", e);
			forecast  = 0;
		} finally
		{
			conector.closeConnection(con);
		}
		return forecast;
	}
	
	
	
}
