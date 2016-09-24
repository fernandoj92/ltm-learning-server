package research.ferjorosa.server.export.fileFormat.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import eu.amidst.core.models.BayesianNetwork;
import research.ferjorosa.core.execution.ExecutionResult;
import research.ferjorosa.server.export.fileFormat.json.cytoscape.CytoscapeJsonBN;
import research.ferjorosa.server.export.fileFormat.json.my.MyJsonExecutionResult;
import research.ferjorosa.server.export.fileFormat.json.my.bn.JsonBnWrapper;
import research.ferjorosa.server.export.fileFormat.json.my.bn.MyJsonBN;

/**
 * Created by Fernando on 27/08/2016.
 */
public class JsonExporter {

    public static String toCytoscapeJson(BayesianNetwork bayesianNetwork, boolean prettyPrinting){
        Gson gson;
        if(prettyPrinting)
            gson = new GsonBuilder().setPrettyPrinting().create();
        else
            gson = new Gson();

        return gson.toJson(new JsonBnWrapper(new CytoscapeJsonBN(bayesianNetwork)));
    }

    public static String toMyJson(BayesianNetwork bayesianNetwork, boolean prettyPrinting){
        Gson gson;
        if(prettyPrinting)
            gson = new GsonBuilder().setPrettyPrinting().create();
        else
            gson = new Gson();

        return gson.toJson(new JsonBnWrapper(new MyJsonBN(bayesianNetwork)));
    }

    public static String toMyJson(ExecutionResult result, boolean prettyPrinting){
        Gson gson;
        if(prettyPrinting)
            gson = new GsonBuilder().setPrettyPrinting().create();
        else
            gson = new Gson();

        return gson.toJson(new MyJsonExecutionResult(result));
    }
}
