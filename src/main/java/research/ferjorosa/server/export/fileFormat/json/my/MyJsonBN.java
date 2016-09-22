package research.ferjorosa.server.export.fileFormat.json.my;

import com.google.gson.annotations.SerializedName;
import eu.amidst.core.models.BayesianNetwork;
import eu.amidst.core.variables.Variable;
import research.ferjorosa.server.export.fileFormat.json.JsonBN;
import research.ferjorosa.server.export.fileFormat.json.my.cpt.JsonCPT;
import research.ferjorosa.server.export.fileFormat.json.my.cpt.MyJsonCptFactory;
import research.ferjorosa.server.export.fileFormat.json.my.dag.MyJsonDAG;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fernando on 20/09/16.
 */
public class MyJsonBN implements JsonBN{

    @SerializedName("dag")
    private MyJsonDAG myJsonDAG;

    @SerializedName("cpts")
    private List<JsonCPT> cpts = new ArrayList<>();

    public MyJsonBN(BayesianNetwork bn){

        this.myJsonDAG = new MyJsonDAG(bn.getDAG());

        MyJsonCptFactory factory = new MyJsonCptFactory();
        for(Variable var : bn.getVariables().getListOfVariables())
            cpts.add(factory.createMyJsonCPT(var,bn.getConditionalDistribution(var)));
    }
}
