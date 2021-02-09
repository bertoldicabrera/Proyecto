package Persistencia.Factory;

import java.io.IOException;

import Persistencia.Dao.*;


public interface IFabricaAbstracta {

	public IDaoJugador crearIDaoJugador();
	//public IDaoJugador crearIDaoJugador(String email);
	
}


