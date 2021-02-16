//https://www.pegaxchange.com/2018/01/28/websocket-server-java/
//https://www.pegaxchange.com/2018/03/23/websocket-client/
//https://www.youtube.com/watch?v=mvAbWWn1cTk&list=PLL_H5w4KA8dP9pPayzYxHCD4IQ80nkfY9&index=2&ab_channel=gammafp

import java.util.HashMap;
import java.util.HashSet;
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


@ServerEndpoint("/endpointGAME")
public class MyWebSocketGAME {
    
	private static Set<Session> sessions = new HashSet<>();
	
	static Map<String, String> sessionPartida = new HashMap<String, String>();
		
    private static MyWebSocketGAME instancia; // SINGLETON
      
    
    @OnOpen
	public static MyWebSocketGAME getInstancia(Session session) {
    	System.out.println("MyWebSocket getInstancia onOpen::" + session.getId());   
		if (instancia == null) {
			instancia = new MyWebSocketGAME();
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
    
    
    @OnMessage
    public void onMsg(String in_DatosJSON, Session session) throws JSONException {
//    	System.out.println("======INICIO GAME==============================");
//    	System.out.println(session.getId() + "new message ==> " + in_DatosJSON);
    	
//    	System.out.println("1");
//    	JSONObject jsonData = new JSONObject(in_DatosJSON);
//    	System.out.println("2");
//    	
//    	String in_PartidaNumero="";
//    	in_PartidaNumero = jsonData.getString("partiSelect");
//    	System.out.println("in_PartidaNumero = jsonData.getString(\"partiSelect\");::" + in_PartidaNumero);
////        
//        System.out.println("cantidad de sessions:" + sessions.size());
//        

        try {
        		for (Session s : sessions) {		
        			if (s.getId() != session.getId()) {
//        				System.out.println("Entro a la s::"+s.getId());
        				String textoRespuestaJson=in_DatosJSON;
            			s.getBasicRemote().sendText(textoRespuestaJson);
					}
        		}
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
//        System.out.println("****************CHAU GAME**************************");
    }
    
    @OnError
    public void onError(Throwable t) {
        System.out.println("onError::" + t.getMessage());
    }
}