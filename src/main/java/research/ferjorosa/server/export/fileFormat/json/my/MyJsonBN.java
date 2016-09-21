package research.ferjorosa.server.util.exportBN.format.json.my;

import com.google.gson.annotations.SerializedName;
import eu.amidst.core.models.BayesianNetwork;
import eu.amidst.core.models.DAG;
import eu.amidst.core.models.ParentSet;
import eu.amidst.core.variables.Variable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fernando on 20/09/16.
 */
public class MyJsonBN {

    @SerializedName("dag")
    private MyJsonDAG myJsonDAG;

    public MyJsonBN(BayesianNetwork bayesianNetwork){

        DAG dag = bayesianNetwork.getDAG();

        List<MyJsonNode> nodes = new ArrayList<>();
        List<MyJsonEdge> edges = new ArrayList<>();

        for(Variable var : dag.getVariables().getListOfVariables()){
            nodes.add(new MyJsonNode(var.getVarID() + ""));
        }

        for(ParentSet parent : dag.getParentSets()){
            for(Variable var : parent.getParents())
                edges.add(new MyJsonEdge(parent.getMainVar().getVarID()+"",var.getVarID()+""));
        }

        this.myJsonDAG = new MyJsonDAG(nodes,edges);
    }
}
