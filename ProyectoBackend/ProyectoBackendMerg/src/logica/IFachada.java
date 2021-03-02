package logica;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.TreeMap;

import logica.excepciones.*;
import logica.valueObjects.*;
import persistencia.excepciones.PersistenciaException;

public interface IFachada extends Remote{
	
	public void registrarJugador(VOJugador in_voJug) throws PersistenciaException, LogicaException, RemoteException , InterruptedException;
	
	public VOJugador Login(String in_userName, String in_userPassword)throws RemoteException, LogicaException, PersistenciaException , InterruptedException;
	
	public ArrayList<voPartida> listarPartidasAReanudar(String in_Nickname)throws PersistenciaException, LogicaException, RemoteException, InterruptedException;
	
	public void guardarPartida(voPartida in_voPartida) throws LogicaException, RemoteException, InterruptedException, PersistenciaException;
	
	public void logout(String in_userName)throws LogicaException, RemoteException, InterruptedException, PersistenciaException;
	
	public boolean jugadorIsOnline(String in_name) throws LogicaException, RemoteException, InterruptedException, PersistenciaException;
	
	public String verConexion(String x) throws RemoteException, InterruptedException;
	
}
