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
	
}
