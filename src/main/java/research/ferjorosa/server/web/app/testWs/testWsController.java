package research.ferjorosa.server.web.app.testWs;

import org.apache.sling.commons.json.JSONObject;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Created by fernando on 15/09/16.
 */
public class testWsController {

    public static Route test = (Request request, Response response) -> {
        return "Test apporved";
    };

    public static Route testMessage = (Request request, Response response) -> {
        String id = request.params("id");
        testWsManager.sendMessage(id, "test message");
        return "Mensaje enviado a "+id;
    };

    public static Route testMessageAll = (Request request, Response response) -> {
        testWsManager.broadcastMessage("broadcast test message");
        return "Mensaje enviado a todas las sesiones WS";
    };

    public static Route listActiveConnections  = (Request request, Response response) -> {
        return new JSONObject().put("connections",testWsManager.getIDs());
    };
}
