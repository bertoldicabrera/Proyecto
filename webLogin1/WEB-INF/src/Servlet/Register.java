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

import logica.IFachada;
import logica.excepciones.LogicaException;
import logica.valueObjects.VOJugador;
import persistencia.excepciones.PersistenciaException;
 
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException  {
    	
    	
    	
    	HttpSession session = request.getSession(true);
    	ConexionCliente conect= new ConexionCliente();
    	fac=conect.getInstancia();
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
                    
                        	//int in_JugadorID,  String in_JugadorUserName, String in_JugadorPassword, 
               			// boolean in_JugadorIsOnline, int in_PuntajeAcumulado
                        	VOJugador in_voJug= new VOJugador(0,nombreUsuario, password, false, 0);
                        	
                            try {
                            	System.out.println("Antes de llamar a la fachada");
								fac.registrarJugador(in_voJug);
								 registroexitoso=true;
							} catch (PersistenciaException e) {
								System.out.println("catch (PersistenciaException e)"+e.toString());
								session.setAttribute("error", e.toString());
								e.printStackTrace();
							} catch (LogicaException e) {
								System.out.println("catch (LogicaException e)"+e.toString());
								session.setAttribute("error", e.toString());
							} catch (InterruptedException e) {
								System.out.println("catch (InterruptedException e)"+e.toString());
								session.setAttribute("error", e.toString());
							} catch (RemoteException e) {
								System.out.println("catch (RemoteException e)"+e.toString());
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
        	
        		session.setAttribute("error","Registro exitoso");
				response.sendRedirect("login.jsp");
			
        }else
        {
    			response.sendRedirect("register.jsp");
    		
        }
        
    }// fin del post
    
    
}
