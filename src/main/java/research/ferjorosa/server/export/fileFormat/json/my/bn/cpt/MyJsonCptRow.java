package research.ferjorosa.server.export.fileFormat.json.my.bn.cpt;

import java.util.List;

/**
 * Created by fernando on 22/09/16.
 */
class MyJsonCptRow {

    private List<MyJsonCptParameter> parameters;

    private List<MyJsonCptParentAssignment> parentAssignments;

    MyJsonCptRow(List<MyJsonCptParameter> parameters, List<MyJsonCptParentAssignment> parentAssignments){
        this.parameters = parameters;
        this.parentAssignments = parentAssignments;
    }



}
