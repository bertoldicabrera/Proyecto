package Servlet;

import java.io.IOException;
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

import Logica.Validador;
import Logica.Excepciones.LogicaException;
import Logica.Excepciones.PersistenciaException;
import Logica.Vo.VOJugador;
import Logica.IFachada;
 
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
    	
    	
    	
    		
        HttpSession session = request.getSession(true);
        String email = request.getParameter("email");
        String passwordPlana = request.getParameter("password");
      
        Pattern p = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
        Matcher m = p.matcher(email);
        Validador v = new Validador();
        session.setAttribute("error", null);
        boolean error=true;
        //campos vacios
        if (email.isEmpty() || passwordPlana.isEmpty()) {
        	
        	//error = true;
			//msgError = "Los campos no pueden estar vacio";
        	session.setAttribute("error", "Hay campos vacios");
 
        } else {
            //No hay campos vacios, veo que la direccion de email sea válida
            if (m.find()) {
            	//error = true;
    			//msgError = "La direccion de email no es correcta";
            	session.setAttribute("error", "La direccion de email no es correcta");
 
            } else {
            	
            	//tengo de encriptar la password antes de mandarla en este paso
            	//String password=DigestUtils.sha512Hex(passwordPlana);
            	
            	
                //La direccion de email si es correcta, verifico que la contraseña tambien lo sea
                if (v.isUsernameOrPasswordValid(passwordPlana)) {
                        try {
                           
                            if (fac.validarCuenta(email, passwordPlana)) {
                                //Significa que la cuenta si existe
                                //OBTENGO EL NOMBRE DEL USUARIO Y LO GUARDO EN UNA SESION
                            	String NombreUsuario=fac.darNombre(email);
                                session.setAttribute("sessionNombre", NombreUsuario);
                                session.setAttribute("sessionEmail", email);
                          
                                CargarArreglo(session, fac);

                                error=false;
                                
                                
                            } else {
                            	
                            	session.setAttribute("error", "Usuario o password incorrecto");
                            }
 
                           
 
                        } catch (Exception e) {
                        	
                        	session.setAttribute("error", e.toString());
                        }
 
                } else {
                	
                	session.setAttribute("error", "Contraseña no es válida");
 
                }
 
 
            }
        }
        if(error==false)
        {
        	
        	response.sendRedirect("panel.jsp");
        }else
        {
        	response.sendRedirect("login.jsp");
        }
        
 
      
 
    }
    
    private void CargarArreglo(HttpSession sessi,IFachada fachada) throws SQLException, RemoteException, PersistenciaException, LogicaException
    {
    	
    	
		
    	ArrayList<VOJugador> arreVOR1 = (ArrayList<VOJugador>) sessi.getAttribute("arre");
		if (arreVOR1 == null)
		{
			arreVOR1 = new ArrayList<VOJugador>();
			for (int i=0;i<fachada.listarJugadores().size();i++)
				arreVOR1.add(fachada.listarJugadores().get(i));
		}else
		{
			sessi.setAttribute("arre", null);
			arreVOR1 = new ArrayList<VOJugador>();
			for (int i=0;i<fachada.listarJugadores().size();i++)
				arreVOR1.add(fachada.listarJugadores().get(i));
		}
			
	
		sessi.setAttribute("arre", arreVOR1);
    }
    
}