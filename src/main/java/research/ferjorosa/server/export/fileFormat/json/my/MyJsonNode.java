package research.ferjorosa.server.util.exportBN.format.json.my;

/**
 * Created by fernando on 20/09/16.
 */
class MyJsonNode {

    private NodeData data;

    MyJsonNode(String id) {
        this.data = new NodeData(id);
    }

    private class NodeData {
        private String id;

        NodeData(String id) {
            this.id = id;
        }
    }
}
