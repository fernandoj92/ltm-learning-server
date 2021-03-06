package research.ferjorosa.server.export.fileFormat.json.my.bn.cpt;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fernando on 21/09/16.
 */
abstract class AbstractJsonCPT implements JsonCPT{

    protected String label;

    protected String variableID;

    protected List<String> parentIDs = new ArrayList<>();

    protected List<MyJsonCptRow> rows = new ArrayList<>();

}
