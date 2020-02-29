package all.criteria;

public class GeneralizedMelchiorInequality implements ICriterion {

    private int left(int n, int[] coefficients) {
        int result = 2 * n + 1;
        for (int k = 2 * n + 2; k < coefficients.length + 2; k++) {
            result += (k - (2 * n + 1)) * coefficients[k - 2];
        }
        return result;
    }

    private int right(int n, int[] coefficients) {
        int result = 0;
        for (int k = 2; k <= 2 * n; k++) {
            result += (2 * n + 1 - k) * coefficients[k - 2];
        }
        return result;
    }

    public boolean hasPassed(int[] coefficients, int amountOfStraights) {
        if (coefficients[coefficients.length - 1] != 0) return true;
        for (int n = 1; n <= (amountOfStraights - 4) / 2; n++) {
            if (left(n, coefficients) > right(n, coefficients)) return false;
        }
        return true;
    }
}