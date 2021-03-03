package logica.valueObjects;

public class VOBase {
	
	private int idBase;
	 private VOCollectionAviones aviones;
	 private VOCollectionArtilleria artilleros; 
	 private VODeposito deposito;
	 private VOTanqueCombustible tanque; 
	 private VOTorreControl torre;

	public VOBase() {
		// TODO Auto-generated constructor stub
	}
	public VOBase(int in_idDabse,VOCollectionAviones in_aviones,VOCollectionArtilleria in_artilleros, VODeposito in_deposito, 
			VOTanqueCombustible in_tanque,VOTorreControl in_torre) {
			this.setIdBase(in_idDabse);
			this.setAviones(in_aviones);
			this.setArtilleros(in_artilleros);
			this.setDeposito(in_deposito);
			this.setTanque(in_tanque);
			this.setTorre(in_torre);
	}
	public int getIdBase() {
		return idBase;
	}
	public void setIdBase(int idBase) {
		this.idBase = idBase;
	}
	public VOCollectionAviones getAviones() {
		return aviones;
	}
	public void setAviones(VOCollectionAviones aviones) {
		this.aviones = aviones;
	}
	public VOCollectionArtilleria getArtilleros() {
		return artilleros;
	}
	public void setArtilleros(VOCollectionArtilleria artilleros) {
		this.artilleros = artilleros;
	}
	public VODeposito getDeposito() {
		return deposito;
	}
	public void setDeposito(VODeposito deposito) {
		this.deposito = deposito;
	}
	public VOTanqueCombustible getTanque() {
		return tanque;
	}
	public void setTanque(VOTanqueCombustible tanque) {
		this.tanque = tanque;
	}
	public VOTorreControl getTorre() {
		return torre;
	}
	public void setTorre(VOTorreControl torre) {
		this.torre = torre;
	}

}
