package persistencia.baseDeDatos.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import Utilitarios.MensajesPersonalizados;
import logica.Artillero;
import logica.Avion;
import logica.Jugador;
import logica.Artillero;
import persistencia.baseDeDatos.consultas.consultas;
import persistencia.baseDeDatos.poolDeConexiones.Conexion;
import persistencia.excepciones.PersistenciaException;
import persistencia.baseDeDatos.poolDeConexiones.Conexion;
import persistencia.baseDeDatos.poolDeConexiones.IConexion;

public class DaoArtilleria {

	private int tope = 12;
	private Artillero[] secuenciaArtilleria;
	public static MensajesPersonalizados mensg = new MensajesPersonalizados();
	int baseid;

	public DaoArtilleria() {

		secuenciaArtilleria = new Artillero[tope];
	}

	public DaoArtilleria(int in_baseid) {
		secuenciaArtilleria = new Artillero[tope];
		this.baseid = in_baseid;
	}

	public boolean estaVacia(IConexion con) throws PersistenciaException {
		boolean existe = false;
		try {
			consultas cons = new consultas();
			String query = cons.estaVaciaArtillero();
			PreparedStatement pstmt = ((Conexion) con).getConnection().prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				existe = true;
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			throw new PersistenciaException(mensg.errorSQLFindArtilleros);
		}
		return existe;

	}

	public void insBack(int in_idBase, Artillero in_Artillero, IConexion con) throws PersistenciaException {

		try {
			consultas cons = new consultas();
			String insert = cons.insertarArtillero();
			PreparedStatement pstmt = ((Conexion) con).getConnection().prepareStatement(insert);
			pstmt.setInt(1, in_Artillero.getCoordX());
			pstmt.setInt(2, in_Artillero.getCoordY());
			pstmt.setBoolean(3, in_Artillero.getEstado());
			pstmt.setInt(4, in_Artillero.getVida());
			pstmt.setBoolean(5, in_Artillero.getHayEnemigo());
			pstmt.setInt(6, in_Artillero.getRangoDeVision());
			pstmt.setInt(7, in_Artillero.getAvionAngle());
			pstmt.setInt(8, in_idBase);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			throw new PersistenciaException(mensg.errorSQLInsertArtillero);
		}
	}

	public Artillero kesimo(int index, IConexion con) throws PersistenciaException {
		Artillero out_Artillero = null;
		Artillero[] arreArtilleria = new Artillero[tope];
		int ind = 0;
		int out_id = 0;
		int out_coordX = 0;
		int out_coordY = 0;
		boolean out_estado = false;
		int out_vida = 0;
		boolean out_hayEnemigo = false;
		int out_rangoDeVision = 0;
		int out_avionAngle = 0;
		int baseid;

		try {
			consultas cons = new consultas();

			String queryArt = cons.listarArtillerosXBase();
			PreparedStatement pstmt1 = ((Conexion) con).getConnection().prepareStatement(queryArt);
			pstmt1.setInt(1, this.baseid);
			ResultSet rs1 = pstmt1.executeQuery();
			if (rs1.next() && (ind < tope)) {
				out_id = rs1.getInt(1);
				out_coordX = rs1.getInt(2);
				out_coordY = rs1.getInt(3);
                out_estado = rs1.getBoolean(4);
				out_vida = rs1.getInt(5);
				out_hayEnemigo = rs1.getBoolean(6);
                out_rangoDeVision = rs1.getInt(7);
				out_avionAngle = rs1.getInt(8);
				baseid=rs1.getInt(9);
				out_Artillero = new Artillero(out_id, out_coordX, out_coordY, out_estado, out_vida, out_hayEnemigo,
						out_rangoDeVision, out_avionAngle,baseid);
				arreArtilleria[ind] = out_Artillero;

			}
			rs1.close();
			pstmt1.close();

		} catch (SQLException e) {
			throw new PersistenciaException(mensg.errorSQLFindArtilleros);
		}
		return arreArtilleria[index];

	}

	public Artillero[] listarArtilleria(IConexion con) throws PersistenciaException {
		consultas cons = new consultas();

		String sqlToExecute = cons.listarArtilleros();
		PreparedStatement prstm;
		try {
			prstm = ((Conexion) con).getConnection().prepareStatement(sqlToExecute);
			ResultSet rs = prstm.executeQuery();
			int i = 0;
			while (rs.next()) {

				Artillero out_av = new Artillero(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getBoolean(4),
						rs.getInt(5), rs.getBoolean(6), rs.getInt(7), rs.getInt(8),rs.getInt(9));
				secuenciaArtilleria[i] = out_av;
				i++;
			}
			rs.close();
			prstm.close();
		} catch (SQLException e) {
			throw new PersistenciaException(mensg.errorSQLFindArtilleros);
		}

		return secuenciaArtilleria;
	}

	public Artillero[] listarArtilleriaXBase(IConexion con) throws PersistenciaException {
		consultas cons = new consultas();

		String sqlToExecute = cons.listarArtillerosXBase();
		PreparedStatement prstm;
		try {
			prstm = ((Conexion) con).getConnection().prepareStatement(sqlToExecute);
			prstm.setInt(1, this.baseid);
			ResultSet rs = prstm.executeQuery();
			int i = 0;
			while ((rs.next()) && (i < tope)) {

				Artillero out_av = new Artillero(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getBoolean(4),
						rs.getInt(5), rs.getBoolean(6), rs.getInt(7), rs.getInt(8),rs.getInt(9));
				secuenciaArtilleria[i] = out_av;
				i++;
			}
			rs.close();
			prstm.close();
		} catch (SQLException e) {
			throw new PersistenciaException(mensg.errorSQLFindArtilleros);
		}

		return secuenciaArtilleria;
	}

	public void setArreArtilleria(Artillero[] in_arreArtilleria) {
		this.secuenciaArtilleria = in_arreArtilleria;
	}

	

}
