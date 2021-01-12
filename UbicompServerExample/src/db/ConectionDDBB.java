package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
/*
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
*/
import logic.Log;


public class ConectionDDBB
{
	public Connection obtainConnection(boolean autoCommit) throws NullPointerException
    {
		 Connection conn = null;
		try {
            Class.forName("com.mysql.jdbc.Driver").newInstance(); 

            String sURL = "jdbc:mysql://185.239.202.139:3306/ubicua";
            conn = DriverManager.getConnection(sURL,"admin","passwd");
            Log.logdb.debug("Conectado!!");

            
        } catch (SQLException e) {
        	 Log.logdb.debug(e.getMessage());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
        	 Log.logdb.debug("Driver no detectado");
        } 
        return conn;
		
		
		/*
        Connection con=null;
        int intentos = 5;
        for (int i = 0; i < intentos; i++) 
        {
        	Log.logdb.info("Attempt {} to connect to the database", i);
        	try
	          {
	            Context ctx = new InitialContext();
	            // Get the connection factory configured in Tomcat
	            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/prueba");

	            // Obtiene una conexion
	            con = ds.getConnection();
				Calendar calendar = Calendar.getInstance();
				java.sql.Date date = new java.sql.Date(calendar.getTime().getTime());
	            Log.logdb.debug("Connection creation. Bd connection identifier: {} obtained in {}", con.toString(), date.toString());
	            con.setAutoCommit(autoCommit);
	        	Log.logdb.info("Conection obtained in the attempt: " + i);
	            i = intentos;
	          } catch (NamingException ex)
	          {
	            Log.logdb.error("Error getting connection while trying: {} = {}", i, ex); 
	          } catch (SQLException ex)
	          {
	            Log.logdb.error("ERROR sql getting connection while trying:{ }= {}", i, ex);
	            throw (new NullPointerException("SQL connection is null"));
	          }
		}        
        return con;*/
    }
    
    public void closeTransaction(Connection con)
    {
        try
          {
            con.commit();
            Log.logdb.debug("Transaction closed");
          } catch (SQLException ex)
          {
            Log.logdb.error("Error closing the transaction: {}", ex);
          }
    }
    
    public void cancelTransaction(Connection con)
    {
        try
          {
            con.rollback();
            Log.logdb.debug("Transaction canceled");
          } catch (SQLException ex)
          {
            Log.logdb.error("ERROR sql when canceling the transation: {}", ex);
          }
    }

    public void closeConnection(Connection con)
    {
        try
          {
        	Log.logdb.info("Closing the connection");
            if (null != con)
              {
				Calendar calendar = Calendar.getInstance();
				java.sql.Date date = new java.sql.Date(calendar.getTime().getTime());
	            Log.logdb.debug("Connection closed. Bd connection identifier: {} obtained in {}", con.toString(), date.toString());
                con.close();
              }

        	Log.logdb.info("The connection has been closed");
          } catch (SQLException e)
          {
        	  Log.logdb.error("ERROR sql closing the connection: {}", e);
        	  e.printStackTrace();
          }
    }
    
    public static PreparedStatement getStatement(Connection con,String sql)
    {
        PreparedStatement ps = null;
        try
          {
            if (con != null)
              {
                ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

              }
          } catch (SQLException ex)
          {
    	        Log.logdb.warn("ERROR sql creating PreparedStatement:{} ", ex);
          }

        return ps;
    }   
    
    //************** CALLS TO THE DATABASE ***************************//
    public static PreparedStatement GetStations(Connection con)
    {
    	return getStatement(con,"SELECT * FROM contenedor");  	
    }    
   
    public static PreparedStatement GetStationSensors(Connection con)
    {
    	return getStatement(con,"SELECT * FROM sensor WHERE id_cont=?;");  	

    }

    public static PreparedStatement GetInfoFromStation(Connection con)
    {
    	return getStatement(con,"SELECT * FROM contenedor WHERE ID=?;");  	
    }
    
    public static PreparedStatement GetStationsFromCiudad(Connection con)
    {
    	return getStatement(con,"SELECT * FROM contenedor WHERE id_ciudad=?");  	
    } 
    
    public static PreparedStatement GetCities(Connection con)
    {
    	return getStatement(con,"SELECT * FROM ciudad;");  	
    }
    
    public static PreparedStatement GetLastValueStationSensor(Connection con)
    {
    	return getStatement(con,"select * from medida where id_contenedor=? AND id_sensor= ? ORDER BY fecha LIMIT 1;");  	
    }
    
    
    public static PreparedStatement InsertnewMeasurement(Connection con)
    {
    	return getStatement(con,"INSERT INTO medida (id_contenedor, id_sensor, fecha, valor) VALUES (?,?,?,?) ON duplicate key update id_contenedor=?, id_sensor=?, fecha=?, valor=?;");  	
    }
   
    
  
      
    public static PreparedStatement GetStationsFromCity(Connection con)
    {
    	return getStatement(con,"SELECT * FROM contenedor WHERE id_ciudad=?");  	
    } 
    
    /*
    public static PreparedStatement GetStationSensorMeasurementLastDays(Connection con)
    {
    	return getStatement(con,"SELECT date(DATE) as date, min(VALUE) as min, max(VALUE) as max, avg(VALUE) as avg, dayofweek(DATE) as dayofweek FROM MEASUREMENT WHERE STATION_ID=? AND SENSORTYPE_ID=? and date(DATE)>=date(now()) - INTERVAL ? DAY and DATE<=now() group by date(DATE) ORDER BY DATE ASC;");  	
    }
    
    public static PreparedStatement GetStationSensorMeasurementLastMonths(Connection con)
    {
    	return getStatement(con,"SELECT month(DATE) as month,min(VALUE) as min, max(VALUE) as max, avg(VALUE) as avg FROM MEASUREMENT WHERE STATION_ID=? AND SENSORTYPE_ID=? and date(DATE)>=date(now()) - INTERVAL ? DAY group by month(DATE) ORDER BY DATE ASC;");  	
    }
    
    
    public static PreparedStatement InsertWeatherForecast(Connection con)
    {
    	return getStatement(con,"INSERT INTO MEASUREMENT (STATION_ID, SENSORTYPE_ID, DATE, VALUE) VALUES (?,?,?,?) ON duplicate key update STATION_ID=?, SENSORTYPE_ID=?, DATE=?, VALUE=?;");  	
    }
    
    public static PreparedStatement GetStationSensorMeasurementMonth(Connection con)
    {
    	return getStatement(con,"SELECT month(DATE) as date,  min(VALUE) as min, max(VALUE) as max, avg(VALUE) as avg FROM MEASUREMENT WHERE STATION_ID=? AND SENSORTYPE_ID=? group by month(DATE) ORDER BY DATE ASC;");  	
    }
    */

    
   
    
    
    
    
    
    
    
    
}
