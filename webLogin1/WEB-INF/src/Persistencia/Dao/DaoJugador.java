package Persistencia.Dao;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Logica.Jugador;
import Logica.Excepciones.PersistenciaException;
import Logica.Vo.VOJugador;
import Persistencia.Consultas.consultas;
import Persistencia.Pool.Conexion;
import Persistencia.Pool.IConexion;
import Utilitarios.MensajesPersonalizados;
public class DaoJugador implements IDaoJugador, Serializable {
	//Data Access Object



private static final long serialVersionUID = 1L;

public static MensajesPersonalizados mensg = new MensajesPersonalizados();
public DaoJugador(){

}
@Override
public boolean member(String email, IConexion con) throws PersistenciaException {
	boolean existe = false;
	try{
		consultas cons = new consultas();
		String query = cons.existsEmail();
		PreparedStatement pstmt = ((Conexion) con).getConnection().prepareStatement (query);
		pstmt.setString(1, email); 
		ResultSet rs = pstmt.executeQuery ();
		if (rs.next ())
			existe = true;
		rs.close ();
		pstmt.close ();
	}catch (SQLException e){
		throw new PersistenciaException (mensg.errorSQLFindUsuario);
	}
	return existe;
}

@Override
public Jugador find(String email, IConexion con) throws PersistenciaException {
	Jugador jug = null;
	int id= 0;
	String nombre= "";
	String password="";
	try{
		consultas cons = new consultas ();
	
		String queryNin = cons.obtenerJugador();
		PreparedStatement pstmt1 = ((Conexion) con).getConnection().prepareStatement (queryNin);
		pstmt1.setString(1, email);
		ResultSet rs1 = pstmt1.executeQuery ();
		if (rs1.next ()){
			id = rs1.getInt(1);
			nombre = rs1.getString(2);
			password = rs1.getString(3);
		}
		rs1.close ();
		pstmt1.close ();
		jug = new Jugador (nombre, email, id, password);
		}
	catch (SQLException e){
		throw new PersistenciaException (mensg.errorSQLFindUsuario);
	}
	return jug;
}


@Override
public void insert(Jugador jug, IConexion con) throws PersistenciaException {
	try{
		consultas cons = new consultas();
		String insert = cons.insertUser();
		PreparedStatement pstmt = ((Conexion) con).getConnection().prepareStatement (insert);
		pstmt.setString(1, jug.getEmail());
		pstmt.setString (2, jug.getPassword());
		pstmt.setString (3, jug.getUserName());
		pstmt.executeUpdate ();
		pstmt.close ();
	}
	catch (SQLException e)
	{
	throw new PersistenciaException (mensg.errorSQLInsertUser);
	}
}

@Override
public void delete(String email, IConexion con) throws PersistenciaException {
	try {
		consultas cons = new consultas();
		String deletJugador = cons.borrarJugador();
		
		PreparedStatement prstm;
		prstm = ((Conexion) con).getConnection().prepareStatement(deletJugador);
		prstm.setString(1, email);
		prstm.executeUpdate();
		prstm.close();
	} catch (SQLException e) {
		throw new PersistenciaException (mensg.errorSQLDeleteusuario);
	}
}


@Override
public List<VOJugador> listarJugadores(IConexion con) throws PersistenciaException {
	consultas cons = new consultas();
	List<VOJugador> listaDeVOJugadores = new ArrayList<VOJugador>();
	String sqlToExecute = cons.listarJug();
	PreparedStatement prstm;
	try {
		prstm = ((Conexion) con).getConnection().prepareStatement(sqlToExecute);
		ResultSet rs = prstm.executeQuery();
		while (rs.next()) {
			VOJugador nuevoVOJ = new VOJugador(rs.getString(1), rs.getInt(2), rs.getString(3), "");
			listaDeVOJugadores.add(nuevoVOJ);
		}
		rs.close();
		prstm.close();
	} catch (SQLException e) {
		throw new PersistenciaException (mensg.errorSQLListarUsuarios);
	}
	
	
	return listaDeVOJugadores;
}
@Override
public boolean isAcountExists(String email, String password, IConexion con) throws  PersistenciaException {
	boolean existe = false;
	try{
		consultas cons = new consultas();
		String query = cons.existEmailPassword();
		PreparedStatement pstmt = ((Conexion) con).getConnection().prepareStatement (query);
		pstmt.setString(1, email);
		pstmt.setString(2, password);
		ResultSet rs = pstmt.executeQuery ();
		if (rs.next ())
			existe = true;
		rs.close ();
		pstmt.close ();
	}catch (SQLException e){
		throw new PersistenciaException (mensg.errorSQLFindUsuario);
	}
	return existe;
	

}

@Override
public String getNameByEmail(String email, IConexion con) throws PersistenciaException {
	
	String nombre= "";

	try{
		consultas cons = new consultas ();
	
		String query = cons.getNameUser();
		PreparedStatement pstmt1 = ((Conexion) con).getConnection().prepareStatement (query);
		pstmt1.setString(1, email);
		ResultSet rs1 = pstmt1.executeQuery ();
		if (rs1.next ()){
			nombre = rs1.getString(1);
		}
		rs1.close ();
		pstmt1.close ();
		}
	catch (SQLException e){
		throw new PersistenciaException (mensg.errorSQLFindUsuario);
	}
	return nombre;
	
}
	    
	   
	 
	    
	  
	    
	    
	    
	    
	    
	   
	    
	  
	
	
}
