//https://www.pegaxchange.com/2018/01/28/websocket-server-java/

//https://www.pegaxchange.com/2018/03/23/websocket-client/


import java.io.IOException;
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

@ServerEndpoint("/endpoint")
public class MyWebSocket {
    
	private Set<Session> sessions = new HashSet<>();
		
    private static PushTimeService pst;
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("onOpen::" + session.getId());   
        sessions.add(session);
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
    public void onMsg(String message, Session session) {
        System.out.println("new message ==> " + message);
        try {
            for (int c = 0; c < 100; c++) {
				for (Session s : sessions) {
                    s.getBasicRemote().sendText("{\"value\" : \"" + (c + 1) + "\"}");
                }
                Thread.sleep(100);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    @OnError
    public void onError(Throwable t) {
        System.out.println("onError::" + t.getMessage());
    }
}