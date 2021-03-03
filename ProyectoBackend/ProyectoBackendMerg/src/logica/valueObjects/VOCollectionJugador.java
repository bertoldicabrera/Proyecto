package logica.valueObjects;

import java.util.TreeMap;



public class VOCollectionJugador {

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

}
