package Servlet;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.IFachada;
import logica.excepciones.LogicaException;
import persistencia.excepciones.PersistenciaException;
 
public class Logout extends HttpServlet {
 
	private static final long serialVersionUID = 1L;

	public IFachada fac;
	//Ya sea que el método sea por GET o POST, cerraremos la sesion.
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException 
            {
    	
    	
    	response.setContentType("text/html;charset=UTF-8");
    	HttpSession session = request.getSession(true);
    	 ConexionCliente conect= new ConexionCliente();
    	 fac=conect.getInstancia();
    	
    	String in_userName= "";
    	in_userName= (String) session.getAttribute("sessionNombre");
    	System.out.println("El nombre del jugador es:"+in_userName);
    	try {
    		if(fac!=null)
    		{
    			fac.logout(in_userName);
    		}
    		else
    		{
    			System.out.println("No fue a a la fachada fac== null");
    		}
		} catch (LogicaException | InterruptedException | PersistenciaException | RemoteException e) {
			
			System.out.println(e.toString());
			
		}
    	session.setAttribute("sessionNombre", null);
    	session.invalidate();
	    response.sendRedirect("index.jsp");
   
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    
    			
    
    
    
    
    
}