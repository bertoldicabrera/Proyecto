package persistencia.baseDeDatos.daos;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Utilitarios.MensajesPersonalizados;
import logica.Avion;
import logica.Jugador;
import persistencia.baseDeDatos.consultas.consultas;
import persistencia.baseDeDatos.poolDeConexiones.Conexion;
import persistencia.baseDeDatos.poolDeConexiones.IConexion;
import persistencia.excepciones.PersistenciaException;

public class DaoDeAviones implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static MensajesPersonalizados mensg = new MensajesPersonalizados();

	private int tope = 4;
	private Avion[] arreavion;
	int baseId;

	public DaoDeAviones() {
		arreavion = new Avion[tope];
	}

	public DaoDeAviones(int in_idBase) {
		this.baseId = in_idBase;
		this.arreavion = new Avion[tope];
	}

	public boolean estaVacia(IConexion con) throws PersistenciaException {
		boolean esta = false;
		try {
			consultas cons = new consultas();
			String query = cons.existenAviones();
			PreparedStatement pstmt = ((Conexion) con).getConnection().prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				esta = true;
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			throw new PersistenciaException(mensg.errorSQLFindAvion);
		}
		return esta;
	}

	public void insback(int in_idbase, Avion in_Avion, IConexion con) throws PersistenciaException {
		try {
			consultas cons = new consultas();
			String insert = cons.insertarAvion();

			PreparedStatement pstmt = ((Conexion) con).getConnection().prepareStatement(insert);
			pstmt.setInt(1, in_Avion.getCoordX());
			pstmt.setInt(2, in_Avion.getCoordY());
			pstmt.setInt(3, in_Avion.getAvionAltura());
			pstmt.setBoolean(4, in_Avion.getEstado());
			pstmt.setInt(5, in_Avion.getVida());
			pstmt.setBoolean(6, in_Avion.getHayEnemigo());
			pstmt.setInt(7, in_Avion.getRangoDeVision());
			pstmt.setBoolean(8, in_Avion.getAvionBomba());
			pstmt.setInt(9, in_Avion.getCantidadBombas());
			pstmt.setInt(10, in_Avion.getAvionCombustible());
			pstmt.setBoolean(11, in_Avion.getEnCampoEnemigo());
			pstmt.setInt(12, in_idbase);

			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			throw new PersistenciaException(mensg.errorSQLInsertAvion);
		}
	}

	public Avion[] listarAviones(IConexion con) throws PersistenciaException {
		consultas cons = new consultas();
		String sqlToExecute = cons.listarAviones();
		PreparedStatement prstm;
		try {
			prstm = ((Conexion) con).getConnection().prepareStatement(sqlToExecute);
			ResultSet rs = prstm.executeQuery();
			int i = 0;
			while ((rs.next()) && (i < tope)) {
				Avion out_av = new Avion(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getBoolean(5),
						rs.getInt(6), rs.getBoolean(7), rs.getInt(8), rs.getBoolean(9), rs.getInt(10), rs.getInt(11),
						rs.getBoolean(12), rs.getInt(13));
				arreavion[i] = out_av;
				i++;
			}
			rs.close();
			prstm.close();
		} catch (SQLException e) {
			throw new PersistenciaException(mensg.errorSQLListarAviones);
		}
		return arreavion;
	}

	public Avion[] listarAvionesXBase(IConexion con) throws PersistenciaException {
		consultas cons = new consultas();
		String sqlToExecute = cons.obtenerAvionXBase();
		PreparedStatement prstm;
		try {
			prstm = ((Conexion) con).getConnection().prepareStatement(sqlToExecute);
			prstm.setInt(1, this.baseId);
			ResultSet rs = prstm.executeQuery();
			int i = 0;
			while ((rs.next()) && (i < tope)) {
				Avion out_av = new Avion(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getBoolean(5),
						rs.getInt(6), rs.getBoolean(7), rs.getInt(8), rs.getBoolean(9), rs.getInt(10), rs.getInt(11),
						rs.getBoolean(12), rs.getInt(13));
				arreavion[i] = out_av;
				i++;
			}
			rs.close();
			prstm.close();
		} catch (SQLException e) {
			throw new PersistenciaException(mensg.errorSQLListarAviones);
		}

		return arreavion;
	}

	public Avion kesimo(int index, IConexion con) throws PersistenciaException {
		Avion avion = null;
		int id = 0;
		int baseoid;
		int coordX = 0;
		int coordY = 0;
		int coordZ;
		boolean estado = false;
		int vida = 0;
		boolean avionBomba = false;
		int cantidadBombas = 0;
		int avionCombustible = 0;
		boolean hayEnemigo = false;
		boolean enCampoEnemigo = false;
		int rangoDeVision = 0;
		Avion[] arregavion = new Avion[tope];

		try {
			int ind = 0;
			consultas cons = new consultas();
			String query = cons.obtenerAvionXBase();
			PreparedStatement pstmt1 = ((Conexion) con).getConnection().prepareStatement(query);
			pstmt1.setInt(1, this.baseId);
			ResultSet rs1 = pstmt1.executeQuery();
			while ((rs1.next()) && (ind < tope)) {
				
				
				
				id = rs1.getInt(1);
				coordX =  rs1.getInt(2);
				coordY = rs1.getInt(3);
				coordZ = rs1.getInt(4);
				estado=  rs1.getBoolean(5);
				vida = rs1.getInt(6);
				hayEnemigo = rs1.getBoolean(7);
				rangoDeVision = rs1.getInt(8);
				avionBomba=rs1.getBoolean(9);
				cantidadBombas = rs1.getInt(10);
				avionCombustible = rs1.getInt(11);
				enCampoEnemigo = rs1.getBoolean(12);
				baseoid = rs1.getInt(13);
				
				
				avion = new Avion(id, coordX, coordY, coordZ, estado, vida, hayEnemigo,rangoDeVision ,avionBomba, cantidadBombas,
						avionCombustible,enCampoEnemigo ,baseoid );
				arregavion[ind] = avion;

			}
			rs1.close();
			pstmt1.close();

		} catch (SQLException e) {
			throw new PersistenciaException(mensg.errorSQLFindAvion);
		}
		return arregavion[index];
	}

	public void setArreAviones(Avion[] in_arreavion) {
		this.arreavion = in_arreavion;
	}

}
