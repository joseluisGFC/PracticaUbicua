package logic;

import java.util.ArrayList;

import db.Ciudad;
import db.ConectionDDBB;

import db.Contenedores;
import db.Medida;
import db.Sensor;
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
	public static ArrayList<Contenedores> getContenedoresFromDB()
	{
		ArrayList<Contenedores> contenedores = new ArrayList<Contenedores>();
		
		ConectionDDBB conector = new ConectionDDBB();
		Connection con = null;
		Contenedores contenedor = new Contenedores();
		try
		{
			con = conector.obtainConnection(true);
			Log.log.debug("Database Connected");
			
			PreparedStatement ps = ConectionDDBB.GetContendores(con);//la query de la base de datos
			Log.log.info("Query=> {}", ps.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				
				contenedor.setId(rs.getInt("id"));//creas un objeto contendor con los datos de la query
				contenedor.setName(rs.getString("id"));
				contenedor.setLatitude(rs.getDouble("latitud"));
				contenedor.setLongitude(rs.getDouble("longuitud"));
				contenedores.add(contenedor);
				contenedor = new Contenedores();
			}	
			
		} catch (SQLException e)
		{
			Log.log.error("Error: {}", e);
			contenedores = new ArrayList<Contenedores>();
		} catch (NullPointerException e)
		{
			Log.log.error("Error: {}", e);
			contenedores = new ArrayList<Contenedores>();
		} catch (Exception e)
		{
			Log.log.error("Error:{}", e);
			contenedores = new ArrayList<Contenedores>();
		} finally
		{
			conector.closeConnection(con);
			
		}
		return contenedores;
		
	}

	public static ArrayList<Sensor> getSensoresFromDB()
	{
		ArrayList<Sensor> sensores = new ArrayList<Sensor>();
		
		ConectionDDBB conector = new ConectionDDBB();
		Connection con = null;
		Sensor sensor = new Sensor();
		try
		{
			con = conector.obtainConnection(true);
			Log.log.debug("Database Connected");
			
			PreparedStatement ps = ConectionDDBB.GetSensores(con);//la query de la base de datos
			Log.log.info("Query=> {}", ps.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				
				sensor.setId(rs.getInt("id"));//creas un objeto contendor con los datos de la query
				sensor.setName(rs.getString("id"));
				sensor.setTipo(rs.getString("tipoSensor"));
				sensor.setvalorMin(rs.getInt("valor_min"));
				sensor.setvalorMax(rs.getInt("valor_max"));
				sensor.setValorAlerta(rs.getInt("valor_alerta"));
				sensores.add(sensor);
				sensor = new Sensor();
			}	
			
		} catch (SQLException e)
		{
			Log.log.error("Error: {}", e);
			sensores = new ArrayList<Sensor>();
		} catch (NullPointerException e)
		{
			Log.log.error("Error: {}", e);
			sensores = new ArrayList<Sensor>();
		} catch (Exception e)
		{
			Log.log.error("Error:{}", e);
			sensores = new ArrayList<Sensor>();
		} finally
		{
			conector.closeConnection(con);
			
		}
		return sensores;
		
	}

	
	public static ArrayList<Medida> getMedidasFromDB()
	{
		ArrayList<Medida> medidas = new ArrayList<Medida>();
		
		ConectionDDBB conector = new ConectionDDBB();
		Connection con = null;
		Medida medida = new Medida();
		try
		{
			con = conector.obtainConnection(true);
			Log.log.debug("Database Connected");
			
			PreparedStatement ps = ConectionDDBB.GetMedidas(con);//la query de la base de datos
			Log.log.info("Query=> {}", ps.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				
				medida.setId_sensor(rs.getInt("id_sensor"));//creas un objeto contendor con los datos de la query
				medida.setId_contenedor(rs.getInt("id_contenedor"));
				medida.setDateMeasurement(rs.getDate("fecha"));
				medida.setValue(rs.getInt("valor"));
				medidas.add(medida);
				medida = new Medida();
			}	
			
		} catch (SQLException e)
		{
			Log.log.error("Error: {}", e);
			medidas = new ArrayList<Medida>();
		} catch (NullPointerException e)
		{
			Log.log.error("Error: {}", e);
			medidas = new ArrayList<Medida>();
		} catch (Exception e)
		{
			Log.log.error("Error:{}", e);
			medidas = new ArrayList<Medida>();
		} finally
		{
			conector.closeConnection(con);
			
		}
		return medidas;
		
	}

	public static ArrayList<Medida> getMedidasFromContenedorFromDB(int id_contenedor)
	{
		ArrayList<Medida> medidas = new ArrayList<Medida>();
		
		ConectionDDBB conector = new ConectionDDBB();
		Connection con = null;
		Medida medida = new Medida();
		try
		{
			con = conector.obtainConnection(true);
			Log.log.debug("Database Connected");
			
			PreparedStatement ps = ConectionDDBB.GetMedidasFromContenedor(con);//la query de la base de datos
			Log.log.info("Query=> {}", ps.toString());
			ps.setInt(1, id_contenedor);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				medida.setId_sensor(rs.getInt("id_sensor"));//creas un objeto contendor con los datos de la query
				medida.setId_contenedor(rs.getInt("id_contenedor"));
				medida.setDateMeasurement(rs.getDate("fecha"));
				medida.setValue(rs.getInt("valor"));
				medidas.add(medida);
				medida = new Medida();
			}	
			
		} catch (SQLException e)
		{
			Log.log.error("Error: {}", e);
			medidas = new ArrayList<Medida>();
		} catch (NullPointerException e)
		{
			Log.log.error("Error: {}", e);
			medidas = new ArrayList<Medida>();
		} catch (Exception e)
		{
			Log.log.error("Error:{}", e);
			medidas = new ArrayList<Medida>();
		} finally
		{
			conector.closeConnection(con);
			
		}
		return medidas;
		
	}

	public static ArrayList<Medida> getMedidasFromContenedorFromSensorFromDB(int id_contenedor, String tipo)
	{
		ArrayList<Medida> medidas = new ArrayList<Medida>();
		
		ConectionDDBB conector = new ConectionDDBB();
		Connection con = null;
		Medida medida = new Medida();
		try
		{
			con = conector.obtainConnection(true);
			Log.log.debug("Database Connected");
			
			PreparedStatement ps = ConectionDDBB.GetMedidasFromContenedorFromSensor(con);//la query de la base de datos
			Log.log.info("Query=> {}", ps.toString());
			ps.setInt(1, id_contenedor);
			ps.setString(2, tipo);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				medida.setId_sensor(rs.getInt("id_sensor"));//creas un objeto contendor con los datos de la query
				medida.setId_contenedor(rs.getInt("id_contenedor"));
				medida.setDateMeasurement(rs.getDate("fecha"));
				medida.setValue(rs.getInt("valor"));
				medidas.add(medida);
				medida = new Medida();
			}	
			
		} catch (SQLException e)
		{
			Log.log.error("Error: {}", e);
			medidas = new ArrayList<Medida>();
		} catch (NullPointerException e)
		{
			Log.log.error("Error: {}", e);
			medidas = new ArrayList<Medida>();
		} catch (Exception e)
		{
			Log.log.error("Error:{}", e);
			medidas = new ArrayList<Medida>();
		} finally
		{
			conector.closeConnection(con);
			
		}
		return medidas;
		
	}	
	
	public static ArrayList<Medida> getMedidasEnAlerta()
	{
		ArrayList<Medida> medidas = new ArrayList<Medida>();
		
		ConectionDDBB conector = new ConectionDDBB();
		Connection con = null;
		Medida medida = new Medida();
		try
		{
			con = conector.obtainConnection(true);
			Log.log.debug("Database Connected");
			
			PreparedStatement ps = ConectionDDBB.GetMedidasEnAlerta(con);//la query de la base de datos
			Log.log.info("Query=> {}", ps.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{				
				medida.setId_sensor(rs.getInt("id_sensor"));//creas un objeto contendor con los datos de la query
				medida.setId_contenedor(rs.getInt("id_contenedor"));
				medida.setDateMeasurement(rs.getDate("fecha"));
				medida.setValue(rs.getInt("valor"));
				medidas.add(medida);
				medida = new Medida();
			}	
			
		} catch (SQLException e)
		{
			Log.log.error("Error: {}", e);
			medidas = new ArrayList<Medida>();
		} catch (NullPointerException e)
		{
			Log.log.error("Error: {}", e);
			medidas = new ArrayList<Medida>();
		} catch (Exception e)
		{
			Log.log.error("Error:{}", e);
			medidas = new ArrayList<Medida>();
		} finally
		{
			conector.closeConnection(con);
			
		}
		return medidas;
		
	}	
	
	public static ArrayList<Contenedores> getContenedorWithId(int id)
	{
		ArrayList<Contenedores> contenedores = new ArrayList<Contenedores>();
		
		ConectionDDBB conector = new ConectionDDBB();
		Connection con = null;
		Contenedores contenedor = new Contenedores();
		try
		{
			con = conector.obtainConnection(true);
			Log.log.debug("Database Connected");
			
			PreparedStatement ps = ConectionDDBB.GetContenedorWithIdFromDB(con);//la query de la base de datos
			Log.log.info("Query=> {}", ps.toString());
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				contenedor.setId(rs.getInt("id"));//creas un objeto contendor con los datos de la query
				contenedor.setName(rs.getString("id"));
				contenedor.setLatitude(rs.getDouble("latitud"));
				contenedor.setLongitude(rs.getDouble("longuitud"));
				contenedores.add(contenedor);
				contenedor = new Contenedores();
			}	
			
		} catch (SQLException e)
		{
			Log.log.error("Error: {}", e);
			contenedores = new ArrayList<Contenedores>();
		} catch (NullPointerException e)
		{
			Log.log.error("Error: {}", e);
			contenedores = new ArrayList<Contenedores>();
		} catch (Exception e)
		{
			Log.log.error("Error:{}", e);
			contenedores = new ArrayList<Contenedores>();
		} finally
		{
			conector.closeConnection(con);
			
		}
		return contenedores;
		
	}	
	
}
