package research.ferjorosa.server.export.fileFormat.json.my.cpt;

import eu.amidst.core.distribution.Multinomial;
import eu.amidst.core.variables.Variable;
import research.ferjorosa.server.export.fileFormat.json.my.cpt.AbstractJsonCPT;
import research.ferjorosa.server.export.fileFormat.json.my.cpt.MyJsonCptRow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fernando on 21/09/16.
 */
class MyJsonCptMultinomial extends AbstractJsonCPT {

    MyJsonCptMultinomial(Variable var, Multinomial dist){

        // VariableID
        this.variableID = var.getVarID() + "";

        // Distribution label
        this.label = dist.label();

        // Rows
        int i = 1;
        List<MyJsonCptParameter> params= new ArrayList<>();
        for(double prob: dist.getParameters()){
            params.add(new MyJsonCptParameter("s"+i, prob));
            i++;
        }
        //                                No parents
        this.rows.add(new MyJsonCptRow(params, null));
    }



}
