package persistencia.baseDeDatos.daos;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Utilitarios.MensajesPersonalizados;
import logica.Base;
import logica.Equipo;
import logica.Jugador;
import persistencia.baseDeDatos.consultas.consultas;
import persistencia.baseDeDatos.poolDeConexiones.Conexion;
import persistencia.baseDeDatos.poolDeConexiones.IConexion;
import persistencia.excepciones.PersistenciaException;

public class DaoEquipo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idpartida;
	private int tope=2;
	private Equipo[] equipos;
	private DaoJugador DaoJ;
	private DaoBase   DaoB;
	public static MensajesPersonalizados mensg = new MensajesPersonalizados();
	
	
	public DaoEquipo() {
		equipos = new Equipo[tope];
		
	}
	
	public DaoEquipo(int in_idpartida, Equipo[] in_EQS) {
		
		this.idpartida=in_idpartida;
		this.equipos = in_EQS;
		
	}
	
	public DaoJugador getDaoJugador() {
		return this.DaoJ;
	}
	
	public DaoBase getDaoBase() {
		return this.DaoB;
	}
	
	
	public Equipo[] getEquiposEnMemoria() {
		return this.equipos;
		
	}
	
	
	
	
	
	
public DaoEquipo(int in_idpartida, Equipo[] in_Equipos, DaoJugador in_DaoJ,DaoBase   in_DaoB ) {
		
		this.idpartida=in_idpartida;
		this.equipos = in_Equipos;
		this.DaoJ= in_DaoJ;
		this.DaoB = in_DaoB;
		
	}
	
	
	
	
	public void  insBack ( int in_idPartida,Equipo in_Equipo, IConexion con) throws PersistenciaException {
	//	System.out.println("Insback equipo in_idPartida" + in_idPartida);
		DaoBase aux= new DaoBase();
		 int idbase = aux.getUltimaBaseID(con);
	//	int equipoID=getUltimoEquipoIdMas1(con);
		 System.out.println("Linea 71 daoequipo ");
		 
		 Jugador[] jugAux=in_Equipo.getJugadores();
		 
		 consultas cons = new consultas();
		 String insertEquipo = cons.insertarEquipo();
		 
		 String insertEquipoJugadores=cons.InsertarEquipoJugador();
		 
		 String bando=in_Equipo.getBando();
		 
		for(int i=0;i<jugAux.length;i++) {
			
			
			     PreparedStatement pstmt1,pstmt2;
			     try {
			    	 //Inserto Equipo
				pstmt1 = ((Conexion) con).getConexion().prepareStatement (insertEquipo);
				pstmt1.setString(1,bando);
				pstmt1.setInt(2,in_idPartida);
				//pstmt1.setInt(3,in_Equipo.getBase().getIdBase());
				pstmt1.setInt(3,idbase);
                pstmt1.executeUpdate ();
				pstmt1.close ();
			
				//Inserto EquipoJugador
				pstmt2 = ((Conexion)con).getConexion().prepareStatement (insertEquipoJugadores);
				pstmt2.setInt(1,jugAux[i].getJugadorId());
				pstmt2.setInt(2,getUltimoEquipoIdMas1(con));
				pstmt2.executeUpdate ();
				pstmt2.close ();
		
			} catch (SQLException e) {
				throw new PersistenciaException (e.toString());
				//throw new PersistenciaException (mensg.errorSQLInsertEquipos);
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
			prstm = ((Conexion) con).getConexion().prepareStatement(sqlToExecute);
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
	
	public Equipo kesimo (int in_indexEQ, IConexion con) throws PersistenciaException
	{    DaoJ=new  DaoJugador();
	     DaoB=new DaoBase();
		Equipo out_Equipo = null;
		Jugador out_Jugador=null;
		Base out_base= null;
	    Jugador [] arreJugador=new Jugador [1] ;
	    String out_bando="";
	    List<Equipo> out_Equipos = new ArrayList<Equipo>();
		
		
		try{
			consultas cons = new consultas ();
		
			String query = cons.listarEquiposDeUnaPartida();
			PreparedStatement pstmt1 = ((Conexion) con).getConexion().prepareStatement (query);
			pstmt1.setInt (1, this.idpartida);
			ResultSet rs1 = pstmt1.executeQuery ();
		
			int ind=0;
			while (rs1.next ()){
 				int out_idEquipo= rs1.getInt(1);
 				out_Jugador=DaoJ.find(rs1.getInt(2), con);
 				arreJugador[ind]=out_Jugador;
 				DaoB = new DaoBase();
				out_base=DaoB.find(rs1.getInt(3), con);
				out_bando=rs1.getString(4);
				out_Equipo=new Equipo(out_idEquipo,arreJugador,out_base,out_bando);
				out_Equipos.add(out_Equipo);
				ind++;
				System.out.println("Ver dentro de DaoEquipo linea 168 esto esta raro");
				}
			rs1.close ();
			pstmt1.close ();
			
			}
		catch (SQLException e){
			throw new PersistenciaException (mensg.errorSQLFindEquipos);
		}
		return out_Equipos.get(in_indexEQ);
	}
	


	public Equipo[]  listarEquipos( IConexion con) throws PersistenciaException
	
	{
        consultas cons = new consultas();
		
		String sqlToExecute = cons.listarEquipos();
		PreparedStatement prstm;
		try {
			prstm = ((Conexion) con).getConexion().prepareStatement(sqlToExecute);
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

	
	

	
	
	public int getUltimoEquipoIdMas1(IConexion con) throws PersistenciaException {
		int cant=0;
		consultas cons = new consultas();
		
		String sqlToExecute = cons.ultimaEquipoId();
		PreparedStatement prstm;
		try {
			prstm = ((Conexion) con).getConexion().prepareStatement(sqlToExecute);
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
			
			prstm = ((Conexion) con).getConexion().prepareStatement(sqlToExecute);
			prstm.setInt (1, in_idpartida);
			ResultSet rs = prstm.executeQuery();
			int i=0;
			System.out.println("hay cosas mal entrar a daoequipo 267");
			DaoBase auxdaobase= new DaoBase();
			while (rs.next()) {
//int in_equipoID, Jugador[]  in_Jugadores, Base  in_base, String  in_bando 
				//sql trae PK_equipo_id, equipoBando, FK_partida_id, FK_base_id
				
				
				Base auxiliarbase= auxdaobase.find(rs.getInt(4), con);
				
				
				Equipo out_av = new Equipo(
						rs.getInt(1),
						devolverJugadoresDeunEquipo(rs.getInt(1),con), //jugadores de un equipo dado un id de equipo me devuel 777
						auxiliarbase,
						rs.getString(2)
						); 
				out_Equipos[i]=out_av ;	
			 i++;               
			}
			aux=new DaoEquipo(in_idpartida,out_Equipos);
			
			rs.close();
			prstm.close();
			
		
		} catch (SQLException e) {
			
			System.out.println(e.toString());
			throw new PersistenciaException (mensg.errorSQLFindEquipos);
		}
		System.out.println("salgo de la 292 daoequipo");
		return aux;
	}
	
private  Jugador[] devolverJugadoresDeunEquipo(int in_EquipoId, IConexion con ) throws PersistenciaException {
	 Jugador[] arreJug= new Jugador[1];
	consultas cons = new consultas();
	
	String sqlToExecute = cons.jugadoresDeunEquipo();
	PreparedStatement prstm;
	try {
		prstm = ((Conexion) con).getConexion().prepareStatement(sqlToExecute);
		prstm.setInt(1, in_EquipoId);
		ResultSet rs = prstm.executeQuery();
		int x=0;
		while (rs.next()) {
		
			Jugador auxjugador= new Jugador( 
					rs.getInt(1),
					rs.getString(2),
					rs.getString(3), 
					rs.getBoolean(4),
					rs.getInt(5)
					
					);
			arreJug[x]=auxjugador;
			
			x++;
			
		}
		rs.close();
		prstm.close();
		System.out.println("sale de la 324");
	} catch (SQLException e) {
		throw new PersistenciaException (mensg.errorSQLFindEquipos+e.toString());
	}
	
	return arreJug;
	
	
}


	

}
