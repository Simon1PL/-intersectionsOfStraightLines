package all.myCriteria;

import all.criteria.ICriterion;

public class EvennessCriterion implements ICriterion {

    public boolean hasPassed(int[] coefficients, int amountOfStraights) {
        if (amountOfStraights % 2 == 1) return true;
        int sum = 0;
        for (int k = 1; k < amountOfStraights / 2 + 1; k++) {
            sum += 2 * k * coefficients[2 * k - 2];
        }
        return sum >= amountOfStraights;
    }
}