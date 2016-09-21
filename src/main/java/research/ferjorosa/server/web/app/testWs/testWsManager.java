package research.ferjorosa.web.app.testWs;

import org.apache.sling.commons.json.JSONObject;
import org.eclipse.jetty.websocket.api.Session;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by fernando on 15/09/16.
 */
public class testWsManager {

    // Store sessions if you want to, for example, broadcast a message to all users
    static final Map<Session, UUID> sessionUuidMap = new ConcurrentHashMap<>();
    static final Map<UUID, Session> uuidSessionMap = new ConcurrentHashMap<>();

    static Set<Session> getSessions(){
        assert(sessionUuidMap.keySet().equals(uuidSessionMap.values()));
        return sessionUuidMap.keySet();
    }

    static Set<UUID> getIDs(){
        assert(uuidSessionMap.keySet().equals(sessionUuidMap.values()));
        return new HashSet<>(sessionUuidMap.values());
    }

    // Metodo para enviar mensajes a todos las sesiones conectadas?
    static void broadcastMessage(String message) {
        sessionUuidMap.keySet().stream().filter(Session::isOpen).forEach(session -> {
                try {
                    session.getRemote().sendString(String.valueOf(new JSONObject()
                            .put("broadcastMessage", message)
                            .put("idList", sessionUuidMap.values())
                    ));
                } catch (Exception e) {
                    e.printStackTrace();
            }
        });
    }

    // Metodo para enviar mensaje a una sesion en particular
    public static void sendMessage(String receiver, String message) {
        UUID sessionID = UUID.fromString(receiver);
        Session session = uuidSessionMap.get(sessionID);
        try {
            session.getRemote().sendString(String.valueOf(new JSONObject()
                    .put("personalMessage", message)));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}


