package logica;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import com.mysql.jdbc.Connection;

import persistencia.baseDeDatos.consultas.consultas;
import persistencia.baseDeDatos.daos.DaoArtilleria;
import persistencia.baseDeDatos.daos.DaoBase;
import persistencia.baseDeDatos.daos.DaoDeAviones;
import persistencia.baseDeDatos.daos.DaoEquipo;
import persistencia.baseDeDatos.daos.DaoJugador;
import persistencia.baseDeDatos.daos.DaoPartidas;
import persistencia.baseDeDatos.poolDeConexiones.Conexion;
import persistencia.baseDeDatos.poolDeConexiones.IConexion;
import persistencia.baseDeDatos.poolDeConexiones.PoolConexiones;
import persistencia.excepciones.PersistenciaException;
import Utilitarios.*;
import logica.IFachada;
import logica.excepciones.*;
import logica.interfaces.IPoolConexiones;
import logica.valueObjects.*;

public class Fachada extends UnicastRemoteObject implements IFachada {
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
			System.out.println(poolConcreto + "*************#########*********");
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

	public void registrarJugador(VOJugador in_voJug) throws PersistenciaException, LogicaException {
		IConexion icon = ipool.obtenerConexion(true);

		try {
			String userName = in_voJug.getJugadorUserName();

			if (daoJ.member(userName, icon)) {

				ipool.liberarConexion(icon, false);
				throw new LogicaException(mensg.errorFachadaYaExisteUsuario);
			} else {

				int ultimoId = daoJ.getUltimoJugadorID(icon);
				Jugador jug = new Jugador(ultimoId++, in_voJug.getJugadorUserName(), in_voJug.getJugadorPassword(),
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
			throws RemoteException, LogicaException, PersistenciaException {
		IConexion icon = ipool.obtenerConexion(true);
		VOJugador out_Voj = null;

		try {
			if (daoJ.member(in_userName, icon)) {

				int id = daoJ.geIdbyName(in_userName, icon);
				Jugador JuG = daoJ.find(id, icon);
				if (JuG.getJugadorPassword() == in_userPassword) {
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
	public TreeMap<Integer, Partida> listarPartidasAReanudar(String in_Nickname)
			throws PersistenciaException, LogicaException {
		IConexion icon = ipool.obtenerConexion(false);
		TreeMap<Integer, Partida> listarPartidasDelJugador = null;

		try {
			if (daoJ.member(in_Nickname, icon)) {

				if (!daoP.estaVacio(icon)) {

					int id = daoJ.geIdbyName(in_Nickname, icon);
					listarPartidasDelJugador = daoP.listarPartidasDeJugador(id, icon);
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
		return listarPartidasDelJugador;
	}

	public void guardarPartida(voPartida in_voPartida) throws LogicaException {

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
			int idbase = daoB.getUltimaIsBase(icon);
			idbase++;
			for (int i = 0; i < largoArreglo; i++) {

				daoE.insBack(idpartida, auxEquipo[i], icon);
				Base auxBase = auxEquipo[i].getBase();

				daoB.insert(idbase, auxEquipo[i].getEquipoID(), icon);

				Avion[] auxAviones = auxBase.getAviones().listarAviones(icon);
				int largoAviones = auxAviones.length;
				for (int j = 0; i < largoAviones; j++) {
					daoAvion.insback(idbase, auxAviones[j], icon);
				}
				Artillero[] auxArtilleria = auxBase.getArtilleros().listarArtilleria(icon);
				int largoArtillero = auxArtilleria.length;
				for (int x = 0; i < largoArtillero; x++) {
					daoArti.insBack(idbase, auxArtilleria[x], icon);
				}
				idbase++;
				icon = ipool.obtenerConexion(true);
			}
		} catch (PersistenciaException e) {
			ipool.liberarConexion(icon, false);
			throw new LogicaException(mensg.errorFachadaListPartidas);
		}

	}

	// precondicion jugador logueado
	public void logout(String in_userName) throws LogicaException {
		IConexion icon = ipool.obtenerConexion(true);
		try {
			daoJ.logoutJugador(in_userName, icon); // ver linea 232

			ipool.liberarConexion(icon, true);
		} catch (PersistenciaException e) {
			ipool.liberarConexion(icon, false);

			throw new LogicaException(mensg.errorFachadaAlHacerLogout);
		}
	}

	public boolean jugadorIsOnline(String in_name) throws LogicaException {
		IConexion icon = ipool.obtenerConexion(true);
		boolean out_es = false;

		try {
			out_es = daoJ.estaOnline(in_name, icon);
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

	private Partida devolverPartidaDadoVO(voPartida in_aux, int in_ultimoIdPartida) {
		Partida out_aux = null;

		out_aux = new Partida(in_ultimoIdPartida, in_aux.getPartidaEstado(),
				in_aux.getPartidaFechaUltimaActualizacion(), in_aux.isPartidaGuardada(), in_aux.getGanadorEquipoID(),
				in_aux.getPartidaNombre(), in_aux.getPartidaCantidadJugadores(), in_aux.getPartidaCreador(),
				in_aux.getPartidaFechaCreada(), in_aux.getEquipos());

		return out_aux;

	}

}
