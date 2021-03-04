package logica.valueObjects;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import persistencia.baseDeDatos.daos.DaoEquipo;

public class VOPartida implements Serializable {
	

	private static final long serialVersionUID = 1L;

	private int partidaId;
    private String partidaEstado;
    private LocalDate  partidaFechaUltimaActualizacion; 
    private boolean partidaGuardada;
	private boolean partidaTermino;
    private String  partidaNombre;
    private int partidaCantidadJugadores;
    private int partidaCreador;
    private LocalDate partidaFechaCreada;
	private VOCollectionEquipo Equipos;
	


	public VOPartida(int in_PartidaId, String in_PartidaEstado, LocalDate in_PartidaFechaUltimaActualizacion,
			boolean in_PartidaGuardada, String  in_PartidaNombre, int in_PartidaCantidadJugadores,
			int in_PartidaCreador, LocalDate in_PartidaFechaCreada,boolean in_partidaTermino, VOCollectionEquipo in_Equi) {
		
		this.setPartidaId(in_PartidaId);
		this.setPartidaEstado(in_PartidaEstado);
		this.setPartidaFechaUltimaActualizacion(in_PartidaFechaUltimaActualizacion); 
		this.setPartidaGuardada(in_PartidaGuardada);
		this.setPartidaNombre(in_PartidaNombre);
		this.setPartidaCantidadJugadores(in_PartidaCantidadJugadores);
		System.out.println("Constructor veo partida linea 37 Al momento de crear en vo jugador la pk de id creador es:"+in_PartidaCreador);
		this.setPartidaCreador(in_PartidaCreador);
		this.setPartidaFechaCreada(in_PartidaFechaCreada);
		this.setPartidaTermino(in_partidaTermino);
		this.setEquipos(in_Equi);
		
		
		}



	public int getPartidaId() {
		return partidaId;
	}



	public void setPartidaId(int partidaId) {
		this.partidaId = partidaId;
	}



	public String getPartidaEstado() {
		return partidaEstado;
	}



	public void setPartidaEstado(String partidaEstado) {
		this.partidaEstado = partidaEstado;
	}



	public LocalDate getPartidaFechaUltimaActualizacion() {
		return partidaFechaUltimaActualizacion;
	}



	public void setPartidaFechaUltimaActualizacion(LocalDate partidaFechaUltimaActualizacion) {
		this.partidaFechaUltimaActualizacion = partidaFechaUltimaActualizacion;
	}



	public boolean isPartidaGuardada() {
		return partidaGuardada;
	}



	public void setPartidaGuardada(boolean partidaGuardada) {
		this.partidaGuardada = partidaGuardada;
	}



	


	public String getPartidaNombre() {
		return partidaNombre;
	}



	public void setPartidaNombre(String partidaNombre) {
		this.partidaNombre = partidaNombre;
	}



	public int getPartidaCantidadJugadores() {
		return partidaCantidadJugadores;
	}



	public void setPartidaCantidadJugadores(int partidaCantidadJugadores) {
		this.partidaCantidadJugadores = partidaCantidadJugadores;
	}



	public int getPartidaCreador() {
		return partidaCreador;
	}



	public void setPartidaCreador(int partidaCreador) {
		this.partidaCreador = partidaCreador;
	}



	public LocalDate getPartidaFechaCreada() {
		return partidaFechaCreada;
	}



	public void setPartidaFechaCreada(LocalDate partidaFechaCreada) {
		this.partidaFechaCreada = partidaFechaCreada;
	}



	public VOCollectionEquipo getEquipos() {
		return Equipos;
	}



	public void setEquipos(VOCollectionEquipo in_Equi) {
		Equipos = in_Equi;
	}



	public boolean isPartidaTermino() {
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
		this.isPartidaTermino()+"| meter otras cosas " );
	}

}
