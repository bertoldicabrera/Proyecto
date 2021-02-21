package persistencia.baseDeDatos.daos;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Utilitarios.MensajesPersonalizados;
import logica.JUGADOR;
import persistencia.baseDeDatos.consultas.consultas;
import persistencia.baseDeDatos.poolDeConexiones.IConexion;
import persistencia.excepciones.PersistenciaException;


public class DAOJUGADOR implements  Serializable {


private static final long serialVersionUID = 1L;

public static MensajesPersonalizados mensg = new MensajesPersonalizados();
public DAOJUGADOR(){

}

public boolean member(String in_JugadorID, IConexion con) throws PersistenciaException {
	boolean existe = false;
	try{
		consultas cons = new consultas();
		String query = cons.existeJugador();
		PreparedStatement pstmt = ((Conexion) con).getConnection().prepareStatement (query);
		pstmt.setString(1, in_JugadorID); 
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

public JUGADOR find(String in_JugadorID, IConexion con) throws PersistenciaException {
	JUGADOR jug = null;
	int id= 0;
	String nombre= "";
	String password="";
	try{
		consultas cons = new consultas ();
	
		String queryNin = cons.obtenerJugador();
		PreparedStatement pstmt1 = ((Conexion) con).getConnection().prepareStatement (queryNin);
		pstmt1.setString(1, in_JugadorID);
		ResultSet rs1 = pstmt1.executeQuery ();
		if (rs1.next ()){
			id = rs1.getInt(1);
			nombre = rs1.getString(2);
			password = rs1.getString(3);
		}
		rs1.close ();
		pstmt1.close ();
		jug = new JUGADOR (nombre, in_JugadorID, id, password);
		}
	catch (SQLException e){
		throw new PersistenciaException (mensg.errorSQLFindUsuario);
	}
	return jug;
}


@Override
public void insert(JUGADOR jug, IConexion con) throws PersistenciaException {
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
public void delete(String in_JugadorID, IConexion con) throws PersistenciaException {
	try {
		consultas cons = new consultas();
		String deletJUGADOR = cons.borrarJUGADOR();
		
		PreparedStatement prstm;
		prstm = ((Conexion) con).getConnection().prepareStatement(deletJUGADOR);
		prstm.setString(1, in_JugadorID);
		prstm.executeUpdate();
		prstm.close();
	} catch (SQLException e) {
		throw new PersistenciaException (mensg.errorSQLDeleteusuario);
	}
}


@Override
public List<VOJUGADOR> listarJUGADORes(IConexion con) throws PersistenciaException {
	consultas cons = new consultas();
	List<VOJUGADOR> listaDeVOJUGADORes = new ArrayList<VOJUGADOR>();
	String sqlToExecute = cons.listarJug();
	PreparedStatement prstm;
	try {
		prstm = ((Conexion) con).getConnection().prepareStatement(sqlToExecute);
		ResultSet rs = prstm.executeQuery();
		while (rs.next()) {
			VOJUGADOR nuevoVOJ = new VOJUGADOR(rs.getString(1), rs.getInt(2), rs.getString(3), "");
			listaDeVOJUGADORes.add(nuevoVOJ);
		}
		rs.close();
		prstm.close();
	} catch (SQLException e) {
		throw new PersistenciaException (mensg.errorSQLListarUsuarios);
	}
	
	
	return listaDeVOJUGADORes;
}
@Override
public boolean isAcountExists(String in_JugadorID, String password, IConexion con) throws  PersistenciaException {
	boolean existe = false;
	try{
		consultas cons = new consultas();
		String query = cons.existEmailPassword();
		PreparedStatement pstmt = ((Conexion) con).getConnection().prepareStatement (query);
		pstmt.setString(1, in_JugadorID);
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
public String getNameByEmail(String in_JugadorID, IConexion con) throws PersistenciaException {
	
	String nombre= "";

	try{
		consultas cons = new consultas ();
	
		String query = cons.getNameUser();
		PreparedStatement pstmt1 = ((Conexion) con).getConnection().prepareStatement (query);
		pstmt1.setString(1, in_JugadorID);
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
