package logica.valueObjects;

import java.io.Serializable;
import java.util.TreeMap;

import logica.Jugador;



public class VOCollectionJugador  implements Serializable  {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
TreeMap<Integer, VOJugador> jugadores;
 
	public VOCollectionJugador() {
		
		jugadores= new TreeMap<Integer, VOJugador>();
	}
	public boolean member(int key) {
		return jugadores.containsKey(key);

	}

	public void insert(int key, VOJugador jug) {

		jugadores.put(key, jug);
	}

	public VOJugador find(int key) {
		return jugadores.get(key);
	}

	public boolean estaVacio() {
		return jugadores.isEmpty();
	}

	public void delete(int key) {
		jugadores.remove(key);
	}

	public TreeMap<Integer, VOJugador> listarJugadores() {

		return jugadores;

	}

	public int largoJugadores() {

		return jugadores.size();
	}

	public void setJugadores(TreeMap<Integer, VOJugador> jugadores) {
		this.jugadores = jugadores;
	}
	
	
	
}
