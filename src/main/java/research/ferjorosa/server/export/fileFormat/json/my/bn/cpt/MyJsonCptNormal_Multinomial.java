package research.ferjorosa.server.export.fileFormat.json.my.bn.cpt;

import eu.amidst.core.distribution.Normal;
import eu.amidst.core.distribution.Normal_MultinomialParents;
import eu.amidst.core.utils.MultinomialIndex;
import eu.amidst.core.variables.Assignment;
import eu.amidst.core.variables.Variable;

import java.util.*;

/**
 * Created by fernando on 21/09/16.
 */
class MyJsonCptNormal_Multinomial extends AbstractJsonCPT {


    MyJsonCptNormal_Multinomial(Variable var, Normal_MultinomialParents dist){

        // VariableID
        this.variableID = var.getVarID() + "";

        // Distribution label
        this.label = dist.label();

        // ParentIDs
        for(Variable parent: dist.getConditioningVariables()){
            this.parentIDs.add(parent.getVarID() + "");
        }
        // Rows

        // Rows: Parent assignments
        List<List<MyJsonCptParentAssignment>> parentValuesList = new ArrayList<>();
        for(int i=0;i<dist.getNumberOfParentAssignments();i++) {
            Assignment parentAssignment = MultinomialIndex.
                    getVariableAssignmentFromIndex(dist.getConditioningVariables(), i);

            Set<Variable> parents = parentAssignment.getVariables();
            List<MyJsonCptParentAssignment> parentValues = new ArrayList<>();
            for(Variable parent: parents){
                //                                                                         s0, s1, s2...
                parentValues.add(new MyJsonCptParentAssignment(parent.getVarID()+ "",  "s" + (int) parentAssignment.getValue(parent)));
            }
            parentValuesList.add(parentValues);
        }

        // Rows: Parameters
        List<List<MyJsonCptParameter>> parameterValuesList = new ArrayList<>();
        for(Normal normalDist: dist.getNormalDistributions()){
            int i = 0;

            // New row's parameter values
            List<MyJsonCptParameter> paramValues = new ArrayList<>();
            for(double value: normalDist.getParameters()){
                if(i == 0) {
                    paramValues.add(new MyJsonCptParameter("mu", value));
                    i++;
                }else
                    paramValues.add(new MyJsonCptParameter("var", value));
            }
            parameterValuesList.add(paramValues);
        }

        // Create the rows
        for(int i = 0; i < parameterValuesList.size(); i++)
            this.rows.add(new MyJsonCptRow(parameterValuesList.get(i), parentValuesList.get(i)));
    }
}
