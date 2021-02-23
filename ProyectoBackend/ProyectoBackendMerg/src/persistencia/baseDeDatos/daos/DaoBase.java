package persistencia.baseDeDatos.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import Utilitarios.MensajesPersonalizados;
import logica.Base;
import logica.Jugador;
import persistencia.baseDeDatos.consultas.consultas;
import persistencia.baseDeDatos.poolDeConexiones.Conexion;
import persistencia.baseDeDatos.poolDeConexiones.IConexion;
import persistencia.excepciones.PersistenciaException;

public class DaoBase {
	
	public static MensajesPersonalizados mensg = new MensajesPersonalizados();
	private TreeMap<Integer,Base> bases; 
	
	public DaoBase() {
		bases=new TreeMap<Integer,Base>();
	}
	
	public boolean member(Integer key,IConexion con ) throws PersistenciaException {
		
		
		
			boolean existe = false;
			try{
				consultas cons = new consultas();
				String query = cons.esmemberbase();
				PreparedStatement pstmt = ((Conexion) con).getConnection().prepareStatement (query);
				pstmt.setInt(1, key); 
				ResultSet rs = pstmt.executeQuery ();
				if (rs.next ())
					existe = true;
				rs.close ();
				pstmt.close ();
			}catch (SQLException e){
				throw new PersistenciaException (mensg.errorSQLFindBase);
			}
			return existe;

	}

	public void insert( Base ba, IConexion con) throws PersistenciaException {
		int hola;
		////Base test= new Base(hola);
		try{
			consultas cons = new consultas();
			String insert = cons.insertarBase();
			PreparedStatement pstmt = 
					((Conexion) con).getConnection().prepareStatement (insert);
			pstmt.setInt(1,  ba.getIdDabse());
			
			
			//id de aviones, idartilleros
//			pstmt.setInt (2, ba.getAviones());
			
			
			
			pstmt.executeUpdate ();
			pstmt.close ();
			
			
			
		}
		catch (SQLException e)
		{
		throw new PersistenciaException (mensg.errorSQLInsertBase);
		}
		
		
		
	}

	public Base find(Integer key, IConexion con) throws PersistenciaException {
		Base base=null;
		try{
			consultas cons = new consultas ();
		
			String query = cons.obtenerBase();
			PreparedStatement pstmt1 = ((Conexion) con).getConnection().prepareStatement (query);
			pstmt1.setInt(1, key);
			ResultSet rs1 = pstmt1.executeQuery ();
			if (rs1.next ()){
//				jugadorId = rs1.getInt(1);
//				jugadorUserName = rs1.getString(2);
//				jugadorPassword = rs1.getString(3);
//				jugadorIsOnline = rs1.getBoolean(4);
//				puntajeAcumulado= rs1.getInt(5);
				
				
			}
			rs1.close ();
			pstmt1.close ();
			 base = new Base (); //Poner las variables 
			}
		catch (SQLException e){
			throw new PersistenciaException (mensg.errorSQLFindBase);
		}
		return base;
		
		
		
		
		
		
		
		
		
	}

	public boolean estaVacia(IConexion con) throws PersistenciaException {
		boolean esta = false;
		try{
			consultas cons = new consultas();
			String query = cons.existeBase();
			PreparedStatement pstmt = ((Conexion) con).getConnection().prepareStatement (query);
			ResultSet rs = pstmt.executeQuery ();
			if (rs.next ())
				esta = true;
			rs.close ();
			pstmt.close ();
		}catch (SQLException e){
			throw new PersistenciaException (mensg.errorSQLFindBase);
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
			throw new PersistenciaException (mensg.errorSQLDeleteBase);
		}
		
		
		
	}

	public TreeMap<Integer, Base> listarBases(IConexion con) throws PersistenciaException {
		
		consultas cons = new consultas();
		TreeMap<Integer, Base> tree_out = new TreeMap<Integer, Base>();
		String sqlToExecute = cons.listarBase();
		PreparedStatement prstm;
		try {
			prstm = ((Conexion) con).getConnection().prepareStatement(sqlToExecute);
			ResultSet rs = prstm.executeQuery();
			while (rs.next()) {
				
				Base base = new Base();//Poner Variables
				tree_out.put(base.getIdDabse(), base);
			}
			rs.close();
			prstm.close();
		} catch (SQLException e) {
			throw new PersistenciaException (mensg.errorSQLListarBase);
		}
		
		
		return tree_out;

		

	}

	public int largoBases(IConexion con) throws PersistenciaException {
		
		int cant=0;
		consultas cons = new consultas();
		
		String sqlToExecute = cons.cantidadTotalBases();
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
			throw new PersistenciaException (mensg.errorSQLFindBase);
		}
		return cant;
	}
	}

	


