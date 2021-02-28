package logica;

public class TanqueCombustible extends Objeto {
	private int cantidadCombustible;
    private boolean enUso;

	public TanqueCombustible(int in_id, int in_coordX, int in_coordY, boolean in_estado, int in_vida, int in_cantidadCombustible, boolean in_enUso) {
		super(in_id, in_coordX, in_coordY, in_estado, in_vida);
		this.setCantidadCombustible(in_cantidadCombustible);
		this.setEnUso(in_enUso);
	}

	public int getCantidadCombustible() {
		return this.cantidadCombustible;
	}

	public void setCantidadCombustible(int in_cantidadCombustible) {
		this.cantidadCombustible = in_cantidadCombustible;
	}

	public boolean getEnUso() {
		return this.enUso;
	}

	public void setEnUso(boolean in_enUso) {
		this.enUso = in_enUso;
	}

}
