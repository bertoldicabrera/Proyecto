package Servlet;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

import logica.IFachada;
import logica.valueObjects.VOJugador;


 
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public IFachada fac;
	
	

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* En este proyecto; este servlet no recibe ni debe recibir nada por GET, 
         * asi que si se lleva a entrar al servelt
         * usando el metodo GET solamente redireccion al index.jsp
         */
        response.sendRedirect("index.jsp");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    	
    	
			conectar ();
    	
        HttpSession session = request.getSession(true);
        boolean registroexitoso= false;
        session.setAttribute("error", null);
        //Declaro e inicio las variables
        String nombreUsuario = request.getParameter("name");
        String password = request.getParameter("password1");
        String confirm_password = request.getParameter("password2");
        //Pattern Clase: define un patrón (para usar en una búsqueda)
        //https://www.w3schools.com/java/java_regex.asp
        Pattern p = Pattern.compile("^([0-9a-zA-Z])$"); // solo acepto letras y numeros
        Matcher m = p.matcher(nombreUsuario);
        Validador v = new Validador();
        
        //Comienzo con las validaciones
        
        
        //campos vacios
        if(nombreUsuario.isEmpty() || password.isEmpty() || confirm_password.isEmpty()){
        	session.setAttribute("error", "Hay campos vacios");
            
        } else {
            //veo que la direccion de email sea válida
        //	The find method scans the input sequence looking forthe next subsequence that matches the pattern. 
            if(m.find()){ 
            	session.setAttribute("error", "El Username no es valido, solo se permiten letras y numeros");
                
            } else {
                //email correcto, verifico la contraseña
                if(v.isUsernameOrPasswordValid(password)){
                    //verifico si la contraseña 1= 2 
                    if(password.equals(confirm_password)){
                        try {
                           
                        	//int in_JugadorID,  String in_JugadorUserName, String in_JugadorPassword, 
               			// boolean in_JugadorIsOnline, int in_PuntajeAcumulado
                        	VOJugador in_voJug= new VOJugador(0,nombreUsuario, password, true, 0);
                            fac.registrarJugador(in_voJug); 
                            registroexitoso=true;
	                        } catch (Exception e) 
	                        	{ 
	                        	session.setAttribute("error", e.toString()); 
	                        	}
                        
                        
                    } else {
                    	session.setAttribute("error", "Las contraseñas no son iguales");}
                    
                } else {
                	session.setAttribute("error", "Contraseña no es válida");}
                
            }
        }
        if (registroexitoso==true)
        {
        	try {
        		session.setAttribute("error","Registro exitoso");
				response.sendRedirect("login.jsp");
			} catch (IOException e) {
				session.setAttribute("error", e.toString());
			}
        }else
        {
        	try {
    			response.sendRedirect("register.jsp");
    		} catch (IOException e) {
    			session.setAttribute("error", e.toString());
    		}
        }
        
        
    }// fin do post
    
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
}
