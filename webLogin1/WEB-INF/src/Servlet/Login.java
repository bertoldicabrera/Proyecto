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
import logica.IFachada;
import logica.excepciones.LogicaException;
import logica.valueObjects.VOJugador;
import logica.valueObjects.VOPartida;
import persistencia.excepciones.PersistenciaException;

 
public class Login extends HttpServlet {
 
	
	private IFachada fac;
	
	
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	response.sendRedirect("login.jsp");
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	
    	
    	//conectar();	estava antes voy a probar si anda bien pasar una session al conectar.
    	
        HttpSession session = request.getSession(true);
        ConexionCliente conect= new ConexionCliente();
        fac=conect.getInstancia();
        
        String UserName = request.getParameter("UserName"); // acá llamo a un parametro del html/jsp
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
							CargarJugador(session,jugador );
							CargarArreglo(session, fac, jugador.getJugadorUserName());
							
	                            error=false;
						 }
                           
						} catch (LogicaException e) {
							session.setAttribute( "error",e.toString());
						} catch (PersistenciaException e) {
							session.setAttribute( "error",e.toString());
						} catch (InterruptedException e) {
							session.setAttribute( "error",e.toString());
						} catch (RemoteException e) {
							session.setAttribute( "error",e.toString());
						}
		                 catch (SQLException e) {
		                	 session.setAttribute( "error",e.toString());
						}
                } else {
                	
                	session.setAttribute("error", "Usuario o password incorrecto");
                }
            }
        }
        if(error==false)
        {
        	// si no hay error puedo redirecionar
        	//response.sendRedirect("JuegoContenedor.jsp"); 
        	response.sendRedirect("panel.jsp");
        	
        }else
        {
        	response.sendRedirect("login.jsp");
        }
        
 
      
 
    } // fin dopost
    
   
    
    
    
    
    
    
    
   
    
    
    
    
    
    private void  CargarJugador(HttpSession session,VOJugador jugador) {
    	ArrayList<VOJugador> ArreJugador = (ArrayList<VOJugador>) session.getAttribute("Jugador");
	    	if (ArreJugador == null)
			{
	    		ArreJugador = new ArrayList<VOJugador>();
	    		ArreJugador.add(jugador);
				}else
				{
				session.setAttribute("Jugador", null);
				ArreJugador = new ArrayList<VOJugador>();
	    		ArreJugador.add(jugador);
				}
	    	
		session.setAttribute("Jugador", ArreJugador);
		
    }
    
    
    
    
    
    private void CargarArreglo(HttpSession session,IFachada fachada, String in_Nickname) throws SQLException, RemoteException, LogicaException
    {
    	
    	
		
    	ArrayList<VOPartida> listadePartidas = (ArrayList<VOPartida>) session.getAttribute("listadePartidas");
    	
    	try {
	    	if (listadePartidas == null)
			{
	    		listadePartidas = new ArrayList<VOPartida>();
				listadePartidas=	fachada.listarPartidasAReanudar(in_Nickname);
				
			//		arreVOR1.add(fachada.listarJugadores().get(i));
			}else
			{
				session.setAttribute("listadePartidas", null);
				listadePartidas = new ArrayList<VOPartida>();
				listadePartidas= fachada.listarPartidasAReanudar(in_Nickname);
				
			}
		} catch (PersistenciaException e) {
			session.setAttribute("error", "Error: contacte al administrador");
		} catch (InterruptedException e) {
			session.setAttribute("error", "Error: contacte al administrador");
		}
	
		session.setAttribute("listadePartidas", listadePartidas);
    }
    
}