package persistencia.excepciones;

public class ClassNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClassNotFoundException() {}

    public ClassNotFoundException(String mensaje)
    {
       super(mensaje);
    }

}
