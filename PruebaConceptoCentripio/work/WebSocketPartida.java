import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONException;
import org.json.JSONObject;

@ServerEndpoint("/webSocketEndPointPartida")
public class WebSocketPartida {
//////	Este web socket será  el principal y el único que sera utilizado para jugar.
//////	Este será  singleton, o sea existirá un único websocket para todos los jugadores y partidas que existan 
//////	cuando se inicia el servidor web (que es donde vive este websocket)

	////// SINGLETON
	private static WebSocketPartida webSocketUnicaInstancia; 
	
	////Colección de SESSION, para todo el websocket
	private static Set<Session> webSocketAllSessions = new HashSet<>();
	
	////Colección de {PARTIDA,{SESSION}} para guardar esa asociación, para todo el websocket
	private static Map<String, ArrayList<Session>> webSocketAllPartidaSessions = new HashMap<String, ArrayList<Session>>();
	
	
    @OnOpen
	public static WebSocketPartida getInstancia(Session in_Session) {  ////antes era public void onOpen(Session session) {
    	msgConsola("*********** Entro al Metodo public static WebSocketPartida getInstancia(Session in_Session) in_Session::" + in_Session.getId() );
    	   	
    	////agrego la session nueva a una lista generica por las dudas
		webSocketAllSessions.add(in_Session);
		
		////NOTA:: Al hash partidaID,{n sessiones}, lo agrego en el primer on message del cliente
		
		////Singleton::
		if (webSocketUnicaInstancia == null) {
			webSocketUnicaInstancia = new WebSocketPartida();
		}		
		msgConsola("*********** Antes del return del Metodo:: WebSocketPartida getInstancia onOpen con in_Session" );
    	
		return webSocketUnicaInstancia;
	}
    
