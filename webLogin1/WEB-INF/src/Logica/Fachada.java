package Logica;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;


import Persistencia.Dao.IDaoJugador;
import Persistencia.Factory.IFabricaAbstracta;
import Persistencia.Poll.IConexion;
import Utilitarios.*;
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
			System.out.println(" Linea 37 fachada");
			String poolConcreto = sp.getpool_className();
			System.out.println(poolConcreto + "*************#########*********");
			
			ipool = (IPoolConexiones) Class.forName(poolConcreto.trim()).newInstance();
			System.out.println("*************#####2####*********");
			String nomFab = sp.getFabricaAbstracta();
			System.out.println("*************#####3####*********");
			fabrica = (IFabricaAbstracta) Class.forName(nomFab.trim()).newInstance();
			System.out.println("*************#####4####*********");
			daoJug = fabrica.crearIDaoJugador();
			System.out.println("*************#####5####*********");
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
		
		try {
			String email = voJug.GetEmail();
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
			ipool.liberarConexion(icon, true);
			
		} catch (Exception e) {
			throw new LogicaException(mensg.errorFachadaListUsuarios);
		} finally {
			ipool.liberarConexion(icon, true);
		}
		return jug;
	}

	

	public void borrarJugador(String email) throws  LogicaException, PersistenciaException {
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


	
	
	public Jugador findJugador (String email) throws LogicaException
	{
		IConexion icon = ipool.obtenerConexion(false);
		Jugador auxJug=null;
		try {
			auxJug=daoJug.find(email, icon);
			ipool.liberarConexion(icon, true);
		} catch (PersistenciaException e) {
			ipool.liberarConexion(icon, true);
			throw new LogicaException(mensg.errorFachadaListUsuarios);
		}
		return auxJug;
	}
	
	
	public String darNombre(String email) throws LogicaException
	{
		IConexion icon = ipool.obtenerConexion(false);
		String nombre=null;
		try {
			nombre= daoJug.getNameByEmail(nombre, icon);
			ipool.liberarConexion(icon, true);
		} catch (PersistenciaException e) {
			ipool.liberarConexion(icon, true);
			throw new LogicaException(mensg.errorFachadaListUsuarios);
		}
		return nombre;
	}

	@Override
	public boolean userRegistrado(String email) throws LogicaException {
		IConexion icon = ipool.obtenerConexion(false);
		boolean existe=false;
		try {
			existe=daoJug.member(email, icon);
			ipool.liberarConexion(icon, true);
		} catch (PersistenciaException e) {
			ipool.liberarConexion(icon, true);
			throw new LogicaException(mensg.errorFachadaListUsuarios);
		}
		
		return existe;
	}

	@Override
	public boolean validarCuenta(String mail, String pass) throws LogicaException {
		IConexion icon = ipool.obtenerConexion(false);
		boolean esValida=false;
		
		try {
			esValida=daoJug.isAcountExists(mail, pass, icon);
		} catch (PersistenciaException e) {
			ipool.liberarConexion(icon, true);
			throw new LogicaException(mensg.errorFachadaListUsuarios);
		}
		
		return esValida;
	}
	

}
