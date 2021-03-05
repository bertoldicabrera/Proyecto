package logica;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import Utilitarios.MensajesPersonalizados;
import Utilitarios.SystemProperties;
import logica.excepciones.LogicaException;
import logica.interfaces.IPoolConexiones;
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
import persistencia.baseDeDatos.daos.DaoArtilleria;
import persistencia.baseDeDatos.daos.DaoBase;
import persistencia.baseDeDatos.daos.DaoDeAviones;
import persistencia.baseDeDatos.daos.DaoEquipo;
import persistencia.baseDeDatos.daos.DaoJugador;
import persistencia.baseDeDatos.daos.DaoPartidas;
import persistencia.baseDeDatos.poolDeConexiones.IConexion;
import persistencia.excepciones.PersistenciaException;

public class Fachada extends UnicastRemoteObject implements IFachada {

	private static final long serialVersionUID = 1L;
	private static Fachada instancia;
	private DaoJugador daoJ;
	private DaoPartidas daoP;
	private DaoEquipo daoE;
	private DaoBase daoB;
	private DaoDeAviones daoAvion;
	private DaoArtilleria daoArti;
	private IPoolConexiones ipool;
	private SystemProperties sp;

	public static MensajesPersonalizados mensg = new MensajesPersonalizados();

	public Fachada() throws RemoteException, PersistenciaException {
		try {
			sp = new SystemProperties();
			String poolConcreto = sp.getpool_className();
			ipool = (IPoolConexiones) Class.forName(poolConcreto.trim()).newInstance();
			daoP = new DaoPartidas();
			daoJ = new DaoJugador();
			daoE = new DaoEquipo();
			daoB = new DaoBase();
			daoAvion = new DaoDeAviones();
			daoArti = new DaoArtilleria();
			
			
			

		} catch (IOException e) {
			throw new PersistenciaException(mensg.errorIO);
		} catch (InstantiationException e) {
			throw new PersistenciaException(mensg.errorPoolCrearIConexion + e.toString());
		} catch (IllegalAccessException e) {
			throw new PersistenciaException(mensg.errorIO);
		} catch (ClassNotFoundException e) {
			throw new PersistenciaException(mensg.errorIO);
		}
	}

	public static Fachada getInstancia() throws RemoteException, PersistenciaException {
		if (instancia == null)
			instancia = new Fachada();
		return instancia;
	}
   public String verConexion(String x) throws RemoteException {
	   System.out.println("Servidor :"+x);
	   return x+"Server";
   }
	public void registrarJugador(VOJugador in_voJug) throws RemoteException,PersistenciaException, LogicaException, InterruptedException {
		IConexion icon = ipool.obtenerConexion(true);

		try {
			String userName = in_voJug.getJugadorUserName();
			if (daoJ.member(userName, icon)) {
				ipool.liberarConexion(icon, false);
				throw new LogicaException(mensg.errorFachadaYaExisteUsuario);
			} else {
				
				Jugador jug = new Jugador(0,in_voJug.getJugadorUserName(), in_voJug.getJugadorPassword(),
						in_voJug.isJugadorIsOnline(), in_voJug.getPuntajeAcumulado());
				daoJ.insert(jug, icon);
				ipool.liberarConexion(icon, true);
			}
		} catch (Exception e) {
			ipool.liberarConexion(icon, false);
			throw new LogicaException(mensg.errorFachadaNuevoUsuario);
		}

	}

