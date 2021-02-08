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
import Utilitarios.*;
import Logica.*;
import Logica.Excepciones.PersistenciaException;
import Logica.Interfaz.IPoolConexiones;
import Logica.Vo.VOJugador;


public abstract class Fachada extends UnicastRemoteObject implements IFachada {
	private static Fachada instancia;
	private IDaoJugador daoN;
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
			daoN = fabrica.crearIDaoNinios();
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

	public void nuevoJugador(VOJugador voN) throws PersistenciaException, LogicaException {
		System.out.println(" ipool.obtenerConexion(true) Dentro de fachada nuevo ninio");
		IConexion icon = ipool.obtenerConexion(true);
		System.out.println("despues de  ipool.obtenerConexion(true) Dentro de fachada nuevo ninio");
		int email = voN.getemail();
		try {
			if (daoN.member(email, icon)) {
				//// cuando se llama a la capa de persistencia, hay que encerrar en try para
				//// capturar y liberar
				ipool.liberarConexion(icon, false);
				throw new LogicaException(mensg.errorFachadaYaExisteNinio);
			} else {
				String apellido = voN.getApellido();
				String nombre = voN.getNombre();
				Ninio nin = new Ninio(email, nombre, apellido);
				System.out.println(nin.getNombre());
				daoN.insert(nin, icon);
				ipool.liberarConexion(icon, true);
			}
		} catch (Exception e) {
			ipool.liberarConexion(icon, false);
			throw new LogicaException(mensg.errorFachadaNuevoNinio);
		}

	}

	
	}

	public List<VOJugador> listarJugadores() throws RemoteException, PersistenciaException, LogicaException {
		IConexion icon = ipool.obtenerConexion(false);
		List<voNinio> ninios = null;
		try {
			ninios = daoN.listarNinios(icon);
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
			if (daoN.member(email, icon)) {
				Ninio auxNinio = daoN.find(email, icon);
				// if(auxNinio.tieneJuguete(email, icon))//AC� SE ROMPIA POR ESO LO SAQUE
				// {
				auxNinio.borrarJuguetes(icon);
				// }
				daoN.delete(email, icon);
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
