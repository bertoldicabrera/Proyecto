package PersistenciaSql;

import java.rmi.Remote;
import java.sql.Connection;

public interface IConexion extends Remote {

	public void Connect(Connection con);
	public Connection getConnection();
}
