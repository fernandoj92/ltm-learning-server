package research.ferjorosa.server.web.app.staticLearn;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import eu.amidst.core.datastream.DataInstance;
import eu.amidst.core.datastream.DataOnMemory;
import eu.amidst.core.datastream.DataStream;
import eu.amidst.core.learning.parametric.bayesian.SVB;
import research.ferjorosa.core.execution.ExecutionResult;
import research.ferjorosa.core.learning.normal.StaticLearningAlgorithm;
import research.ferjorosa.core.learning.normal.structural.ABI;
import research.ferjorosa.core.learning.normal.structural.ABIConfig;
import research.ferjorosa.core.learning.normal.structural.variables.FSSMeasureFactory;
import research.ferjorosa.core.learning.normal.structural.variables.FSSMeasures;
import research.ferjorosa.core.models.LTM;

import research.ferjorosa.server.export.fileFormat.json.JsonTransform;
import research.ferjorosa.server.web.app.localData.LocalDataService;
import research.ferjorosa.server.web.app.staticLearn.parameters.ABIParameters;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;
import java.util.List;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;

/**
 * Created by fernando on 5/09/16.
 */
public class StaticLearnController {


    public static Route learnFlatLtmABI = (Request request, Response response) -> {
        try {

            System.out.println("learnFlatLtmABI");

            String algorithmParameters = request.body();
            Gson gson = new Gson();
            ABIParameters params = gson.fromJson(algorithmParameters, ABIParameters.class);

            if (!params.isValid()) {
                response.status(HTTP_BAD_REQUEST);
                return "Invalid Json";
            }

            //Creamos la configuracion del ABI
            ABIConfig approximateBIConfig = new ABIConfig(params.getMaxIslandSize(), params.getBaseLvCardinality(), params.getUdTestThreshold());
            // Llamamos al metodo pasandole los parametros
            SVB parameterLearningAlgorithm = new SVB();
            StaticLearningAlgorithm staticLearningAlgorithm = new ABI(approximateBIConfig, parameterLearningAlgorithm, FSSMeasureFactory.retrieveInstance(params.getFssMeasure()));
            // Devolvemos una respuesta en formato JSON conteniendo la BN
            ExecutionResult result = null;
            DataStream<DataInstance> data = LocalDataService.openDataFile(params.getSelectedFile());
            for (DataOnMemory<DataInstance> batch : data.iterableOverBatches(params.getBatchSize())) {
                result = staticLearningAlgorithm.execute(batch);
            }

            System.out.println(result.toString());

            //Return the BN in Json format
            return JsonTransform.toMyJson(result, false);

        }catch(JsonParseException jpe){
            response.status(HTTP_BAD_REQUEST);
            return "Json Parse Exception";
        }catch(Exception e){
            response.status(HTTP_INTERNAL_ERROR);
            return "Server Error";
        }
    };

    public static Route listFssMeasures = (Request request, Response response) -> {
        Gson gson = new GsonBuilder().create();
        List<String> fssMeasures = new ArrayList<String>(FSSMeasures.values().length);
        for(FSSMeasures fssMeasure : FSSMeasures.values())
            fssMeasures.add(fssMeasure.toString());
        return gson.toJson(fssMeasures);
    };
}
