package logica;

public abstract class Objeto {

	private int id;

	private int coordX;

	private int coordY;

	private boolean estado;

	private int vida;

	public Objeto(int in_id, int in_coordX, int in_coordY, boolean in_estado, int in_vida) {
		this.id = in_id;
		this.coordX = in_coordX;
		this.coordY = in_coordY;
		this.estado = in_estado;
		this.vida = in_vida;
	}

	public void setId(int in_id) {
		this.id = in_id;
	}

	public void setCoordX(int in_coordX) {
		this.coordX = in_coordX;

	}

	public void setCoordY(int in_coordY) {
		this.coordY = in_coordY;
	}

	public void setEstado(boolean in_estado) {
		this.estado = in_estado;
	}

	public void setVida(int in_vida) {
		this.vida = in_vida;
	}

	public int GetId() {
		return this.id;
	}

	public int getCoordX() {
		return this.coordX;
	}

	public int getCoordY() {
		return this.coordY;
	}

	public boolean getEstado() {
		return this.estado;
	}

	public int getVida() {
		return this.vida;
	}

}