	public VOJugador Login(String in_userName, String in_userPassword)
			throws RemoteException, LogicaException, PersistenciaException, InterruptedException {
		IConexion icon = ipool.obtenerConexion(true);
		VOJugador out_Voj = null;
		try {
			if (daoJ.member(in_userName, icon)) {
				int id = daoJ.geIdbyName(in_userName, icon);
				Jugador JuG = daoJ.find(id, icon);
				
				if (JuG.getJugadorPassword().equals(in_userPassword)) {
					out_Voj = devolverVOJugador(JuG);
				} else {
					ipool.liberarConexion(icon, true);
					throw new LogicaException(mensg.errorFachadaNoExisteUsuario);
				}
			}
		} catch (Exception e) {
			ipool.liberarConexion(icon, false);
			throw new LogicaException(mensg.errorFachadaNoExisteUsuario);
		}
		return out_Voj;
	}

	
	// PRE:Jugador Logueado y registrado
	public ArrayList<VOPartida> listarPartidasAReanudar(String in_Nickname)
			throws PersistenciaException, LogicaException, RemoteException , InterruptedException{
		IConexion icon = ipool.obtenerConexion(false);
		
		ArrayList<VOPartida> voPartidas = null;
		TreeMap<Integer, Partida> aux = null;

		try {
			if (daoJ.member(in_Nickname, icon)) {

				if (!daoP.estaVacio(icon)) {

					int id = daoJ.geIdbyName(in_Nickname, icon);
					aux =  daoP.listarPartidasDeJugador(id, icon);
					
					//ArrayList<VOPartida> voPartidas = null;
						voPartidas = new ArrayList<VOPartida>();
						Iterator<Partida> Itr = aux.values().iterator();
						while (Itr.hasNext()) {
							Partida auxiliar = Itr.next();
//							VOPartida parti = new VOPartida(
//									auxiliar.getPartidaId() 
//									, auxiliar.getPartidaEstado() 
//									, auxiliar.getPartidaFechaUltimaActualizacion()  
//									, auxiliar.isPartidaGuardada() 
//									, auxiliar.getPartidaNombre() 
//									, auxiliar.getPartidaCantidadJugadores() 
//									, auxiliar.getPartidaCreador() 
//									, auxiliar.getPartidaFechaCreada() 
//									, auxiliar.getPartidaTermino() 
//									, auxiliar.getEquipos() 
//									);
//							voPartidas.add(parti);
					}
						
						
						
					
					ipool.liberarConexion(icon, true);

				} else {
					ipool.liberarConexion(icon, false);
					throw new LogicaException(mensg.errorFachadaListaVacia);
				}

			} else {
				ipool.liberarConexion(icon, false);
				throw new LogicaException(mensg.errorFachadaNoExisteUsuario);
			}
		} catch (Exception e) {
			ipool.liberarConexion(icon, false);
			throw new LogicaException(mensg.errorFachadaListPartidas);
		}
		return voPartidas;
	}

	
	//precondicion jugador logueado y registrado
	public void guardarPartida(VOPartida in_voPartida) throws LogicaException, RemoteException , InterruptedException, PersistenciaException {
		 System.out.println("Entro guardar partida 195");
		IConexion icon = ipool.obtenerConexion(true);
		try {
			// pedimos el ultimo idpartida
			// creamos la partida con ese id
			// para cada jugador de la partida crear en la tabla relacion
			int idpartida = daoP.getUltimaPartidaIDMas1(icon);
            System.out.println("Entro guardar partida 202 y el idultima partina contiene****"+idpartida);
            Partida part=new Partida();
            
           
            System.out.println(" 203 vamos a mostrar toda la partida para ver que viene en el vopartida");
            
            in_voPartida.mostrarPartidaPorPantalla();
            
            
            
			 part = devolverPartidaDadoVO(in_voPartida);// el problema está acá dentro
			 
			 System.out.println(" 215 La cantidad de jugadores de una partida es:"+part.getPartidaCantidadJugadores());
			 
			daoP.insert(part, icon);
//			 Creo los equipos e inserto los equipos con el id de la partida
		   System.out.println(" 215 despues del insert");
		     Equipo[] auxEquipo = null;
			auxEquipo = part.getEquipos().listarEquipos(icon);// Esto es un arreglo con los N equipos
			System.out.println(" Salio de listar");
		    int largoArreglo = auxEquipo.length;
			System.out.println("El largo es:"+largoArreglo);
			
			
			for (int i = 0; i < largoArreglo; i++) {

			
				System.out.println(" Salio del isback");
				Base auxBase = auxEquipo[i].getBase();
				System.out.println(" despues de cargar la base");
				Deposito auxDep = auxBase.getDeposito();
				TanqueCombustible auxTC = auxBase.getTanque();
				TorreControl auxTControl = auxBase.getTorre();
				System.out.println(" pasa los objetos chicos");
			    daoB.insert( auxEquipo[i].getEquipoID(), auxDep, auxTC, auxTControl, icon);
				Avion[] auxAviones = auxBase.getAviones().listarAviones(icon);
				int largoAviones = auxAviones.length;
				System.out.println("int largoAviones = auxAviones.length::"+largoAviones);
				for (int j = 0; i < largoAviones-1; j++) {
					System.out.println("j:"+j);
					daoAvion.insback(daoB.getUltimaIsBase(icon), auxAviones[j], icon);
				}
				Artillero[] auxArtilleria = auxBase.getArtilleros().listarArtilleria(icon);
			     int largoArtillero = auxArtilleria.length;
				for (int x = 0; i < largoArtillero; x++) {
					daoArti.insBack(daoB.getUltimaIsBase(icon), auxArtilleria[x], icon);
				}
			     daoE.insBack(idpartida, auxEquipo[0], icon);
			    ipool.liberarConexion(icon, true);
			
		}
			} catch (PersistenciaException e) {
			ipool.liberarConexion(icon, false);
			System.out.println("se rompe y sale por acá");
			throw new LogicaException(mensg.errorFachadaGuardarPartidas);
		}

	}

