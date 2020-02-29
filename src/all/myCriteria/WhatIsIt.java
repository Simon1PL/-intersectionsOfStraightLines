package all.myCriteria;

import all.criteria.ICriterion;

public class WhatIsIt implements ICriterion {

    private int left(int n, int[] coefficients, int amountOfStraights) {
        int result = 0;
        for (int k = 2; k < coefficients.length - (amountOfStraights - 1) / n + 2; k++) {
            result += coefficients[k + (amountOfStraights - 1) / n - 2] * ((amountOfStraights - 1) / n + k);
        }
        return result;
    }

    public boolean hasPassed(int[] coefficients, int amountOfStraights) {
        for (int n = 1; n <= (amountOfStraights - 4) / 2; n++) {
            if (left(n, coefficients, amountOfStraights) > (n - 1) * amountOfStraights) return false;
        }
        return true;
    }
}