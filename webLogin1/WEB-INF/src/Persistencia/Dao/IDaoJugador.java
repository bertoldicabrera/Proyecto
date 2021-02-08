package Persistencia.Dao;

import java.io.FileNotFoundException;
import java.io.*;
import java.sql.*;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

import Logica.*;
import Logica.Vo.VOJugador;
import Persistencia.*;


public interface IDaoJugador  {
	

	 public void Connect() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException;
	
	 public void disconnect() throws SQLException, ClassNotFoundException;
	
	 public boolean isAcountExists(String email, String password) throws SQLException;
	
	 public boolean isEmailRegistered(String email) throws SQLException;
	
	 public void registerUser(String email, String password, String name) throws SQLException;
	 
	 public String getNameByEmail(String email) throws SQLException;
	 
	 public ArrayList<VOJugador> allUsers() throws SQLException;
	 
	

}
