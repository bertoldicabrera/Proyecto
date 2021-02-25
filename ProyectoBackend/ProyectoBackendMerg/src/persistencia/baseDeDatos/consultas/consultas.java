package persistencia.baseDeDatos.consultas;

public class consultas {

	//Dado el nombre de jugador se trae el jugador
	public String existeJugador(){
		String query="select idJugador, nombreJugador, passJugador, onlineJugador, puntajeJugador from JUGADOR where nombreJugador=(?)";
		return query;
	}
	
	//Dado el id de la partida se trae la partida
	public String existePartida(){
		String query="select idPartida,estadoPartida,ultimaactualizacionPartida,guardadaPartida,nombrePartida,cantjugadoresPartida,creadorPartida,fechacreadaPartida,terminoPartida from PARTIDA where partidaId=(?)";
		return query;
	}
	
	//Inserta Jugador 
	public String insertarJugador() {
		String query="insert into JUGADOR(idJugador, nombreJugador, passJugador, onlineJugador, puntajeJugador) values(?,?,?,?,?)";
		return query;
	}
	//Insertar Partida
	public String insertarPartida() {
		String query="insert into PARTIDA(idPartida, estadoPartida, ultimaactualizacionPartida, guardadaPartida, nombrePartida,  cantjugadoresPartida,  creadorPartida, fechacreadaPartida,  terminoPartida) values(?,?,?,?,?,?,?,?,?)";
		return query;
	}
	
	//Listar Jugador
	public String listarJugadores() {
		String query="select idJugador,nombreJugador,passJugador,onlineJugador,puntajeJugador from JUGADOR ORDER BY jugadorId";
		return query;
	}
	
	
	//Dado el id de jugador se trae el jugador
	 public String obtenerJugador() {
		String query="select idJugador,nombreJugador,passJugador,onlineJugador,puntajeJugador from JUGADOR"
			       + " where jugadorId=(?)";
		return query;
	}
	

	
	//Borrar partida dado un id
	public String borrarPartida() {
		String query="DELETE FROM PARTIDA WHERE idPartida=(?);";
		return query;
	}
	//Borrar jugador dado un id
	public String borrarJugador() {
		String query="DELETE FROM PARTIDA WHERE  jugadorId=(?);";
		return query;
	}
	
    //Se verifica que la cuenta del jugador exista
	public String acountExists()
	{
		String query="select idJugador,nombreJugador,passJugador,onlineJugador,puntajeJugador from JUGADOR"
			       + " where nombreJugador=(?) and  passJugador=(?);";
		return query;
	}
	// Se obtiene el nombre del jugador dado el id del jugador
	public String getName()
	{
		String query="select nombreJugador from JUGADOR where idJugador=(?)";
		return query;
	}
	// Se obtiene la cantidad total de jugadores del juego
	public String cantidadTotalJugadores() {
		String query="SELECT count(*) as cantidad FROM JUGADOR";
		return query;
	}
	// Se obtiene la cantidad total de jugadores del juego que estan online 
	public String cantidadJugadoresOnLine()
	{
		String query="SELECT count(*) as cantidad FROM JUGADOR where onlineJugador=(1)";
		return query;
	}
	//Se devuelven los artilleros 
	public String estaVaciaArtillero() {
		String query="Select idArt, CoordXArt, CoordYArt, EstadoArt, VidaArt, HayEnemigoArt, RangoDeVisionArt, AnguloArt, idbaseArt from Artillero";
		return query;
	}
	//Se insertan artilleros
	public String insertarArtillero() {
		String query="insert into Artillero(idArt, CoordXArt, CoordYArt, EstadoArt, VidaArt, HayEnemigoArt, RangoDeVisionArt, AnguloArt, idbaseArt) values(?,?,?,?,?,?,?,?,?)";
		return query;
	}
	
	//Se devuelven los aviones 
	public String existenAviones() {
		String query="Select idAvion, CoordXAvion, CoordYAvion, EstadoAvion, VidaAvion, AnguloAvion, BombasEnAvion, BombasAvion, AlturaAvion, CombustibleAvion, HayEnemigoAvion, RangoDeVisionAvion, EnCampoEnemigo, idbaseAvion from Avion";
		return query;
	}
	//Se insertan Aviones
	public String insertarAvion() {
		String query="insert into Avion (idAvion, CoordXAvion, CoordYAvion, EstadoAvion, VidaAvion, AnguloAvion, BombasEnAvion, BombasAvion, AlturaAvion, CombustibleAvion, HayEnemigoAvion, RangoDeVisionAvion, EnCampoEnemigo, idbaseAvion) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		return query;
	}
   
	//Se listan todos los aviones
	public String listarAviones() {
		String query="Select idAvion,CoordXAvion,CoordYAvion,EstadoAvion,VidaAvion,AnguloAvion,BombasEnAvion,BombasAvion,AlturaAvion,CombustibleAvion,HayEnemigoAvion,RangoDeVisionAvion,EnCampoEnemigo,idbaseAvion From Avion;";
		return query;
	}


