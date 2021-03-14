package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.excepciones.LogicaException;
import logica.valueObjects.VOPartida;
import persistencia.excepciones.PersistenciaException;

public class Unirse extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	response.sendRedirect("panel.jsp");
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	boolean exitoso=false;
    	boolean creador=false;
    	response.setContentType("text/html;charset=UTF-8");
    	HttpSession session = request.getSession(true);
    	 ConexionCliente conect= new ConexionCliente();
    	 String idPartida = request.getParameter("idPartida"); // acá llamo a un parametro del html/jsp
         String NombreJugador = (String) session.getAttribute("sessionNombre");
         
        String esCreador="true"; 
        creador= esCreador.equals(request.getParameter("CrearPartida"));
         int id= Integer.parseInt(idPartida);
    	 
    	
    	 if (creador==true)
         {
 				response.sendRedirect("JuegoContenedor.jsp");
 			
         }else
         {
     			response.sendRedirect("error.jsp");
     		
         }
    	
	    
      
    }

}
