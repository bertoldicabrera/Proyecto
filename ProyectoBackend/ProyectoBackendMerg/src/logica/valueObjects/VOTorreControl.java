package logica.valueObjects;

public class VOTorreControl extends VOObjeto {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean hayEnemigo;
	private int rangoDeVision;

	public VOTorreControl(int in_id, int in_coordX, int in_coordY, boolean in_estado, int in_vida,boolean in_hayEnemigo, int in_rangoDeVision) {
		super(in_id, in_coordX, in_coordY, in_estado, in_vida);
		this.setHayEnemigo(in_hayEnemigo);
		this.setRangoDeVision(in_rangoDeVision);
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
}
