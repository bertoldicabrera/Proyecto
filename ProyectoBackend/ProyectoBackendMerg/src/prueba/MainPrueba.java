package prueba;

import java.io.IOException;
import java.rmi.NotBoundException;

public class MainPrueba {

	
	
	public static void main(String[] args) throws InterruptedException {
		
		MenuPrueba nuevo;
		try {
			nuevo = new MenuPrueba();
			nuevo.MenuJugador();
		} catch (IOException | NotBoundException e) {
			System.out.println(e.toString());
			
		}
		
	}
}
