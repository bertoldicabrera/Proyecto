package logica.valueObjects;

import java.io.Serializable;

public class VODaoEquipo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idpartida;
	private int tope=2;
	private VOEquipo[] equipos;
	private VODaoJugador DaoJ;
	private VODaoBase   DaoB;

	public VODaoEquipo() {
		equipos = new VOEquipo[tope];
	}
	public VODaoEquipo(int in_idpartida, VOEquipo[] in_EQS) {
		this.setIdpartida(in_idpartida);
		this.equipos = in_EQS;
		}
	
	public void  insBack ( VOEquipo in_Equipo)
	{
		equipos[tope]=in_Equipo;
		tope--;
	}
	
	public boolean estaVacia()  {
		boolean esta = false;
		
			if (equipos.length==0)
				esta = true;
		
		return esta;
		}
	public boolean estaLlena() {
		boolean esta = false;
		
		if (equipos.length==2)
			esta = true;
	
	return esta;
		
	}
	
	public VOEquipo kesimo(int index) {
		return equipos[index];
	}

	public VOEquipo[] listarArtilleria() {
		return equipos;
	}
	
	
	public VOEquipo[] getSecuenciaArtilleria() {
		return equipos;
	}
	public int getIdpartida() {
		return idpartida;
	}
	public void setIdpartida(int idpartida) {
		this.idpartida = idpartida;
	}
	public VODaoJugador getDaoJ() {
		return DaoJ;
	}
	public void setDaoJ(VODaoJugador daoJ) {
		DaoJ = daoJ;
	}
	public VODaoBase getDaoB() {
		return DaoB;
	}
	public void setDaoB(VODaoBase daoB) {
		DaoB = daoB;
	}

	

}
