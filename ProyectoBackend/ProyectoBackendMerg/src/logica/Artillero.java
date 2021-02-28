package logica;

public class Artillero extends Objeto{
	private boolean hayEnemigo;

	private int  rangoDeVision;

	private float avionAngle;
	
	public Artillero(int in_id, int in_coordX, int in_coordY, 
			boolean in_estado, int in_vida, boolean in_hayEnemigo,
			int in_rangoDeVision, float in_avionAngle) {
		super(in_id, in_coordX, in_coordY, in_estado, in_vida);
		this.setHayEnemigo(in_hayEnemigo);
		this.setRangoDeVision(in_rangoDeVision);
		this.setAvionAngle(in_avionAngle);
		
		
		
		
		
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

	public float getAvionAngle() {
		return avionAngle;
	}

	public void setAvionAngle(float in_avionAngle) {
		this.avionAngle = in_avionAngle;
	}



}
