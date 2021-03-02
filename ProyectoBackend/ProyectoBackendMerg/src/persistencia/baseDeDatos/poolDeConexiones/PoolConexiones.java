package persistencia.baseDeDatos.poolDeConexiones;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import Utilitarios.MensajesPersonalizados;
import Utilitarios.SystemProperties;
import logica.interfaces.IPoolConexiones;
import persistencia.excepciones.PersistenciaException;

public class PoolConexiones implements IPoolConexiones, Serializable {

	private static final long serialVersionUID = 1L;
	private String driver="";
	private String url="";
	private String user="";
	private String pass="";
	private Conexion arre_conexiones[];
	private boolean auto_Commit;
	private int tamanio;
	private int creadas;
	private int tope;
	
	private SystemProperties sp = new SystemProperties();
	public static MensajesPersonalizados mensg = new MensajesPersonalizados();

	public PoolConexiones() throws PersistenciaException, FileNotFoundException, IOException, ClassNotFoundException {
		/*
		 * Constructor de la clase. Realiza la carga del driver, solicita memoria para
		 * el arreglo con tope e inicializa los distintos atributos.
		 */
//		Properties p = new Properties();
//		String nomArch = "config/config.properties";
//		p.load(new FileInputStream(nomArch));
		driver = sp.getMysql_driver();
		System.out.println("imprime"+driver.toString());
		
		tamanio = Integer.parseInt(sp.getTamPool());
		url = sp.getMysql_url();
		user = sp.getMysql_user();
		pass = sp.getMysql_password();
		// nivelTransaccionalidad=Integer.parseInt(p.getProperty("nivelTrans"));
		auto_Commit = Boolean.parseBoolean(sp.getAuto_Commit());
		creadas = 0;
		tope = 0;
		arre_conexiones = new Conexion[tamanio];

		for (int i = 0; i < tamanio; i++) {
			arre_conexiones[i] = new Conexion();
		}
		Class.forName(driver);
	}

	@Override
	public synchronized IConexion obtenerConexion(boolean aux) throws PersistenciaException, InterruptedException {
		/*
		 * Solicita una conexión al pool. En caso de que todas estén actualmente en uso,
		 * bloqueará al usuario hasta que otro usuario libere alguna
		 */
		IConexion icon = null;
		while (icon == null) {

			if (tope > 0) {
				icon = (IConexion) arre_conexiones[tope - 1];
				tope--;
			} else {
				if (creadas < tamanio) {
					try {
						Connection con_aux = DriverManager.getConnection(url, user, pass);
						con_aux.setAutoCommit(auto_Commit);
						con_aux.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
						icon = (IConexion) new Conexion(con_aux);
						creadas++;
					} catch (SQLException e) {
						String texto = "Error al acceder a la base";
						throw new PersistenciaException(texto);
					}
				} else {
					wait();
				}
			}
		}
		return icon;
	}

	@Override
	public synchronized void liberarConexion(IConexion ic, boolean aux) throws PersistenciaException {
		/*
		 * Devuelve una conexión al pool y avisa a posibles usuarios bloqueados. Si ok
		 * vale true, hará commit al devolverla, sino hará rollback.
		 */
		try {
			if (aux) {
				(((Conexion) ic).getConexion()).commit();
			} else {
				(((Conexion) ic).getConexion()).rollback();
			}
			arre_conexiones[tope] = ((Conexion) ic);
			tope++;
			notify();
		} catch (SQLException e) {
			String texto = "Error al acceder a la base";
			throw new PersistenciaException(texto);
		}
	}
}
