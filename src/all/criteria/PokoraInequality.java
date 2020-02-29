package all.criteria;

import static java.lang.Math.ceil;
import static java.lang.Math.pow;

public class PokoraInequality implements ICriterion{

    private int left(int[] coefficients, int amountOfStraights) {
        int result = amountOfStraights;
        for (int k = 5; k < coefficients.length + 2; k++) {
            result += (pow(k,2)/4-4) * coefficients[k - 2];
        }
        return result;
    }

    public boolean hasPassed(int[] coefficients, int amountOfStraights) {
        for (int i=(int)ceil(2/3.0*amountOfStraights)-2; i<coefficients.length; i++){
            if(coefficients[i]!=0) return true;
        }
        return (left(coefficients, amountOfStraights) <= coefficients[0]+3/4.0*coefficients[1]);
    }
}