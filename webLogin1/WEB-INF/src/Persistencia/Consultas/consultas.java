package Persistencia.Consultas;

public class consultas {

	
	public  String existEmailPassword ()
	{
		
	String query = 
	"SELECT * FROM web.usuarios" + 
	" WHERE email= ?"+" AND password= ?";
	return query;
	}
	
	//Metodo para consultar si el email recibido ya esta registrado
	public  String existsEmail ()
	{
		String query = 
		"SELECT * FROM web.usuarios"+
		" WHERE email= ?";
		return query;
	}
	
	
	public String insertUser()
	{
		String query = 
	"INSERT INTO web.usuarios"+
	" (email,password,name)"+
	" VALUES (?,?,?)";
		return query;
	}
	public String getNameUser()
	{
		String query =
	"SELECT * FROM web.usuarios"+
	" WHERE email= ?";
		return query;
	}
	
	public String listarJug()
	{
		String query =
		"SELECT name, id, email  FROM web.usuarios"+
		" order by id";
				return query;
	}
	public String obtenerJugador() {
		String query="select u.id,  u.name, u.password from web.usuarios u"
			       + " where u.email=(?)";
		return query;
	}
	
	public String borrarJugador() {
		String query="DELETE FROM web.usuarios where email=(?)";
		return query;
	}
}
