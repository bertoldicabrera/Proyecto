package Utilitarios;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import Logica.IFachada;
import Logica.Excepciones.LogicaException;
import Logica.Excepciones.PersistenciaException;
import Logica.Vo.VOJugador;
public class MainPrueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		conectar();
		registrar();
		
	}
	
	public static void registrar()
	{
		System.out.println("======================================");
		VOJugador vo= new VOJugador("test4", 5, "test4@test4.com", "password");
    	
        //Llegado a este punto significa que todo esta correcto, por lo tanto ingreso a la DB
    	try {
			fa.nuevoJugador(vo);
		} catch (RemoteException | LogicaException | PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	static IFachada fa;
	static SystemProperties sp; 
	
private static void conectar ()
{
	System.out.println( "*************####Entra a conetar#####*********");
	try {
		sp = new SystemProperties();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		String ip = sp.getIpServidor();
		String puerto = sp.getPuertoServidor();
		String ruta = "//" + ip + ":" + puerto + "/"+ sp.getNombreAPublicar();
		try {
			fa  = (IFachada) Naming.lookup(ruta);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

}
