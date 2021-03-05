package persistencia.baseDeDatos.consultas;

public class consultas {
    //Revisado ok
	public String InsertarEquipoJugador() {
		String query = "insert into EQUIPOS_JUGADORES(PK_jugador_id,PK_equipo_id) values(?,?)";
		return query;
	}
    //Revisado ok
	// Dado el nombre de jugador se trae el jugador
	public String existeJugador() {
		String query = "select PK_jugador_id, jugadorUserName, jugadorPassword, jugadorIsOnline, jugadorPuntajeAcumulado from JUGADORES where jugadorUserName=(?)";
		return query;
	}
    //Revisado ok
	// Dado el id de la partida se trae la partida
	public String existePartida() {
		String query = "select PK_partida_id, partidaEstado ,partidaFechaUltimaActualizacion, partidaGuardada, partidaNombre, partidaCantidadJugadores, FK_partidaCreador_id, partidaFechaCreada,partidaTermino "
				       + "from PARTIDAS where PK_partida_id=(?)";
		return query;
	}
    //Revisado ok
	// Inserta Jugador
	public String insertarJugador() {
		String query = "insert into JUGADORES(jugadorUserName,jugadorPassword,jugadorIsOnline,jugadorPuntajeAcumulado) values(?,?,?,?)";
		return query;
	}
    //Revisado ok
	// Insertar Partida
	public String insertarPartida() {
		String query = "insert into PARTIDAS(partidaEstado,partidaFechaUltimaActualizacion,partidaGuardada,partidaNombre,partidaCantidadJugadores,FK_partidaCreador_id,partidaFechaCreada,partidaTermino) "
				+ "values(?,?,?,?,?,?,?,?)";
		return query;
	}
    //Revisado ok
	// Listar Jugador
	public String listarJugadores() {
		String query = "select PK_jugador_id, jugadorUserName, jugadorPassword, jugadorIsOnline, jugadorPuntajeAcumulado "
				       + "from JUGADORES ORDER BY PK_jugador_id";
		return query;
	}
    //Revisado ok
	// Dado el id de jugador se trae el jugador
	public String obtenerJugador() {
		String query = "select PK_jugador_id, jugadorUserName, jugadorPassword, jugadorIsOnline, jugadorPuntajeAcumulado "
				       + "from JUGADORES"
				       + " where PK_jugador_id=(?)";
		return query;
	}
    //Revisado ok
    // Borrar partida dado un id
	public String borrarPartida() {
		String query = "DELETE FROM PARTIDAS WHERE PK_partida_id=(?);";
		return query;
	}
    //Revisado ok
	// Borrar jugador dado un id
	public String borrarJugador() {
		String query = "DELETE FROM JUGADORES WHERE  PK_jugador_id=(?);";
		return query;
	}
    //Revisado ok
	// Se verifica que la cuenta del jugador exista
	public String acountExists() {
		String query = "select PK_jugador_id, jugadorUserName, jugadorPassword, jugadorIsOnline, jugadorPuntajeAcumulado"
				         + " from JUGADORES"
				         + " where jugadorUserName=(?) and  jugadorPassword=(?);";
		return query;
	}
    //Revisado ok
	// Se obtiene el nombre del jugador dado el id del jugador
	public String getName() {
		String query = "select jugadorUserName from JUGADORES where PK_jugador_id=(?)";
		return query;
	}
	//Revisado ok
	// Se obtiene la cantidad total de jugadores del juego
	public String cantidadTotalJugadores() {
		String query = "SELECT count(*) FROM JUGADORES";
		return query;
	}
	//Revisado ok
    // Se obtiene la cantidad total de jugadores del juego que estan online
	public String cantidadJugadoresOnLine() {
		String query = "SELECT count(*) FROM JUGADORES where jugadorIsOnline=1";
		return query;
	}
	//Revisado ok
	// Se devuelven los artilleros
	public String estaVaciaArtillero() {
		String query = "Select PK_artillero_id, artilleroCoordX, artilleroCoordY, artilleroEstado, "
				       + "artilleroVida, artilleroHayEnemigo, artilleroRangoVision, artilleroAngulo, FK_base_id "
				       + "from ARTILLEROS";
		return query;
	}
    //Revisado ok
	// Se insertan artilleros
	public String insertarArtillero() {
		String query = "insert into ARTILLEROS(artilleroCoordX,artilleroCoordY,artilleroEstado,artilleroVida,artilleroHayEnemigo,artilleroRangoVision,artilleroAngulo,FK_base_id) values(?,?,?,?,?,?)";
		return query;
	}
    //Revisado ok
   // Se devuelven los aviones
	public String existenAviones() {
		String query = "Select PK_avion_id, avionCoordX, avionCoordY, avionCoordZ, avionEstado, avionVida, avionHayEnemigo, avionRangoVision, avionTieneBomba, avionCantBombas, "
				        + " avionCantCombustible, avionEnCampoEnemigo, "
				        + "FK_base_id from AVIONES";
		return query;
	}
    //Revisado ok
	// Se insertan Aviones
	public String insertarAvion() {
		String query = "insert into AVIONES(avionCoordX,avionCoordY,avionCoordZ,avionEstado,avionVida,avionHayEnemigo,avionRangoVision,avionTieneBomba,avionCantBombas,avionCantCombustible,avionEnCampoEnemigo,FK_base_id) "
				      + "values(?,?,?,?,?,?,?,?,?,?,?,?)";
		return query;
	}
    //Revisado ok
	// Se listan todos los aviones
	public String listarAviones() {
		String query = "Select PK_avion_id, avionCoordX, avionCoordY, avionCoordZ, avionEstado, avionVida, avionHayEnemigo, avionRangoVision, avionTieneBomba, "
				        + "avionCantBombas,avionCantCombustible, avionEnCampoEnemigo, FK_base_id  From AVIONES;";
		return query;
	}
     //Revisado ok
	// Se listan toda la artilleria
	public String listarArtilleros() {
		String query = "Select PK_artillero_id,artilleroCoordX,artilleroCoordY,artilleroEstado,artilleroVida,"
				        + "artilleroHayEnemigo,artilleroRangoVision,artilleroAngulo,FK_base_id"
				      + "from ARTILLEROS;";
		return query;
	}
    //Revisado ok
	// Se valida si hay bases
	public String existeBase() {
		String query = "Select PK_base_id, FK_depBombas_id, FK_depCombustible_id, FK_torreControl_id"
				       + "From BASES";
		return query;
	}
    //Revisado ok
	// Se ingresa una base
	public String insertarBase() {
		String query = "insert into BASES(FK_depBombas_id,FK_depCombustible_id,FK_torreControl_id) values(?,?,?)";
		return query;
	}
    //Revisado ok
	// Se obtiene una base dado su ID
	public String obtenerBase() {
		String query = "Select PK_base_id,FK_depBombas_id,FK_depCombustible_id,FK_torreControl_id"
				       + " From BASES where PK_base_id=(?);";
		return query;
	}
    //Revisado ok
	// Borra una base dado su id
	public String borrarbase() {
		String query = "DELETE FROM BASES WHERE  PK_base_id=(?);";
		return query;
	}
	//Revisado ok
	public String listarBase() {
		String query = "Select PK_base_id,FK_depBombas_id,FK_depCombustible_id,FK_torreControl_id, "
				      + "From BASES;";
		return query;
	}
	//Revisado ok
	public String cantidadTotalBases() {
		String query = "Select count(PK_base_id) From BASES;";
		return query;
	}
	//Revisado ok
	public String existenJugadores() {
		String query = "Select PK_jugador_id, jugadorUserName, jugadorPassword, jugadorIsOnline, jugadorPuntajeAcumulado "
				     + "From JUGADORES;";
		return query;
	}
    //Revisado ok
	public String insertarEquipo() {
		String query = "insert into EQUIPOS(equipoBando,FK_partida_id,FK_base_id) values(?,?,?);";
		return query;
	}
    //Revisado ok
	public String cantidadEquipos() {
		String query = "Select count(PK_equipo_id) From EQUIPOS;";
		return query;
	}
    //Revisado ok
	public String obtenerEquipoxIDPartida() {
		String query = " select PK_equipo_id, equipoBando, FK_partida_id, FK_base_id from EQUIPOS where FK_partida_id =(?)";
		return query;
	}
    //Revisado ok
	public String listarEquipos() {
		String query = "Select PK_equipo_id, equipoBando, FK_partida_id, FK_base_id From EQUIPOS;";
		return query;
	}
    //Revisado ok
	public String ultimoJugadorID() {
		String query = "Select Max(idJugador) From JUGADORES;";
		return query;
	}
    //Revisado ok
	public String getJugadorIdByName() {
		String query = "Select PK_jugador_id, jugadorUserName, jugadorPassword, jugadorIsOnline, jugadorPuntajeAcumulado "
				     + "From JUGADORES where jugadorUserName=(?);";
		return query;
	}
    //Revisado OK
	public String listarPartidasDeUnJugador() {

		String query = "SELECT P.PK_partida_id, P.partidaEstado, P.partidaFechaUltimaActualizacion, "
				      + "P.partidaGuardada,P.partidaNombre, P.partidaCantidadJugadores, "
				      + "P.FK_partidaCreador_id,P.partidaFechaCreada,P.partidaTermino " + 
				       "FROM EQUIPOS_JUGADORES EJ,EQUIPOS E, PARTIDAS P " + 
				       "WHERE EJ.PK_jugador_id=(?) and EJ.PK_equipo_id=E.PK_equipo_id and E.FK_partida_id=P.PK_partida_id " + 
				       "ORDER BY P.PK_partida_id";
		return query;
	}
    //Revisado ok
	public String existenPartidas() {
		String query="select PK_partida_id, partidaEstado ,partidaFechaUltimaActualizacion, "
				   + "partidaGuardada, partidaNombre, partidaCantidadJugadores, FK_partidaCreador_id, partidaFechaCreada,partidaTermino " + 
				     "from PARTIDAS;";
	
		return query;
	}
    //Revisado ok
	public String ultimaPartidaID() {
		String query = "Select Max(PK_partida_id) From PARTIDAS;";
		return query;
	}
    //Revisado ok
	public String ultimaBaseID() {
		String query = "Select Max(PK_base_id) From BASES;";
		return query;
	}
    //Revisado ok
	public String ultimaEquipoId() {
		String query = "Select Max(PK_equipo_id) From EQUIPOS;";
		return query;
	}
  //Revisado ok
	public String ultimaTorreId() {
		String query = "Select Max(PK_torreControl_id) From TORRES_DE_CONTROL;";
		return query;
	}
  //Revisado ok
	public String ultimaTanqueId() {
		String query = "Select Max(PK_depCombustible_id) From DEPOSITO_DE_COMBUSTIBLE;";
		return query;
	}
 //Revisado ok
	public String ultimaDepositoId() {
		String query = "Select Max(PK_depBombas_id) From DEPOSITOS_DE_BOMBAS;";
		return query;
	}
	//Revisado ok
	public String listarEquiposDeUnaPartida() {
		String query = "Select PK_equipo_id, equipoBando, FK_partida_id, FK_base_id from EQUIPOS where FK_partida_id=(?);";
		return query;
	}
	//Revisado ok
	public String obtenerDepositobyId() {
		String query = "Select PK_depBombas_id,  depBombasCoordX,  depBombasCoordY,  depBombasEstado,  depBombasVida,  depBombasCantBombas,  depBombasEnUso"
				      + " from DEPOSITOS_DE_BOMBAS where PK_depBombas_id=(?);";
		return query;
	}

