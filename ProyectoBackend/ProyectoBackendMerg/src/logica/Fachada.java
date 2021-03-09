package logica;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
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
		System.out.println("Servidor :" + x);
		return x + "Server";
	}

	public void registrarJugador(VOJugador in_voJug)
			throws RemoteException, PersistenciaException, LogicaException, InterruptedException {
		IConexion icon = ipool.obtenerConexion(true);

		try {
			String userName = in_voJug.getJugadorUserName();
			if (daoJ.member(userName, icon)) {
				ipool.liberarConexion(icon, false);
				throw new LogicaException(mensg.errorFachadaYaExisteUsuario);
			} else {

				Jugador jug = new Jugador(0, in_voJug.getJugadorUserName(), in_voJug.getJugadorPassword(),
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
					daoJ.loginJugador(id, icon);
					out_Voj = devolverVOJugador(JuG);
					ipool.liberarConexion(icon, true);
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

	public VOPartida ReanudarPartida(int in_partidaid) throws PersistenciaException, InterruptedException {
		IConexion icon = ipool.obtenerConexion(false);
		VOPartida outVOPartida = null;
		Partida aux = null;

		try {
			System.out.println("linea 142cargo una partida con los datos basicos");
			aux = daoP.find(in_partidaid, icon);
			System.out.println("linea 144 reanudarpartida");
			System.out.println("145 en fachada antes de entrar a transformar en veo partida");
			aux.mostrarPartidaPorPantalla();
			//
			int ver = aux.getEquipos().largo(icon);
			System.out.println("Largo del equipo es:" + ver);
			outVOPartida = DevolverVoPartidaDadoPartida(aux, icon);
			System.out.println("linea 149 reanudarpartida");
			ipool.liberarConexion(icon, true);

		} catch (PersistenciaException e) {
			ipool.liberarConexion(icon, true);
			System.out.println(e.toString());
		}

		return outVOPartida;

	}

	// PRE:Jugador Logueado y registrado
	public ArrayList<VOPartida> listarPartidasAReanudar(String in_Nickname)
			throws PersistenciaException, LogicaException, RemoteException, InterruptedException {
		IConexion icon = ipool.obtenerConexion(false);

		ArrayList<VOPartida> voPartidas = null;
		TreeMap<Integer, Partida> aux = null;
		System.out.println(in_Nickname);
		try {
			if (daoJ.member(in_Nickname, icon)) {

				if (daoP.estaVacio(icon) == false) {

					int id = daoJ.geIdbyName(in_Nickname, icon);
					System.out.println("149" + id);
					aux = daoP.listarPartidasDeJugador(id, icon);
					voPartidas = new ArrayList<VOPartida>();
					Iterator<Partida> Itr = aux.values().iterator();
					while (Itr.hasNext()) {
						Partida auxiliar = Itr.next();

						VOPartida parti = new VOPartida(auxiliar.getPartidaId(), auxiliar.getPartidaEstado(),
								auxiliar.getPartidaFechaUltimaActualizacion(), auxiliar.isPartidaGuardada(),
								auxiliar.getPartidaNombre(), auxiliar.getPartidaCantidadJugadores(),
								auxiliar.getPartidaCreador(), auxiliar.getPartidaFechaCreada(),
								auxiliar.getPartidaTermino(), null);

						// atencion estamos pasando en null todo lo demas
						// dado que solo le interesa al jugador listar las partidas a reanudar
						// esto es el id, nombre de la partida y como muncho la fecha
						voPartidas.add(parti);

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

			throw new LogicaException(e.toString());
		}
		return voPartidas;
	}

	// precondicion jugador logueado y registrado
	public void guardarPartida(VOPartida in_voPartida)
			throws LogicaException, RemoteException, InterruptedException, PersistenciaException {
		IConexion icon = ipool.obtenerConexion(true);
		try {
			// pedimos el ultimo idpartida
			// creamos la partida con ese id
			// para cada jugador de la partida crear en la tabla relacion
			Partida part = new Partida();
			part = devolverPartidaDadoVO(in_voPartida);// el problema está acá dentro
			// Creo la partida y la inserto
			daoP.insert(part, icon);
			// Pido al usuario equipo en memoria
			Equipo[] auxEquipo = null;
			auxEquipo = part.getEquipos().getEquiposEnMemoria();// Esto es un arreglo con los N equipos
			int largoArreglo = auxEquipo.length;
			for (int i = 0; i < largoArreglo; i++) { // por cada equipo

				// Inserto Base
				Base auxBase = auxEquipo[i].getBase();
				Deposito auxDep = auxBase.getDeposito();
				TanqueCombustible auxTC = auxBase.getTanque();
				TorreControl auxTControl = auxBase.getTorre();
				daoB.insert(auxEquipo[i].getEquipoID(), auxDep, auxTC, auxTControl, icon);

				// inserto Equipo
				int idpartida = daoP.getUltimaPartidaIDMas1(icon);

				daoE.insBack(idpartida, auxEquipo[i], icon); // ver que va antes si el equipo o los aviones y torretas

				int idbase = daoB.getUltimaBaseID(icon);

				// Creo Aviones , inserto aviones
				Avion[] auxAviones = auxBase.getAviones().getArreAvionesEnMemoria();
				int largoAviones = auxAviones.length;

				for (int j = 0; j < largoAviones; j++) {

					daoAvion.insback(idbase, auxAviones[j], icon);
				}
				// Creo Artilleria , inserto artillertia
				Artillero[] auxArtilleria = auxBase.getArtilleros().getArreArtilleriaEnMemoria();
				int largoArtillero = auxArtilleria.length;
				for (int x = 0; x < largoArtillero; x++) {
					daoArti.insBack(idbase, auxArtilleria[x], icon);
				}

				ipool.liberarConexion(icon, true);

			}
		} catch (PersistenciaException e) {
			ipool.liberarConexion(icon, false);
			throw new LogicaException(e.toString());
			// throw new LogicaException(mensg.errorFachadaGuardarPartidas);
		}

	}

	// precondicion jugador logueado

	public void logout(String in_userName)
			throws LogicaException, RemoteException, InterruptedException, PersistenciaException {
		IConexion icon = ipool.obtenerConexion(true);
		try {
			int id = daoJ.geIdbyName(in_userName, icon);
			daoJ.logoutJugador(id, icon);

			ipool.liberarConexion(icon, true);
		} catch (PersistenciaException e) {
			ipool.liberarConexion(icon, false);
			throw new LogicaException(e.toString());
			// throw new LogicaException(mensg.errorFachadaAlHacerLogout);
		}
	}

	public boolean jugadorIsOnline(String in_name)
			throws LogicaException, RemoteException, InterruptedException, PersistenciaException {
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

	

	/// INICIO FUNCIONES AUXILIARES PRIVADAS UTILIZADAS PARA EL REQUERIMIENTO
	/// GUARDAR PARTIDA, EN ORDEN DE EJECUCION

	private Partida devolverPartidaDadoVO(VOPartida in_aux) {
		Partida out_aux = null;

		// VOCollectionEquipo equipos = in_aux.getEquipos();

		out_aux = new Partida(in_aux.getPartidaId(), // ok
				in_aux.getPartidaEstado(), // ok
				in_aux.getPartidaFechaUltimaActualizacion(), // ok
				in_aux.isPartidaGuardada(), // ok
				in_aux.getPartidaNombre(), // ok
				in_aux.getPartidaCantidadJugadores(), // ok
				in_aux.getPartidaCreador(), // ok
				in_aux.getPartidaFechaCreada(), // ok
				in_aux.isPartidaTermino(), // ok
				devolverDaoEquipoDadoVO(in_aux.getEquipos()));

		return out_aux;

	}

	private DaoEquipo devolverDaoEquipoDadoVO(VOCollectionEquipo in_DaoEquipo) {
		// int in_idpartida, Equipo[] in_Equipos, DaoJugador in_DaoJ,DaoBase in_DaoB

		DaoEquipo auxDaoEquipo = new DaoEquipo(in_DaoEquipo.getIdpartida(),
				devolverArreEquipoDadoVO(in_DaoEquipo.getequipos()), devolverDaoJugadorDadoVO(in_DaoEquipo.getDaoJ()),
				devolverDaoBaseDadoVO(in_DaoEquipo.getDaoB()));

		return auxDaoEquipo;

	}

	private Equipo[] devolverArreEquipoDadoVO(VOEquipo[] in_voEquipo) {

		Equipo[] aux = new Equipo[in_voEquipo.length];

		for (int i = 0; i < in_voEquipo.length; i++) {
			aux[i] = devolverEquipoDadoVO(in_voEquipo[i]);
		}

		return aux;

	}

	private Equipo devolverEquipoDadoVO(VOEquipo in_voEquipo) {
		// int in_equipoID, Jugador[] in_Jugadores, Base in_base, String in_bando
		Equipo out = new Equipo(in_voEquipo.getEquipoID(), devolverArreJugadorDadoVO(in_voEquipo.getJugadores()),

				devolverBaseDadoVO(in_voEquipo.getBase()),

				in_voEquipo.getBando());

		return out;

	}

	private Base devolverBaseDadoVO(VOBase in_Base) {

		Base auxBase = new Base(in_Base.getIdBase(), devolverDaoAvionesDadoVO(in_Base.getAviones()),
				devolverDaoArtilleriaDadoVO(in_Base.getArtilleros()), devolverDepositoDadoVO(in_Base.getDeposito()),
				devolverTanqueCombustibleDadoVO(in_Base.getTanque()), devolverTorreControlDadoVO(in_Base.getTorre()));
		return auxBase;

	}

	private DaoDeAviones devolverDaoAvionesDadoVO(VOCollectionAviones in_DaoAviones) {

		DaoDeAviones auxDaoAviones = new DaoDeAviones(in_DaoAviones.getBaseId());
		int max = in_DaoAviones.getArreavion().length;
		Avion[] aviones = new Avion[max];
		VOAvion[] voAviones = in_DaoAviones.getArreavion();
		for (int i = 0; i < max; i++) {
			// int in_PK_avion_id, int in_avionCoordX, int in_avionCoordY,int in_avionCoordZ
			// ,boolean in_estado, int in_vida,boolean in_hayEnemigo,
			// int in_rangoDeVision ,boolean in_avionBomba,int in_cantidadBombas, int
			// in_avionCombustible,boolean in_enCampoEnemigo,int in_baseid)
			Avion av = new Avion(voAviones[i].GetId(), voAviones[i].getCoordX(), voAviones[i].getCoordY(),
					voAviones[i].getAvionAltura(), voAviones[i].getEstado(), voAviones[i].getVida(),
					voAviones[i].getHayEnemigo(), voAviones[i].getRangoDeVision(), voAviones[i].getAvionBomba(),
					voAviones[i].getCantidadBombas(), voAviones[i].getAvionCombustible(),
					voAviones[i].getEnCampoEnemigo(), voAviones[i].getBaseid());
			aviones[i] = av;
		}
		auxDaoAviones.setArreAviones(aviones);

		return auxDaoAviones;
	}

	private DaoArtilleria devolverDaoArtilleriaDadoVO(VOCollectionArtilleria in_DaoArtilleria) {

		// int in_id, int in_coordX, int in_coordY,
		// boolean in_estado, int in_vida, boolean in_hayEnemigo,
		// int in_rangoDeVision, int in_ArtilleroAngulo,int in_base_id
		//
		DaoArtilleria auxDaoArtilleria = new DaoArtilleria(in_DaoArtilleria.getBaseid());
		int max = in_DaoArtilleria.getSecuenciaArtilleria().length;
		Artillero[] artillerias = new Artillero[max];
		VOArtillero[] VOartillerias = in_DaoArtilleria.getSecuenciaArtilleria();

		for (int i = 0; i < max; i++) {
			Artillero ar = new Artillero(VOartillerias[i].GetId(), VOartillerias[i].getCoordX(),
					VOartillerias[i].getCoordY(), VOartillerias[i].getEstado(), VOartillerias[i].getVida(),
					VOartillerias[i].getHayEnemigo(), VOartillerias[i].getRangoDeVision(),
					VOartillerias[i].getArtilleroAngulo(), VOartillerias[i].getbase_id());
			artillerias[i] = ar;
		}

		auxDaoArtilleria.setArreArtilleria(artillerias);
		return auxDaoArtilleria;

	}

	private Deposito devolverDepositoDadoVO(VODeposito in_Deposito) {
		// int in_id, int in_coordX, int in_coordY, boolean in_estado, int in_vida,int
		// in_cantidadBombas,boolean in_enUso) {
		Deposito depaux = new Deposito(in_Deposito.GetId(), in_Deposito.getCoordX(), in_Deposito.getCoordY(),
				in_Deposito.getEstado(), in_Deposito.getVida(), in_Deposito.getCantidaBombas(), in_Deposito.getEnUso());

		return depaux;
	}

	private TanqueCombustible devolverTanqueCombustibleDadoVO(VOTanqueCombustible in_TanqueCombustible) {
		// int in_id, int in_coordX, int in_coordY, boolean in_estado, int in_vida, int
		// in_cantidadCombustible, boolean in_enUso

		TanqueCombustible auxDepTanque = new TanqueCombustible(in_TanqueCombustible.GetId(),
				in_TanqueCombustible.getCoordX(), in_TanqueCombustible.getCoordY(), in_TanqueCombustible.getEstado(),
				in_TanqueCombustible.getVida(), in_TanqueCombustible.getCantidadCombustible(),
				in_TanqueCombustible.getEnUso());
		return auxDepTanque;
	}

	private TorreControl devolverTorreControlDadoVO(VOTorreControl in_TorreControl) { // System.out.println("devolverTorreControlDadoVO
																						// 355");
																						// int in_id, int in_coordX, int
																						// in_coordY, boolean in_estado,
																						// int in_vida,boolean
																						// in_hayEnemigo, int
																						// in_rangoDeVision
		TorreControl auxTorreControl = new TorreControl(in_TorreControl.GetId(), in_TorreControl.getCoordX(),
				in_TorreControl.getCoordY(), in_TorreControl.getEstado(), in_TorreControl.getVida(),
				in_TorreControl.getHayEnemigo(), in_TorreControl.getRangoDeVision());
		return auxTorreControl;
	}

	private Jugador[] devolverArreJugadorDadoVO(VOJugador[] in_Jugador) {
		Jugador[] aux = new Jugador[in_Jugador.length];

		for (int i = 0; i < in_Jugador.length; i++) {
			aux[i] = devolverJugadordadoVO(in_Jugador[i]);
		}

		return aux;

	}

	private Jugador devolverJugadordadoVO(VOJugador in_aux) {

		Jugador out_aux = new Jugador(in_aux.getJugadorId(), in_aux.getJugadorUserName(), in_aux.getJugadorPassword(),
				in_aux.isJugadorIsOnline(), in_aux.getPuntajeAcumulado());
		return out_aux;

	}

	private DaoJugador devolverDaoJugadorDadoVO(VOCollectionJugador in_DaoJugador) {

		DaoJugador daoJugador = new DaoJugador();
		TreeMap<Integer, Jugador> aux = new TreeMap<Integer, Jugador>();

		Iterator<VOJugador> Itr = in_DaoJugador.listarJugadores().values().iterator();
		while (Itr.hasNext()) {
			VOJugador auxiliar = Itr.next();
			aux.put(devolverJugadordadoVO(auxiliar).getJugadorId(), devolverJugadordadoVO(auxiliar));

		}

		daoJugador.setJugadores(aux);
		return daoJugador;

	}

	private DaoBase devolverDaoBaseDadoVO(VOCollectionBase in_DaoBase) {
		DaoBase daoBases = new DaoBase();
		TreeMap<Integer, Base> aux = new TreeMap<Integer, Base>();

		Iterator<VOBase> Itr = in_DaoBase.getBases().values().iterator();
		while (Itr.hasNext()) {
			VOBase auxiliar = Itr.next();
			aux.put(devolverBaseDadoVO(auxiliar).getIdBase(), devolverBaseDadoVO(auxiliar));
		}
		daoBases.setBases(aux);
		return daoBases;

	}

	/// FIN FUNCIONES AUXILIARES PRIVADAS UTILIZADAS PARA EL REQUERIMIENTO GUARDAR
	/// PARTIDA, EN ORDEN DE EJECUCION

	/// INICIO FUNCIONES AUXILIARES PRIVADAS UTILIZADAS PARA EL REQUERIMIENTO
	/// REANUDAR PARTIDA, EN ORDEN DE EJECUCION

	private VOPartida DevolverVoPartidaDadoPartida(Partida in_Partida, IConexion con) throws PersistenciaException {
		VOPartida out = null;

		out = new VOPartida(in_Partida.getPartidaId(), in_Partida.getPartidaEstado(),
				in_Partida.getPartidaFechaUltimaActualizacion(), in_Partida.isPartidaGuardada(),
				in_Partida.getPartidaNombre(), in_Partida.getPartidaCantidadJugadores(), in_Partida.getPartidaCreador(),
				in_Partida.getPartidaFechaCreada(), in_Partida.getPartidaTermino(),
				DevolverVOCollectionEquiposDesdeEquipos(in_Partida.getEquipos(), con)); // llamar a

		return out;

	}

	private VOCollectionEquipo DevolverVOCollectionEquiposDesdeEquipos(DaoEquipo in_Equipos, IConexion con)
			throws PersistenciaException {

		VOCollectionEquipo out = null;
		out = new VOCollectionEquipo(in_Equipos.getIdpartida(),

				devolverArreVOEquipoDadoEquipo(in_Equipos.getEquiposEnMemoria()),
				devolverColletionJugadorDadoDao(in_Equipos.getDaoJugador()),
				devolverVOCollectionBaseDadoDao(in_Equipos.getDaoBase()));

		return out;

	}

	private VOEquipo[] devolverArreVOEquipoDadoEquipo(Equipo[] in_Equipo) throws PersistenciaException {

		VOEquipo[] aux = new VOEquipo[in_Equipo.length];

		for (int i = 0; i < in_Equipo.length; i++) {
			aux[i] = DevolverVoEquipoDadoEquipo(in_Equipo[i]);

		}
		return aux;

	}

	private VOEquipo DevolverVoEquipoDadoEquipo(Equipo in_Equipo) throws PersistenciaException { // int in_equipoID,
																									// VOJugador[]
																									// in_Jugadores,
																									// VOBase in_base,
																									// String in_bando

		VOEquipo out = new VOEquipo(in_Equipo.getEquipoID(),
				DevolverArreVoJugadorDadoJugadores(in_Equipo.getJugadores()),
				DevolverVOBaseDadoBase(in_Equipo.getBase()), in_Equipo.getBando());
		System.out.println("Equipo" + out.getEquipoID());

		return out;

	}

	private VOJugador[] DevolverArreVoJugadorDadoJugadores(Jugador[] in_jugador) throws PersistenciaException { // t
																												// in_JugadorID,
																												// String
																												// in_JugadorUserName,
																												// String
																												// in_JugadorPassword,
																												// boolean
																												// in_JugadorIsOnline,
																												// int
																												// in_PuntajeAcumulado

		int tam = 0;
		tam = in_jugador.length;

		VOJugador[] out = new VOJugador[tam];
		for (int i = 0; i < tam; i++) {

			VOJugador auxiliar = new VOJugador(in_jugador[i].getJugadorId(), in_jugador[i].getJugadorUserName(),
					in_jugador[i].getJugadorPassword(), in_jugador[i].isJugadorIsOnline(),
					in_jugador[i].getPuntajeAcumulado());

			out[i] = auxiliar;
		}

		return out;
	}

	private VOBase DevolverVOBaseDadoBase(Base b) throws PersistenciaException {

		VOBase out = null;
		out = new VOBase(b.getIdBase(), DevolverColeccionDeAvionesdesdeAviones(b.getAviones()),
				DevolverColeccionDeArtillerodeArtillero(b.getArtilleros()),
				DevolverVODepositoDadoDeposito(b.getDeposito()), DevolverVOTanqueCombusatibleDadoTanque(b.getTanque()),
				DevolverVOTorreDadoTorre(b.getTorre()));

		return out;

	}

	private VOCollectionAviones DevolverColeccionDeAvionesdesdeAviones(DaoDeAviones aviones)
			throws PersistenciaException {
		VOCollectionAviones out = new VOCollectionAviones();
		Avion[] arreAV = null;
		arreAV = aviones.getArreAvionesEnMemoria();
		for (int i = 0; i < arreAV.length; i++) {
			VOAvion avionaux = DevolverVOAvionesDadoAvion(arreAV[i]);
			out.insback(avionaux);
		}

		return out;
	}

	private VOAvion DevolverVOAvionesDadoAvion(Avion a) {
		VOAvion out = null;

		out = new VOAvion(a.GetId(), a.getCoordX(), a.getCoordY(), a.getAvionAltura(), a.getEstado(), a.getVida(),
				a.getHayEnemigo(), a.getRangoDeVision(), a.getAvionBomba(), a.getCantidadBombas(),
				a.getAvionCombustible(), a.getEnCampoEnemigo(), a.getBaseid());
		return out;
	}

	private VOCollectionArtilleria DevolverColeccionDeArtillerodeArtillero(DaoArtilleria artilleria)
			throws PersistenciaException {

		// Aca viene la base
		VOCollectionArtilleria out = new VOCollectionArtilleria();
		Artillero[] arreAr = null;
		arreAr = artilleria.getArreArtilleriaEnMemoria();
		for (int i = 0; i < arreAr.length; i++) {
			arreAr[i].setbase_id(artilleria.getBaseid());
			VOArtillero artilleriaaux = DevolverVOArtillerosDadoArtillero(arreAr[i]);

			out.insBack(artilleriaaux);
		}
		return out;
	}

	private VOArtillero DevolverVOArtillerosDadoArtillero(Artillero a) {
		VOArtillero out = null;
		out = new VOArtillero(a.GetId(), a.getCoordX(), a.getCoordY(), a.getEstado(), a.getVida(), a.getHayEnemigo(),
				a.getRangoDeVision(), a.getArtilleroAngulo(), a.getbase_id());

		return out;
	}

	private VODeposito DevolverVODepositoDadoDeposito(Deposito t) {
		VODeposito out = null;
		out = new VODeposito(t.GetId(), t.getCoordX(), t.getCoordY(), t.getEstado(), t.getVida(), t.getCantidaBombas(),
				t.getEnUso());
		return out;
	}

	private VOTanqueCombustible DevolverVOTanqueCombusatibleDadoTanque(TanqueCombustible t) {
		VOTanqueCombustible out = null;
		out = new VOTanqueCombustible(t.GetId(), t.getCoordX(), t.getCoordY(), t.getEstado(), t.getVida(),
				t.getCantidadCombustible(), t.getEnUso());
		return out;
	}

	private VOTorreControl DevolverVOTorreDadoTorre(TorreControl t) {
		VOTorreControl out = null;
		out = new VOTorreControl(t.GetId(), t.getCoordX(), t.getCoordY(), t.getEstado(), t.getVida(), t.getHayEnemigo(),
				t.getRangoDeVision());
		return out;

	}

	private VOCollectionJugador devolverColletionJugadorDadoDao(DaoJugador in_DaoJugador) throws PersistenciaException {
		VOCollectionJugador daoJugador = new VOCollectionJugador();
		TreeMap<Integer, VOJugador> aux = new TreeMap<Integer, VOJugador>();
		Iterator<Jugador> Itr = in_DaoJugador.getJugadores().values().iterator();

		while (Itr.hasNext()) {
			Jugador auxiliar = Itr.next();
			aux.put(devolverVOJugador(auxiliar).getJugadorId(), devolverVOJugador(auxiliar));

		}

		daoJugador.setJugadores(aux);

		return daoJugador;

	}

	private VOJugador devolverVOJugador(Jugador in_aux) {

		VOJugador out_aux = null;
		out_aux = new VOJugador(in_aux.getJugadorId(), in_aux.getJugadorUserName(), in_aux.getJugadorPassword(),
				in_aux.isJugadorIsOnline(), in_aux.getPuntajeAcumulado());

		return out_aux;

	}

	private VOCollectionBase devolverVOCollectionBaseDadoDao(DaoBase in_DaoBase) throws PersistenciaException {

		VOCollectionBase daoBases = new VOCollectionBase();
		TreeMap<Integer, VOBase> aux = new TreeMap<Integer, VOBase>();

		Iterator<Base> Itr = in_DaoBase.getBases().values().iterator();
		while (Itr.hasNext()) {
			Base auxiliar = Itr.next();
			aux.put(DevolverVOBaseDadoBase(auxiliar).getIdBase(), DevolverVOBaseDadoBase(auxiliar));
		}
		daoBases.setBases(aux);
		return daoBases;

	}

	/// FIN FUNCIONES AUXILIARES PRIVADAS UTILIZADAS PARA EL REQUERIMIENTO REANUDAR
	/// PARTIDA, EN ORDEN DE EJECUCION

}
