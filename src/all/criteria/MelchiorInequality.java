package all.criteria;

public class MelchiorInequality implements ICriterion{

    private int left(int[] coefficients) {
        int result = 3;
        for (int k = 4; k < coefficients.length + 2; k++) {
            result += (k - 3) * coefficients[k - 2];
        }
        return result;
    }

    public boolean hasPassed(int[] coefficients, int amountOfStraights) {
        return (coefficients[coefficients.length - 1] != 0 || left(coefficients) <= coefficients[0]);
    }
}
