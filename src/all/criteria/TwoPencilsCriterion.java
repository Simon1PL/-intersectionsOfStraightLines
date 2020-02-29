package all.criteria;

import static java.lang.Math.ceil;

public class TwoPencilsCriterion implements ICriterion {

    public boolean hasPassed(int[] coefficients, int amountOfStraights) {
        if (coefficients[coefficients.length - 1] == 1) return true;
        int pointsAmount = 0, multP1 = 0, multP2 = 0;
        for (int i = coefficients.length - 1; i >= 0; i--) {
            if (coefficients[i] != 0) {
                pointsAmount += coefficients[i];
                if (multP1 == 0) {
                    multP1 = i + 2;
                    if (coefficients[i] > 1) multP2 = i + 2;
                } else if (multP2 == 0) multP2 = i + 2;
            }
        }
        return ((multP1 - 1) * (multP2 - 1) + ceil((amountOfStraights + 1 - multP1 - multP2) / (double) (multP2 - 1)) + 2 <= pointsAmount);
    }
}