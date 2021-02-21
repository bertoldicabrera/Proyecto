package persistencia.baseDeDatos.daos;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Utilitarios.MensajesPersonalizados;
import logica.Jugador;
import persistencia.baseDeDatos.consultas.consultas;
import persistencia.baseDeDatos.poolDeConexiones.IConexion;
import persistencia.excepciones.PersistenciaException;
import persistencia.baseDeDatos.poolDeConexiones.Conexion;
import persistencia.baseDeDatos.poolDeConexiones.IConexion;


public class DaoJugador implements  Serializable {


private static final long serialVersionUID = 1L;

public static MensajesPersonalizados mensg = new MensajesPersonalizados();
public DaoJugador(){

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

public Jugador find(int in_JugadorID, IConexion con) throws PersistenciaException {
	Jugador jug = null;
	int jugadorId=0;
	String jugadorUserName="";
	String jugadorPassword="";
	boolean jugadorIsOnline=false;
	int puntajeAcumulado=0;
	
	
	
	try{
		consultas cons = new consultas ();
	
		String queryNin = cons.obtenerJugador();
		PreparedStatement pstmt1 = ((Conexion) con).getConnection().prepareStatement (queryNin);
		pstmt1.setInt(1, in_JugadorID);
		ResultSet rs1 = pstmt1.executeQuery ();
		if (rs1.next ()){
			jugadorId = rs1.getInt(1);
			jugadorUserName = rs1.getString(2);
			jugadorPassword = rs1.getString(3);
			jugadorIsOnline = rs1.getBoolean(4);
			puntajeAcumulado= rs1.getInt(5);
		}
		rs1.close ();
		pstmt1.close ();
		jug = new Jugador (jugadorId, jugadorUserName, jugadorPassword, jugadorIsOnline, puntajeAcumulado);
		}
	catch (SQLException e){
		throw new PersistenciaException (mensg.errorSQLFindUsuario);
	}
	return jug;
}


public void insert(Jugador in_jugador, IConexion con) throws PersistenciaException {
	try{
		consultas cons = new consultas();
		String insert = cons.insertarJugador();
		PreparedStatement pstmt = ((Conexion) con).getConnection().prepareStatement (insert);
		pstmt.setInt(1, in_jugador.getJugadorId());
		pstmt.setString (2, in_jugador.getJugadorUserName());
		pstmt.setString (3, in_jugador.getJugadorPassword());
		pstmt.setBoolean(4, in_jugador.isJugadorIsOnline());
		pstmt.setInt (5, in_jugador.getPuntajeAcumulado());
		pstmt.executeUpdate ();
		pstmt.close ();
	}
	catch (SQLException e)
	{
	throw new PersistenciaException (mensg.errorSQLInsertUser);
	}
}

public void delete(int in_JugadorID, IConexion con) throws PersistenciaException {
	try {
		consultas cons = new consultas();
		String deletJUGADOR = cons.borrarJugador();
		
		PreparedStatement prstm;
		prstm = ((Conexion) con).getConnection().prepareStatement(deletJUGADOR);
		prstm.setInt(1, in_JugadorID);
		prstm.executeUpdate();
		prstm.close();
	} catch (SQLException e) {
		throw new PersistenciaException (mensg.errorSQLDeleteusuario);
	}
}


public List<Jugador> listarJugadores(IConexion con) throws PersistenciaException {
	consultas cons = new consultas();
	List<Jugador> lista_out = new ArrayList<Jugador>();
	String sqlToExecute = cons.listarJugadores();
	PreparedStatement prstm;
	try {
		prstm = ((Conexion) con).getConnection().prepareStatement(sqlToExecute);
		ResultSet rs = prstm.executeQuery();
		while (rs.next()) {
			
			Jugador nuevoJ = new Jugador(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getInt(5));
			lista_out.add(nuevoJ);
		}
		rs.close();
		prstm.close();
	} catch (SQLException e) {
		throw new PersistenciaException (mensg.errorSQLListarUsuarios);
	}
	
	
	return lista_out;
}

public int  cantidadJugadores(IConexion con) throws PersistenciaException {
	
	int cant=0;
	consultas cons = new consultas();
	
	String sqlToExecute = cons.cantidadTotalJugadores();
	PreparedStatement prstm;
	try {
		prstm = ((Conexion) con).getConnection().prepareStatement(sqlToExecute);
		ResultSet rs = prstm.executeQuery();
		if (rs.next()) {
			cant=rs.getInt(1);
		}
		rs.close();
		prstm.close();
	} catch (SQLException e) {
		throw new PersistenciaException (mensg.errorSQLCantidadUsuarios);
	}
	return cant;
}



public int cantidadJugadoresEnLinea(IConexion con) throws PersistenciaException {
	int cant=0;
	consultas cons = new consultas();
	
	String sqlToExecute = cons.cantidadJugadoresOnLine();
	PreparedStatement prstm;
	try {
		prstm = ((Conexion) con).getConnection().prepareStatement(sqlToExecute);
		ResultSet rs = prstm.executeQuery();
		if (rs.next()) {
			cant=rs.getInt(1);
		}
		rs.close();
		prstm.close();
	} catch (SQLException e) {
		throw new PersistenciaException (mensg.errorSQLCantidadUsuarios);
	}
	return cant;
	
}






public boolean isAcountExists(String in_JugadorUserName, String in_JugadorPassword, IConexion con) throws  PersistenciaException {
	boolean existe = false;
	try{
		consultas cons = new consultas();
		String query = cons.acountExists();
		PreparedStatement pstmt = ((Conexion) con).getConnection().prepareStatement (query);
		pstmt.setString(1, in_JugadorUserName);
		pstmt.setString(2, in_JugadorPassword);
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

public String getNameById(String in_JugadorID, IConexion con) throws PersistenciaException {
	
	String nombre= "";

	try{
		consultas cons = new consultas ();
	
		String query = cons.getName();
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