package persistencia.baseDeDatos.daos;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import Utilitarios.MensajesPersonalizados;
import logica.Artillero;
import logica.Avion;
import logica.Base;
import logica.Deposito;
import logica.Equipo;
import logica.Jugador;
import logica.TanqueCombustible;
import logica.TorreControl;
import persistencia.baseDeDatos.consultas.consultas;
import persistencia.baseDeDatos.poolDeConexiones.Conexion;
import persistencia.baseDeDatos.poolDeConexiones.IConexion;
import persistencia.excepciones.PersistenciaException;

public class DaoBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static MensajesPersonalizados mensg = new MensajesPersonalizados();
	private TreeMap<Integer, Base> bases;

	public DaoBase() {
		bases = new TreeMap<Integer, Base>();
	}

	public boolean member(Integer key, IConexion con) throws PersistenciaException {
		boolean existe = false;
		try {
			consultas cons = new consultas();
			String query = cons.obtenerBase();
			PreparedStatement pstmt = ((Conexion) con).getConnection().prepareStatement(query);
			pstmt.setInt(1, key);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				existe = true;
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			throw new PersistenciaException(mensg.errorSQLFindBase);
		}
		return existe;

	}

	public void insert(int in_idEquipo, Deposito in_Deposito, TanqueCombustible in_TanqueCombustible,
			TorreControl in_TorreControl, IConexion con) throws PersistenciaException {
		
		try {

			consultas cons = new consultas();
			// Inserto depostio
			String insertDep = cons.insertarDeposito();
			PreparedStatement pstmt1 = ((Conexion) con).getConnection().prepareStatement(insertDep);
			pstmt1.setInt(1, in_Deposito.getCoordX());
			pstmt1.setInt(2, in_Deposito.getCoordY());
			pstmt1.setBoolean(3, in_Deposito.getEstado());
			pstmt1.setInt(4, in_Deposito.getVida());
			pstmt1.setInt(5, in_Deposito.getCantidaBombas());
			pstmt1.setBoolean(6, in_Deposito.getEnUso());
			pstmt1.executeUpdate();
			pstmt1.close();
			// Inserto TanqueCombustible
			String insertTC = cons.insertarTanqueCombustible();
			PreparedStatement pstmt2 = ((Conexion) con).getConnection().prepareStatement(insertTC);
		
			pstmt1.setInt(1, in_TanqueCombustible.getCoordX());
			pstmt1.setInt(2, in_TanqueCombustible.getCoordY());
			pstmt1.setBoolean(3, in_TanqueCombustible.getEstado());
			pstmt1.setInt(4, in_TanqueCombustible.getVida());
			pstmt1.setInt(5, in_TanqueCombustible.getCantidadCombustible());
			pstmt1.setBoolean(6, in_TanqueCombustible.getEnUso());
			pstmt1.executeUpdate();
			pstmt1.close();
			// Inserto TorreControl
			String insertTControl = cons.insertarTorreControl();
			PreparedStatement pstmt3 = ((Conexion) con).getConnection().prepareStatement(insertTControl);
			pstmt1.setInt(1, in_TorreControl.getCoordX());
			pstmt1.setInt(2, in_TorreControl.getCoordY());
			pstmt1.setBoolean(3, in_TorreControl.getEstado());
			pstmt1.setInt(4, in_TorreControl.getVida());
			pstmt1.setBoolean(5, in_TorreControl.getHayEnemigo());
			pstmt1.setInt(6, in_TorreControl.getRangoDeVision());
			pstmt1.executeUpdate();
			pstmt1.close();
			String insertBase = cons.insertarBase();
			PreparedStatement pstmt4 = ((Conexion) con).getConnection().prepareStatement(insertBase);
			pstmt4.setInt(1, getUltimoTorreId(con));
			pstmt4.setInt(2, getTanqueId(con));
			pstmt4.setInt(3, getUltimoTorreId(con));
			pstmt4.executeUpdate();
			pstmt4.close();
		} catch (SQLException e) {
			throw new PersistenciaException(mensg.errorSQLInsertBase);
		}

	}

	public Base find(int in_idBase, IConexion con) throws PersistenciaException {
		Base out_base = null;
		Deposito out_deposito = null;
		Avion[] arreavion = null;
		Artillero[] arreart = null;
		TanqueCombustible out_tanquecombustible = null;
		TorreControl out_torrecontrol = null;
		DaoDeAviones out_aviones = null;
		DaoArtilleria out_artilleros = null;
		Equipo out_equipo = null;

		try {
			consultas cons = new consultas();

			String query = cons.obtenerBase();
			PreparedStatement pstmt1 = ((Conexion) con).getConnection().prepareStatement(query);
			pstmt1.setInt(1, in_idBase);
			ResultSet rs1 = pstmt1.executeQuery();
			if (rs1.next()) {
				out_deposito = findDeposito(rs1.getInt(2), con);
				out_tanquecombustible = findTanqueCombustible(rs1.getInt(3), con);
				out_torrecontrol = findTorreControl(rs1.getInt(4), con);
				out_aviones = new DaoDeAviones(in_idBase);
				arreavion = out_aviones.listarAvionesXBase(con);
				out_aviones.setArreAviones(arreavion);
				out_artilleros = new DaoArtilleria(in_idBase);
				arreart = out_artilleros.listarArtilleriaXBase(con);
				out_artilleros.setArreArtilleria(arreart);
			}
			rs1.close();
			pstmt1.close();
			out_base = new Base(in_idBase, out_aviones, out_artilleros, out_deposito, out_tanquecombustible,
					out_torrecontrol);
		} catch (SQLException e) {
			throw new PersistenciaException(mensg.errorSQLFindBase);
		}
		return out_base;

	}

	public boolean estaVacia(IConexion con) throws PersistenciaException {
		boolean esta = false;
		try {
			consultas cons = new consultas();
			String query = cons.existeBase();
			PreparedStatement pstmt = ((Conexion) con).getConnection().prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				esta = true;
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			throw new PersistenciaException(mensg.errorSQLFindBase);
		}
		return esta;
	}

	public void delete(Integer key, IConexion con) throws PersistenciaException {

		try {
			consultas cons = new consultas();
			String borrarBase = cons.borrarbase();

			PreparedStatement prstm;
			prstm = ((Conexion) con).getConnection().prepareStatement(borrarBase);
			prstm.setInt(1, key);
			prstm.executeUpdate();
			prstm.close();
		} catch (SQLException e) {
			throw new PersistenciaException(mensg.errorSQLDeleteBase);
		}

	}

	public TreeMap<Integer, Base> listarBases(IConexion con) throws PersistenciaException {

		consultas cons = new consultas();
		Base out_base = null;
		Deposito out_deposito = null;
		Avion[] arreavion = null;
		Artillero[] arreart = null;
		TanqueCombustible out_tanquecombustible = null;
		TorreControl out_torrecontrol = null;
		DaoDeAviones out_aviones = null;
		DaoArtilleria out_artilleros = null;
		TreeMap<Integer, Base> tree_out = new TreeMap<Integer, Base>();
		String sqlToExecute = cons.listarBase();
		PreparedStatement prstm;
		try {
			prstm = ((Conexion) con).getConnection().prepareStatement(sqlToExecute);
			ResultSet rs = prstm.executeQuery();
			while (rs.next()) {
				int idBase = rs.getInt(1);
				out_deposito = findDeposito(rs.getInt(2), con);
				out_tanquecombustible = findTanqueCombustible(rs.getInt(3), con);
				out_torrecontrol = findTorreControl(rs.getInt(4), con);
				out_aviones = new DaoDeAviones(rs.getInt(1));
				arreavion = out_aviones.listarAvionesXBase(con);
				out_aviones.setArreAviones(arreavion);
				out_artilleros = new DaoArtilleria(rs.getInt(1));
				arreart = out_artilleros.listarArtilleriaXBase(con);
				out_artilleros.setArreArtilleria(arreart);

				out_base = new Base(idBase, out_aviones, out_artilleros, out_deposito, out_tanquecombustible,
						out_torrecontrol);
				tree_out.put(out_base.getIdDabse(), out_base);
			}
			rs.close();
			prstm.close();
		} catch (SQLException e) {
			throw new PersistenciaException(mensg.errorSQLListarBase);
		}

		return tree_out;

	}

	public int largoBases(IConexion con) throws PersistenciaException {

		int cant = 0;
		consultas cons = new consultas();

		String sqlToExecute = cons.cantidadTotalBases();
		PreparedStatement prstm;
		try {
			prstm = ((Conexion) con).getConnection().prepareStatement(sqlToExecute);
			ResultSet rs = prstm.executeQuery();
			if (rs.next()) {
				cant = rs.getInt(1);
			}
			rs.close();
			prstm.close();
		} catch (SQLException e) {
			throw new PersistenciaException(mensg.errorSQLFindBase);
		}
		return cant;
	}

	public int getUltimoTorreId(IConexion con) throws PersistenciaException {
		int cant = 0;
		consultas cons = new consultas();

		String sqlToExecute = cons.ultimaTorreId();
		PreparedStatement prstm;
		try {
			prstm = ((Conexion) con).getConnection().prepareStatement(sqlToExecute);
			ResultSet rs = prstm.executeQuery();
			if (rs.next()) {
				cant = rs.getInt(1);
			}
			rs.close();
			prstm.close();
		} catch (SQLException e) {
			throw new PersistenciaException(mensg.errorSQLFindEquipos);
		}
		return cant;

	}

	public int getTanqueId(IConexion con) throws PersistenciaException {
		int cant = 0;
		consultas cons = new consultas();

		String sqlToExecute = cons.ultimaTanqueId();
		PreparedStatement prstm;
		try {
			prstm = ((Conexion) con).getConnection().prepareStatement(sqlToExecute);
			ResultSet rs = prstm.executeQuery();
			if (rs.next()) {
				cant = rs.getInt(1);
			}
			rs.close();
			prstm.close();
		} catch (SQLException e) {
			throw new PersistenciaException(mensg.errorSQLFindEquipos);
		}
		return cant;

	}

	public int getDepositoId(IConexion con) throws PersistenciaException {
		int cant = 0;
		consultas cons = new consultas();

		String sqlToExecute = cons.ultimaDepositoId();
		PreparedStatement prstm;
		try {
			prstm = ((Conexion) con).getConnection().prepareStatement(sqlToExecute);
			ResultSet rs = prstm.executeQuery();
			if (rs.next()) {
				cant = rs.getInt(1);
			}
			rs.close();
			prstm.close();
		} catch (SQLException e) {
			throw new PersistenciaException(mensg.errorSQLFindEquipos);
		}
		return cant;

	}

	public int getUltimaIsBase(IConexion con) throws PersistenciaException {
		int cant = 0;
		consultas cons = new consultas();

		String sqlToExecute = cons.ultimaBaseID();
		PreparedStatement prstm;
		try {
			prstm = ((Conexion) con).getConnection().prepareStatement(sqlToExecute);
			ResultSet rs = prstm.executeQuery();
			if (rs.next()) {
				cant = rs.getInt(1);
			}
			rs.close();
			prstm.close();
		} catch (SQLException e) {
			throw new PersistenciaException(mensg.errorSQLFindEquipos);
		}
		return cant;

	}

	public Deposito findDeposito(int in_depositoId, IConexion con) throws PersistenciaException {
		Deposito Dep = null;
		consultas cons = new consultas();
		int Coordx;
		int Coordy;
		boolean estado;
		int vida;
		int cantidadDep;
		boolean enUso;

		try {
			String query = cons.obtenerDepositobyId();
			PreparedStatement pstmt1 = ((Conexion) con).getConnection().prepareStatement(query);
			pstmt1.setInt(1, in_depositoId);
			ResultSet rs1;
			rs1 = pstmt1.executeQuery();
			if (rs1.next()) {

				Coordx = rs1.getInt(2);
				Coordy = rs1.getInt(3);
				estado = rs1.getBoolean(4);
				vida = rs1.getInt(5);
				cantidadDep = rs1.getInt(6);
				enUso = rs1.getBoolean(7);
				Dep = new Deposito(in_depositoId, Coordx, Coordy, estado, vida, cantidadDep, enUso);
			}
			rs1.close();
			pstmt1.close();
		} catch (SQLException e) {
			throw new PersistenciaException(mensg.errorSQLFindDeposito);
		}

		return Dep;

	}

	public TanqueCombustible findTanqueCombustible(int in_tanqueCombustibleId, IConexion con)
			throws PersistenciaException {
		TanqueCombustible TQ = null;
		consultas cons = new consultas();
		int Coordx;
		int Coordy;
		boolean estado;
		int vida;
		int cantidadCombustible;
		boolean enUso;

		try {
			String query = cons.obtenerTanquebyId();
			PreparedStatement pstmt1 = ((Conexion) con).getConnection().prepareStatement(query);
			pstmt1.setInt(1, in_tanqueCombustibleId);
			ResultSet rs1;
			rs1 = pstmt1.executeQuery();
			if (rs1.next()) {

				Coordx = rs1.getInt(2);
				Coordy = rs1.getInt(3);
				estado = rs1.getBoolean(4);
				vida = rs1.getInt(5);
				cantidadCombustible = rs1.getInt(6);
				enUso = rs1.getBoolean(7);
				TQ = new TanqueCombustible(in_tanqueCombustibleId, Coordx, Coordy, estado, vida, cantidadCombustible,
						enUso);
			}
			rs1.close();
			pstmt1.close();
		} catch (SQLException e) {
			throw new PersistenciaException(mensg.errorSQLFindTanqueCombustible);
		}
		return TQ;
	}

	public TorreControl findTorreControl(int in_torreControlId, IConexion con) throws PersistenciaException {
		TorreControl TC = null;
		consultas cons = new consultas();
		int Coordx;
		int Coordy;
		boolean estado;
		int vida;
		boolean hayenemigo;
		int rangovision;

		try {
			String query = cons.obtenerTorreControlbyId();
			PreparedStatement pstmt1 = ((Conexion) con).getConnection().prepareStatement(query);
			pstmt1.setInt(1, in_torreControlId);
			ResultSet rs1;
			rs1 = pstmt1.executeQuery();
			if (rs1.next()) {

				Coordx = rs1.getInt(2);
				Coordy = rs1.getInt(3);
				estado = rs1.getBoolean(4);
				vida = rs1.getInt(5);
				hayenemigo = rs1.getBoolean(6);
				rangovision = rs1.getInt(7);
				TC = new TorreControl(in_torreControlId, Coordx, Coordy, estado, vida, hayenemigo, rangovision);
			}
			rs1.close();
			pstmt1.close();
		} catch (SQLException e) {
			throw new PersistenciaException(mensg.errorSQLFindTorreControl);
		}
		return TC;
	}

}
