package Persistencia.Factory;

import Persistencia.Dao.*;


public class FabricaMySQL implements IFabricaAbstracta{

	public IDaoJugador crearIDaoJugador() {
		return new DaoJugador();
	}

	//fernando: verificar que fue necesario crear un constructor sin la cedula para la fabrica
//	public IDaoJugador crearIDaoJugador(String email) {
//		return null;// new DaoJugador(email);
//	}

	
	
}
