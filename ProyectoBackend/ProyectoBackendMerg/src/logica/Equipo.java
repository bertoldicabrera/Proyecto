package logica;

public class Equipo {

	private int equipoID;
	//private int tope=1;
	private Jugador[] arreJugadores;
	private Base base;
	private String bando;
	
	
	public Equipo() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Equipo(int in_equipoID, Jugador[] in_arreJugadores, Base  in_base, String  in_bando ) {
		this.setEquipoID( in_equipoID);
		this.setArreJugadores(in_arreJugadores);
		this.setBase(  in_base);
		this.setBando(  in_bando);
	}


	public int getEquipoID() {
		return equipoID;
	}


	public void setEquipoID(int in_equipoID) {
		this.equipoID =  in_equipoID;
	}


	public Jugador[] getArreJugadores() {
		return arreJugadores;
	}


	public void setArreJugadores(Jugador[]  in_arreJugadores) {
		this.arreJugadores =  in_arreJugadores;
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





	

}