	// precondicion jugador logueado
	
	public void logout(String in_userName) throws LogicaException, RemoteException , InterruptedException, PersistenciaException{
		IConexion icon = ipool.obtenerConexion(true);
		try {
			int id = daoJ.geIdbyName(in_userName, icon);
			daoJ.logoutJugador(id, icon);

			ipool.liberarConexion(icon, true);
		} catch (PersistenciaException e) {
			ipool.liberarConexion(icon, false);

			throw new LogicaException(mensg.errorFachadaAlHacerLogout);
		}
	}

	public boolean jugadorIsOnline(String in_name) throws LogicaException, RemoteException, InterruptedException, PersistenciaException {
		IConexion icon = ipool.obtenerConexion(true);
		boolean out_es = false;

		try {
			int id = daoJ.geIdbyName(in_name, icon);
			out_es = daoJ.estaOnline(id, icon);
			ipool.liberarConexion(icon, true);

		} catch (Exception e) {
			ipool.liberarConexion(icon, false);
			throw new LogicaException(mensg.errorFachadaEstaOnline);
		}
		return out_es;

	}

	private VOJugador devolverVOJugador(Jugador in_aux) {
		System.out.println("devolverVOJugador 271");

		VOJugador out_aux = null;
		out_aux = new VOJugador(in_aux.getJugadorId(), in_aux.getJugadorUserName(), in_aux.getJugadorPassword(),
				in_aux.isJugadorIsOnline(), in_aux.getPuntajeAcumulado());

		return out_aux;

	}

	private Partida devolverPartidaDadoVO(VOPartida in_aux) {
		System.out.println("devolverPartidaDadoVO 281");
		Partida out_aux=null;
	
	VOCollectionEquipo equipos = in_aux.getEquipos();
	System.out.println("El id DE LA PARTIDA ES: ******"+equipos.getIdpartida());
	
	
	 out_aux = new Partida
				(in_aux.getPartidaId(), //ok
						in_aux.getPartidaEstado(), //ok
						in_aux.getPartidaFechaUltimaActualizacion(),//ok
						in_aux.isPartidaGuardada(), //ok
						in_aux.getPartidaNombre(),//ok
						in_aux.getPartidaCantidadJugadores(),//ok
						in_aux.getPartidaCreador(), //ok
						in_aux.getPartidaFechaCreada(),//ok
						in_aux.isPartidaTermino(), //ok
						devolverDaoEquipoDadoVO(in_aux.getEquipos()));

				
		return out_aux;

	}
	
	
	
