package persistencia.baseDeDatos.consultas;

public class consultas {
	
	public String existeJugador(){
		String query="select * from JUGADOR where jugadorId=(?)";
		return query;
	}
	
	public String existePartida(){
		String query="select * from PARTIDA where partidaId=(?)";
		return query;
	}
	
	public String insertarJugador() {
		String query="insert into JUGADOR values(?,?,?,?,?)";
		return query;
	}
	
	public String insertarPartida() {
		String query="insert into PARTIDA values(?,?,?,?,?,?,?,?,?)";
		return query;
	}
	
	
	public String listarJugadores() {
		String query="select * from JUGADOR ORDER BY jugadorId";
		return query;
	}
	
	public String listarPartidas() {
		String query="select * From PARTIDA where jugadorId=(?) ORDER BY numero";
		return query;
		}
	
	public String obtenerJugador() {
		String query="select * from JUGADOR"
			       + " where jugadorId=(?)";
		return query;
	}
	
	public String obtenerPartida() {
		String query="select * from PARTIDA"
			       + " where partidaId=(?)";
		return query;
	}
	
	
	public String borrarPartida() {
		String query="";
		return query;
	}
	public String borrarJugador() {
		String query=" ";
		return query;
	}
	
//String in_JugadorUserName, String in_JugadorPassword
	public String acountExists()
	{
		String query=" ";
		return query;
	}
	
	public String getName()
	{
		String query=" ";
		return query;
	}
	public String cantidadTotalJugadores() {
		String query=" ";
		return query;
	}
	
	public String cantidadJugadoresOnLine()
	{
		String query=" ";
		return query;
	}
	
	public String estaVaciaArtillero() {
		String query=" ";
		return query;
	}
	public String insertarArtillero() {
		String query=" ";
		return query;
	}
	
	public String obtenerKesimoArtillero() {
		String query="";
		return query;
	}
	public String cantidadArtilleros()
	{
		String query=" ";
		return query;
	}

	public String existenAviones() {
		// TODO Auto-generated method stub
		return null;
	}

	public String insertarAvion() {
		// TODO Auto-generated method stub
		return null;
	}

	public String listarAviones() {
		// TODO Auto-generated method stub
		return null;
	}

	public String obtenerAvion() {
		// TODO Auto-generated method stub
		return null;
	}

	public String listarArtilleros() {
		// TODO Auto-generated method stub
		return null;
	}

	public String existeBase() {
		// TODO Auto-generated method stub
		return null;
	}

	public String insertarBase() {
		// TODO Auto-generated method stub
		return null;
	}

	public String obtenerBase() {
		// TODO Auto-generated method stub
		return null;
	}

	public String esmemberbase() {
		// TODO Auto-generated method stub
		return null;
	}

	public String borrarbase() {
		// TODO Auto-generated method stub
		return null;
	}

	public String listarBase() {
		// TODO Auto-generated method stub
		return null;
	}

	public String cantidadTotalBases() {
		// TODO Auto-generated method stub
		return null;
	}

	public String existenJugadores() {
		// TODO Auto-generated method stub
		return null;
	}

	public String insertarEquipo() {
		// TODO Auto-generated method stub
		return null;
	}

	public String cantidadEquipos() {
		// TODO Auto-generated method stub
		return null;
	}

	public String obtenerKesimoEquipo() {
		// TODO Auto-generated method stub
		return null;
	}

	public String listarEquipos() {
		// TODO Auto-generated method stub
		return null;
	}

	public String ultimoJugadorID() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getJugadorIdByName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String listarPartidasDeUnJugador() {
		
		//Consultamos tabla auxiliar con relacion
		//Hacer un Join de partidaJugador y devolever solo partidas por terminas de un jugador
		// TODO Auto-generated method stub
		return null;
	}

	public String existenPartidas() {
		// TODO Auto-generated method stub
		return null;
	}

	public String ultimaPartidaID() {
		// TODO Auto-generated method stub
		return null;
	}

	public String ultimaBaseID() {
		// TODO Auto-generated method stub
		return null;
	}

	public String ultimaEquipoId() {
		// TODO Auto-generated method stub
		return null;
	}

	public String ultimaTorreId() {
		// TODO Auto-generated method stub
		return null;
	}

	public String ultimaTanqueId() {
		// TODO Auto-generated method stub
		return null;
	}

	public String ultimaDepositoId() {
		// TODO Auto-generated method stub
		return null;
	}

	public String listarEquiposDeUnaPartida() {
		// TODO Auto-generated method stub
		return null;
	}

	public String obtenerDepositobyId() {
		// TODO Auto-generated method stub
		return null;
	}

	public String obtenerTanquebyId() {
		// TODO Auto-generated method stub
		return null;
	}

	public String obtenerTorreControlbyId() {
		// TODO Auto-generated method stub
		return null;
	}

	public String obtenerAvionXBase() {
		// TODO Auto-generated method stub
		return null;
	}
}
