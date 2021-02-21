package persistencia.baseDeDatos.daos;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
			throw new PersistenciaException (mensg.errorSQLFindPartida);
		}
		return existe;
	}
	
	public Partida find (int in_PartidaId, IConexion con) throws PersistenciaException{
		Partida out_Part = null;
		
		int out_PartidaId=0;
		String out_PartidaEstado = null;
		Date out_PartidaFechaUltimaActualizacio = null;
		boolean out_PartidaGuardada = false;
		int out_GanadorEquipoID = 0;
		String  out_PartidaNombre = null;
		int out_PartidaCantidadJugadores = 0;
		int out_PartidaCreador = 0;
		Date out_PartidaFechaCreada = null;
		
		
		try{
			consultas cons = new consultas ();
		
			String queryNin = cons.obtenerJugador();
			PreparedStatement pstmt1 = ((Conexion) con).getConnection().prepareStatement (queryNin);
			pstmt1.setInt (1, in_PartidaId);
			ResultSet rs1 = pstmt1.executeQuery ();
			if (rs1.next ()){
				 out_PartidaId= rs1.getInt(1);
				 out_PartidaEstado= rs1.getString(2);
				 out_PartidaFechaUltimaActualizacio= rs1.getDate(3);
				 out_PartidaGuardada= rs1.getBoolean(4);
				 out_GanadorEquipoID= rs1.getInt(5);
				  out_PartidaNombre= rs1.getString(6);
				 out_PartidaCantidadJugadores= rs1.getInt(7);
				 out_PartidaCreador= rs1.getInt(8);
				 out_PartidaFechaCreada= rs1.getDate(9);
				
			}
			rs1.close ();
			pstmt1.close ();
			out_Part = new Partida 
					(out_PartidaId, out_PartidaEstado,
					out_PartidaFechaUltimaActualizacio, out_PartidaGuardada,
					out_GanadorEquipoID, out_PartidaNombre, out_PartidaCantidadJugadores, 
					out_PartidaCreador, out_PartidaFechaCreada );
			}
		catch (SQLException e){
			throw new PersistenciaException (mensg.errorSQLFindPartida);
		}
		return out_Part;
	}
	
	public void insert (Partida in_part, IConexion con) throws PersistenciaException{
		try{
			consultas cons = new consultas();
			String insert = cons.insertarPartida();
			PreparedStatement pstmt = ((Conexion) con).getConnection().prepareStatement (insert);
			pstmt.setInt(1, in_part.getPartidaId());
			pstmt.setString (2, in_part.getPartidaEstado());
			//ver si esto no rompe!!!!!
			pstmt.setDate(3, (java.sql.Date) in_part.getPartidaFechaUltimaActualizacion());
			pstmt.setBoolean(4, in_part.isPartidaGuardada());
			pstmt.setInt(5,in_part.getGanadorEquipoID());
			pstmt.setString (6, in_part.getPartidaNombre());
			pstmt.setInt(7, in_part.getPartidaCantidadJugadores());
			pstmt.setInt(8, in_part.getPartidaCreador());
			pstmt.setDate(9, (java.sql.Date) in_part.getPartidaFechaCreada());
			
			pstmt.executeUpdate ();
			pstmt.close ();
		}
		catch (SQLException e)
		{
		throw new PersistenciaException (mensg.errorSQLInsertPartida);
		}
	}

	
	//seguir haciendo refactor en esta parte
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
