package research.ferjorosa.web.app;

import research.ferjorosa.web.app.staticLearn.StaticLearnController;
import research.ferjorosa.web.app.localData.LocalDataController;
import research.ferjorosa.web.app.streamLearn.StreamWebSocketHandler;

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
        //init(); // Needed if you don't define any HTTP routes after your WebSocket routes

        get("/listLocalFiles", LocalDataController.listLocalFiles);
        post("/learn/flatLTM", StaticLearnController.learnFlatLtmABI);

    }
}
