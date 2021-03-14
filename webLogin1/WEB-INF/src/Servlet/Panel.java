package Servlet;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.IFachada;
import logica.excepciones.LogicaException;
import logica.valueObjects.VOJugador;
import persistencia.excepciones.PersistenciaException;

public class Panel extends HttpServlet {
	
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
    	
    	response.setContentType("text/html;charset=UTF-8");
    	HttpSession session = request.getSession(true);
    	 ConexionCliente conect= new ConexionCliente();
    	 fac=conect.getInstancia();
    	
    	String in_userName= "";
    	in_userName= (String) session.getAttribute("sessionNombre"); //del panel.jsp por request
    	 
    	session.setAttribute("sessionNombre", in_userName);
    	ArrayList<VOJugador> ArreJugador = (ArrayList<VOJugador>) session.getAttribute("Jugador"); // el jugador del panel por request
    	session.setAttribute("Jugadorx", ArreJugador);
    	
	    response.sendRedirect("JuegoContenedor.jsp");
      
    }
    
	 
	 
	
	 
	
}
