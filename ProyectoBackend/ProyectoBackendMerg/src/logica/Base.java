package logica;

import java.io.Serializable;

import persistencia.baseDeDatos.daos.DaoArtilleria;
import persistencia.baseDeDatos.daos.DaoDeAviones;

public class Base  implements Serializable{
	
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private int idBase;
 private DaoDeAviones aviones;
 private DaoArtilleria artilleros; 
 private Deposito deposito;
 private TanqueCombustible tanque; 
 private TorreControl torre;


	public Base() {
	}
	public Base(int in_idDabse,DaoDeAviones in_aviones,  
			DaoArtilleria in_artilleros, Deposito in_deposito, 
			TanqueCombustible in_tanque,TorreControl in_torre) {
		this.setIdDabse(in_idDabse);
		this.setAviones(in_aviones);
		this.setArtilleros(in_artilleros);
		this.setDeposito(in_deposito);
		this.setTanque(in_tanque);
		this.setTorre(in_torre);
		
		
	}
	
	
	

	public int getIdDabse() {
		return idBase;
	}

	public void setIdDabse(int in_idDabse) {
		this.idBase = in_idDabse;
	}

	public DaoDeAviones getAviones() {
		return aviones;
	}

	public void setAviones(DaoDeAviones in_aviones) {
		this.aviones = in_aviones;
	}

	public DaoArtilleria getArtilleros() {
		return artilleros;
	}

	public void setArtilleros(DaoArtilleria in_artilleros) {
		this.artilleros = in_artilleros;
	}

	public Deposito getDeposito() {
		return deposito;
	}

	public void setDeposito(Deposito in_deposito) {
		this.deposito = in_deposito;
	}

	public TanqueCombustible getTanque() {
		return tanque;
	}

	public void setTanque(TanqueCombustible in_tanque) {
		this.tanque = in_tanque;
	}

	public TorreControl getTorre() {
		return torre;
	}

	public void setTorre(TorreControl in_torre) {
		this.torre = in_torre;
	}
	
	
	
	

}
