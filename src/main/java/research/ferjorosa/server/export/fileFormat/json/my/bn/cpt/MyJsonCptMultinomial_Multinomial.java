package research.ferjorosa.server.export.fileFormat.json.my.bn.cpt;

import eu.amidst.core.distribution.Multinomial;
import eu.amidst.core.distribution.Multinomial_MultinomialParents;
import eu.amidst.core.utils.MultinomialIndex;
import eu.amidst.core.variables.Assignment;
import eu.amidst.core.variables.Variable;

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

        // Rows: Parent assignments
        List<List<MyJsonCptParentAssignment>> RowParentAssignments = new ArrayList<>();
        for(int i=0;i<dist.getNumberOfParentAssignments();i++) {
            Assignment parentAssignment = MultinomialIndex.
                    getVariableAssignmentFromIndex(dist.getConditioningVariables(), i);

            Set<Variable> parents = parentAssignment.getVariables();
            List<MyJsonCptParentAssignment> parentAssignments = new ArrayList<>();
            for(Variable parent: parents){
                //                                                                         s0, s1, s2...
                parentAssignments.add(new MyJsonCptParentAssignment(parent.getVarID()+ "", "s" + (int) parentAssignment.getValue(parent)));
            }
            RowParentAssignments.add(parentAssignments);
        }

        // Rows: Parameters
        List<List<MyJsonCptParameter>> RowParameters = new ArrayList<>();
        for(Multinomial multinomialDist: dist.getMultinomialDistributions()){
            int i = 0;
            List<MyJsonCptParameter> paramsList = new ArrayList<>();
            for(double prob: multinomialDist.getParameters()){
                paramsList.add(new MyJsonCptParameter("s"+i,prob));
                i++;
            }
            RowParameters.add(paramsList);
        }

        // Create the rows
        for(int i = 0; i < RowParameters.size(); i++)
            this.rows.add(new MyJsonCptRow(RowParameters.get(i), RowParentAssignments.get(i)));
    }
}
