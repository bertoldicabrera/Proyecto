package logica;

import java.io.IOException;

import Utilitarios.SystemProperties;

public class Avion extends Objeto {
	
	private float avionAngle;
	private boolean avionBomba;
	private int  cantidadBombas;
	private int avionAltura;
	private int  avionCombustible;
	private boolean  hayEnemigo;
	private boolean enCampoEnemigo;
	private int rangoDeVision;
	

	public Avion(int in_id, int in_coordX, int in_coordY, boolean in_estado, int in_vida, float in_avionAngle, boolean in_avionBomba,int  in_cantidadBombas, int in_avionAltura, int in_avionCombustible,boolean in_hayEnemigo,boolean in_enCampoEnemigo,int in_rangoDeVision) {
		super(in_id, in_coordX, in_coordY, in_estado, in_vida);
		this.setAvionAngle(in_avionAngle);
		this.setAvionBomba(in_avionBomba);
		this.setCantidadBombas(in_cantidadBombas);
		this.setAvionAltura(in_avionAltura);
		this.setAvionCombustible(in_avionCombustible);
		this.setHayEnemigo(in_hayEnemigo);
		this.setEnCampoEnemigo(in_enCampoEnemigo);
		this.setRangoDeVision(in_rangoDeVision);
		
		
	}


	public float getAvionAngle() {
		return this.avionAngle;
	}


	public void setAvionAngle(float in_avionAngle) {
		this.avionAngle = in_avionAngle;
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
	
}
