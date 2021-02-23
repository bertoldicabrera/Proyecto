package persistencia.baseDeDatos.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Utilitarios.MensajesPersonalizados;
import logica.Artillero;
import logica.Base;
import logica.Equipo;
import logica.Jugador;
import persistencia.baseDeDatos.consultas.consultas;
import persistencia.baseDeDatos.poolDeConexiones.Conexion;
import persistencia.baseDeDatos.poolDeConexiones.IConexion;
import persistencia.excepciones.PersistenciaException;

public class DaoEquipo {
	private int idpartida;
	private int tope=2;
	private Equipo[] equipos;
	public static MensajesPersonalizados mensg = new MensajesPersonalizados();
	
	

	public DaoEquipo() {
		equipos = new Equipo[tope];
		
	}
	
	public DaoEquipo(int in_idpartida, Equipo[] in_EQS) {
		this.idpartida=in_idpartida;
		this.equipos = in_EQS;
		
	}
	
	
	
	public void  insBack ( int in_idPartida,Equipo in_Equipo,int in_idBase, IConexion con) throws PersistenciaException {
		 int equipoID=getUltimoEquipoId(con);
		 Jugador[] jugAux=in_Equipo.getJugadores();
		 consultas cons = new consultas();
		 String insert = cons.insertarEquipo();
		 String bando=in_Equipo.getBando();
		 
		for(int i=0;i<jugAux.length;i++) {
			
			     PreparedStatement pstmt;
			     try {
				pstmt = ((Conexion) con).getConnection().prepareStatement (insert);
				pstmt.setInt(1,equipoID);
				pstmt.setInt(2,jugAux[i].getJugadorId());
				pstmt.setString(3,bando);
				pstmt.setInt(4,in_idPartida);
				pstmt.setInt(5,in_idBase);

				
				pstmt.executeUpdate ();
				pstmt.close ();
		
			} catch (SQLException e) {
				throw new PersistenciaException (mensg.errorSQLInsertEquipos);
			}
			
		}
		
	}

	
	
	
	public int largo ( IConexion con) throws PersistenciaException
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
	
	public Equipo kesimo (int in_equipoID, IConexion con) throws PersistenciaException
	{
		Equipo out_Equipo = null;
		
		//Falta todo
		
		
		try{
			consultas cons = new consultas ();
		
			String query = cons.obtenerKesimoEquipo();
			PreparedStatement pstmt1 = ((Conexion) con).getConnection().prepareStatement (query);
			pstmt1.setInt (1, in_equipoID);
			ResultSet rs1 = pstmt1.executeQuery ();
			if (rs1.next ()){
//				out_id= rs1.getInt(1);
//				out_coordX= rs1.getInt(2);
//				out_coordY= rs1.getInt(3);
//				
//				out_estado= rs1.getBoolean(4);
//				out_vida= rs1.getInt(5);
//				out_hayEnemigo= rs1.getBoolean(6);
//				
//				out_rangoDeVision= rs1.getInt(7);
//				out_avionAngle= rs1.getFloat(8);
//				
				
			}
			rs1.close ();
			pstmt1.close ();
			out_Equipo = new Equipo ();
			}
		catch (SQLException e){
			throw new PersistenciaException (mensg.errorSQLFindEquipos);
		}
		return out_Equipo;
	}
	


	public Equipo[]  listarEquipos( IConexion con) throws PersistenciaException
	
	{
        consultas cons = new consultas();
		
		String sqlToExecute = cons.listarEquipos();
		PreparedStatement prstm;
		try {
			prstm = ((Conexion) con).getConnection().prepareStatement(sqlToExecute);
			ResultSet rs = prstm.executeQuery();
			int i=0;
			while (rs.next()) {

				Equipo out_av = new Equipo();
				equipos[i]=out_av ;	
			 i++;               
			}
			rs.close();
			prstm.close();
		} catch (SQLException e) {
			throw new PersistenciaException (mensg.errorSQLFindEquipos);
		}
		return equipos;
	}

	public int getIdpartida() {
		return idpartida;
	}

	public void setIdpartida(int idpartida) {
		this.idpartida = idpartida;
	}

	public int getUltimaIsBase(IConexion con) throws PersistenciaException {
		int cant=0;
		consultas cons = new consultas();
		
		String sqlToExecute = cons.ultimaBaseID();
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
	
	
	public int getUltimoEquipoId(IConexion con) throws PersistenciaException {
		int cant=0;
		consultas cons = new consultas();
		
		String sqlToExecute = cons.ultimaEquipoId();
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

public DaoEquipo listarEquiposDeUnaPartida(int in_idpartida, IConexion con) throws PersistenciaException
	{
	
	    DaoEquipo aux=null;
        consultas cons = new consultas();
        Equipo[] out_Equipos=new  Equipo[tope];
        
		
		String sqlToExecute = cons.listarEquiposDeUnaPartida();
		PreparedStatement prstm;
		try {
			prstm = ((Conexion) con).getConnection().prepareStatement(sqlToExecute);
			ResultSet rs = prstm.executeQuery();
			int i=0;
			while (rs.next()) {

				Equipo out_av = new Equipo();
				out_Equipos[i]=out_av ;	
			 i++;               
			}
			aux=new DaoEquipo(in_idpartida,out_Equipos);
			
			rs.close();
			prstm.close();
		} catch (SQLException e) {
			throw new PersistenciaException (mensg.errorSQLFindEquipos);
		}
		
		return aux;
	}
	


	

}
