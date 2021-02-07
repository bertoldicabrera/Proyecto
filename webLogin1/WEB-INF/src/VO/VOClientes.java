package VO;

import java.io.Serializable;
public class VOClientes implements Serializable{

private static final long serialVersionUID = 1L;

private  String name;
private  int  id;
private  String  email;

public VOClientes(String nom, int cod,  String  mail)
{
  name=nom;
  id=cod;
  email=mail;
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

}
