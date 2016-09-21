package research.ferjorosa.server.util.exportBN.format.json.my;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by fernando on 20/09/16.
 */
class MyJsonDAG {
    @SerializedName("nodes")
    private List<MyJsonNode> nodes;
    @SerializedName("edges")
    private List<MyJsonEdge> edges;

    MyJsonDAG(List<MyJsonNode> nodes, List<MyJsonEdge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }
}

