package PersistenciaSql;

import java.io.Serializable;

public class PersistenceExceptions extends Exception implements Serializable {


	private static final long serialVersionUID = 1L;
private int codigoError;
    
    public PersistenceExceptions(int cod)
    {
        codigoError=cod;
    }
      
    public String getPersistenceException()
    {
         
        String mensaje="";
         
        switch(codigoError)
        {
            case 1:
                mensaje="Error ";
                break;
            case 2:
                mensaje="Error ";
                break;
            case 3:
            	mensaje="Error ";
                break;
            case 4:
            	mensaje="Error ";
                break;
            case 5:
            	mensaje="Error ";
                break;   
            
            default:mensaje="Error de acceso a base de datos";
            }
         
        return mensaje;
         
    }
	
}
