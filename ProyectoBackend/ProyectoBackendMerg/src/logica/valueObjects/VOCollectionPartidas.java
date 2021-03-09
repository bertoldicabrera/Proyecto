package logica.valueObjects;

import java.io.Serializable;
import java.util.TreeMap;

public class VOCollectionPartidas implements Serializable {

	private static final long serialVersionUID = 1L;
	TreeMap<Integer, VOPartida> partidas;

	public VOCollectionPartidas() {
		partidas = new TreeMap<Integer, VOPartida>();
	}

	public boolean member(int key) {
		return partidas.containsKey(key);

	}

	public void insert(int key, VOPartida jug) {

		partidas.put(key, jug);
	}

	public VOPartida find(int key) {
		return partidas.get(key);
	}

	public boolean estaVacio() {
		return partidas.isEmpty();
	}

	public void delete(int key) {
		partidas.remove(key);
	}

	public TreeMap<Integer, VOPartida> listarPartidas() {

		return partidas;

	}

	public int largoPartidas() {

		return partidas.size();
	}

}
