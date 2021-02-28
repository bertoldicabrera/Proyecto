package logica;

public class Deposito extends Objeto {
	private int  cantidaBombas;
    private boolean enUso;

	public Deposito(int in_id, int in_coordX, int in_coordY, boolean in_estado, int in_vida,int in_cantidadBombas,boolean in_enUso) {
		super(in_id, in_coordX, in_coordY, in_estado, in_vida);
		this.setCantidaBombas(in_cantidadBombas);
		this.setEnUso(in_enUso);
	}

	public int getCantidaBombas() {
		return this.cantidaBombas;
	}

	public void setCantidaBombas(int in_cantidaBombas) {
		this.cantidaBombas = in_cantidaBombas;
	}

	public boolean getEnUso() {
		return enUso;
	}

	public void setEnUso(boolean in_enUso) {
		this.enUso = in_enUso;
	}

}
