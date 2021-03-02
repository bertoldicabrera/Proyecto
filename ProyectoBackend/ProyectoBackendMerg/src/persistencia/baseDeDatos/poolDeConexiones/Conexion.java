package persistencia.baseDeDatos.poolDeConexiones;

import java.sql.Connection;

import java.io.Serializable;

public class Conexion implements IConexion,Serializable  {

	private static final long serialVersionUID = 4649443822404914037L;
	Connection con;

	public Conexion () {
		con=null;
	};
	
	public Conexion (Connection c) {
		con=c;
	};
	
	public Connection getConexion () {
		return con;
	}
}