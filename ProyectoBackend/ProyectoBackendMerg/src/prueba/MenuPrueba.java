package prueba;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
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
                      	 VOJugador x = new VOJugador(1,  "in_JugadorUserName1" , "in_JugadorPassword1", true, 1);
						modelo.registrarJugador(x);
						
						System.out.println("ingresado jugador 1"); 
						
						 
                     	 VOJugador y = new VOJugador(2,  "in_JugadorUserName2" , "in_JugadorPassword2", true, 1);
						modelo.registrarJugador(y);
						
						System.out.println("ingresado jugador 2"); 
						
						
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
						
						VOJugador x= modelo.Login("in_JugadorUserName1" , "in_JugadorPassword1");
						System.out.println("Menu prueba linea 92, el usuario logreado es el id:    "+x.getJugadorId());
						VOJugador y= modelo.Login("in_JugadorUserName2" , "in_JugadorPassword2");
						System.out.println("Menu prueba linea 112, el usuario logreado es el id:    "+y.getJugadorId());
						
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
                        
                        VOPartida vopartidaprueba; // una partida
                        VOCollectionEquipo equipos= new VOCollectionEquipo(); // una partida tiene 2 equipos
                        VOCollectionJugador coleccionJug = new VOCollectionJugador(); // para el equipo
                        System.out.println("Puse de 1 hasta el 2 para que coincidan los id de jugador que arranca en 1");
                        
                        for(int k=1;k<3;k++) // acá hago los dos equipos
                        {
                        	VOEquipo eq; // 
                        	VOCollectionBase   vobases = new VOCollectionBase() ; // un equipo tiene una bases
                        	
          //para crear un equipo
                        	VOCollectionAviones aviones=new VOCollectionAviones();//ok
                            VOCollectionArtilleria artillerias= new VOCollectionArtilleria(); //ok
                            VOJugador[] arregloJugadores=new VOJugador[1];//ok na equipo tiene 1 jugador
                           // VOAvion[] arreavion = new VOAvion [4]; //ok
                            
                            
                            
        // creo aviones de prueba
                         for (int i=0;i<4;i++)
                         {  //(int in_PK_avion_id, int in_avionCoordX, int in_avionCoordY,int in_avionCoordZ ,boolean in_estado, int in_vida,boolean in_hayEnemigo,
                        	// int in_rangoDeVision ,boolean in_avionBomba,int  in_cantidadBombas, int in_avionCombustible,boolean in_enCampoEnemigo,int in_baseid)
                        	 VOAvion avion=new VOAvion(i,i,i,i,true,i,true,i,true,i,i,true,k);
                        	// arreavion[i]=avion;
                        	 aviones.insback(avion);
                         }
      // creo artilleros de prueba
                         for (int j=0;j<12;j++)
                         {  
                        	// int in_id, int in_coordX, int in_coordY, 
                 			//boolean in_estado, int in_vida, boolean in_hayEnemigo,
                 			//int in_rangoDeVision, int in_ArtilleroAngulo,int in_base_id
                        	 
                        	 VOArtillero artillero=new VOArtillero(j,j,j,true,j,true,j,j,k);
                        	 artillerias.insBack(artillero);
                         }
    //creo las cosas pequeãs
                         
                         //int in_id, int in_coordX, int in_coordY, boolean in_estado, int in_vida,int in_cantidadBombas,boolean in_enUso
                         VODeposito deposito= new VODeposito(1,1,1,true,1,1,true);
                         //int in_id, int in_coordX, int in_coordY, boolean in_estado, int in_vida,boolean in_hayEnemigo, int in_rangoDeVision
                         VOTorreControl torrecontro=new VOTorreControl(1,1,1,true,1,true,1);
                         //int in_id, int in_coordX, int in_coordY, boolean in_estado, int in_vida, int in_cantidadCombustible, boolean in_enUso
                         VOTanqueCombustible tanquecombustible= new VOTanqueCombustible(1,1,1,true,1,1,true);
                         
                        //int in_idDabse,VODaoAviones in_aviones,VODaoArtilleria in_artilleros, VODeposito in_deposito, VOTanqueCombustible in_tanque,VOTorreControl in_torre
     //creo la  base                 
                         VOBase base=new VOBase(k,aviones,artillerias,deposito,tanquecombustible,torrecontro);
                         vobases.insert(base.getIdBase(),base);
                        // int in_equipoID, VOJugador[]  in_Jugadores, VOBase  in_base, String  in_bando
                         
                       //  int in_JugadorID,  String in_JugadorUserName, String in_JugadorPassword, 
            			// boolean in_JugadorIsOnline, int in_PuntajeAcumulado
                         
     //creo el jugador                    
                        
                         VOJugador nuevo= new VOJugador(k,"in_JugadorUserName"+k,"in_JugadorPassword"+k,true,1);
                         System.out.println("creo jugador  en menu linea 209****"+ nuevo.getJugadorId());
                         arregloJugadores[0]=nuevo;
                         
                     	coleccionJug.insert(nuevo.getJugadorId(), nuevo);
                         
                      //   coleccionJug.insert(nuevo.getJugadorId(), nuevo);
                       //  System.out.println("creo jugador  en menu linea 213****"+nuevo.getJugadorUserName();
                         //int in_equipoID, VOJugador[]  in_Jugadores, VOBase  in_base, String  in_bando
                         
    //creo el equipo            
                         
                         System.out.println("Antes de VOEQUIPO 223 Y LA BASE TIENE ID "+base.getIdBase() );
                         
                         // acá entra un arreglo de jugadores
                    	eq= new VOEquipo(k,arregloJugadores,base,"bando"+k);
                    	
                    	System.out.println("despues de equipo "+eq.getEquipoID() );
                    	
    // lo pongo dentro de su "coleccion de equipos"
                    	//( VOEquipo in_Equipo,VOCollectionJugador in_DaoJ, VOCollectionBase   in_DaoB  )
                    	//acá tiene de entrar una vocollecionjug
                    	
                    	//atencion 1 para el id de partida
                         equipos.insBack(1,eq,coleccionJug ,vobases ); 
                         
                        
                        }// termina el for de los dos equipos
                        
                        System.out.println("Linea 234 veo si los jugadores del equipo : "+equipos.getDaoJ().find(1).getJugadorUserName() );
                        System.out.println("Linea 235 veo si los jugadores del equipo : "+equipos.getDaoJ().find(2).getJugadorUserName() );
                        
                     	 // int in_PartidaId, String in_PartidaEstado, Date in_PartidaFechaUltimaActualizacion,
              			//boolean in_PartidaGuardada, String  in_PartidaNombre, int in_PartidaCantidadJugadores,
              			//int in_PartidaCreador, Date in_PartidaFechaCreada,boolean in_partidaTermino, VODaoEquipo in_Equi
                        LocalDate in_PartidaFechaCreada= LocalDate.of(2021,03,02) ;
                      //  Date in_PartidaFechaCreada=new Date(2021,03,02);
                        System.out.println("************"+in_PartidaFechaCreada.toString());
                        
                         vopartidaprueba=new VOPartida(1,"abierta:",in_PartidaFechaCreada,true,"mierda",2,0,in_PartidaFechaCreada,true,equipos);
                        
                         System.out.println("partida creada con los dos equipos y tiene el id de partida: "+vopartidaprueba.getPartidaId());
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
