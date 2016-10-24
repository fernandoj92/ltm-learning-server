package research.ferjorosa.server.web.app.staticLearn.parameters;


import research.ferjorosa.core.learning.normal.structural.variables.FSSMeasures;

/**
 * Created by fernando on 5/09/16.
 */
public class ABIParameters {

    private String fssMeasure;

    private Integer maxIslandSize;

    private Integer baseLvCardinality;

    private Double udTestThreshold;

    private Integer batchSize;

    private String selectedFile;

    public String getFssMeasure() {
        return fssMeasure;
    }

    public Integer getMaxIslandSize() {
        return maxIslandSize;
    }

    public Integer getBaseLvCardinality() {
        return baseLvCardinality;
    }

    public Double getUdTestThreshold() {
        return udTestThreshold;
    }

    public Integer getBatchSize(){
        return batchSize;
    }

    public String getSelectedFile(){
        return selectedFile;
    }

    public boolean isValid(){
        return validate(fssMeasure) &&
                maxIslandSize != null &&
                baseLvCardinality != null &&
                udTestThreshold != null &&
                batchSize != null &&
                batchSize != 0 &&
                selectedFile != null &&
                !selectedFile.isEmpty();
    }

    private boolean validate(String fssMeasure){
        return fssMeasure != null &&
                !fssMeasure.isEmpty() &&
                (fssMeasure.equals(FSSMeasures.MUTUAL_INFORMATION.toString()));
    }

}
