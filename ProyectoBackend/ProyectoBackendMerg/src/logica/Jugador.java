package logica;

public class Jugador {
	
	 private int jugadorId;
	 private String jugadorUserName;
	 private String jugadorPassword;
	 private boolean jugadorIsOnline;
	 private int puntajeAcumulado;
	 
	 
	 
	 public Jugador() {
	}

	 public Jugador(int in_JugadorID,  String in_JugadorUserName, String in_JugadorPassword, 
			 boolean in_JugadorIsOnline, int in_PuntajeAcumulado)
	 {
		 this.setJugadorId(in_JugadorID);
		 this.setJugadorUserName(in_JugadorUserName);
		 this.setJugadorPassword(in_JugadorPassword);
		 this.setJugadorIsOnline(in_JugadorIsOnline);
		 this.setPuntajeAcumulado(in_PuntajeAcumulado);
	 }
	 
	
	public String getJugadorPassword() {
		return jugadorPassword;
	}

	public void setJugadorPassword(String in_jugadorPassword) {
		this.jugadorPassword = in_jugadorPassword;
	}

	public int getJugadorId() {
		return jugadorId;
	}

	public void setJugadorId(int in_jugadorId) {
		this.jugadorId = in_jugadorId;
	}

	public String getJugadorUserName() {
		return jugadorUserName;
	}

	public void setJugadorUserName(String in_jugadorUserName) {
		this.jugadorUserName = in_jugadorUserName;
	}

	public int getPuntajeAcumulado() {
		return puntajeAcumulado;
	}

	public void setPuntajeAcumulado(int in_puntajeAcumulado) {
		this.puntajeAcumulado = in_puntajeAcumulado;
	}

	public boolean isJugadorIsOnline() {
		return jugadorIsOnline;
	}

	public void setJugadorIsOnline(boolean in_jugadorIsOnline) {
		this.jugadorIsOnline = in_jugadorIsOnline;
	}
}
