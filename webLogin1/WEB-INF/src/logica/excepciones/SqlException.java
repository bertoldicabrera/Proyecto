package logica.excepciones;

public class SqlException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SqlException() {}

      public SqlException(String mensaje)
      {
    	
         super(mensaje);
      }

     
      
}
