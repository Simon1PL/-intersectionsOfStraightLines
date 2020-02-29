package all.myCriteria;

import all.criteria.ICriterion;

import static java.lang.Math.min;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class RectangleInequality implements ICriterion {

    public boolean hasPassed(int[] coefficients, int amountOfStraights) {
        int sum = 0;
        for (int k = 2; k < coefficients.length + 2; k++) {
            sum += k * coefficients[k - 2];
        }
        int pointsAmount = 0;
        for (int coefficient : coefficients) {
            pointsAmount += coefficient;
        }
        return sum <= min((amountOfStraights + sqrt(pow(amountOfStraights, 2) + 4 * amountOfStraights * pointsAmount * (pointsAmount - 1))) / 2, (pointsAmount + sqrt(pow(pointsAmount, 2) + 4 * pointsAmount * amountOfStraights * (amountOfStraights - 1))) / 2);
    }
}