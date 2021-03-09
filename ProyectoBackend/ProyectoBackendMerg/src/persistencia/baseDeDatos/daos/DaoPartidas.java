package persistencia.baseDeDatos.daos;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.TreeMap;
import Utilitarios.MensajesPersonalizados;
import logica.Partida;
import persistencia.baseDeDatos.consultas.consultas;
import persistencia.baseDeDatos.poolDeConexiones.Conexion;
import persistencia.baseDeDatos.poolDeConexiones.IConexion;
import persistencia.excepciones.PersistenciaException;

public class DaoPartidas implements Serializable {

	private static final long serialVersionUID = 1L;

	public static MensajesPersonalizados mensg = new MensajesPersonalizados();

	public DaoPartidas() {

	}

	public boolean member(int in_PartidaId, IConexion con) throws PersistenciaException {
		boolean existe = false;
		try {
			consultas cons = new consultas();
			String query = cons.existePartida();
			PreparedStatement pstmt = ((Conexion) con).getConexion().prepareStatement(query);
			pstmt.setInt(1, in_PartidaId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				existe = true;
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			throw new PersistenciaException(mensg.errorSQLFindPartida);
		}
		return existe;
	}

	public Partida find(int in_PartidaId, IConexion con) throws PersistenciaException {
		Partida out_Part = null;
		int out_PartidaId = 0;
		String out_PartidaEstado = null;
		LocalDate out_PartidaFechaUltimaActualizacio = null;
		boolean out_PartidaGuardada = false;
		String out_PartidaNombre = null;
		int out_PartidaCantidadJugadores = 0;
		int out_PartidaCreador = 0;
		LocalDate out_PartidaFechaCreada = null;
		boolean out_terminoPartida = false;

		try {
			consultas cons = new consultas();

			String query = cons.existePartida();
			PreparedStatement pstmt1 = ((Conexion) con).getConexion().prepareStatement(query);
			pstmt1.setInt(1, in_PartidaId);
			ResultSet rs1 = pstmt1.executeQuery();
			if (rs1.next()) {
				out_PartidaId = rs1.getInt(1);
				out_PartidaEstado = rs1.getString(2);
				out_PartidaFechaUltimaActualizacio = rs1.getDate(3).toLocalDate();
				out_PartidaGuardada = rs1.getBoolean(4);

				out_PartidaNombre = rs1.getString(5);
				out_PartidaCantidadJugadores = rs1.getInt(6);
				out_PartidaCreador = rs1.getInt(7);
				out_PartidaFechaCreada = rs1.getDate(8).toLocalDate();
				out_terminoPartida = rs1.getBoolean(9);

			}
			rs1.close();
			pstmt1.close();

			DaoEquipo daoEq = new DaoEquipo();

			out_Part = new Partida(out_PartidaId, out_PartidaEstado, out_PartidaFechaUltimaActualizacio,
					out_PartidaGuardada, out_PartidaNombre, out_PartidaCantidadJugadores, out_PartidaCreador,
					out_PartidaFechaCreada, out_terminoPartida, daoEq.listarEquiposDeUnaPartidaXID(in_PartidaId, con)); // acá
																														// estoy
																														// seteando
																														// el
																														// daoequipo
			// en esa partida

		}

		catch (SQLException e) {

			throw new PersistenciaException(e.toString());
			// throw new PersistenciaException (mensg.errorSQLFindPartida);
		}
		return out_Part;
	}

	public void insert(Partida in_part, IConexion con) throws PersistenciaException {
		try {
			consultas cons = new consultas();
			String insert = cons.insertarPartida();

			PreparedStatement pstmt = ((Conexion) con).getConexion().prepareStatement(insert);
			pstmt.setString(1, in_part.getPartidaEstado());

			// Date date = Date.valueOf(in_part.getPartidaFechaUltimaActualizacion());
			int dia = in_part.getPartidaFechaUltimaActualizacion().getDayOfYear();
			int mes = in_part.getPartidaFechaUltimaActualizacion().getDayOfMonth();
			int anio = in_part.getPartidaFechaUltimaActualizacion().getYear();
			java.sql.Date date1 = new java.sql.Date(dia, mes, anio);
			// System.out.println("la fecha es"+date1.toString());

			pstmt.setDate(2, date1);
			pstmt.setBoolean(3, in_part.isPartidaGuardada());
			pstmt.setString(4, in_part.getPartidaNombre());
			pstmt.setInt(5, in_part.getPartidaCantidadJugadores());
			pstmt.setInt(6, in_part.getPartidaCreador());
			int dia2 = in_part.getPartidaFechaCreada().getDayOfYear();
			int mes2 = in_part.getPartidaFechaCreada().getDayOfMonth();
			int anio2 = in_part.getPartidaFechaCreada().getYear();
			java.sql.Date date2 = new java.sql.Date(dia2, mes2, anio2);
			pstmt.setDate(7, date2);
			pstmt.setBoolean(8, in_part.getPartidaTermino());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			throw new PersistenciaException(e.toString());
			// throw new PersistenciaException (mensg.errorSQLInsertPartida);
		}
	}

	public void delete(int in_PartidaId, IConexion con) throws PersistenciaException {

		try {
			consultas cons = new consultas();
			String deletPart = cons.borrarPartida();

			PreparedStatement prstm;
			prstm = ((Conexion) con).getConexion().prepareStatement(deletPart);
			prstm.setInt(1, in_PartidaId);
			prstm.executeUpdate();
			prstm.close();
		} catch (SQLException e) {
			throw new PersistenciaException(mensg.errorSQLDeletePartida);
		}

	}

	public TreeMap<Integer, Partida> listarPartidasDeJugador(int in_IdJugador, IConexion con)
			throws PersistenciaException {
		consultas cons = new consultas();
		TreeMap<Integer, Partida> listaDePartidas = new TreeMap<Integer, Partida>();
		String sqlToExecute = cons.listarPartidasDeUnJugador();
		PreparedStatement prstm;
		try {
			prstm = ((Conexion) con).getConexion().prepareStatement(sqlToExecute);
			System.out.println("206" + sqlToExecute);
			prstm.setInt(1, in_IdJugador);
			ResultSet rs = prstm.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getInt(1));

				System.out.println(rs.getInt(1));
				Partida nuevaPartida = new Partida(rs.getInt(1), rs.getString(2), rs.getDate(3).toLocalDate(),

						rs.getBoolean(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getDate(8).toLocalDate(),
						rs.getBoolean(9), null);
				listaDePartidas.put(nuevaPartida.getPartidaId(), nuevaPartida);
			}
			rs.close();
			prstm.close();
		} catch (SQLException e) {
			throw new PersistenciaException(e.toString());
		}

		return listaDePartidas;
	}

	public boolean estaVacio(IConexion con) throws PersistenciaException {
		boolean esta = true;
		try {
			consultas cons = new consultas();
			String query = cons.existenPartidas();
			PreparedStatement pstmt = ((Conexion) con).getConexion().prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				esta = false;

			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			throw new PersistenciaException(e.toString());
		}
		return esta;
	}

	public int getUltimaPartidaIDMas1(IConexion con) throws PersistenciaException {
		int cant = 0;
		consultas cons = new consultas();

		String sqlToExecute = cons.ultimaPartidaID();
		PreparedStatement prstm;
		try {
			prstm = ((Conexion) con).getConexion().prepareStatement(sqlToExecute);
			ResultSet rs = prstm.executeQuery();
			if (rs.next()) {
				cant = rs.getInt(1);
			}
			rs.close();
			prstm.close();
		} catch (SQLException e) {
			throw new PersistenciaException(mensg.errorSQLListarPartidas);
		}
		return cant;

	}

}
