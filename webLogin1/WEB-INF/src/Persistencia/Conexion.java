package Persistencia;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import Persistencia.Poll.IConexion;

public abstract class Conexion  implements IConexion{

	
	public Connection connection;
	
	
	
	public Conexion(Connection con) {
	}

	
	

    //Desconectar a la Base de datos
    public abstract Connection getConnection();

 


	
}