    //Se listan toda la artilleria
	public String listarArtilleros() {
		String query="Select idArt,CoordXArt,CoordYArt,EstadoArt,VidaArt,HayEnemigoArt,RangoDeVisionArt,AnguloArt,idbaseArt from Artillero;";
		return query;
	}
    //Se valida si hay bases
	public String existeBase() {
		String query="Select idBase, idTCtrolBase, idTcombBase, idDepoBase, idEquipoBase From BASE";
		return query;
	}
    //Se ingresa una base
	public String insertarBase() {
		String query="insert into BASE(idBase, idTCtrolBase, idTcombBase, idDepoBase, idEquipoBase) values(?,?,?,?,?)";
		return query;
	}
    //Se obtiene una base dado su ID
	public String obtenerBase() {
		String query="Select idBase,idTCtrolBase,idTcombBase,idDepoBase,idEquipoBase From BASE where idBase=(?);";
		return query;
	}
    //Borra una base dado su id
	public String borrarbase() {
		String query="DELETE FROM BASE WHERE  idBase=(?);";
		return query;
	}

	public String listarBase() {
		String query="Select idBase,idTCtrolBase,idTcombBase,idDepoBase,idEquipoBase From BASE;";
		return query;
	}

	public String cantidadTotalBases() {
		String query="Select count(idBase) From BASE;";
		return query;
	}

	public String existenJugadores() {
		String query="Select idJugador, nombreJugador, passJugador, onlineJugador, puntajeJugador From JUGADOR;";
		return query;
	}

	public String insertarEquipo() {
		String query="insert into Equipo (idEquipo, idJugadorEquipo, bandoEquipo, partidaEquipo) values(?,?,?,?);";
		return query;
	}

	public String cantidadEquipos() {
		String query="Select count(idEquipo) From Equipo;";
		return query;
	}

	public String obtenerEquipoxIDPartida() {
		String query=" select idEquipo, idJugadorEquipo, bandoEquipo, partidaEquipo from Equipo where partidaEquipo =(?)";
		return query;
	}

	public String listarEquipos() {
		String query="Select idEquipo, idJugadorEquipo, bandoEquipo, partidaEquipo From Equipo;";
		return query;
	}

	public String ultimoJugadorID() {
		String query="Select MaAX(idJugador) From Jugador;";
		return query;
	}

	public String getJugadorIdByName() {
		String query="Selec idJugador, nombreJugador, passJugador, onlineJugador, puntajeJugador From Jugador where nombreJugador=(?);";
		return query;
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
		String query="Select MaAX(idPartida) From Partida;";
		return query;
	}

	public String ultimaBaseID() {
		String query="Select MaAX(idBase) From BASE;";
		return query;
	}

	public String ultimaEquipoId() {
		String query="Select MaAX(idEquipo) From Equipo;";
		return query;
	}

	public String ultimaTorreId() {
		String query="Select MaAX(idTCtrol) From torrecontrol;";
		return query;
	}

	public String ultimaTanqueId() {
		String query="Select MaAX(idTComb) From tanquecombustible;";
		return query;
	}

	public String ultimaDepositoId() {
		String query="Select MaAX(idDepo) From deposito;";
		return query;
	}

	public String listarEquiposDeUnaPartida() {
		String query="Select idEquipo, idJugadorEquipo, bandoEquipo, partidaEquipo from Equipo where idPartida=(?);";
		return query;
	}

	public String obtenerDepositobyId() {
		String query="Select idDepo,  CoordXDepo,  CoordYDepo,  EstadoDepo,  VidaDepo,  CanBomboDepo,  EnUsoDepo from deposito where idDepo=(?);";
		return query;
	}

	public String obtenerTanquebyId() {
		String query="Select idTComb,  CoordXTComb,  CoordYTComb,   EstadoTComb,  VidaTComb,  CantCombTComb,  EnUsoTComb from tanquecombustible where idTComb=(?);";
		return query;
	}

	public String obtenerTorreControlbyId() {
		String query="Select idTCtrol, CoordXTCtrol, CoordYTCtrol, EstadoTCtrol, VidaTCtrol, HayEnemigoTCtrol, RangoDeVisionTCtrol from torrecontrol where idTCtrol=(?);";
		return query;
	}

	public String obtenerAvionXBase() {
		String query="Select idAvion, CoordXAvion, CoordYAvion, EstadoAvion, VidaAvion, AnguloAvion, BombasEnAvion, BombasAvion, AlturaAvion, CombustibleAvion, HayEnemigoAvion, RangoDeVisionAvion, EnCampoEnemigo, idbaseAvion from avion where idbaseAvion=(?);";
		return query;
	}

	public String listarArtillerosXBase() {
		String query="Select idArt, CoordXArt, CoordYArt, EstadoArt, VidaArt, HayEnemigoArt, RangoDeVisionArt, AnguloArt, idbaseArt from artillero where idbaseArt=(?);";
		return query;
	}

	// listar partidas dado un jugador(Ver LUEGO hay que hacer un join con Equipo,
	// JugadorEquipo ------------------------)
	public String listarPartidas() {
		String query = "select idPartida, estadoPartida, ultimaactualizacionPartida, guardadaPartida, nombrePartida,  cantjugadoresPartida,  creadorPartida, fechacreadaPartida,  terminoPartida From PARTIDA where jugadorId=(?) ORDER BY numero";
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
