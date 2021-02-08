package Logica.Interfaz;

import Persistencia.Poll.IConexion;

				
public interface IPoolConexiones {

	public IConexion obtenerConexion(boolean modifica);
	
    public void liberarConexion(IConexion con, boolean ok);
		
		
	
	
}
