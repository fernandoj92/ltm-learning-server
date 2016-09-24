package research.ferjorosa.server.export.fileFormat.json.my.bn.dag;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fernando on 20/09/16.
 */
class MyJsonEdge {
    @SerializedName("source")
    private String source;
    @SerializedName("target")
    private String target;

    MyJsonEdge(String source, String target) {
        this.source = source;
        this.target = target;

    }
}