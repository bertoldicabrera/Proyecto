package Utilitarios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class MensajesPersonalizados {
	
	public String errorSQLejecutarUse;
	public String errorCargarMensajes;
	public String errorCargarParametros;
	public String errorJARClassForName;
	public String errorSQLConectarseBase;
	public String errorSQLCrearConexion;
	public String errorSQLCerrarConexion;
	public String errorSQLborrarBase;
	public String errorSQLcrearBase;
	public String errorSQLcreateTableUsuarios, errorSQLUpdateUsuario;
	public String errorSQLclavePrimaria;
	public String errorSQLclaveForanea;
	public String errorPoolConstructor;
	public String errorPoolCrearIConexion;
	public String errorPoolWait;
	public String errorPoolNotify;
	public String errorPoolCommit;
	public String errorPoolRollBack;
	public String errorFachadaConectarse;
	public String errorFachadaGetInstancia;
	public String warningServidorSinHttps;
	public String infoServerInit, errorSQLDeleteusuario,errorSQLListarUsuarios, errorSQLConsultarDescripcion,
	errorSQLConsultarCantidadDePuntos, errorSQLFindUsuario, errorFachadaNuevoUsuario, errorFachadaYaExisteUsuario,
	errorFachadaNoExisteUsuario,errorFachadaListUsuarios, errorGraficaNoHayUsuario, errorIO, errorSQLInsertUser;


	
	public MensajesPersonalizados(){
		Properties p = new Properties();
		String nomArchivoProperty = "config/mensajes.properties";
		try {
			p.load(new FileInputStream(nomArchivoProperty));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		errorSQLejecutarUse 				= p.getProperty("errorSQLejecutarUse");  
		errorSQLcreateTableUsuarios			= p.getProperty("errorSQLcreateTableUsuarios");
		errorCargarMensajes				 	= p.getProperty("errorCargarMensajes"); 
		errorCargarParametros				= p.getProperty("errorCargarParametros");    
		errorJARClassForName			 	= p.getProperty("errorJARClassForName");    
		errorSQLConectarseBase				= p.getProperty("errorSQLConectarseBase");    
		errorSQLCrearConexion				= p.getProperty("errorSQLCrearConexion");    
		errorSQLCerrarConexion 				= p.getProperty("errorSQLCerrarConexion");    
		errorSQLborrarBase				 	= p.getProperty("errorSQLborrarBase");    
		errorSQLcrearBase 					= p.getProperty("errorSQLcrearBase");    
		errorSQLUpdateUsuario 				= p.getProperty("errorSQLUpdateUsuario");    
		errorSQLDeleteusuario 				= p.getProperty("errorSQLDeleteusuario");    
		errorSQLListarUsuarios 				= p.getProperty("errorSQLListarUsuarios"); 
		errorSQLConsultarDescripcion 		= p.getProperty("errorSQLConsultarDescripcion");    
		errorSQLConsultarCantidadDePuntos 	= p.getProperty("errorSQLConsultarCantidadDePuntos");    
		errorSQLFindUsuario 				= p.getProperty("errorSQLFindUsuario");    
		errorFachadaNuevoUsuario 			= p.getProperty("errorFachadaNuevoUsuario");  
		errorFachadaYaExisteUsuario 		= p.getProperty("errorFachadaYaExisteUsuario");
		errorFachadaNoExisteUsuario 		= p.getProperty("errorFachadaNoExisteUsuario");
		errorFachadaListUsuarios 			= p.getProperty("errorFachadaListUsuarios");
		errorGraficaNoHayUsuario 			= p.getProperty("errorGraficaNoHayUsuario");
		errorSQLclavePrimaria 				= p.getProperty("errorSQLclavePrimaria");    
		errorSQLclaveForanea 				= p.getProperty("errorSQLclaveForanea");    
		errorPoolConstructor				= p.getProperty("errorPoolConstructor");    
		errorPoolCrearIConexion 			= p.getProperty("errorPoolCrearIConexion");    
		errorPoolWait						= p.getProperty("errorPoolWait");    
		errorPoolNotify 					= p.getProperty("errorPoolNotify");    
		errorPoolCommit 					= p.getProperty("errorPoolCommit");    
		errorPoolRollBack 					= p.getProperty("errorPoolRollBack");    
		errorFachadaConectarse 				= p.getProperty("errorFachadaConectarse");    
		errorFachadaGetInstancia 			= p.getProperty("errorFachadaGetInstancia");    
		warningServidorSinHttps 			= p.getProperty("warningServidorSinHttps");    
		infoServerInit 						= p.getProperty("infoServerInit"); 
		errorIO								= p.getProperty("errorIO"); 
		errorSQLInsertUser					= p.getProperty("errorSQLInsertUser");
		;
	}

}