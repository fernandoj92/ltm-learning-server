package research.ferjorosa.server.export.fileFormat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import eu.amidst.core.models.BayesianNetwork;
import research.ferjorosa.server.export.fileFormat.json.cytoscape.CytoscapeJsonBN;
import research.ferjorosa.server.export.fileFormat.json.my.MyJsonBN;

/**
 * Created by Fernando on 27/08/2016.
 */
public class ConvertBN {

    public static String toCytoscapeJson(BayesianNetwork bayesianNetwork, boolean prettyPrinting){
        Gson gson;
        if(prettyPrinting)
            gson = new GsonBuilder().setPrettyPrinting().create();
        else
            gson = new Gson();

        return gson.toJson(new CytoscapeJsonBN(bayesianNetwork));

    }

    public static String toMyJson(BayesianNetwork bayesianNetwork, boolean prettyPrinting){
        Gson gson;
        if(prettyPrinting)
            gson = new GsonBuilder().setPrettyPrinting().create();
        else
            gson = new Gson();

        return gson.toJson(new MyJsonBN(bayesianNetwork));
    }
}
