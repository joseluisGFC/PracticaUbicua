package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import db.Medida;
import logic.Log;
import logic.Logic;

/**
 * SERVLET THAR SEARCH ALL THE CITIES STORED IN THE DATABASE
 */
@WebServlet("/GetMedidasFromContenedor")
public class GetMedidasFromContenedor extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public GetMedidasFromContenedor() 
    {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Log.log.info("-- Get Medidas From Contendor information from DB--");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try 
		{
			int id_contenedor = Integer.parseInt(request.getParameter("id_contenedor"));	
			Log.log.info("stationId= "+id_contenedor);
			ArrayList<Medida> valores =  Logic.getMedidasFromContenedorFromDB(id_contenedor);//llamas a logic DB para extraer el contenido de la base de datos
			String jsonStations = new Gson().toJson(valores);//Crea el json
			Log.log.info("JSON Values=> {}", jsonStations);
			out.println(jsonStations);
		} catch (NumberFormatException nfe) 
		{
			out.println("-1");
			Log.log.error("Number Format Exception: {}", nfe);
		} catch (IndexOutOfBoundsException iobe) 
		{
			out.println("-1");
			Log.log.error("Index out of bounds Exception: {}", iobe);
		} catch (Exception e) 
		{
			out.println("-1");
			Log.log.error("Exception: {}", e);
		} finally 
		{
			out.close();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
