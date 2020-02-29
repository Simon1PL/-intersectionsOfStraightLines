package all.myCriteria;

import all.criteria.ICriterion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BasicMatricalTracesInequality implements ICriterion {
    private List<Integer> amountOfStraightsWhenCriterionWorks = new ArrayList<>(Arrays.asList(3, 6, 10, 15, 21, 28, 36, 45, 55, 66, 78, 91, 105, 120, 136, 153, 171, 190));

    private int left(int n, int[] coefficients) {
        int result = 0;
        for (int k = 0; k < coefficients.length - n + 2; k++) {
            result += coefficients[n + k - 2];
        }
        return result;
    }

    public boolean hasPassed(int[] coefficients, int amountOfStraights) {
        int n = amountOfStraightsWhenCriterionWorks.indexOf(amountOfStraights) + 2;
        if (n != 1) {
            return left(n, coefficients) <= n + 1;
        }
        return true;
    }

}
