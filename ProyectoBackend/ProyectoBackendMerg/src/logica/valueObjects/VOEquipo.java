package logica.valueObjects;

import java.io.Serializable;

public class VOEquipo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int equipoID;
	private int tope = 1;
	private VOJugador[] jugadores;
	private VOBase base;
	private String bando;

	public VOEquipo() {

	}

	public VOEquipo(int in_equipoID, VOJugador[] in_Jugadores, VOBase in_base, String in_bando) {
		this.setJugadores(new VOJugador[tope]);
		this.setEquipoID(in_equipoID);
		this.setJugadores(in_Jugadores);
		this.setBase(in_base);
		this.setBando(in_bando);
	}

	public int getEquipoID() {
		return equipoID;
	}

	public void setEquipoID(int equipoID) {
		this.equipoID = equipoID;
	}

	public int getTope() {
		return tope;
	}

	public void setTope(int tope) {
		this.tope = tope;
	}

	public VOJugador[] getJugadores() {
		return jugadores;
	}

	public void setJugadores(VOJugador[] jugadores) {
		this.jugadores = jugadores;
	}

	public VOBase getBase() {
		return base;
	}

	public void setBase(VOBase base) {
		this.base = base;
	}

	public String getBando() {
		return bando;
	}

	public void setBando(String bando) {
		this.bando = bando;
	}

	public void mostrarPorPantallaVoEquipo() {
		System.out.println("|El id de equipo es: " + this.getEquipoID());
	}

}
