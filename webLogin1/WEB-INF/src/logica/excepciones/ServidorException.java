package logica.excepciones;

public class ServidorException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServidorException() {}

    public ServidorException(String mensaje)
    {
       super(mensaje);
    }
}