	private Avion  devolverAvionDadoVO(VOAvion in_Avion)
	{    //System.out.println("devolverAvionDadoVO 308");
		//int in_PK_avion_id, int in_avionCoordX, int in_avionCoordY,int in_avionCoordZ ,boolean in_estado, int in_vida,boolean in_hayEnemigo,
		//int in_rangoDeVision ,boolean in_avionBomba,int  in_cantidadBombas, int in_avionCombustible,boolean in_enCampoEnemigo,int in_baseid)
		
		Avion avionAux=new Avion(in_Avion.GetId(),in_Avion.getCoordX(),in_Avion.getCoordY(),in_Avion.getAvionAltura(),in_Avion.getEstado(),in_Avion.getVida(),in_Avion.getHayEnemigo(),
				in_Avion.getRangoDeVision(),in_Avion.getAvionBomba(),in_Avion.getCantidadBombas(),
				in_Avion.getAvionCombustible(),in_Avion.getEnCampoEnemigo(),in_Avion.getBaseid());
		        
		
		return avionAux;
	}
	
	private Artillero  devolverArtilleroDadoVO(VOArtillero in_Artillero)
	{     // System.out.println("devolverArtilleroDadoVO 321");
		//int in_id, int in_coordX, int in_coordY, 
		//boolean in_estado, int in_vida, boolean in_hayEnemigo,
		//int in_rangoDeVision, int in_ArtilleroAngulo,int in_base_id
		
		Artillero artilleroAux= new Artillero(in_Artillero.GetId(),in_Artillero.getCoordX(),in_Artillero.getCoordY(),
				in_Artillero.getEstado(),in_Artillero.getVida(),in_Artillero.getHayEnemigo(),
				in_Artillero.getRangoDeVision(),in_Artillero.getArtilleroAngulo(),in_Artillero.getbase_id());
		return artilleroAux;
		
		
	}
	
	private Deposito  devolverDepositoDadoVO(VODeposito in_Deposito)
	{   //System.out.println("devolverDepositoDadoVO 335");
	//	int in_id, int in_coordX, int in_coordY, boolean in_estado, int in_vida,int in_cantidadBombas,boolean in_enUso) {
		Deposito depaux=new Deposito(in_Deposito.GetId(),in_Deposito.getCoordX(),in_Deposito.getCoordY(),
				in_Deposito.getEstado(),in_Deposito.getVida(),in_Deposito.getCantidaBombas(),in_Deposito.getEnUso());
		
		return depaux;
	}
	
	private TanqueCombustible  devolverTanqueCombustibleDadoVO(VOTanqueCombustible in_TanqueCombustible)
	{  // System.out.println("devolverTanqueCombustibleDadoVO 344"); 
		//int in_id, int in_coordX, int in_coordY, boolean in_estado, int in_vida, int in_cantidadCombustible, boolean in_enUso
		
		TanqueCombustible auxDepTanque=new TanqueCombustible(in_TanqueCombustible.GetId(),in_TanqueCombustible.getCoordX(),
				                                           in_TanqueCombustible.getCoordY(),in_TanqueCombustible.getEstado(),
				                                               in_TanqueCombustible.getVida(),in_TanqueCombustible.getCantidadCombustible(),
				                                               in_TanqueCombustible.getEnUso());
		return auxDepTanque;
	}
	
	private TorreControl  devolverTorreControlDadoVO(VOTorreControl in_TorreControl)
	{  //System.out.println("devolverTorreControlDadoVO 355"); 
		//int in_id, int in_coordX, int in_coordY, boolean in_estado, int in_vida,boolean in_hayEnemigo, int in_rangoDeVision
		TorreControl auxTorreControl=new TorreControl(in_TorreControl.GetId(),in_TorreControl.getCoordX()
				                      ,in_TorreControl.getCoordY(),in_TorreControl.getEstado(),in_TorreControl.getVida(),
				                      in_TorreControl.getHayEnemigo(),in_TorreControl.getRangoDeVision());
		return auxTorreControl;
	}
	
