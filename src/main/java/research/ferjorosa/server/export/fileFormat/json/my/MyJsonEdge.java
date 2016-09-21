package research.ferjorosa.server.util.exportBN.format.json.my;

/**
 * Created by fernando on 20/09/16.
 */
class MyJsonEdge {

    private EdgeData data;

    MyJsonEdge(String source, String target) {
        this.data = new EdgeData(source, target);
    }

    private class EdgeData {

        private String source;
        private String target;

        EdgeData(String source, String target) {
            this.source = source;
            this.target = target;
        }
    }
}