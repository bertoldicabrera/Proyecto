package Logica;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import Persistencia.*;
import Persistencia.Dao.IDaoJugador;
import Persistencia.Factory.IFabricaAbstracta;
import Persistencia.Poll.IConexion;
import Utilitarios.*;
import Logica.*;
import Logica.Excepciones.LogicaException;
import Logica.Excepciones.PersistenciaException;
import Logica.Interfaz.IPoolConexiones;
import Logica.Vo.VOJugador;


public class Fachada extends UnicastRemoteObject implements IFachada {
	private static final long serialVersionUID = 1L;
	private static Fachada instancia;
	private IDaoJugador daoJug;
	private IPoolConexiones ipool;
	private SystemProperties sp;
	private IFabricaAbstracta fabrica;
	public static MensajesPersonalizados mensg = new MensajesPersonalizados();

	public Fachada() throws RemoteException, PersistenciaException {
		try {
			sp = new SystemProperties();
			String poolConcreto = sp.getpool_className();
			System.out.println(poolConcreto + "*************#########*********");
			ipool = (IPoolConexiones) Class.forName(poolConcreto.trim()).newInstance();

			String nomFab = sp.getFabricaAbstracta();
			fabrica = (IFabricaAbstracta) Class.forName(nomFab.trim()).newInstance();
			daoJug = fabrica.crearIDaoJugador();
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

	public void nuevoJugador(VOJugador voJug) throws PersistenciaException, LogicaException {
		IConexion icon = ipool.obtenerConexion(true);
		String email = voJug.GetEmail();
		try {
			if (daoJug.member(email, icon)) {
				
				ipool.liberarConexion(icon, false);
				throw new LogicaException(mensg.errorFachadaYaExisteUsuario);
			} else {
				String password = voJug.GetPassword();
				String nombre = voJug.GetName();
				
				Jugador nin = new Jugador(nombre,email,0, password);
				
				daoJug.insert(nin, icon);
				ipool.liberarConexion(icon, true);
			}
		} catch (Exception e) {
			ipool.liberarConexion(icon, false);
			throw new LogicaException(mensg.errorFachadaNuevoUsuario);
		}

	}


	public List<VOJugador> listarJugadores() throws RemoteException, PersistenciaException, LogicaException {
		IConexion icon = ipool.obtenerConexion(false);
		List<VOJugador> jug = null;
		try {
			jug = daoJug.listarJugadores(icon);
		} catch (Exception e) {
			throw new LogicaException(mensg.errorFachadaListUsuarios);
		} finally {
			ipool.liberarConexion(icon, true);
		}
		return jug;
	}

	

	public void borrarJugador(String email) throws RemoteException, LogicaException, PersistenciaException {
		IConexion icon = ipool.obtenerConexion(true);
		try {
			if (daoJug.member(email, icon)) {
				daoJug.delete(email, icon);
				ipool.liberarConexion(icon, true);
			} else {
				ipool.liberarConexion(icon, false);
				throw new LogicaException(mensg.errorFachadaNoExisteUsuario);
			}
		} catch (Exception e) {
			ipool.liberarConexion(icon, false);
			throw new LogicaException(mensg.errorFachadaDeleteUsuario);
		}
	}

	

}
