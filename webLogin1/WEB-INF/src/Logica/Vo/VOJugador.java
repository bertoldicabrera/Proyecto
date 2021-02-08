package Logica.Vo;

import java.io.Serializable;
public class VOJugador implements Serializable{

private static final long serialVersionUID = 1L;

private  String name;
private  int  id;
private  String  email, password;

public VOJugador(String nom, int cod,  String  mail, String pass)
{
  name=nom;
  id=cod;
  email=mail;
  password=pass;
}

public String GetName()
{
  return name;
}

public int GetId()
{
  return id;
}
public String GetEmail()
{
  return email;
}

public String GetPassword() {
	// TODO Auto-generated method stub
	return password;
}

}
