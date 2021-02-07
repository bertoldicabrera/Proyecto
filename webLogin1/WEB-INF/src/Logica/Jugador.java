package Logica;


public class Jugador {
	

	 	public String userName;
	    public String email;
	    public  int  id;
	    public String password;
	    
	    public Jugador( String nom,String mail, int codId, String pass) {
	    	this.userName=nom;
	    	this.email=mail;
	    	this.id=codId;
	    	this.password=pass;
	    }
	    
	    public String getUserName() {
	        return userName;
	    }
	 
	    public String getEmail() {
	        return email;
	    }
	    
	    public int getId() {
	    	return id;
	    }
	    public String getPassword() {
	    	 return password;
	    }
	    
	 
	    public void setUserName(String userName) {
	        this.userName = userName;
	    }
	    
	    public void setEmail(String email) {
	        this.email = email;
	    }
	    public void setId(int idd) {
	        this.id = idd;
	    }
	    public void setPassword(String pass) {
	        this.password= pass;
	    }
}
