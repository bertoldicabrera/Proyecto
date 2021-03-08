package Servlet;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;


import logica.IFachada;
import logica.excepciones.LogicaException;
import logica.valueObjects.VOJugador;
import persistencia.excepciones.PersistenciaException;

 
public class Login extends HttpServlet {
 
	
	public IFachada fac;
	
	
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	response.sendRedirect("login.jsp");
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	conectar ();
    	
    		
        HttpSession session = request.getSession(true);
        String UserName = request.getParameter("UserName");
        String passwordPlana = request.getParameter("password");
      
        Pattern p = Pattern.compile("^([0-9a-zA-Z])$"); // solo acepto letras y numeros
        Matcher m = p.matcher(UserName);
        Validador v = new Validador();
        session.setAttribute("error", null);
        boolean error=true;
        //campos vacios
        if (UserName.isEmpty() || passwordPlana.isEmpty()) {
        	
        	//error = true;
			//msgError = "Los campos no pueden estar vacio";
        	session.setAttribute("error", "Hay campos vacios");
 
        } else {
            //No hay campos vacios, veo que el nombre ingresado es valido
        	//The find method scans the input sequence looking forthe next subsequence that matches the pattern. 
            if (m.find()) {
            	error = true;
    			//msgError = "El Username no es valido";
            	session.setAttribute("error", "El Username no es valido, solo se permiten letras y numeros");
 
            } else {
            	
            	//tengo de encriptar la password antes de mandarla en este paso
            	//String password=DigestUtils.sha512Hex(passwordPlana);
                //La direccion de email si es correcta, verifico que la contraseña tambien lo sea
                if (v.isUsernameOrPasswordValid(passwordPlana)) {
                           
                           try {
							VOJugador jugador= new VOJugador();
									
							jugador=fac.Login(UserName, passwordPlana);
						 if( jugador==null ) {
							 error=true;
						 }
						 else
						 {
							 session.setAttribute("sessionNombre", jugador.getJugadorUserName());
	                          //  CargarArreglo(session, fac);
	                            error=false;
						 }
							 
                           
						} catch (LogicaException e) {
							System.out.println( "Error"+e.toString());
						} catch (PersistenciaException e) {
							System.out.println( "Error"+e.toString());
						} catch (InterruptedException e) {
							System.out.println( "Error"+e.toString());
						}
                         
 
                } else {
                	
                	session.setAttribute("error", "Usuario o password incorrecto");
 
                }
 
 
            }
        }
        if(error==false)
        {
        	// si no hay error puedo redirecionar
        	response.sendRedirect("panel.jsp");
        }else
        {
        	response.sendRedirect("login.jsp");
        }
        
 
      
 
    } // fin dopost
    
    
    private void conectar ()
    {
    	
    	/// Parametros van locales a un serverlet en el web.xml
    			String ipServidor = super.getInitParameter("ipServidor");
    			String puerto = super.getInitParameter("puerto");
    			String nombreAPublicar = super.getInitParameter("nombreAPublicar");
    			String ruta = "//" + ipServidor + ":" + puerto + "/" + nombreAPublicar;
    			try {
    				fac = (IFachada) Naming.lookup(ruta);
    			} catch (MalformedURLException e) {
    				System.out.println( "Error"+e.toString());
    				
    			} catch (RemoteException e) {
    				System.out.println( "Error"+e.toString());
    			} catch (NotBoundException e) {
    				System.out.println( "Error"+e.toString());
    			}
    			
    }
    
    
    
    
    
    
    
    
    private void CargarArreglo(HttpSession sessi,IFachada fachada) throws SQLException, RemoteException, LogicaException
    {
    	
    	
		
    	ArrayList<VOJugador> arreVOR1 = (ArrayList<VOJugador>) sessi.getAttribute("arre");
		if (arreVOR1 == null)
		{
			arreVOR1 = new ArrayList<VOJugador>();
		//	for (int i=0;i<fachada.listarJugadores().size();i++)
		//		arreVOR1.add(fachada.listarJugadores().get(i));
		}else
		{
			sessi.setAttribute("arre", null);
			arreVOR1 = new ArrayList<VOJugador>();
		////	for (int i=0;i<fachada.listarJugadores().size();i++)
			//	arreVOR1.add(fachada.listarJugadores().get(i));
		}
			
	
		sessi.setAttribute("arre", arreVOR1);
    }
    
}