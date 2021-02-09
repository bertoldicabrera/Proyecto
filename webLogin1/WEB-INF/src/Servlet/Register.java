package Servlet;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

import Logica.IFachada;
import Logica.Validador;
import Logica.Vo.VOJugador;
 
public class Register extends HttpServlet {
 
	public IFachada fa;

	private static final long serialVersionUID = 1L;

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
        HttpSession session = request.getSession(true);
        boolean registroexitoso= false;
        session.setAttribute("error", null);
        //Declaro e inicio las variables
        String nombreUsuario = request.getParameter("name");
        String emailUsuario = request.getParameter("email");
        String password = request.getParameter("password1");
        String confirm_password = request.getParameter("password2");
        //Pattern Clase: define un patrón (para usar en una búsqueda)
        //https://www.w3schools.com/java/java_regex.asp
        Pattern p = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
        Matcher m = p.matcher(emailUsuario);
        Validador v = new Validador();
        
        //Comienzo con las validaciones
        
        
        //campos vacios
        if(nombreUsuario.isEmpty() || emailUsuario.isEmpty() || password.isEmpty() || confirm_password.isEmpty()){
        	session.setAttribute("error", "Hay campos vacios");
            
        } else {
            //veo que la direccion de email sea válida
            if(m.find()){
            	session.setAttribute("error", "La direccion de email no es correcta");
                
            } else {
                //email correcto, verifico la contraseña
                if(v.isUsernameOrPasswordValid(password)){
                    //verifico si la contraseña 1= 2 
                    if(password.equals(confirm_password)){
                        try {
                           
                                if(fa.userRegistrado(emailUsuario)){
                                	session.setAttribute("error", "Esta direccion de correo ya fue registrada");
                                } else {
                                    
                                	
                                	
                                	//tengo de encriptar la password antes de mandarla en este paso
                                	String encriptPassword=DigestUtils.sha512Hex(password);
                                	VOJugador vo= new VOJugador(nombreUsuario, 0, emailUsuario, encriptPassword);
                                	
                                    //Llegado a este punto significa que todo esta correcto, por lo tanto ingreso a la DB
                                	fa.nuevoJugador(vo);
                                    
                                    registroexitoso=true;
                                }
                            
                           
                             
                        } catch (Exception e) 
                        { session.setAttribute("error", e.toString()); }
                        
                        
                        
                    } else {
                    	session.setAttribute("error", "Las contraseñas no son iguales");
                        
                    }
                    
                } else {
                	session.setAttribute("error", "Contraseña no es válida");
                   
                }
                
                
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
        
        
    }
}
