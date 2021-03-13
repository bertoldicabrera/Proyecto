package logica.valueObjects;

import java.io.Serializable;

public class VOCollectionArtilleria implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int tope = 12;
	private VOArtillero[] secuenciaArtilleria;
	int baseid;

	public VOCollectionArtilleria() {
		setSecuenciaArtilleria(new VOArtillero[tope]);
	}

	public VOCollectionArtilleria(int in_baseid) {
		secuenciaArtilleria = new VOArtillero[tope];
		this.baseid = in_baseid;
	}

	public boolean estaVacia() {
		boolean esta = false;

		if (secuenciaArtilleria.length == 0)
			esta = true;

		return esta;
	}

	public boolean estaLlena() {
		boolean esta = false;

		if (secuenciaArtilleria.length == 12)
			esta = true;

		return esta;

	}

	public void insBack(VOArtillero in_Artillero) {
		secuenciaArtilleria[tope - 1] = in_Artillero;
		tope--;
	}

	public VOArtillero kesimo(int index) {
		return secuenciaArtilleria[index];
	}

	public VOArtillero[] listarArtilleria() {
		return secuenciaArtilleria;
	}

	public VOArtillero[] getSecuenciaArtilleria() {
		return secuenciaArtilleria;
	}

	public void setSecuenciaArtilleria(VOArtillero[] secuenciaArtilleria) {
		this.secuenciaArtilleria = secuenciaArtilleria;
	}

	public int getBaseid() {

		return this.baseid;
	}

}
