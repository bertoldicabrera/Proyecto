package logica.valueObjects;

import java.io.Serializable;


public class VOCollectionAviones implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int tope = 4;
	private VOAvion[] arreavion;
	private int baseId;

	public VOCollectionAviones() {
		setArreavion(new VOAvion[tope]);
	}
	public VOCollectionAviones(int in_idBase) {
		this.baseId = in_idBase;
		this.arreavion = new VOAvion[tope];
	}
	public boolean estaVacia()  {
		boolean esta = false;
			if (arreavion.length==0)
				esta = true;
		return esta;
		}
	
	public boolean estaLlena() {
		boolean esta = false;
		if (arreavion.length==12)
			esta = true;
	return esta;
	}
	public void insback(VOAvion in_avion) {
		arreavion[tope]=in_avion;
		tope--;
	}
	public VOAvion[] listarAviones(){
		return arreavion;
	}
	
	public VOAvion kesimo(int index) {
		return arreavion[index];
	}
	
	

	public VOAvion[] getArreavion() {
		return arreavion;
	}

	public void setArreavion(VOAvion[] arreavion) {
		this.arreavion = arreavion;
	}

	public int getBaseId() {
		return baseId;
	}

	public void setBaseId(int baseId) {
		this.baseId = baseId;
	}

}
