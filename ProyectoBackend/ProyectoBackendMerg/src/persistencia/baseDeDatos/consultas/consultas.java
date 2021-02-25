package persistencia.baseDeDatos.consultas;

public class consultas {

	// Dado el nombre de jugador se trae el jugador
	public String existeJugador() {
		String query = "select * from JUGADOR where nombreJugador=(?)";
		return query;
	}

	// Dado el id de la partida se trae la partida
	public String existePartida() {
		String query = "select idPartida,estadoPartida,ultimaactualizacionPartida,guardadaPartida,nombrePartida,cantjugadoresPartida,creadorPartida,fechacreadaPartida,terminoPartida from PARTIDA where partidaId=(?)";
		return query;
	}

	// Inserta Jugador
	public String insertarJugador() {
		String query = "insert into JUGADOR values(?,?,?,?,?)";
		return query;
	}

	// Insertar Partida
	public String insertarPartida() {
		String query = "insert into PARTIDA values(?,?,?,?,?,?,?,?,?)";
		return query;
	}

	// Listar Jugador
	public String listarJugadores() {
		String query = "select idJugador,nombreJugador,passJugador,onlineJugador,puntajeJugador from JUGADOR ORDER BY jugadorId";
		return query;
	}

	// Dado el id de jugador se trae el jugador
	public String obtenerJugador() {
		String query = "select idJugador,nombreJugador,passJugador,onlineJugador,puntajeJugador from JUGADOR"
				+ " where jugadorId=(?)";
		return query;
	}

	// Borrar partida dado un id
	public String borrarPartida() {
		String query = "DELETE FROM PARTIDA WHERE idPartida=(?);";
		return query;
	}

	// Borrar jugador dado un id
	public String borrarJugador() {
		String query = "DELETE FROM PARTIDA WHERE  jugadorId=(?);";
		return query;
	}

	// Se verifica que la cuenta del jugador exista
	public String acountExists() {
		String query = "select idJugador,nombreJugador,passJugador,onlineJugador,puntajeJugador from JUGADOR"
				+ " where nombreJugador=(?) and  passJugador=(?);";
		return query;
	}

	// Se obtiene el nombre del jugador dado el id del jugador
	public String getName() {
		String query = "select nombreJugador from JUGADOR where idJugador=(?)";
		return query;
	}

	// Se obtiene la cantidad total de jugadores del juego
	public String cantidadTotalJugadores() {
		String query = "SELECT count(*) as cantidad FROM JUGADOR";
		return query;
	}

	// Se obtiene la cantidad total de jugadores del juego que estan online
	public String cantidadJugadoresOnLine() {
		String query = "SELECT count(*) as cantidad FROM JUGADOR where onlineJugador=(1)";
		return query;
	}

	// Se devuelven los artilleros
	public String estaVaciaArtillero() {
		String query = "Select * from Artillero";
		return query;
	}

	// Se insertan artilleros
	public String insertarArtillero() {
		String query = "insert into Artillero values(?,?,?,?,?,?,?,?,?)";
		return query;
	}

	// Se devuelven los aviones
	public String existenAviones() {
		String query = "Select * from Avion";
		return query;
	}

	// Se insertan Aviones
	public String insertarAvion() {
		String query = "insert into Avion values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		return query;
	}

	// Se listan todos los aviones
	public String listarAviones() {
		String query = "Select idAvion,CoordXAvion,CoordYAvion,EstadoAvion,VidaAvion,AnguloAvion,BombasEnAvion,BombasAvion,AlturaAvion,CombustibleAvion,HayEnemigoAvion,RangoDeVisionAvion,EnCampoEnemigo,idbaseAvion From Avion;";
		return query;
	}

	// Se listan toda la artilleria
	public String listarArtilleros() {
		String query = "Select idArt,CoordXArt,CoordYArt,EstadoArt,VidaArt,HayEnemigoArt,RangoDeVisionArt,AnguloArt,idbaseArt from Artillero;";
		return query;
	}

	// Se valida si hay bases
	public String existeBase() {
		String query = "Select * From BASE";
		return query;
	}

	// Se ingresa una base
	public String insertarBase() {
		String query = "insert into BASE values(?,?,?,?,?)";
		return query;
	}

	// Se obtiene una base dado su ID
	public String obtenerBase() {
		String query = "Select idBase,idTCtrolBase,idTcombBase,idDepoBase,idEquipoBase From BASE where idBase=(?);";
		return query;
	}

	// Borra una base dado su id
	public String borrarbase() {
		String query = "DELETE FROM BASE WHERE  idBase=(?);";
		return query;
	}

	public String listarBase() {
		String query="Select idBase,idTCtrolBase,idTcombBase,idDepoBase,idEquipoBase From BASE";
		return query;
	}

	public String cantidadTotalBases() {
		String query="Select count(idBase) From BASE";
		return query;
	}

	public String existenJugadores() {
		String query="Select * From JUGADOR";
		return query;
	}

	public String insertarEquipo() {
		String query="insert into Equipo values(?,?,?,?,?)\"";
		return query;
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

		// Consultamos tabla auxiliar con relacion
		// Hacer un Join de partidaJugador y devolever solo partidas por terminas de un
		// jugador
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

	public String listarArtillerosXBase() {
		// TODO Auto-generated method stub
		return null;
	}

	// listar partidas dado un jugador(Ver LUEGO hay que hacer un join con Equipo,
	// JugadorEquipo ------------------------)
	public String listarPartidas() {
		String query = "select * From PARTIDA where jugadorId=(?) ORDER BY numero";
		return query;
	}

	// Setea a un jugador online dado su nombre
	public String isOnLineJugador() {
		String query = "UPDATE JUGADOR" + "SET onlineJugador = 1" + "WHERE nombreJugador=(?);";
		return query;
	}

	public String logoutJugadorPorUserName() {
		String query = "UPDATE JUGADOR" + "SET onlineJugador = 0" + "WHERE nombreJugador=(?);";
		return query;
	}

}
