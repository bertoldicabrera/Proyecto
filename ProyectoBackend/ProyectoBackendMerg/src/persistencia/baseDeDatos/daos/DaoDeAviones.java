package persistencia.baseDeDatos.daos;

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

public class DaoDeAviones {
	public static MensajesPersonalizados mensg = new MensajesPersonalizados();
	
	private int tope= 4;
	private Avion[]arreavion;
	int baseId;
	
	public DaoDeAviones() {
		arreavion= new Avion[tope];
		}
	public DaoDeAviones(int in_idBase) {
		this.baseId=in_idBase;
		this.arreavion= new Avion[tope];
		}
		
	
		public boolean estaVacia(IConexion con) throws PersistenciaException {
			boolean esta = false;
			try{
				consultas cons = new consultas();
				String query = cons.existenAviones();
				PreparedStatement pstmt = ((Conexion) con).getConnection().prepareStatement (query);
				ResultSet rs = pstmt.executeQuery ();
				if (rs.next ())
					esta = true;
				rs.close ();
				pstmt.close ();
			}catch (SQLException e){
				throw new PersistenciaException (mensg.errorSQLFindAvion);
			}
			return esta;
		} 
		
		
	
		public void insback(int idbase,Avion in_Avion, IConexion con) throws PersistenciaException {
			try{
				consultas cons = new consultas();
				String insert = cons.insertarAvion();
				
				PreparedStatement pstmt = ((Conexion) con).getConnection().prepareStatement (insert);
				pstmt.setInt(1, idbase);
				pstmt.setInt(2, in_Avion.GetId());
				pstmt.setInt (3,in_Avion.getCoordX());
				pstmt.setInt (4, in_Avion.getCoordY());
				pstmt.setBoolean(5, in_Avion.getEstado());
				pstmt.setInt (6, in_Avion.getVida());
				pstmt.setFloat(7,in_Avion.getAvionAngle());
				pstmt.setBoolean(8, in_Avion.getAvionBomba());
				pstmt.setInt (9, in_Avion.getCantidadBombas());
				pstmt.setInt (10, in_Avion.getAvionAltura());
				pstmt.setInt (11, in_Avion.getAvionCombustible());
				pstmt.setBoolean(12, in_Avion.getHayEnemigo());
				pstmt.setBoolean(13, in_Avion.getEnCampoEnemigo());
				pstmt.setInt (14, in_Avion.getRangoDeVision());
				
				pstmt.executeUpdate ();
				pstmt.close ();
			}
			catch (SQLException e)
			{
			throw new PersistenciaException (mensg.errorSQLInsertAvion);
			}
		}
		
		
		public Avion[] listarAviones(IConexion con) throws PersistenciaException {
			consultas cons = new consultas();
			//List<Avion> lista_out = new ArrayList<Avion>();
			String sqlToExecute = cons.listarAviones();
			PreparedStatement prstm;
			try {
				prstm = ((Conexion) con).getConnection().prepareStatement(sqlToExecute);
				ResultSet rs = prstm.executeQuery();
				int i=0;
				while ((rs.next())&&(i<tope)) {
					Avion out_av = new Avion(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getBoolean(4), rs.getInt(5),
							                rs.getFloat(6),rs.getBoolean(7),rs.getInt(8),rs.getInt(9),rs.getInt(10),
							                rs.getBoolean(11),rs.getBoolean(12),rs.getInt(13));
					arreavion[i]=out_av ;	
							  i++;               
					//lista_out.add(out_av);
				}
				rs.close();
				prstm.close();
			} catch (SQLException e) {
				throw new PersistenciaException (mensg.errorSQLListarAviones);
			}
			
			
			return arreavion;
		}
		
		
		public Avion[] listarAvionesXBase(IConexion con) throws PersistenciaException {
			consultas cons = new consultas();
			//List<Avion> lista_out = new ArrayList<Avion>();
			String sqlToExecute = cons.obtenerAvionXBase();
			PreparedStatement prstm;
			try {
				prstm = ((Conexion) con).getConnection().prepareStatement(sqlToExecute);
				 prstm.setInt(1, this.baseId);
				 ResultSet rs = prstm.executeQuery();
				int i=0;
				while ((rs.next())&&(i<tope)) {
					Avion out_av = new Avion(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getBoolean(4), rs.getInt(5),
							                rs.getFloat(6),rs.getBoolean(7),rs.getInt(8),rs.getInt(9),rs.getInt(10),
							                rs.getBoolean(11),rs.getBoolean(12),rs.getInt(13));
					arreavion[i]=out_av ;	
							  i++;               
					//lista_out.add(out_av);
				}
				rs.close();
				prstm.close();
			} catch (SQLException e) {
				throw new PersistenciaException (mensg.errorSQLListarAviones);
			}
			
			
			return arreavion;
		}
		
		
		
		public Avion kesimo(int index, IConexion con) throws PersistenciaException {
			Avion avion = null;			
			int id = 0;
            int coordX = 0;
            int coordY = 0;
            boolean estado = false;
            int vida = 0;
			float avionAngle = 0;
			boolean avionBomba = false;
			int  cantidadBombas = 0;
			int avionAltura = 0;
			int  avionCombustible = 0;
			boolean  hayEnemigo = false;
			boolean enCampoEnemigo = false;
			int rangoDeVision = 0;
			Avion[]arregavion= new Avion[tope];
			
			try{
				int ind=0;
				
				consultas cons = new consultas ();
			    
				String query = cons.obtenerAvionXBase();
				PreparedStatement pstmt1 = ((Conexion) con).getConnection().prepareStatement (query);
				pstmt1.setInt(1, this.baseId);
				ResultSet rs1 = pstmt1.executeQuery ();
				while ((rs1.next ()) &&(ind<tope)){
					id = rs1.getInt(1);
					coordX = rs1.getInt(2);
					coordY = rs1.getInt(3);
					estado = rs1.getBoolean(4);
					vida= rs1.getInt(5);
					avionAngle=rs1.getFloat(6);
					avionBomba=rs1.getBoolean(7);
					cantidadBombas=rs1.getInt(8);
					avionAltura=rs1.getInt(9);
					avionCombustible=rs1.getInt(10);
					hayEnemigo=rs1.getBoolean(11);
					enCampoEnemigo=rs1.getBoolean(12);
					rangoDeVision=rs1.getInt(13);
					avion = new Avion (id, coordX, coordY, estado, vida,avionAngle,avionBomba,cantidadBombas,avionAltura,avionCombustible,
					           hayEnemigo,enCampoEnemigo,rangoDeVision);
					arregavion[ind]=avion;
					
						
				}
				rs1.close ();
				pstmt1.close ();
				
				}
			catch (SQLException e){
				throw new PersistenciaException (mensg.errorSQLFindAvion);
			
			}
			return arregavion[index] ;
		}
		
		
		public void setArreAviones(Avion[] in_arreavion) {
			this.arreavion = in_arreavion;
		}
		
		
		
	}


