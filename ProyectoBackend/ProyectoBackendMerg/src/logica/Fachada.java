package logica;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeMap;

import Utilitarios.MensajesPersonalizados;
import Utilitarios.SystemProperties;
import logica.excepciones.LogicaException;
import logica.interfaces.IPoolConexiones;
import logica.valueObjects.VOArtillero;
import logica.valueObjects.VOAvion;
import logica.valueObjects.VOBase;
import logica.valueObjects.VODaoArtilleria;
import logica.valueObjects.VODaoAviones;
import logica.valueObjects.VODaoBase;
import logica.valueObjects.VODaoEquipo;
import logica.valueObjects.VODaoJugador;
import logica.valueObjects.VODeposito;
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
		System.out.println("Login fachada     0");
		IConexion icon = ipool.obtenerConexion(true);
		VOJugador out_Voj = null;
        System.out.println("antes del try");
		try {
			 System.out.println("entra al try");
			if (daoJ.member(in_userName, icon)) {
				System.out.println("es member");
				int id = daoJ.geIdbyName(in_userName, icon);
				System.out.println("antes del find");
				Jugador JuG = daoJ.find(id, icon);
				System.out.println("despues");
				
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

	// PRE:Jugador Logueado
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

	public void guardarPartida(VOPartida in_voPartida) throws LogicaException, RemoteException , InterruptedException, PersistenciaException {

		IConexion icon = ipool.obtenerConexion(true);
		try {
			// pedimos el ultimo idpartida
			// creamos la partida con ese id
			// para cada jugador de la partida crear en la tabla relacion
			int idpartida = daoP.getUltimaPartidaID(icon);

			Partida part = devolverPartidaDadoVO(in_voPartida, idpartida++);
			daoP.insert(part, icon);
			// Creo los equipos e inserto los equipos con el id de la partida
			Equipo[] auxEquipo = null;
			auxEquipo = part.getEquipos().listarEquipos(icon);// Esto es un arreglo con los N equipos
			int largoArreglo = auxEquipo.length;
			for (int i = 0; i < largoArreglo; i++) {

				daoE.insBack(idpartida, auxEquipo[i], icon);
				Base auxBase = auxEquipo[i].getBase();
				Deposito auxDep = auxBase.getDeposito();
				TanqueCombustible auxTC = auxBase.getTanque();
				TorreControl auxTControl = auxBase.getTorre();
				daoB.insert( auxEquipo[i].getEquipoID(), auxDep, auxTC, auxTControl, icon);
				Avion[] auxAviones = auxBase.getAviones().listarAviones(icon);
				int largoAviones = auxAviones.length;
				for (int j = 0; i < largoAviones; j++) {
					daoAvion.insback(daoB.getUltimaIsBase(icon), auxAviones[j], icon);
				}
				Artillero[] auxArtilleria = auxBase.getArtilleros().listarArtilleria(icon);
				int largoArtillero = auxArtilleria.length;
				for (int x = 0; i < largoArtillero; x++) {
					daoArti.insBack(daoB.getUltimaIsBase(icon), auxArtilleria[x], icon);
				}
				icon = ipool.obtenerConexion(true);
			}
		} catch (PersistenciaException e) {
			ipool.liberarConexion(icon, false);
			throw new LogicaException(mensg.errorFachadaListPartidas);
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

		VOJugador out_aux = null;
		out_aux = new VOJugador(in_aux.getJugadorId(), in_aux.getJugadorUserName(), in_aux.getJugadorPassword(),
				in_aux.isJugadorIsOnline(), in_aux.getPuntajeAcumulado());

		return out_aux;

	}

	private Partida devolverPartidaDadoVO(VOPartida in_aux, int in_ultimoIdPartida) {
		Partida out_aux = null;

	
		
		
		//int in_PartidaId, String in_PartidaEstado, Date in_PartidaFechaUltimaActualizacion,
		//boolean in_PartidaGuardada, String  in_PartidaNombre, int in_PartidaCantidadJugadores,
		//int in_PartidaCreador, Date in_PartidaFechaCreada,boolean in_partidaTermino, VODaoEquipo in_Equi
		
		
		
		
//		out_aux = new Partida(in_ultimoIdPartida, in_aux.getPartidaEstado(),in_aux.getPartidaFechaUltimaActualizacion(),
//				      in_aux.isPartidaGuardada(),in_aux.getPartidaNombre(),in_aux.getPartidaCantidadJugadores(),in_aux.getPartidaCreador(),
//				      in_aux.getPartidaFechaCreada(),in_aux.isPartidaGuardada(),);
				
		return out_aux;

	}
	
	
	
	private Avion  devolverAvionDadoVO(VOAvion in_Avion)
	{   
		//int in_PK_avion_id, int in_avionCoordX, int in_avionCoordY,int in_avionCoordZ ,boolean in_estado, int in_vida,boolean in_hayEnemigo,
		//int in_rangoDeVision ,boolean in_avionBomba,int  in_cantidadBombas, int in_avionCombustible,boolean in_enCampoEnemigo,int in_baseid)
		
		Avion avionAux=new Avion(in_Avion.GetId(),in_Avion.getCoordX(),in_Avion.getCoordY(),in_Avion.getAvionAltura(),in_Avion.getEstado(),in_Avion.getVida(),in_Avion.getHayEnemigo(),
				in_Avion.getRangoDeVision(),in_Avion.getAvionBomba(),in_Avion.getCantidadBombas(),
				in_Avion.getAvionCombustible(),in_Avion.getEnCampoEnemigo(),in_Avion.getBaseid());
		        
		
		return avionAux;
	}
	
	private Artillero  devolverArtilleroDadoVO(VOArtillero in_Artillero)
	{    
		//int in_id, int in_coordX, int in_coordY, 
		//boolean in_estado, int in_vida, boolean in_hayEnemigo,
		//int in_rangoDeVision, int in_ArtilleroAngulo,int in_base_id
		
		Artillero artilleroAux= new Artillero(in_Artillero.GetId(),in_Artillero.getCoordX(),in_Artillero.getCoordY(),
				in_Artillero.getEstado(),in_Artillero.getVida(),in_Artillero.getHayEnemigo(),
				in_Artillero.getRangoDeVision(),in_Artillero.getArtilleroAngulo(),in_Artillero.getbase_id());
		return null;
		
		
	}
	
	private Deposito  devolverDepositoDadoVO(VODeposito in_Deposito)
	{
	//	int in_id, int in_coordX, int in_coordY, boolean in_estado, int in_vida,int in_cantidadBombas,boolean in_enUso) {
		Deposito depaux=new Deposito(in_Deposito.GetId(),in_Deposito.getCoordX(),in_Deposito.getCoordY(),
				in_Deposito.getEstado(),in_Deposito.getVida(),in_Deposito.getCantidaBombas(),in_Deposito.getEnUso());
		
		return depaux;
	}
	
	private TanqueCombustible  devolverTanqueCombustibleDadoVO(VOTanqueCombustible in_TanqueCombustible)
	{
		//int in_id, int in_coordX, int in_coordY, boolean in_estado, int in_vida, int in_cantidadCombustible, boolean in_enUso
		
		TanqueCombustible auxDepTanque=new TanqueCombustible(in_TanqueCombustible.GetId(),in_TanqueCombustible.getCoordX(),
				                                           in_TanqueCombustible.getCoordY(),in_TanqueCombustible.getEstado(),
				                                               in_TanqueCombustible.getVida(),in_TanqueCombustible.getCantidadCombustible(),
				                                               in_TanqueCombustible.getEnUso());
		return auxDepTanque;
	}
	
	private TorreControl  devolverTorreControlDadoVO(VOTorreControl in_TorreControl)
	{
		//int in_id, int in_coordX, int in_coordY, boolean in_estado, int in_vida,boolean in_hayEnemigo, int in_rangoDeVision
		TorreControl auxTorreControl=new TorreControl(in_TorreControl.GetId(),in_TorreControl.getCoordX()
				                      ,in_TorreControl.getCoordY(),in_TorreControl.getEstado(),in_TorreControl.getVida(),
				                      in_TorreControl.getHayEnemigo(),in_TorreControl.getRangoDeVision());
		return auxTorreControl;
	}
	
	private DaoDeAviones  devolverDaoAvionesDadoVO(VODaoAviones in_DaoAviones)
	{
		
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
		
		
		
	private DaoArtilleria  devolverDaoArtilleriaDadoVO(VODaoArtilleria in_DaoArtilleria)
	{
		
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
	{
		//int in_idDabse,DaoDeAviones in_aviones,  
		//DaoArtilleria in_artilleros, Deposito in_deposito, 
		//TanqueCombustible in_tanque,TorreControl in_torre
		
		Base auxBase=new Base(in_Base.getIdBase(),devolverDaoAvionesDadoVO(in_Base.getAviones()),
				             devolverDaoArtilleriaDadoVO(in_Base.getArtilleros()),
				          devolverDepositoDadoVO(in_Base.getDeposito()),devolverTanqueCombustibleDadoVO(in_Base.getTanque()),devolverTorreControlDadoVO(in_Base.getTorre()));
		return auxBase;
		
		
	}
	
	
	

	
	
	private DaoBase  devolverDaoBaseDadoVO(VODaoBase in_DaoBase)
	{
		return null;
		
		
	}
	private DaoEquipo  devolverDaoEquipoDadoVO(VODaoEquipo in_DaoEquipo)
	{
		return null;
		
		
	}
	
	private DaoJugador  devolverDaoJugadorDadoVO(VODaoJugador in_DaoJugador)
	{
		return null;
		
		
	}
	
	

	
	

}
