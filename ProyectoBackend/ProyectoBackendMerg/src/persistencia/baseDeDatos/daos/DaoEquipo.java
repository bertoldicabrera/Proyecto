package persistencia.baseDeDatos.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Utilitarios.MensajesPersonalizados;
import logica.Base;
import logica.Equipo;
import logica.Jugador;
import persistencia.baseDeDatos.consultas.consultas;
import persistencia.baseDeDatos.poolDeConexiones.Conexion;
import persistencia.baseDeDatos.poolDeConexiones.IConexion;
import persistencia.excepciones.PersistenciaException;

public class DaoEquipo {
	
	
	public static MensajesPersonalizados mensg = new MensajesPersonalizados();

	public DaoEquipo() {
		// TODO Auto-generated constructor stub
		
	}
	
	public void  insBack (Equipo in_Equipo, IConexion con) {
		try{
			
			 int equipoID;
			 int idJugador;
			 int baseId;
			 String bando;
			
			
			
			consultas cons = new consultas();
			String insert = cons.insertarEquipo();
			PreparedStatement pstmt = ((Conexion) con).getConnection().prepareStatement (insert);
			
			pstmt.setInt(1, in_Equipo.getEquipoID());
			pstmt.setInt (2, in_Equipo.getJugador().getJugadorId());
			pstmt.setInt (3, in_Equipo.getBase().getIdDabse());
			pstmt.setString(4, in_Equipo.getBando());
			
			pstmt.executeUpdate ();
			pstmt.close ();
		}
		catch (SQLException e)
		{
		throw new PersistenciaException (mensg.errorSQLInsertEquipos);
		}
		
		
		
	}

	
	
	
	public int largo ( IConexion con)
	{
		int cant=0;
		consultas cons = new consultas();
		
		String sqlToExecute = cons.cantidadEquipos();
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
			throw new PersistenciaException (mensg.errorSQLFindEquipos);
		}
		return cant;
		
	}
	
	public Equipo kesimo (int in_equipoID, IConexion con)
	{
		return null;
	}
	


	public Equipo[]  listarEquipos( IConexion con)
	{
		return null;
	}
	public void  borrarEquipos( IConexion con)
	{
	
		
	}

	


	

}
