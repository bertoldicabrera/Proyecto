package logica;

public class Avion extends Objeto {
	
	private boolean avionBomba;
	private int  cantidadBombas;
	private int avionAltura;
	private int  avionCombustible;
	private boolean  hayEnemigo;
	private boolean enCampoEnemigo;
	private int rangoDeVision;
	private int Baseid;
	

	public Avion(int in_PK_avion_id, int in_avionCoordX, int in_avionCoordY,int in_avionCoordZ ,boolean in_estado, int in_vida,boolean in_hayEnemigo,int in_rangoDeVision ,boolean in_avionBomba,int  in_cantidadBombas, int in_avionCombustible,boolean in_enCampoEnemigo,int in_baseid) {
		super(in_PK_avion_id, in_avionCoordX, in_avionCoordY, in_estado, in_vida);
		this.setAvionBomba(in_avionBomba);
		this.setCantidadBombas(in_cantidadBombas);
		this.setAvionAltura(in_avionCoordZ);
		this.setAvionCombustible(in_avionCombustible);
		this.setHayEnemigo(in_hayEnemigo);
		this.setEnCampoEnemigo(in_enCampoEnemigo);
		this.setRangoDeVision(in_rangoDeVision);
		this.setBaseid(in_baseid);
	}


	public boolean getAvionBomba() {
		return this.avionBomba;
	}


	public void setAvionBomba(boolean in_avionBomba) {
		this.avionBomba = in_avionBomba;
	}


	public int getCantidadBombas() {
		return this.cantidadBombas;
	}


	public void setCantidadBombas(int in_cantidadBombas) {
		this.cantidadBombas = in_cantidadBombas;
	}


	public int getAvionAltura() {
		return this.avionAltura;
	}


	public void setAvionAltura(int in_avionAltura) {
		this.avionAltura = in_avionAltura;
	}


	public int getAvionCombustible() {
		return this.avionCombustible;
	}


	public void setAvionCombustible(int in_avionCombustible) {
		this.avionCombustible = in_avionCombustible;
	}


	public boolean getHayEnemigo() {
		return this.hayEnemigo;
	}


	public void setHayEnemigo(boolean in_hayEnemigo) {
		this.hayEnemigo = in_hayEnemigo;
	}


	public boolean getEnCampoEnemigo() {
		return this.enCampoEnemigo;
	}


	public void setEnCampoEnemigo(boolean in_enCampoEnemigo) {
		this.enCampoEnemigo = in_enCampoEnemigo;
	}


	public int getRangoDeVision() {
		return this.rangoDeVision;
	}


	public void setRangoDeVision(int in_rangoDeVision) {
		this.rangoDeVision = in_rangoDeVision;
	}


	public int getBaseid() {
		return Baseid;
	}


	public void setBaseid(int baseid) {
		Baseid = baseid;
	}
	
}
