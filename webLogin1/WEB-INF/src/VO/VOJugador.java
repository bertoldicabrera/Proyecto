package logica.valueObjects;

import java.io.Serializable;

public class VOJugador implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int jugadorId;
	 private String jugadorUserName;
	 private String jugadorPassword;
	 private boolean jugadorIsOnline;
	 private int puntajeAcumulado;      
	 
	 
	
	
	 
	 public VOJugador(int in_JugadorID,  String in_JugadorUserName, String in_JugadorPassword, 
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



	public void setJugadorPassword(String jugadorPassword) {
		this.jugadorPassword = jugadorPassword;
	}



	public int getJugadorId() {
		return jugadorId;
	}



	public void setJugadorId(int jugadorId) {
		this.jugadorId = jugadorId;
	}



	public String getJugadorUserName() {
		return jugadorUserName;
	}



	public void setJugadorUserName(String jugadorUserName) {
		this.jugadorUserName = jugadorUserName;
	}



	public boolean isJugadorIsOnline() {
		return jugadorIsOnline;
	}



	public void setJugadorIsOnline(boolean jugadorIsOnline) {
		this.jugadorIsOnline = jugadorIsOnline;
	}



	public int getPuntajeAcumulado() {
		return puntajeAcumulado;
	}



	public void setPuntajeAcumulado(int puntajeAcumulado) {
		this.puntajeAcumulado = puntajeAcumulado;
	}
	
	
	
}
