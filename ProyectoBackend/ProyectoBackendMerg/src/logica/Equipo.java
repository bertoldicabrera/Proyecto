package logica;

import java.io.Serializable;

public class Equipo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int equipoID;
	private int tope=1;
	private Jugador[] jugadores; 
	private Base base;
	private String bando;
	
	
	public Equipo() {
		this.setJugadores(new  Jugador[tope]);
	}
	
	
	public Equipo(int in_equipoID, Jugador[]  in_Jugadores, Base  in_base, String  in_bando ) {
		
		this.setEquipoID( in_equipoID);
        this.setJugadores(in_Jugadores);
		this.setBase(  in_base);
		this.setBando(  in_bando);
	}


	public int getEquipoID() {
		return this.equipoID;
	}


	public void setEquipoID(int in_equipoID) {
		this.equipoID =  in_equipoID;
	}

	public Base getBase() {
		return base;
	}

	public void setBase(Base  in_base) {
		this.base =  in_base;
	}
	
	
	public String getBando() {
		return bando;
	}

	public void setBando(String  in_bando) {
		this.bando =  in_bando;
	}


	public Jugador[] getJugadores() {
		return this.jugadores;
	}


	public void setJugadores(Jugador[] jugadores) {
		this.jugadores = jugadores;
	}


}
