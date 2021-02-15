//https://www.pegaxchange.com/2018/01/28/websocket-server-java/

//https://www.pegaxchange.com/2018/03/23/websocket-client/


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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

//import org.json.*;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;


@ServerEndpoint("/endpoint")
public class MyWebSocket {
    
	private static Set<Session> sessions = new HashSet<>();
	
	static Map<String, String> sessionPartida = new HashMap<String, String>();
		
    private static MyWebSocket instancia; // SINGLETON
    
//    @OnOpen
//    public void onOpen(Session session) {
//        System.out.println("onOpen::" + session.getId());   
//        sessions.add(session);
//        
//        
//    }
    
    
    @OnOpen
	public static MyWebSocket getInstancia(Session session) {
    	System.out.println("MyWebSocket getInstancia onOpen::" + session.getId());   
		if (instancia == null) {
			instancia = new MyWebSocket();
		}
		sessions.add(session);
		sessionPartida.put(session.getId(), "9999");
		
		return instancia;
	}
    
    
    
    @OnClose
    public void onClose(Session session) {
        System.out.println("onClose::" +  session.getId());
        sessions.remove(session);
    }
    
//    @OnMessage
//    public void onMessage(String message, Session session) {
//        System.out.println("onMessage::From=" + session.getId() + " Message=" + message);
//        
//        try {
//            session.getBasicRemote().sendText("Hello Client " + session.getId() + "!");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    
    @OnMessage
    public void onMsg(String in_DatosJSON, Session session) throws JSONException {
    	System.out.println("===========*INICIO==================================");
    	System.out.println(session.getId() + "new message ==> " + in_DatosJSON);
    	
    	System.out.println("1");
    	JSONObject jsonData = new JSONObject(in_DatosJSON);
    	System.out.println("2");
    	
    	String in_PartidaNumero="";
    	in_PartidaNumero = jsonData.getString("partiSelect");
    	System.out.println("in_PartidaNumero = jsonData.getString(\"partiSelect\");::" + in_PartidaNumero);
//        
        System.out.println("cantidad de sessions:" + sessions.size());
        
//        sessionPartida.put(session.getId(), in_PartidaNumero);
//        x = sessionPartida.get(session);
        String actualValue = sessionPartida.get(session.getId());
        sessionPartida.replace(session.getId(), actualValue, in_PartidaNumero);
        
        System.out.println("cantidad de sessionPartida:" + sessionPartida.size());
        try {
        	String partidaNumero;// = sessionPartida.get(session.getId());
        	for (int c = 0; c < 5; c++) {
            	System.out.println("===entrada "+c+" for de session============");
				
            	for (Session s : sessions) {
					partidaNumero = "";
					partidaNumero = sessionPartida.get(s.getId()); 
					
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
        System.out.println("****************Hola**************************");
    }
    
    @OnError
    public void onError(Throwable t) {
        System.out.println("onError::" + t.getMessage());
    }
}