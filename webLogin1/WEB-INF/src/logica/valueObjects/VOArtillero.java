package logica.valueObjects;

import java.io.Serializable;

public class VOArtillero extends VOObjeto  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean hayEnemigo;
	private int  rangoDeVision;
	private int artilleroAngulo;
	private int in_base_id;
	public VOArtillero(int in_id, int in_coordX, int in_coordY, 
			boolean in_estado, int in_vida, boolean in_hayEnemigo,
			int in_rangoDeVision, int in_ArtilleroAngulo,int in_base_id) {
		super(in_id, in_coordX, in_coordY, in_estado, in_vida);
		this.setHayEnemigo(in_hayEnemigo);
		this.setRangoDeVision(in_rangoDeVision);
		this.setArtilleroAngulo(in_ArtilleroAngulo);
		this.setbase_id(in_base_id);
	}

	public boolean getHayEnemigo() {
		return this.hayEnemigo;
	}

	public void setHayEnemigo(boolean in_hayEnemigo) {
		this.hayEnemigo = in_hayEnemigo;
	}

	public int getRangoDeVision() {
		return this.rangoDeVision;
	}

	public void setRangoDeVision(int in_rangoDeVision) {
		this.rangoDeVision = in_rangoDeVision;
	}

	public int getArtilleroAngulo() {
		return artilleroAngulo;
	}

	public void setArtilleroAngulo(int in_avionAngle) {
		this.artilleroAngulo = in_avionAngle;
	}
   
	public int getbase_id() {
		return in_base_id;
	}

	public void setbase_id(int in_base_id) {
		this.artilleroAngulo = in_base_id;
	}

}
