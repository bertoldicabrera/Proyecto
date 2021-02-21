package persistencia.baseDeDatos.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import Utilitarios.MensajesPersonalizados;
import logica.Artillero;
import logica.Jugador;
import logica.Artillero;
import persistencia.baseDeDatos.consultas.consultas;
import persistencia.baseDeDatos.poolDeConexiones.Conexion;
import persistencia.excepciones.PersistenciaException;
import persistencia.baseDeDatos.poolDeConexiones.Conexion;
import persistencia.baseDeDatos.poolDeConexiones.IConexion;

public class DaoArtilleria {
	
	private int tope=12;
	private Artillero[] secuenciaArtilleria;
	public static MensajesPersonalizados mensg = new MensajesPersonalizados();
	
public  DaoArtilleria( ) {
		
		secuenciaArtilleria= new Artillero[tope];
	}

	
	public Artillero[] getSecuenciaArtilleria() {
		return secuenciaArtilleria;
	}
	public void setSecuenciaArtilleria(Artillero[] secuenciaArtilleria) {
		this.secuenciaArtilleria = secuenciaArtilleria;
	}
	

	 
	public boolean estaVacia ( IConexion con) throws PersistenciaException
	{
		boolean existe = false;
		try{
			consultas cons = new consultas();
			String query = cons.estaVaciaArtillero();
			PreparedStatement pstmt = ((Conexion) con).getConnection().prepareStatement (query);
			ResultSet rs = pstmt.executeQuery ();
			if (rs.next ())
				existe = true;
			rs.close ();
			pstmt.close ();
		}catch (SQLException e){
			throw new PersistenciaException (mensg.errorSQLFindArtilleros);
		}
		return existe;
	
	}
	
	public void insBack(Artillero in_Artillero, IConexion con) throws PersistenciaException {
		
		
		try{
			consultas cons = new consultas();
			String insert = cons.insertarArtillero();
			PreparedStatement pstmt = ((Conexion) con).getConnection().prepareStatement (insert);
			pstmt.setInt(1, in_Artillero.GetId());
			pstmt.setInt (2, in_Artillero.getCoordX());
			pstmt.setInt (3, in_Artillero.getCoordY());
			pstmt.setBoolean(4, in_Artillero.getEstado());
			pstmt.setInt (5, in_Artillero.getVida());
			pstmt.setBoolean(6, in_Artillero.getHayEnemigo());
			pstmt.setInt (7, in_Artillero.getRangoDeVision());
			pstmt.setFloat(8,in_Artillero.getAvionAngle() );
			pstmt.executeUpdate ();
			pstmt.close ();
		}
		catch (SQLException e)
		{
		throw new PersistenciaException (mensg.errorSQLInsertArtillero);
		}
	}
	
	
	
	


	public Artillero  kesimo(int in_ArtilleroId, IConexion con) throws PersistenciaException {
		Artillero out_Artillero = null;
		
		int out_id = 0; int out_coordX = 0; int out_coordY = 0; boolean out_estado = false; 
		int out_vida = 0; boolean out_hayEnemigo = false;
		int out_rangoDeVision = 0; float out_avionAngle = 0;
		
		
		try{
			consultas cons = new consultas ();
		
			String queryArt = cons.obtenerKesimoArtillero();
			PreparedStatement pstmt1 = ((Conexion) con).getConnection().prepareStatement (queryArt);
			pstmt1.setInt (1, in_ArtilleroId);
			ResultSet rs1 = pstmt1.executeQuery ();
			if (rs1.next ()){
				out_id= rs1.getInt(1);
				out_coordX= rs1.getInt(2);
				out_coordY= rs1.getInt(3);
				out_estado= rs1.getBoolean(4);
				out_vida= rs1.getInt(5);
				out_hayEnemigo= rs1.getBoolean(6);
				out_rangoDeVision= rs1.getInt(7);
				out_avionAngle= rs1.getFloat(8);
				
				
			}
			rs1.close ();
			pstmt1.close ();
			out_Artillero = new Artillero (out_id, out_coordX, out_coordY,out_estado,
					out_vida, out_hayEnemigo, out_rangoDeVision, out_avionAngle);
			}
		catch (SQLException e){
			throw new PersistenciaException (mensg.errorSQLFindArtilleros);
		}
		return out_Artillero;
		
	}
	
	

	public int largo( IConexion con) throws PersistenciaException {
		
		int cant=0;
		consultas cons = new consultas();
		
		String sqlToExecute = cons.cantidadArtilleros();
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
			throw new PersistenciaException (mensg.errorSQLFindArtilleros);
		}
		return cant;
	}
	

}