package logica;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
import java.util.TreeMap;

import logica.excepciones.*;
import logica.valueObjects.*;
import persistencia.excepciones.PersistenciaException;

public interface IFachada extends Remote{
	
	public void registrarJugador(VOJugador in_voJug) throws PersistenciaException, LogicaException;

	public VOJugador Login(String in_userName,String in_userPassword) throws RemoteException, LogicaException, PersistenciaException;
	public TreeMap<Integer, Partida> listarPartidasAReanudar(String in_Nickname) throws PersistenciaException, LogicaException;
	public void guardarPartida (voPartida in_voPartida) throws LogicaException;
	
}
