package Servlet;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import logica.IFachada;
import persistencia.excepciones.PersistenciaException;

public class ConexionCliente  extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private static Fachada instancia;
	private static IFachada instancia;

	public ConexionCliente()  {
		
	//	if(instancia==null){
		/// Parametros van locales a un serverlet en el web.xml
		String ipServidor ="localhost";//super.getInitParameter("ipServidor");
		String puerto ="8095"; //super.getInitParameter("puerto");
		String nombreAPublicar = "PROYECTO";//super.getInitParameter("nombreAPublicar");
		String ruta = "//" + ipServidor + ":" + puerto + "/" + nombreAPublicar;
		try {
			instancia = (IFachada) Naming.lookup(ruta);
		} catch (MalformedURLException e) {
			System.out.println(e.toString());
		} catch (RemoteException e) {
			System.out.println(e.toString());
		} catch (NotBoundException e) {
			System.out.println(e.toString());
		}
	//	}
		
	}
	
	public static IFachada getInstancia() {
		return instancia;
	}

	
	    			
	    


    	
    	
   
	
	
	
	
}
