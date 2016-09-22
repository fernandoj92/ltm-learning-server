package research.ferjorosa.server.export.fileFormat.json.my.cpt;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fernando on 22/09/16.
 */
class MyJsonCptRow {

    private Map<String, Double> parameterValues = new HashMap<>();

    private Map<String, Double> parentValues = new HashMap<>();

    MyJsonCptRow(Map<String, Double> parameterValues, Map<String, Double> parentValues){
        this.parameterValues = parameterValues;
        this.parentValues = parentValues;
    }
}