    @OnClose
    public void onClose(Session in_Session) {
    	msgConsola("=========== Entro al Metodo:: public static WebSocketPartida getInstancia(Session in_Session)");
    	
    	////remuevo la session de la lista generica
    	webSocketAllSessions.remove(in_Session);
    	
    	boolean isElimine = false;
    	String idPartidaActualRecorrida = "";
    	Set<Session> sessionesDePartidaActualRecorrida = new HashSet<>();
    	System.out.println("Cantidad de elementos del hashmap antes::"+webSocketAllPartidaSessions.size());
    	for (Map.Entry<String, ArrayList<Session>> entry : webSocketAllPartidaSessions.entrySet()) {
    	    idPartidaActualRecorrida = entry.getKey();
    	    sessionesDePartidaActualRecorrida = (Set<Session>) entry.getValue();
    	    System.out.println("Cantidad de elementos de la subcoleccion antes::"+sessionesDePartidaActualRecorrida.size());
    	    
    	    isElimine = false;
    	    for (Session sessionActual : sessionesDePartidaActualRecorrida) {
    	    	if (sessionActual.getId().equals(in_Session.getId())) {
    	    		sessionesDePartidaActualRecorrida.remove(in_Session.getId());
    	    		isElimine = true;
    	    		System.out.println("Cantidad de elementos de la subcoleccion luego de borrar 1::"+sessionesDePartidaActualRecorrida.size());
				}
    	    }
    	    if (isElimine) {
        	    for (Session sessionActual : sessionesDePartidaActualRecorrida) {
        	    	try {
						sessionActual.getBasicRemote().sendText("fromOnClose::in_Session.getId()=closeSession");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        	    }
			}
    	    System.out.println("Cantidad de elementos de la subcoleccion luego::"+sessionesDePartidaActualRecorrida.size());
    	    if (sessionesDePartidaActualRecorrida.size()==0) {
    	    	webSocketAllPartidaSessions.remove(entry.getKey());
    	    	System.out.println("Cantidad de elementos del hashmap despues de borrar 1::"+webSocketAllPartidaSessions.size());
			}
    	    
    	}
    	System.out.println("Cantidad de elementos del hashmap despues::"+webSocketAllPartidaSessions.size());
	    
    	
    	////recorro todas las partidas, entro en cada subcoleccion y si esta esa session la elimino...
    	///luego de borrar si la subcoleccion quedo en 0, remuevo la partida del hash...    	
    	
    	
    	////cuando un jugador de la subcoleccion cierra, a las otras avisarles desde aca con
    	///s.getBasicRemote().sendText("usuario x cerro");
    	msgConsola("=========== Salio del Metodo:: public void onClose(Session in_Session)");
    	
    }
    
    @OnError
    public void onError(Throwable t) {
    	msgConsola("########### Entro al Metodo:: public void onError(Throwable t)");
    	msgConsola("onError Throwable t::" + t.getMessage());
    	msgConsola("########### Salio del Metodo:: public void onError(Throwable t)");
    }
    
    
   
    
    @OnMessage
    public void onMsg(String in_DatosJSON, Session in_Session) throws JSONException {
    	msgConsola("$$$$$$$$$$$ Entro al Metodo public void onMsg(String in_DatosJSON, Session in_Session) throws JSONException");
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	System.out.println("==***====INICIO==============================");
    	System.out.println(in_Session.getId() + " new message ==> " + in_DatosJSON);
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	if ("PartidaID="=="PartidaID=") {
    		String in_idPartida = "";
        	//System.out.println("ID PARTIDA:: "+in_idPartida);
        	
//        	////Creo una coleccion para insertar 1 jugador
//        	////Colección de SESSION que tenga una PARTIDA. (no seran todas las del websocket) No necesita que tenga mucha performance
//        	ArrayList<Session> partidaSessions = new ArrayList<Session>();
//        	partidaSessions.add(in_Session);
//        	
//        	////Inserto este jugador dentro de la lista, como value al map de key 99999)
//        	////(luego va a entrar otro jugador, y luego con los send y onmessage, se les reacomoda y quedan los 2 jugadores con la misma id de partida
//        	webSocketAllPartidaSessions.put("99999",partidaSessions);
		} else {

			
	    	
	    	
	    	System.out.println("1");
	    	JSONObject jsonData = new JSONObject(in_DatosJSON);
	    	System.out.println("2");
	    	
	    	String in_PartidaNumero="";
	    	in_PartidaNumero = jsonData.getString("partiSelect");
	    	System.out.println("in_PartidaNumero = jsonData.getString(\"partiSelect\");::" + in_PartidaNumero);
	//        
	        System.out.println("cantidad de sessions:" + webSocketAllSessions.size());
	        
	//        sessionPartida.put(session.getId(), in_PartidaNumero);
	//        x = sessionPartida.get(session);
	//        String actualValue = sessionPartida.get(in_Session.getId());
	//        sessionPartida.replace(in_Session.getId(), actualValue, in_PartidaNumero);
	        
	//        System.out.println("cantidad de sessionPartida:" + sessionPartida.size());
	        try {
	        	String partidaNumero;// = sessionPartida.get(session.getId());
	        	for (int c = 0; c < 5; c++) {
	            	System.out.println("===entrada "+c+" for de session============");
					
	            	for (Session s : webSocketAllSessions) {
						partidaNumero = "";
	//					partidaNumero = sessionPartida.get(s.getId()); 
						
						System.out.println("session s actual del for::" +s.getId());
						System.out.println("===partidaNumero::"+partidaNumero);
						System.out.println("===in_PartidaNumero::"+in_PartidaNumero);
						if ( (!partidaNumero.isEmpty()) && (!in_PartidaNumero.isEmpty()) && (partidaNumero.equals(in_PartidaNumero)) ) {
							System.out.println("deberia entrar solo 1");
	                        String textoRespuestaJson="";
	                        JSONObject jsonData2 = new JSONObject();
	                        jsonData2.put("codigorespuesta", (c + 1) );
	    
	                        
	                    	 Map<String, String> mapJSON = new HashMap<String, String>();
	
	                       
	                    	mapJSON.put("nombre1","josesito");
	                    	mapJSON.put("nombre2","pepito");
	                        jsonData2.put("mapJson", mapJSON );
	                        textoRespuestaJson=jsonData2.toString();
	                        System.out.println("Se envia JSON:"+ textoRespuestaJson);
							s.getBasicRemote().sendText(textoRespuestaJson);
							
							
							
							//						s.getBasicRemote().sendText("{\"value\" : \"" + (c + 1) + "\"}");
							}
						else
							System.out.println("no entro a imprimir");
						
						
					}
	                Thread.sleep(100);
	            }
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	            e.printStackTrace();
	        }
		}
        System.out.println("****************chau**************************");
  
        msgConsola("$$$$$$$$$$$ Salio del Metodo public void onMsg(String in_DatosJSON, Session in_Session) throws JSONException");
    	
    }
    
    
    
    
    
    ////Pasar estos 3 a un utilitarios luego en el proyecto general
    public static void msgConsola(String in_msgToConsole) {
    	printCompleteDateTime();
        System.out.println(in_msgToConsole.trim());
    }

	public static void printCompleteDateTime() {
		System.out.print(getCompleteDateTime()+"==>");
//		System.out.print(new Date().toString()+"==>");
	}
		
	public static String getCompleteDateTime() {
		String YMDHMS = "";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		YMDHMS = (String) (dtf.format(now));
		
		return YMDHMS;
	}
}