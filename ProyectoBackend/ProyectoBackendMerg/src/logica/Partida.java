package logica;

import java.util.Date;

import persistencia.baseDeDatos.daos.DaoEquipo;

public class Partida {

	private int partidaId;

	private String partidaEstado;

	private Date partidaFechaUltimaActualizacion; 

	private boolean partidaGuardada;

	private int ganadorEquipoID;

	private String  partidaNombre;

	private int partidaCantidadJugadores;

	private int partidaCreador;

	private Date partidaFechaCreada;
	
	private DaoEquipo Equipos;





public Partida() {
}

public Partida(int in_PartidaId, String in_PartidaEstado, Date in_PartidaFechaUltimaActualizacion,
boolean in_PartidaGuardada, int in_ganadorEquipoID, String  in_PartidaNombre, int in_PartidaCantidadJugadores,
int in_PartidaCreador, Date in_PartidaFechaCreada)
{
	this.setPartidaId(in_PartidaId);
	this.setPartidaEstado(in_PartidaEstado);
	this.setPartidaFechaUltimaActualizacion(in_PartidaFechaUltimaActualizacion); 
	this.setPartidaGuardada(in_PartidaGuardada);
	this.setGanadorEquipoID(in_ganadorEquipoID);
	this.setPartidaNombre(in_PartidaNombre);
	this.setPartidaCantidadJugadores(in_PartidaCantidadJugadores);
	this.setPartidaCreador(in_PartidaCreador);
	this.setPartidaFechaCreada(in_PartidaFechaCreada);
	
}

public int getPartidaId() {
	return partidaId;
}

public void setPartidaId(int in_partidaId) {
	this.partidaId = in_partidaId;
}

public String getPartidaEstado() {
	return partidaEstado;
}

public void setPartidaEstado(String in_partidaEstado) {
	this.partidaEstado = in_partidaEstado;
}

public Date getPartidaFechaUltimaActualizacion() {
	return partidaFechaUltimaActualizacion;
}

public void setPartidaFechaUltimaActualizacion(Date in_partidaFechaUltimaActualizacion) {
	this.partidaFechaUltimaActualizacion = in_partidaFechaUltimaActualizacion;
}

public boolean isPartidaGuardada() {
	return partidaGuardada;
}

public void setPartidaGuardada(boolean in_partidaGuardada) {
	this.partidaGuardada = in_partidaGuardada;
}

public int getGanadorEquipoID() {
	return ganadorEquipoID;
}

public void setGanadorEquipoID(int in_ganadorEquipoID) {
	this.ganadorEquipoID = in_ganadorEquipoID;
}

public String getPartidaNombre() {
	return partidaNombre;
}

public void setPartidaNombre(String in_partidaNombre) {
	this.partidaNombre = in_partidaNombre;
}

public int getPartidaCantidadJugadores() {
	return partidaCantidadJugadores;
}

public void setPartidaCantidadJugadores(int in_partidaCantidadJugadores) {
	this.partidaCantidadJugadores = in_partidaCantidadJugadores;
}

public int getPartidaCreador() {
	return partidaCreador;
}

public void setPartidaCreador(int in_partidaCreador) {
	this.partidaCreador = in_partidaCreador;
}

public Date getPartidaFechaCreada() {
	return partidaFechaCreada;
}

public void setPartidaFechaCreada(Date in_partidaFechaCreada) {
	this.partidaFechaCreada = in_partidaFechaCreada;
}




}
