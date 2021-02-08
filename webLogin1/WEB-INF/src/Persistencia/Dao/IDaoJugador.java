package Persistencia.Dao;


import java.sql.SQLException;
import java.util.List;

import Logica.Jugador;
import Logica.Excepciones.PersistenciaException;
import Logica.Vo.VOJugador;

import Persistencia.Poll.IConexion;


public interface IDaoJugador  {
	
	//Con el email nos fijamos si existe isEmailRegistered
	public boolean member (String email, IConexion con) throws PersistenciaException;
		
	//public jugador find 
	public Jugador find (String email, IConexion con) throws PersistenciaException;
		
	//public void insert  jugador
	public void insert (Jugador jug, IConexion con) throws PersistenciaException;
		
	//public void delete 
	public void delete(String email, IConexion con) throws PersistenciaException;
		
	//public Listar jugadores
	public List <VOJugador> listarJugadores(IConexion con) throws PersistenciaException;

	public boolean isAcountExists(String email, String password) throws SQLException;
	
	 
	public String getNameByEmail(String email) throws SQLException;
	
	
}