	//Revisado ok
	public String obtenerTanquebyId() {
		String query = "Select PK_depCombustible_id,  depCombustibleCoordX,  depCombustibleCoordY,   depCombustibleEstado,"
						+ " depCombustibleVida,  depCombustibleCantCombustible,  depCombustibleEnUso"
						+ " from DEPOSITO_DE_COMBUSTIBLE"
						+ " where PK_depCombustible_id=(?);";
		return query;
	}
	//Revisado ok
	public String obtenerTorreControlbyId() {
		String query = "Select PK_torreControl_id, torreControlCoordX, torreControlCoordY, torreControlEstado, torreControlVida,"
					+ " torreControlHayEnemigo, torreControlRangoVision"
					+ " from TORRES_DE_CONTROL where PK_torreControl_id=(?);";
		return query;
	}
	
    //Revisado ok
	public String obtenerAvionXBase() {
		String query = "Select PK_avion_id, avionCoordX, avionCoordY, avionCoordZ, avionEstado, avionVida, avionHayEnemigo, "
				    + " avionRangoVision, avionTieneBomba, avionCantBombas, avionCantCombustible, avionEnCampoEnemigo, FK_base_id "
				     + " from AVIONES where FK_base_id=(?);";
		return query;
	}
    //Revisado ok
	public String listarArtillerosXBase() {
		String query = "Select PK_artillero_id, artilleroCoordX, artilleroCoordY, artilleroEstado, artilleroVida,"
				        + " artilleroHayEnemigo, artilleroRangoVision, artilleroAngulo, FK_base_id "
				        + "from ARTILLEROS where FK_base_id=(?);";
		return query;
	}

