package Logica;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import Logica.Excepciones.LogicaException;
import Logica.Excepciones.PersistenciaException;
import Logica.Vo.VOJugador;

public interface IFachada extends Remote{
	
	
	public void nuevoJugador (VOJugador voN) throws LogicaException, PersistenciaException, RemoteException;
	
	
	public List<VOJugador> listarJugadores () throws PersistenciaException, RemoteException, LogicaException;
	
	
	public void borrarJugador (String email) throws LogicaException, PersistenciaException, RemoteException;
	

	public Jugador findJugador (String email) throws LogicaException , RemoteException;
	public String darNombre(String email) throws LogicaException, RemoteException;
	public boolean userRegistrado (String email) throws LogicaException, RemoteException;
	public boolean validarCuenta(String mail, String pass) throws LogicaException, RemoteException;

}
