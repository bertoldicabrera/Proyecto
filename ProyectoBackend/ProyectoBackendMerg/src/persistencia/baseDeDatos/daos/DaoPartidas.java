package persistencia.baseDeDatos.daos;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.Connection;
import Utilitarios.MensajesPersonalizados;
import logica.Partida;
import logica.valueObjects.*;
import persistencia.baseDeDatos.consultas.consultas;
import persistencia.baseDeDatos.poolDeConexiones.Conexion;
import persistencia.baseDeDatos.poolDeConexiones.IConexion;
import persistencia.excepciones.PersistenciaException;

public class DaoPartidas implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public static MensajesPersonalizados mensg = new MensajesPersonalizados();
	public DaoPartidas (){

	}
	
	public boolean member (int in_PartidaId, IConexion con) throws PersistenciaException{
		boolean existe = false;
		try{
			consultas cons = new consultas();
			String query = cons.existePartida();
			PreparedStatement pstmt = ((Conexion) con).getConnection().prepareStatement (query);
			pstmt.setInt (1, in_PartidaId);
			ResultSet rs = pstmt.executeQuery ();
			if (rs.next ())
				existe = true;
			rs.close ();
			pstmt.close ();
		}catch (SQLException e){
			throw new PersistenciaException (mensg.errorSQLFindNinio);
		}
		return existe;
	}
	
	public Partida find (int in_PartidaId, IConexion con) throws PersistenciaException{
		Partida part = null;
		String nombre= "";
		String apellido="";
		try{
			consultas cons = new consultas ();
		
			String queryNin = cons.obtenerJugador();
			PreparedStatement pstmt1 = ((Conexion) con).getConnection().prepareStatement (queryNin);
			pstmt1.setInt (1, in_PartidaId);
			ResultSet rs1 = pstmt1.executeQuery ();
			if (rs1.next ()){
				nombre = rs1.getString(1);
				apellido = rs1.getString(2);
			}
			rs1.close ();
			pstmt1.close ();
			part = new Partida (in_PartidaId, queryNin, null, false, in_PartidaId, queryNin, in_PartidaId, in_PartidaId, null );
			}
		catch (SQLException e){
			throw new PersistenciaException (mensg.errorSQLFindNinio);
		}
		return jug;
	}
	
	public void insert (Partida part, IConexion con) throws PersistenciaException{
		try{
			consultas cons = new consultas();
			String insert = cons.insertarPartida();
			PreparedStatement pstmt = ((Conexion) con).getConnection().prepareStatement (insert);
			pstmt.setInt(1, part.getCedula());
			pstmt.setString (2, part.getNombre());
			pstmt.setString (3, part.getApellido());
			pstmt.executeUpdate ();
			pstmt.close ();
		}
		catch (SQLException e)
		{
		throw new PersistenciaException (mensg.errorSQLInsertNinio);
		}
	}

	 public void delete(int in_PartidaId, IConexion con) throws PersistenciaException
	{
		
		try {
			consultas cons = new consultas();
			String deletNinio = cons.borrarPartida();
			
			PreparedStatement prstm;
			prstm = ((Conexion) con).getConnection().prepareStatement(deletNinio);
			prstm.setInt(1, in_PartidaId);
			prstm.executeUpdate();
			prstm.close();
		} catch (SQLException e) {
			throw new PersistenciaException (mensg.errorSQLDeleteNinio);
		}
		
	}
	
	public List <voNinio> listarNinios(IConexion con) throws PersistenciaException
	{
		consultas cons = new consultas();
		List<voNinio> listaDeVONinios = new ArrayList<voNinio>();
		String sqlToExecute = cons.listarPartidas();
		
		PreparedStatement prstm;
		try {
			
			prstm = ((Conexion) con).getConnection().prepareStatement(sqlToExecute);
			ResultSet rs = prstm.executeQuery();
			
			while (rs.next()) {
			    voNinio nuevoVONinio = new voNinio(rs.getInt(1), rs.getString(2), rs.getString(3));
			    listaDeVONinios.add(nuevoVONinio);
			}
			rs.close();
			prstm.close();
		} catch (SQLException e) {
			throw new PersistenciaException (mensg.errorSQLListarNinios);
		}
		
		
		return listaDeVONinios;
	}
}
