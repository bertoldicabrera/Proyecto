package logica.valueObjects;

import java.io.Serializable;
import java.util.Date;

import persistencia.baseDeDatos.daos.DaoEquipo;

public class VOPartida implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int partidaId;
    private String partidaEstado;
    private Date partidaFechaUltimaActualizacion; 
    private boolean partidaGuardada;
	private boolean partidaTermino;
    private String  partidaNombre;
    private int partidaCantidadJugadores;
    private int partidaCreador;
    private Date partidaFechaCreada;
	private DaoEquipo Equipos;
	



	public VOPartida(int in_PartidaId, String in_PartidaEstado, Date in_PartidaFechaUltimaActualizacion,
			boolean in_PartidaGuardada, String  in_PartidaNombre, int in_PartidaCantidadJugadores,
			int in_PartidaCreador, Date in_PartidaFechaCreada,boolean in_partidaTermino, DaoEquipo in_Equi) {
		
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



	public void setPartidaId(int partidaId) {
		this.partidaId = partidaId;
	}



	public String getPartidaEstado() {
		return partidaEstado;
	}



	public void setPartidaEstado(String partidaEstado) {
		this.partidaEstado = partidaEstado;
	}



	public Date getPartidaFechaUltimaActualizacion() {
		return partidaFechaUltimaActualizacion;
	}



	public void setPartidaFechaUltimaActualizacion(Date partidaFechaUltimaActualizacion) {
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



	public Date getPartidaFechaCreada() {
		return partidaFechaCreada;
	}



	public void setPartidaFechaCreada(Date partidaFechaCreada) {
		this.partidaFechaCreada = partidaFechaCreada;
	}



	public DaoEquipo getEquipos() {
		return Equipos;
	}



	public void setEquipos(DaoEquipo equipos) {
		Equipos = equipos;
	}



	public boolean isPartidaTermino() {
		return partidaTermino;
	}



	public void setPartidaTermino(boolean partidaTermino) {
		this.partidaTermino = partidaTermino;
	}

}
