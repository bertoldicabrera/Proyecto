package Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

import Logica.Validador;
import Logica.Vo.VOJugador;
import Persistencia.Dao.DaoJugador;
 
public class Login extends HttpServlet {
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        /* En este proyecto; este servlet no recibe ni debe recibir nada por GET, 
         * asi que si se lleva a entrar al servelt
         * usando el metodo GET solamente redireccion al index.jsp
         */
    
    	
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
        DaoJugador d = new DaoJugador();
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
            	String password=DigestUtils.sha512Hex(passwordPlana);
            	
            	
                //La direccion de email si es correcta, verifico que la contraseña tambien lo sea
                if (v.isUsernameOrPasswordValid(password)) {
                        try {
                            d.Connect();
                            if (d.isAcountExists(email, password)) {
                                //Significa que la cuenta si existe
                                //OBTENGO EL NOMBRE DEL USUARIO Y LO GUARDO EN UNA SESION
                            	
                                String NombreUsuario = d.getNameByEmail(email);
                                session.setAttribute("sessionNombre", NombreUsuario);
                                session.setAttribute("sessionEmail", email);
                                ///test
                                //***************
                                CargarArreglo(session, d);

                                error=false;
                                
                                
                            } else {
                            	
                            	session.setAttribute("error", "Usuario o password incorrecto");
                            }
 
                            d.disconnect();
 
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
    
    private void CargarArreglo(HttpSession sessi, DaoJugador userDao) throws SQLException
    {
    	
    	
		
    	ArrayList<VOJugador> arreVOR1 = (ArrayList<VOJugador>) sessi.getAttribute("arre");
		if (arreVOR1 == null)
		{
			arreVOR1 = new ArrayList<VOJugador>();
			for (int i=0;i<userDao.allUsers().size();i++)
				arreVOR1.add(userDao.allUsers().get(i));
		}else
		{
			sessi.setAttribute("arre", null);
			arreVOR1 = new ArrayList<VOJugador>();
			for (int i=0;i<userDao.allUsers().size();i++)
				arreVOR1.add(userDao.allUsers().get(i));
		}
			
	
		sessi.setAttribute("arre", arreVOR1);
    }
    
}