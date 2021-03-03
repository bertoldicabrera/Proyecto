package logica.valueObjects;

import java.io.Serializable;
import java.util.TreeMap;


public class VOCollectionBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TreeMap<Integer, VOBase> bases;

	public VOCollectionBase() {
		setBases(new TreeMap<Integer, VOBase>());
	}
	
	
	public boolean member(int key) {
		return bases.containsKey(key);

	}

	public void insert(int key, VOBase bas) {

		bases.put(key, bas);
	}

	public VOBase find(int key) {
		return bases.get(key);
	}

	public boolean estaVacio() {
		return bases.isEmpty();
	}

	public void delete(int key) {
		bases.remove(key);
	}

	public TreeMap<Integer, VOBase> listarbases() {

		return bases;

	}

	public int largobases() {

		return bases.size();
	}
	

	public TreeMap<Integer, VOBase> getBases() {
		return bases;
	}

	public void setBases(TreeMap<Integer, VOBase> bases) {
		this.bases = bases;
	}

}
