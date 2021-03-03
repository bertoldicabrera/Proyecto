package prueba;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Scanner;

import Utilitarios.SystemProperties;
import logica.IFachada;
import logica.excepciones.LogicaException;
import logica.valueObjects.VOArtillero;
import logica.valueObjects.VOAvion;
import logica.valueObjects.VOBase;
import logica.valueObjects.VOCollectionArtilleria;
import logica.valueObjects.VOCollectionAviones;
import logica.valueObjects.VOCollectionBase;
import logica.valueObjects.VOCollectionEquipo;
import logica.valueObjects.VOCollectionJugador;
import logica.valueObjects.VODeposito;
import logica.valueObjects.VOEquipo;
import logica.valueObjects.VOJugador;
import logica.valueObjects.VOPartida;
import logica.valueObjects.VOTanqueCombustible;
import logica.valueObjects.VOTorreControl;
import persistencia.excepciones.PersistenciaException;

public class MenuPrueba {

	public IFachada modelo;
	static SystemProperties sp;
	
	public MenuPrueba() throws IOException, NotBoundException,FileNotFoundException, InterruptedException {
		System.out.println("Antes iFachada");
		sp = new SystemProperties();
		String ip = sp.getIpServidor();
		String puerto = sp.getPuertoServidor();
		String nombreAPublicar = sp.getNombreAPublicar();
		int port = Integer.parseInt(puerto);
		String ruta = "//" + ip + ":" + port + "/"+nombreAPublicar ;
		
		

		
		System.out.println("Look");
		modelo  = (IFachada) Naming.lookup(ruta);
		if(modelo !=null) {
			 System.out.println("Levanta iFachada");
		}else
			System.out.println("No levanto iFachada");
		
		String x=modelo.verConexion("Menu");
		System.out.println(x);
		
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
                      	 VOJugador x = new VOJugador(1,  "in_JugadorUserName" , "in_JugadorPassword", true, 1);
						modelo.registrarJugador(x);
						
						System.out.println("ingresado"); 
						
						
					} catch (RemoteException e) {
						System.out.println(e.toString());
						 
					} catch (PersistenciaException e) {
						System.out.println(e.toString());
					} catch (LogicaException e) {
						System.out.println(e.toString());
					}
                        
                                                        
                        break;
                    case 2:
                    	System.out.println("Has seleccionado la opcion 2 Loguearse para jugar:");
                        
					try {
						
						VOJugador y= modelo.Login("in_JugadorUserName" , "in_JugadorPassword");
						System.out.println("Menu prueba linea 92, el usuario logreado es el id:    "+y.getJugadorId());
						
					} catch (RemoteException e) {
						System.out.println(e.toString());
					} catch (LogicaException e) {
						System.out.println(e.toString());
					} catch (PersistenciaException e) {
						System.out.println(e.toString());
					}
                        break;
                    case 3:
                        System.out.println("Has seleccionado la opcion 3 Iniciar nueva partida:");                       
                       
                        
                        
                        
                        
                        break;
                    case 4:
                        System.out.println("Has seleccionado la opcion 4 Listar partidas Pausadas:");
                       
					try {
						
						
						modelo.listarPartidasAReanudar("in_JugadorUserName");
						
						
						
					} catch (RemoteException e) {
						System.out.println(e.toString());
					} catch (PersistenciaException e) {
						System.out.println(e.toString());
					} catch (LogicaException e) {
						System.out.println(e.toString());
					}
                        break;
                    
                    case 5:
                        System.out.println("Has seleccionado la opcion 5 Guardar una partida:");
                        VOCollectionEquipo equipos= new VOCollectionEquipo();
                        for(int k=0;k<2;k++)
                        {
                       
                        VOCollectionAviones aviones=new VOCollectionAviones();
                        VOCollectionArtilleria artillerias= new VOCollectionArtilleria();
                        VOCollectionBase   DaoB = new VOCollectionBase() ;
                        VOJugador[] coleccionJug=null;
                         for (int i=0;i<4;i++)
                         {  //(int in_PK_avion_id, int in_avionCoordX, int in_avionCoordY,int in_avionCoordZ ,boolean in_estado, int in_vida,boolean in_hayEnemigo,
                        	// int in_rangoDeVision ,boolean in_avionBomba,int  in_cantidadBombas, int in_avionCombustible,boolean in_enCampoEnemigo,int in_baseid)
                        	 VOAvion avion=new VOAvion(i,i,i,i,true,i,true,i,true,i,i,true,k);
                        	  aviones.insback(avion);
                         }
                         for (int j=0;j<12;j++)
                         {  
                        	// int in_id, int in_coordX, int in_coordY, 
                 			//boolean in_estado, int in_vida, boolean in_hayEnemigo,
                 			//int in_rangoDeVision, int in_ArtilleroAngulo,int in_base_id
                        	 
                        	 VOArtillero artillero=new VOArtillero(j,j,j,true,j,true,j,j,k);
                        	 artillerias.insBack(artillero);
                         }
                         //int in_id, int in_coordX, int in_coordY, boolean in_estado, int in_vida,int in_cantidadBombas,boolean in_enUso
                         VODeposito deposito= new VODeposito(1,1,1,true,1,1,true);
                         //int in_id, int in_coordX, int in_coordY, boolean in_estado, int in_vida,boolean in_hayEnemigo, int in_rangoDeVision
                         VOTorreControl torrecontro=new VOTorreControl(1,1,1,true,1,true,1);
                         //int in_id, int in_coordX, int in_coordY, boolean in_estado, int in_vida, int in_cantidadCombustible, boolean in_enUso
                         VOTanqueCombustible tanquecombustible= new VOTanqueCombustible(1,1,1,true,1,1,true);
                         
                        //int in_idDabse,VODaoAviones in_aviones,VODaoArtilleria in_artilleros, VODeposito in_deposito, VOTanqueCombustible in_tanque,VOTorreControl in_torre
                         
                         VOBase base=new VOBase(k,aviones,artillerias,deposito,tanquecombustible,torrecontro);
                         DaoB.insert(base.getIdBase(),base);
                        // int in_equipoID, VOJugador[]  in_Jugadores, VOBase  in_base, String  in_bando
                         
                       //  int in_JugadorID,  String in_JugadorUserName, String in_JugadorPassword, 
            			// boolean in_JugadorIsOnline, int in_PuntajeAcumulado
                         coleccionJug[k]= new VOJugador(k,"jug"+k,"Jugpass"+k,true,k);
                         //int in_equipoID, VOJugador[]  in_Jugadores, VOBase  in_base, String  in_bando
                    	 VOEquipo eq= new VOEquipo(k,coleccionJug,base,"bando"+k);
                           equipos.insBack(eq); ;
                        }
                     	 // int in_PartidaId, String in_PartidaEstado, Date in_PartidaFechaUltimaActualizacion,
              			//boolean in_PartidaGuardada, String  in_PartidaNombre, int in_PartidaCantidadJugadores,
              			//int in_PartidaCreador, Date in_PartidaFechaCreada,boolean in_partidaTermino, VODaoEquipo in_Equi
                        Date in_PartidaFechaCreada=new Date(2021,03,02);
                        VOPartida vopartidaprueba=new VOPartida(1,"abierta:",in_PartidaFechaCreada,true,"mierda",2,0,in_PartidaFechaCreada,true,equipos);
					try {
						modelo.guardarPartida(vopartidaprueba);
					} catch (RemoteException e) {
						System.out.println(e.toString());
					} catch (LogicaException e) {
						System.out.println(e.toString());
					} catch (PersistenciaException e) {
						System.out.println(e.toString());
					}
                        
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
