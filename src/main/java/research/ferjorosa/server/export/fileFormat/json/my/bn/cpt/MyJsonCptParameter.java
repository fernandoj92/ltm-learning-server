package research.ferjorosa.server.export.fileFormat.json.my.bn.cpt;

/**
 * Created by fernando on 22/09/16.
 */
class MyJsonCptParameter {

    private String parameter;

    private Double value;

    MyJsonCptParameter(){}

    MyJsonCptParameter(String parameter, Double value){
        this.parameter = parameter;
        this.value = value;
    }
}