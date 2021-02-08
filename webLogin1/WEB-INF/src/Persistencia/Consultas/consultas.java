package Persistencia.Consultas;

public class consultas {

	
	public  String belongEmailPassword ()
	{
		
	String query = 
	"SELECT * FROM usuarios" + 
	" WHERE email= ?"+" AND password= ?";
	return query;
	}
	
	//Metodo para consultar si el email recibido ya esta registrado
	public  String existsEmail ()
	{
		String query = 
		"SELECT * FROM usuarios"+
		" WHERE email= ?";
		return query;
	}
	
	
	public String insertUser()
	{
		String query = 
	"INSERT INTO usuarios"+
	" (email,password,name)"+
	" VALUES (?,?,?)";
		return query;
	}
	public String getNameUser()
	{
		String query =
	"SELECT * FROM usuarios"+
	" WHERE email= ?";
		return query;
	}
	
	public String allUsersQuery()
	{
		String query =
		"SELECT id, email, name FROM usuarios"+
		" order by id";
				return query;
	}
	public String obtenerJugador() {
		String query="select u.id,  u.name, u.password from usuarios u"
			       + " where u.email=(?)";
		return query;
	}
	
	
}