	//Revisado ok
	// listar partidas dado un jugador ver linea 207 
	//listar partidas de todos los jugadores...
	public String listarPartidas() {
		String query = "select PK_partida_id, partidaEstado, partidaFechaUltimaActualizacion," 
				+ " partidaGuardada, partidaNombre, partidaCantidadJugadores," 
				+ " FK_partidaCreador_id, partidaFechaCreada, partidaTermino" 
				+ " FROM partidas" 
				+ " ORDER BY PK_partida_id;";
		return query;
	}
	//Revisado ok
	// Setea a un jugador online dado su nombre, 
	//hay que desabilitar el modo seguro para poder hacer este tipo de modificacion
	//por lo que se decidió llamar a get idbyUsername y cambiar la consulta
	public String isOnLineJugador() {
		String query = "UPDATE JUGADORES" + "SET jugadorIsOnline = 1" + "WHERE PK_jugador_id=(?);";
		return query;
	}
	//Revisado ok
	//mismo caso que isonline
	public String logoutJugadorPorUserName() {
		String query = "UPDATE JUGADORES" + "SET jugadorIsOnline = 0" + "WHERE PK_jugador_id=(?);";
		return query;
	}
	//Revisado ok
	public String insertarDeposito() {
		String query = "insert into DEPOSITOS_DE_BOMBAS(depBombasCoordX,depBombasCoordY,depBombasEstado,depBombasVida,depBombasCantBombas,depBombasEnUso) values(?,?,?,?,?,?);";
		return query;

	}
   //Revisado ok
	public String insertarTanqueCombustible() {
		String query = "insert into DEPOSITO_DE_COMBUSTIBLE(depCombustibleCoordX,depCombustibleCoordY,depCombustibleEstado,depCombustibleVida,depCombustibleCantCombustible,depCombustibleEnUso) values(?,?,?,?,?,?);";
		return query;
	}
	//Revisado ok
	public String insertarTorreControl() {
		String query = "insert into TORRES_DE_CONTROL(torreControlCoordX,torreControlCoordY,torreControlEstado,torreControlVida,torreControlHayEnemigo,torreControlRangoVision) values(?,?,?,?,?,?);";
		return query;
	}

}
