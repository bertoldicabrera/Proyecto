package logica;

public class Artillero extends Objeto{
	private boolean hayEnemigo;

	private int  rangoDeVision;

	private int avionAngle;
	int in_base_id;
	
	public Artillero(int in_id, int in_coordX, int in_coordY, 
			boolean in_estado, int in_vida, boolean in_hayEnemigo,
			int in_rangoDeVision, int in_avionAngle,int in_base_id) {
		super(in_id, in_coordX, in_coordY, in_estado, in_vida);
		this.setHayEnemigo(in_hayEnemigo);
		this.setRangoDeVision(in_rangoDeVision);
		this.setAvionAngle(in_avionAngle);
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

	public int getAvionAngle() {
		return avionAngle;
	}

	public void setAvionAngle(int in_avionAngle) {
		this.avionAngle = in_avionAngle;
	}
   
	public int getbase_id() {
		return in_base_id;
	}

	public void setbase_id(int in_base_id) {
		this.avionAngle = in_base_id;
	}


}
