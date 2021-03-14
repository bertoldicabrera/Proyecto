package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.IFachada;
import logica.excepciones.LogicaException;
import logica.valueObjects.VOPartida;
import persistencia.excepciones.PersistenciaException;

public class Abandonar  extends HttpServlet {


	private static final long serialVersionUID = 1L;
	public IFachada fac;
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	response.sendRedirect("login.jsp");
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	boolean exitoso=false;
    	response.setContentType("text/html;charset=UTF-8");
    	HttpSession session = request.getSession(true);
    	 ConexionCliente conect= new ConexionCliente();
    	 fac=conect.getInstancia();
    	 ArrayList<VOPartida> ArreVOPartida = (ArrayList<VOPartida>) session.getAttribute("Partida");
    	 
    	 try {
			fac.guardarPartida(ArreVOPartida.get(0));
			exitoso=true;
		} catch (LogicaException e) {
			session.setAttribute("error", e.toString());
		} catch (InterruptedException e) {
			session.setAttribute("error", e.toString());
		} catch (PersistenciaException e) {
			session.setAttribute("error", e.toString());
		}
    	
    	
    	
    	 if (exitoso==true)
         {
 				response.sendRedirect("panel.jsp");
 			
         }else
         {
     			response.sendRedirect("error.jsp");
     		
         }
    	
	    
      
    }

}
