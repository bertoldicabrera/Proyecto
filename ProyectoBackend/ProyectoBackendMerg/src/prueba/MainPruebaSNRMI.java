package prueba;

import java.rmi.RemoteException;

import logica.Fachada;
import logica.excepciones.LogicaException;
import logica.valueObjects.VOJugador;
import persistencia.excepciones.PersistenciaException;

public class MainPruebaSNRMI {

	public MainPruebaSNRMI() {
       

	}
	public static void main(String[] args) throws LogicaException {
		
	
		try {
			Fachada f=new Fachada();
			VOJugador x = new VOJugador(1,  "in_JugadorUserName" , "in_JugadorPassword", true, 1);
			f.registrarJugador(x);
			System.out.println("Registro");
			
			
		} catch (RemoteException | PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

}
