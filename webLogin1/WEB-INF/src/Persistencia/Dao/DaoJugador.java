package Persistencia.Dao;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import Logica.Vo.VOJugador;
import Persistencia.Consultas.Queries;
public class DaoJugador {
	//Data Access Object


private Connection connection;
	    
	    
	    //Conectar a la Base de datos
	    public void Connect() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException 
	    {
	    	
	    	connection = connect();
	    
	    }
	    //Desconectar a la Base de datos
	    public void disconnect() throws SQLException, ClassNotFoundException
	    {
	        connection.close();
	    }
	    
	    //Metodo para consultar si un email y contraseñan pertenecen a una cuenta registrada
	    public boolean isAcountExists(String email, String password) throws SQLException
	    {
	    	boolean exist=false;
	    	Queries Consult = new Queries();
	    	String query = Consult.belongEmailPassword();
	    //	connection.setTransactionIsolation (Connection.TRANSACTION_SERIALIZABLE);
	    	//connection.setAutoCommit (false);
	    	PreparedStatement ps;
			try {
				ps = connection.prepareStatement (query);
				ps.setString(1, email);
		    	ps.setString(2, password);
		    	ResultSet rs = ps.executeQuery();
		    	exist= rs.next();
		    	//connection.commit ();
		    	rs.close();
		    	ps.close();
				// probar a ver si anda
				
			} catch (SQLException e) {
			//	connection.rollback ();
				
			}
	    	
	        return exist;
	    }
	    
	    //Metodo para consultar si el email recibido ya esta registrado
	    public boolean isEmailRegistered(String email) throws SQLException
	    {	
	    	boolean registered=false;
	    	Queries Consult= new Queries();
	    	String query = Consult.existsEmail();
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setString(1, email);
	        ResultSet rs = ps.executeQuery();
	        registered= rs.next();
	        rs.close();
	        ps.close();
	        return registered;
	    }
	    
	    //Metodo para registrar una cuenta
	    public void registerUser(String email, String password, String name) throws SQLException
	    {
	    	Queries Consult= new Queries();
	        String query =Consult.insertUser(); 
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setString(1, email);
	    	ps.setString(2, password);
	    	ps.setString(3, name);
	        
	        ps.executeUpdate();
	        ps.close();
	    }
	    
	    
	    public String getNameByEmail(String email) throws SQLException
	    {	String Name ="";
	    	Queries Consult= new Queries();
	        String query = Consult.getNameUser();
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setString(1,email );
	        ResultSet rs = ps.executeQuery();
	        if (rs.next())
	        {
	        	Name=rs.getString("name");
	        }
	        
	        rs.close();
	        ps.close();
	        return Name;
	        
	    }
	    public ArrayList<VOJugador> allUsers() throws SQLException
	    {	
	    	ArrayList<VOJugador> arre= new ArrayList<VOJugador>();
	    	Queries Consult= new Queries();
	    	String query = Consult.allUsersQuery();
	    	PreparedStatement ps = connection.prepareStatement(query);
	    	ResultSet rs = ps.executeQuery();
	    	while(rs.next()){
	    		VOJugador nuevo= new VOJugador(rs.getString("name"),rs.getInt("id"), rs.getString("email") );
	    		
	    		arre.add(nuevo);
	    		
                }
	    	
	    	rs.close();
	        ps.close();
	    	
	    	return arre;
	        
	    }
	    
	    private static Connection connect() throws FileNotFoundException, IOException, ClassNotFoundException, SQLException 
		{
			Properties p = new Properties();
			String nomArch = "C:\\Users\\pepe\\eclipse-workspace\\webLogin1\\config.properties";
			//String nomArch ="config.properties";
			p.load(new FileInputStream(nomArch));
			String driver = p.getProperty("driver");
			String url = p.getProperty("url");
			String user = p.getProperty("user");
			String password = p.getProperty("password");
			Class.forName(driver);
			Connection connec = DriverManager.getConnection(url, user, password);
			return connec;
			
		}
	    
	  
	
	
}
