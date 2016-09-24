package research.ferjorosa.server.export.fileFormat.json.my;

import com.google.gson.annotations.SerializedName;
import research.ferjorosa.core.execution.ExecutionResult;
import research.ferjorosa.server.export.fileFormat.json.my.bn.JsonBN;
import research.ferjorosa.server.export.fileFormat.json.my.bn.MyJsonBN;

/**
 * Created by fernando on 23/09/16.
 */
public class MyJsonExecutionResult {

    @SerializedName("bayesianNetwork")
    private JsonBN bayesianNetwork;

    @SerializedName("uniqueID")
    private String uniqueID;

    @SerializedName("algorithm")
    private String algorithm;

    @SerializedName("index")
    private int index;

    @SerializedName("nanoStart")
    private double nanoStart;

    @SerializedName("nanoFinish")
    private double nanoFinish;

    public MyJsonExecutionResult(ExecutionResult result){
        this.bayesianNetwork = new MyJsonBN(result.getModel().getLearntBayesianNetwork());
        this.algorithm = result.getAlgorithm();
        this.index = result.getIndex();
        this.nanoStart = result.getNanoStart();
        this.nanoFinish = result.getNanoFinish();
    }
}