	private DaoDeAviones  devolverDaoAvionesDadoVO(VOCollectionAviones in_DaoAviones)
	{ // System.out.println("devolverDaoAvionesDadoVO 364"); 
		
		DaoDeAviones auxDaoAviones= new DaoDeAviones(in_DaoAviones.getBaseId());
		int max=in_DaoAviones.getArreavion().length;
		Avion[] aviones=new Avion[max];
		VOAvion[] voAviones=in_DaoAviones.getArreavion();
		for(int i=0;i<max;i++)
		{   
			//int in_PK_avion_id, int in_avionCoordX, int in_avionCoordY,int in_avionCoordZ ,boolean in_estado, int in_vida,boolean in_hayEnemigo,
			//int in_rangoDeVision ,boolean in_avionBomba,int  in_cantidadBombas, int in_avionCombustible,boolean in_enCampoEnemigo,int in_baseid)
			Avion av=new Avion(voAviones[i].GetId(),voAviones[i].getCoordX(),voAviones[i].getCoordY(),voAviones[i].getAvionAltura(),voAviones[i].getEstado(),
					          voAviones[i].getVida(),voAviones[i].getHayEnemigo(),voAviones[i].getRangoDeVision(),voAviones[i].getAvionBomba(),
					          voAviones[i].getCantidadBombas(),voAviones[i].getAvionCombustible(),voAviones[i].getEnCampoEnemigo(),voAviones[i].getBaseid());
			aviones[i]=av;
		}
		auxDaoAviones.setArreAviones(aviones);
		
			
			
		return	auxDaoAviones;
		}
		
		
		
	private DaoArtilleria  devolverDaoArtilleriaDadoVO(VOCollectionArtilleria in_DaoArtilleria)
	{   // System.out.println("devolverDaoArtilleriaDadoVO 389"); 
		
//		int in_id, int in_coordX, int in_coordY, 
//		boolean in_estado, int in_vida, boolean in_hayEnemigo,
//		int in_rangoDeVision, int in_ArtilleroAngulo,int in_base_id
//		
		DaoArtilleria auxDaoArtilleria= new DaoArtilleria(in_DaoArtilleria.getBaseid());
		int max=in_DaoArtilleria.getSecuenciaArtilleria().length;
		Artillero[] artillerias=new Artillero[max];
		VOArtillero[] VOartillerias=in_DaoArtilleria.getSecuenciaArtilleria();
		
		for (int i=0; i<max;i++)
		{
			Artillero ar= new Artillero(VOartillerias[i].GetId(),VOartillerias[i].getCoordX(),VOartillerias[i].getCoordY(),VOartillerias[i].getEstado(),
					VOartillerias[i].getVida(),VOartillerias[i].getHayEnemigo(),VOartillerias[i].getRangoDeVision(),VOartillerias[i].getArtilleroAngulo(),
					VOartillerias[i].getbase_id());
			      artillerias[i]=ar;
		}
			
		auxDaoArtilleria.setArreArtilleria(artillerias);
		return	auxDaoArtilleria;
		
	}
		
		
	
	
	private Base  devolverBaseDadoVO(VOBase in_Base)
	{  // System.out.println("devolverBaseDadoVO 417"); 
		//int in_idDabse,DaoDeAviones in_aviones,  
		//DaoArtilleria in_artilleros, Deposito in_deposito, 
		//TanqueCombustible in_tanque,TorreControl in_torre
		
		Base auxBase=new Base(in_Base.getIdBase(),devolverDaoAvionesDadoVO(in_Base.getAviones()),
				             devolverDaoArtilleriaDadoVO(in_Base.getArtilleros()),
				          devolverDepositoDadoVO(in_Base.getDeposito()),
				          devolverTanqueCombustibleDadoVO(in_Base.getTanque()),
				          devolverTorreControlDadoVO(in_Base.getTorre()));
		return auxBase;
		
		
	}
	
	
	
	
	private DaoJugador  devolverDaoJugadorDadoVO(VOCollectionJugador in_DaoJugador)
	{  
		//System.out.println("devolverDaoJugadorDadoVO 436"); 
		
		DaoJugador daoJugador= new DaoJugador();
		TreeMap<Integer, Jugador> aux= new TreeMap<Integer, Jugador>();
		////System.out.println("null 440");

		Iterator<VOJugador> Itr =  in_DaoJugador.listarJugadores().values().iterator();
		//System.out.println("null 442");
		while (Itr.hasNext()) {
			VOJugador auxiliar = Itr.next();
			aux.put(devolverJugadordadoVO(auxiliar).getJugadorId(), devolverJugadordadoVO(auxiliar));
			
		}
		
		daoJugador.setJugadores(aux);
		return daoJugador;

	}

	
	
