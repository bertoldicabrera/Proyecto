package persistencia.baseDeDatos.daos;

import logica.Equipo;

public class DaoEquipo {
	
	private int tope=2;
	private Equipo[] arreEquipo;
	

	public DaoEquipo() {
		// TODO Auto-generated constructor stub
	}


	public Equipo[] getArreEquipo() {
		return arreEquipo;
	}


	public void setArreEquipo(Equipo[] in_arreEquipo) {
		this.arreEquipo = in_arreEquipo;
	}
	
	

}
