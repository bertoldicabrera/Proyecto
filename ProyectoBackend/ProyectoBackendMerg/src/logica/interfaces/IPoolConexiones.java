package logica.interfaces;

import persistencia.baseDeDatos.poolDeConexiones.*;
import persistencia.excepciones.PersistenciaException;
				
public interface IPoolConexiones {

	public IConexion obtenerConexion(boolean aux) throws PersistenciaException, InterruptedException;

	public void liberarConexion(IConexion ic, boolean aux) throws PersistenciaException;
}

