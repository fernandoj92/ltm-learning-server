package research.ferjorosa.server.export.fileFormat.json.my.cpt;

import eu.amidst.core.distribution.Multinomial;
import eu.amidst.core.distribution.Multinomial_MultinomialParents;
import eu.amidst.core.utils.MultinomialIndex;
import eu.amidst.core.variables.Assignment;
import eu.amidst.core.variables.Variable;
import research.ferjorosa.server.export.fileFormat.json.my.cpt.AbstractJsonCPT;
import research.ferjorosa.server.export.fileFormat.json.my.cpt.MyJsonCptRow;

import java.util.*;

/**
 * Created by fernando on 21/09/16.
 */
class MyJsonCptMultinomial_Multinomial extends AbstractJsonCPT {

    MyJsonCptMultinomial_Multinomial(Variable var, Multinomial_MultinomialParents dist){

        // VariableID
        this.variableID = var.getVarID() + "";

        // Distribution label
        this.label = dist.label();

        // ParentIDs
        for(Variable parent: dist.getConditioningVariables()){
            this.parentIDs.add(parent.getVarID() + "");
        }
        // Rows

        // Rows: Parent values
        List<Map<String, Double>> parentValuesList = new ArrayList<>();
        for(int i=0;i<dist.getNumberOfParentAssignments();i++) {
            Assignment parentAssignment = MultinomialIndex.
                    getVariableAssignmentFromIndex(dist.getConditioningVariables(), i);

            Set<Variable> parents = parentAssignment.getVariables();
            Map<String, Double> parentValues = new HashMap<>();
            for(Variable parent: parents){
                parentValues.put(parent.getVarID()+ "", parentAssignment.getValue(parent));
            }
            parentValuesList.add(parentValues);
        }

        // Rows: Parameter values
        List<Map<String, Double>> parameterValuesList = new ArrayList<>();
        for(Multinomial multinomialDist: dist.getMultinomialDistributions()){
            int i = 1;
            Map<String, Double> value = new HashMap<>();
            for(double prob: multinomialDist.getParameters()){
                value.put("s"+i,prob);
                i++;
            }
            parameterValuesList.add(value);
        }

        // Create the rows
        for(int i = 0; i < parameterValuesList.size(); i++)
            this.rows.add(new MyJsonCptRow(parameterValuesList.get(i), parentValuesList.get(i)));
    }
}
