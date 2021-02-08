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
				//// cuando se llama a la capa de persistencia, hay que encerrar en try para
				//// capturar y liberar
				ipool.liberarConexion(icon, false);
				throw new LogicaException(mensg.errorFachadaYaExisteUsuario);
			} else {
				String apellido = voJug.getApellido();
				String nombre = voJug.getNombre();
				Ninio nin = new Ninio(email, nombre, apellido);
				System.out.println(nin.getNombre());
				daoJug.insert(nin, icon);
				ipool.liberarConexion(icon, true);
			}
		} catch (Exception e) {
			ipool.liberarConexion(icon, false);
			throw new LogicaException(mensg.errorFachadaNuevoJuginio);
		}

	}

	
	}

	public List<VOJugador> listarJugadores() throws RemoteException, PersistenciaException, LogicaException {
		IConexion icon = ipool.obtenerConexion(false);
		List<voJuginio> ninios = null;
		try {
			ninios = daoJug.listarJugadores(icon);
		} catch (Exception e) {
			throw new LogicaException(mensg.errorFachadaListNinios);
		} finally {
			ipool.liberarConexion(icon, true);
		}
		return ninios;
	}

	

	public void borrarJugadores(String email) throws RemoteException, LogicaException, PersistenciaException {
		IConexion icon = ipool.obtenerConexion(true);
		try {
			if (daoJug.member(email, icon)) {
				Ninio auxNinio = daoJug.find(email, icon);
				// if(auxNinio.tieneJuguete(email, icon))//AC� SE ROMPIA POR ESO LO SAQUE
				// {
				auxNinio.borrarJuguetes(icon);
				// }
				daoJug.delete(email, icon);
				ipool.liberarConexion(icon, true);
			} else {
				ipool.liberarConexion(icon, false);
				throw new LogicaException(mensg.errorFachadaNoExisteNinio);
			}
		} catch (Exception e) {
			ipool.liberarConexion(icon, false);
			throw new LogicaException(mensg.errorFachadaDeleteJuguetesByCINinio);
		}
	}

}
