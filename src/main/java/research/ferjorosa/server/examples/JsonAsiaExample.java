package research.ferjorosa.server.examples;

import eu.amidst.core.datastream.DataInstance;
import eu.amidst.core.datastream.DataOnMemory;
import eu.amidst.core.datastream.DataStream;
import eu.amidst.core.io.DataStreamLoader;
import research.ferjorosa.core.execution.ExecutionResult;
import research.ferjorosa.core.learning.normal.StaticLearningAlgorithm;
import research.ferjorosa.core.learning.normal.structural.ABI;
import research.ferjorosa.core.learning.normal.structural.ABIConfig;
import research.ferjorosa.server.export.LocalFileExporter;
import research.ferjorosa.server.export.fileFormat.json.JsonTransform;

/**
 * Created by Fer on 27/09/2016.
 */
public class JsonAsiaExample {

    public static void main(String[] args) throws Exception {

        DataStream<DataInstance> data = DataStreamLoader.open("data/Asia_train.arff");

        StaticLearningAlgorithm staticLearningAlgorithm = new ABI(new ABIConfig());

        for (DataOnMemory<DataInstance> batch : data.iterableOverBatches(100)){
            ExecutionResult result = staticLearningAlgorithm.execute(batch);
            String jsonResult = JsonTransform.toMyJson(result, true);
            LocalFileExporter
                    .writeJsonFile(jsonResult, System.getProperty("user.dir") + "/results/myJsonResult1.json");
        }

    }
}
