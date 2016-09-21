package research.ferjorosa.web.app.testWs;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.util.UUID;

/**
 * Created by fernando on 15/09/16.
 */
@WebSocket
public class testWsHandler {

    @OnWebSocketConnect
    public void onConnect(Session session) throws Exception {
        UUID id = UUID.randomUUID();
        testWsManager.sessionUuidMap.put(session, id);
        testWsManager.uuidSessionMap.put(id, session);
    }

    @OnWebSocketClose
    public void onClose(Session session, int statusCode, String reason) {
        UUID id = testWsManager.sessionUuidMap.get(session);
        testWsManager.uuidSessionMap.remove(id);
        testWsManager.sessionUuidMap.remove(session);

    }

    @OnWebSocketMessage
    public void onMessage(Session session, String message) {
        // We do nothing because the purpose is one-directional, from the server to the client
        System.out.println("msg: " + message + ", from "+ testWsManager.sessionUuidMap.get(session));
    }
}
