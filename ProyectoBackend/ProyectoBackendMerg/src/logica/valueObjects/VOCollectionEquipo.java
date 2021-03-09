package logica.valueObjects;

import java.io.Serializable;

public class VOCollectionEquipo implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idpartida;
	private int tope = 2;
	private VOEquipo[] equipos;
	private VOCollectionJugador DaoJ;
	private VOCollectionBase DaoB;

	public VOCollectionEquipo() {
		equipos = new VOEquipo[tope];
	}

	public VOCollectionEquipo(int in_idpartida, VOEquipo[] in_EQS) {
		this.setIdpartida(in_idpartida);
		this.equipos = in_EQS;
	}

	public VOCollectionEquipo(int in_idpartida, VOEquipo[] in_Equipo, VOCollectionJugador in_DaoJ,
			VOCollectionBase in_DaoB) {
		this.idpartida = in_idpartida;
		equipos = in_Equipo;
		this.DaoJ = in_DaoJ;
		this.DaoB = in_DaoB;
	}

	public void insBack(int in_idpartida, VOEquipo in_Equipo, VOCollectionJugador in_DaoJ, VOCollectionBase in_DaoB) {
		this.idpartida = in_idpartida;
		equipos[tope - 1] = in_Equipo;
		tope--;
		this.DaoJ = in_DaoJ;
		this.DaoB = in_DaoB;
	}

	public boolean estaVacia() {
		boolean esta = false;

		if (equipos.length == 0)
			esta = true;

		return esta;
	}

	public boolean estaLlena() {
		boolean esta = false;

		if (equipos.length == 2)
			esta = true;

		return esta;

	}

	public VOEquipo kesimo(int index) {
		return equipos[index];
	}

	public VOEquipo[] getequipos() {
		return equipos;
	}

	public int getIdpartida() {
		return idpartida;
	}

	public void setIdpartida(int idpartida) {
		this.idpartida = idpartida;
	}

	public VOCollectionJugador getDaoJ() {
		return DaoJ;
	}

	public void setDaoJ(VOCollectionJugador daoJ) {
		DaoJ = daoJ;
	}

	public VOCollectionBase getDaoB() {
		return DaoB;
	}

	public void setDaoB(VOCollectionBase daoB) {
		DaoB = daoB;
	}

}
