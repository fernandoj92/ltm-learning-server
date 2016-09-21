package research.ferjorosa.server.web.app;

import research.ferjorosa.server.web.app.staticLearn.StaticLearnController;
import research.ferjorosa.server.web.app.localData.LocalDataController;
import research.ferjorosa.server.web.app.streamLearn.StreamWebSocketHandler;
import research.ferjorosa.server.web.app.testWs.testWsController;
import research.ferjorosa.server.web.app.testWs.testWsHandler;

import static spark.Spark.*;

/**
 * Application module. Here will be defined its main settings and the api will be initialized in the form of its
 * corresponding controllers.
 */
public class Application {

    public static void main(String[] args){

        // Configure Spark
        port(8899); // Server port
        //staticFiles.location("/public");

        webSocket("/stream", StreamWebSocketHandler.class);
        webSocket("/testWs", testWsHandler.class);
        //init(); // Needed if you don't define any HTTP routes after your WebSocket routes

        get("/test",                testWsController.test);
        get("/lol", (req,response)-> "LOL");


        get("/listLocalFiles",          LocalDataController.listLocalFiles);
        post("/learn/flatLTM",          StaticLearnController.learnFlatLtmABI);
        get("/testMessage/:id",         testWsController.testMessage);
        get("/testMessageAll",          testWsController.testMessageAll);
        get("/listActiveConnections",   testWsController.listActiveConnections);

    }
}
