package Controllers;

import java.rmi.Naming;
import javax.swing.JOptionPane;
import Logic.IFachadaLogica;
import Logic.Mensajes;
import Logic.SystemProperties;

public abstract class ControllerContenedorPrincipal {

	protected IFachadaLogica iFachada;

	public ControllerContenedorPrincipal() {

		CargarIFachadaLogica();

	}

	private void CargarIFachadaLogica() {

		try {
			SystemProperties sp = new SystemProperties();
			String ip = sp.getIpServidor();
			String puerto = sp.getPuertoServidor();
			String nombreAPublicar = sp.getNombreAPublicar();
			String ruta = "//" + ip + ":" + puerto + "/" + nombreAPublicar;

			// accedo remotamente a la cuenta bancaria publicada en el servidor
			setiFachada((IFachadaLogica) Naming.lookup(ruta));

		} catch (Exception e) // si la ruta no esta bien formada
		{
			JOptionPane.showMessageDialog(null, Mensajes.M_ErrorAlIntentarConectarServer, "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public IFachadaLogica getiFachada() {
		return iFachada;
	}

	public void setiFachada(IFachadaLogica iFachada) {
		this.iFachada = iFachada;
	}

}