	private DaoBase  devolverDaoBaseDadoVO(VOCollectionBase in_DaoBase)
	{
		 //System.out.println("devolverDaoBaseDadoVO 461"); 
		DaoBase daoBases= new DaoBase();
		TreeMap<Integer, Base>  aux=new TreeMap<Integer, Base>();
		
		Iterator<VOBase> Itr = in_DaoBase.getBases().values().iterator();
		while (Itr.hasNext()) {
			VOBase auxiliar = Itr.next();
			aux.put(devolverBaseDadoVO(auxiliar).getIdDabse(), devolverBaseDadoVO(auxiliar));
		}
		daoBases.setBases(aux);
		return daoBases;
		
	}
	
	
	private Jugador devolverJugadordadoVO(VOJugador in_aux) {
		  // System.out.println("evolverJugadordadoVO 477 en la fachada se rompe"); 
		   
		   
		  
		   
		   
		Jugador out_aux = new Jugador(in_aux.getJugadorId(), in_aux.getJugadorUserName(), 
				in_aux.getJugadorPassword(),in_aux.isJugadorIsOnline(), in_aux.getPuntajeAcumulado());
//				
//		out_aux = new Jugador(in_aux.getJugadorId(), in_aux.getJugadorUserName(), in_aux.getJugadorPassword(),
//				in_aux.isJugadorIsOnline(), in_aux.getPuntajeAcumulado());
     //  System.out.println("Creo jugador");
		return out_aux;

	}
	
	private Equipo devolverEquipoDadoVO(VOEquipo in_voEquipo) {
		//int in_equipoID, Jugador[]  in_Jugadores, Base  in_base, String  in_bando
		//System.out.println("mostrar los jugadores acá, está llegando con un largo de:"+in_voEquipo.getJugadores().length);
		Equipo out = new Equipo(in_voEquipo.getEquipoID(),
				devolverArreJugadorDadoVO(in_voEquipo.getJugadores()), 
				
				devolverBaseDadoVO(in_voEquipo.getBase()),
				
				in_voEquipo.getBando() );
		
		return out;
		
		
	}
	
	private Jugador[] devolverArreJugadorDadoVO(VOJugador[] in_Jugador) {
		
		Jugador[] aux= new Jugador[in_Jugador.length];
		
		for (int i=0; i<in_Jugador.length; i++) {
			aux[i]= devolverJugadordadoVO(in_Jugador[i]);
		}
		
		return aux;
		
	}
	
	
	
	
	private Equipo[] devolverArreEquipoDadoVO(VOEquipo[] in_voEquipo) {
		Equipo[] aux= new Equipo[in_voEquipo.length];
		
		for (int i=0; i<in_voEquipo.length; i++) {
			aux[i]=devolverEquipoDadoVO( in_voEquipo[i]);
		}
		
		return aux;
		
	}

	
	private DaoEquipo  devolverDaoEquipoDadoVO(VOCollectionEquipo in_DaoEquipo)
	{   
		//int in_idpartida, Equipo[] in_Equipos, DaoJugador in_DaoJ,DaoBase   in_DaoB
		
		
		DaoEquipo auxDaoEquipo= new DaoEquipo(in_DaoEquipo.getIdpartida(),devolverArreEquipoDadoVO( in_DaoEquipo.getequipos()),
				 devolverDaoJugadorDadoVO(in_DaoEquipo.getDaoJ()), devolverDaoBaseDadoVO(in_DaoEquipo.getDaoB()));
		
		
		
		return	auxDaoEquipo;
		
		
	}
	
	

	
	

}
