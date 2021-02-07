package Persistencia.Poll;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.sun.xml.internal.bind.CycleRecoverable.Context;

import Logica.Vo.VOClientes;

public class PoolConexiones {

	
	private String driver;
	private String url; 
	private String user; 
	private String password;
	private int nivelTransaccionalidad;
	private Conexion[] conexiones;
	private int tamanio =10;
	private int creadas=0;
	private int tope=0;
	
	
	
	
	
	public PoolConexiones() throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
		
		
		
		conexiones= new Conexion[tamanio];
		
		
		 
		
		try {
			// nombre del recurso en el context.xml
			InitialContext ctx = null;
			ctx =  new InitialContext();
			
			
			
			 DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/web");
			
			Connection con = ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public IConexion obtenerConexion (boolean modifica)
	{IConexion con=null;
	
		if (modifica==true)
		{
			//modifica
		}else
		{
			//no modifica
		}
		return con;
	}
	public void  liberarConexion (IConexion con, boolean ok) {
	
	}
	
	
	
	
}
