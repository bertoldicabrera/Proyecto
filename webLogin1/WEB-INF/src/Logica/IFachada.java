package Logica;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import Logica.*;
import Logica.Excepciones.LogicaException;
import Logica.Excepciones.PersistenciaException;
import Logica.Vo.VOJugador;
import Persistencia.*;

public interface IFachada extends Remote{
	
	
	public void nuevoJugador (VOJugador voN) throws LogicaException, PersistenciaException, RemoteException;
	
	
	public List<VOJugador> listarJugadores () throws PersistenciaException, RemoteException, LogicaException;
	
	
	public void borrarJugador (String email) throws LogicaException, PersistenciaException, RemoteException;
	
	public boolean cuentaValida (String email, String password) throws LogicaException;

	public Jugador findJugador (String email) throws LogicaException;
	public String darNombre(String email) throws LogicaException;
	public boolean userRegistrado (String email) throws LogicaException;

}
