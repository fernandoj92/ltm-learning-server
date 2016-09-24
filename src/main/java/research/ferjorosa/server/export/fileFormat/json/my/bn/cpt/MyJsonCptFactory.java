package research.ferjorosa.server.export.fileFormat.json.my.bn.cpt;

import eu.amidst.core.distribution.ConditionalDistribution;
import eu.amidst.core.distribution.Multinomial;
import eu.amidst.core.distribution.Multinomial_MultinomialParents;
import eu.amidst.core.distribution.Normal_MultinomialParents;
import eu.amidst.core.variables.DistributionTypeEnum;
import eu.amidst.core.variables.Variable;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by fernando on 21/09/16.
 */
public class MyJsonCptFactory {


    public JsonCPT createMyJsonCPT(Variable var, ConditionalDistribution dist) {

        DistributionTypeEnum varType = var.getDistributionTypeEnum();
        List<DistributionTypeEnum> parentTypes = dist.getConditioningVariables().stream()
                .map(x->x.getDistributionTypeEnum())
                .collect(Collectors.toList());

        if(varType == DistributionTypeEnum.MULTINOMIAL){

            if(!parentTypes.contains(DistributionTypeEnum.MULTINOMIAL) && parentTypes.size() > 0)
                throw new IllegalArgumentException("Json Parsing Error: Parents of the variable have to be multinomial");

            // Multinomial distribution
            if(parentTypes.size() == 0)
                return new MyJsonCptMultinomial(var, (Multinomial) dist);

            // Multinomial_Multinomial distribution
            else if(parentTypes.size() > 0)
                return new MyJsonCptMultinomial_Multinomial(var, (Multinomial_MultinomialParents) dist);

        }
        else if(varType == DistributionTypeEnum.NORMAL){

            if(!parentTypes.contains(DistributionTypeEnum.MULTINOMIAL) && parentTypes.size() > 0)
                throw new IllegalArgumentException("Json Parsing Error: Parents of the variable have to be multinomial");
        /*
            // Normal distribution
            if(parentTypes.size() == 0)
                return 0;
        */
            // Normal_Multinomial distribution
            else if(parentTypes.size() > 0)
                return new MyJsonCptNormal_Multinomial(var, (Normal_MultinomialParents) dist);
        }
        throw new IllegalArgumentException("The main variable must contain a multinomial/normal distribution");
    }
}
