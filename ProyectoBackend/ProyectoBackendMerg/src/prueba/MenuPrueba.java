package prueba;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import Utilitarios.SystemProperties;
import logica.IFachada;
import logica.excepciones.LogicaException;
import logica.valueObjects.VOJugador;
import persistencia.excepciones.PersistenciaException;

public class MenuPrueba {

	public IFachada modelo;
	static SystemProperties sp;
	
	public MenuPrueba() throws IOException, NotBoundException {
		System.out.println("Antes iFachada");
		sp = new SystemProperties();
		String ip = sp.getIpServidor();
		String puerto = sp.getPuertoServidor();
		String ruta = "//" + ip + ":" + puerto + "/"+ sp.getNombreAPublicar();
		System.out.println("Look");
		modelo  = (IFachada) Naming.lookup(ruta);
		System.out.println("Levanta iFachada");
	}
	
	
	public void MenuJugador() throws  InterruptedException 
    {
        Scanner sn2 = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario
        while (!salir) 
        {
            MenuJuga();   
                System.out.println("Escribe una de las opciones:");
                opcion = sn2.nextInt(); 
              //  ConexionCliente Conexion= new ConexionCliente();
               // IFachada sin= Conexion.NuevaConexionCliente();
                
                switch (opcion) 
                {
                    case 1:
                    	
                    	
                   	 
                   	try {
                   		
                   		
                   		System.out.println("Has seleccionado la opcion 1 Registrarse:");  
                      	 VOJugador x = new
                                   VOJugador(1,  "in_JugadorUserName" , "in_JugadorPassword", 
                              			 true, 1);
						modelo.registrarJugador(x);
						
						System.out.println("ingresado"); 
						
						
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (PersistenciaException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (LogicaException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                        
                                                        
                        break;
                    case 2:
                    	System.out.println("Has seleccionado la opcion 2 Loguearse para jugar:");
                        
					try {
						modelo.Login("in_JugadorUserName" , "in_JugadorPassword");
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (LogicaException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (PersistenciaException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                        break;
                    case 3:
                        System.out.println("Has seleccionado la opcion 3 Iniciar nueva partida:");                       
                       
                        /*try {
                        	
                        	//mostrar este vo
                        	ListarVOPartida(sin.VisualizarPartida(IngreseUsuarioContrasenia()));
                        	// esto devuelve un vo partida
                		} catch (JugadorException  e) {
                			System.out.println(e.getMensajeJugadoresExcep());
                		}
                		catch ( PartidaException e) {
                			System.out.println(e.getMensajePartidaExcep());
                		}
                		*/
                        break;
                    case 4:
                        System.out.println("Has seleccionado la opcion 4 Listar partidas Pausadas:");
                        /*
                        try {
                        	System.out.println("Complete los campos, Usuario, contrasenia y numero:");
                        	
                        	sin.RealizarNuevoIntento(IngreseUsuarioContrasenia(), IngreseNumeroPorTeclado());
                		} catch (JugadorException  e) {
                			System.out.println(e.getMensajeJugadoresExcep());
                		}
                		catch ( PartidaException e) {
                			System.out.println(e.getMensajePartidaExcep());
                		}
                        */
                        break;
                    
                    case 5:
                        System.out.println("Has seleccionado la opcion 5 Guardar una partida:");
                        /*
                        try {
                        	sin.abandonarPartida(IngreseUsuarioContrasenia());
                		} catch (JugadorException  e) {
                			System.out.println(e.getMensajeJugadoresExcep());
                		}
                		catch ( PartidaException e) {
                			System.out.println(e.getMensajePartidaExcep());
                		}
                        */
                        break;
                    case 6:
                        System.out.println("Has seleccionado la opcion 6 Listar todas las partidas de todos los jugadores:");                
                        /*try {
                        	
                        	ListarTodoElRanking(sin.ListarRankingGlobal());	
                		} catch (JugadorException e) {
                			System.out.println(e.getMensajeJugadoresExcep());
                		}
                   */
                        break;
                    case 7:
                    	System.out.println("Volver");
                        //7salir = true;
                  //      MenuAdministrador();
                        break;  
                    default:
                        System.out.println("Solo numero entre 1 y 7");
                }
        }sn2.close();
    }
	
	private void MenuJuga()
	{
			System.out.println("");
	    	
	    	System.out.println("1. Registrarse:");
	    	System.out.println("2. Loguearse para jugar:");
	        System.out.println("3. Iniciar nueva partida:");
	        System.out.println("4. Listar partidas Pausadas:");
	        System.out.println("5. Guardar una partida:");
	        System.out.println("6. Listar todas las partidas de todos los jugadores:");
	        System.out.println("7. Volver a menu principal");
	 }
	
	
}
