package research.ferjorosa.server.export.fileFormat.json.my.bn.dag;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fernando on 20/09/16.
 */
class MyJsonNode {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;

    MyJsonNode(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
