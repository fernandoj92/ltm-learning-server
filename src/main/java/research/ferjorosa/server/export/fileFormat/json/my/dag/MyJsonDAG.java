package research.ferjorosa.server.export.fileFormat.json.my.dag;

import com.google.gson.annotations.SerializedName;
import eu.amidst.core.models.DAG;
import eu.amidst.core.models.ParentSet;
import eu.amidst.core.variables.Variable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fernando on 20/09/16.
 */
public class MyJsonDAG {
    @SerializedName("nodes")
    private List<MyJsonNode> nodes;
    @SerializedName("edges")
    private List<MyJsonEdge> edges;

    public MyJsonDAG(DAG dag){
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();

        for(Variable var : dag.getVariables().getListOfVariables()){
            nodes.add(new MyJsonNode(var.getVarID() + "", var.getName()));
        }

        for(ParentSet parent : dag.getParentSets()){
            for(Variable var : parent.getParents())
                edges.add(new MyJsonEdge(parent.getMainVar().getVarID()+"",var.getVarID()+""));
        }
    }
}

