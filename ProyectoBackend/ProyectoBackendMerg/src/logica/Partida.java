package logica;

import java.time.LocalDate;
import java.util.Date;

import persistencia.baseDeDatos.daos.DaoEquipo;

public class Partida {

	private int partidaId;
    private String partidaEstado;
    private LocalDate partidaFechaUltimaActualizacion; 
    private boolean partidaGuardada;
	private boolean partidaTermino;
    private String  partidaNombre;
    private int partidaCantidadJugadores;
    private int partidaCreador;
    private LocalDate partidaFechaCreada;
	private DaoEquipo Equipos;


public Partida() {
}

public Partida(int in_PartidaId, String in_PartidaEstado, LocalDate in_PartidaFechaUltimaActualizacion,
boolean in_PartidaGuardada, String  in_PartidaNombre, int in_PartidaCantidadJugadores,
int in_PartidaCreador, LocalDate in_PartidaFechaCreada,boolean in_partidaTermino, DaoEquipo in_Equi)
{
	this.setPartidaId(in_PartidaId);
	this.setPartidaEstado(in_PartidaEstado);
	this.setPartidaFechaUltimaActualizacion(in_PartidaFechaUltimaActualizacion); 
	this.setPartidaGuardada(in_PartidaGuardada);
	this.setPartidaNombre(in_PartidaNombre);
	this.setPartidaCantidadJugadores(in_PartidaCantidadJugadores);
	this.setPartidaCreador(in_PartidaCreador);
	this.setPartidaFechaCreada(in_PartidaFechaCreada);
	this.setPartidaTermino(in_partidaTermino);
	this.setEquipos(in_Equi);
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

public LocalDate getPartidaFechaUltimaActualizacion() {
	return partidaFechaUltimaActualizacion;
}

public void setPartidaFechaUltimaActualizacion(LocalDate in_partidaFechaUltimaActualizacion) {
	this.partidaFechaUltimaActualizacion = in_partidaFechaUltimaActualizacion;
}

public boolean isPartidaGuardada() {
	return partidaGuardada;
}

public void setPartidaGuardada(boolean in_partidaGuardada) {
	this.partidaGuardada = in_partidaGuardada;
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

public LocalDate getPartidaFechaCreada() {
	return partidaFechaCreada;
}

public void setPartidaFechaCreada(LocalDate in_partidaFechaCreada) {
	this.partidaFechaCreada = in_partidaFechaCreada;
}

public DaoEquipo getEquipos() {
	
	return Equipos;
}

public void setEquipos(DaoEquipo equipos) {
	Equipos = equipos;
}

public boolean getPartidaTermino() {
	return partidaTermino;
}

public void setPartidaTermino(boolean partidaTermino) {
	this.partidaTermino = partidaTermino;
}

public void mostrarPartidaPorPantalla()
{
	System.out.println(this.getPartidaId()+"|"+
	this.getPartidaEstado()+"|"+
	this.getPartidaFechaUltimaActualizacion()+"|"+
	this.isPartidaGuardada()+"|"+
	this.getPartidaNombre()+"|"+
	this.getPartidaCantidadJugadores()+"|"+
	this.getPartidaCreador()+"|"+
	this.getPartidaFechaCreada()+"|"+
	this.getPartidaTermino()+"| meter otras cosas "		
			);
	
}




}
