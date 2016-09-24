package research.ferjorosa.server.export.fileFormat.json.my.bn;

import com.google.gson.annotations.SerializedName;
import research.ferjorosa.server.export.fileFormat.json.my.bn.JsonBN;

/**
 * Created by fernando on 22/09/16.
 */
public class JsonBnWrapper {

    @SerializedName("bayesianNetwork")
    private JsonBN jsonBN;

    public JsonBnWrapper(JsonBN jsonBN){ this.jsonBN = jsonBN; }
}