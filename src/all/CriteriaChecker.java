package all;

import all.criteria.*;

class CriteriaChecker {
    private ICriterion[] criteriaPassed;
    private ICriterion[] criteriaNotPassed;
    private int amountOfStraights;

    CriteriaChecker(ICriterion[] criteriaPassed, ICriterion[] criteriaNotPassed, int amountOfStraights) {
        this.criteriaPassed = criteriaPassed;
        this.criteriaNotPassed = criteriaNotPassed;
        this.amountOfStraights = amountOfStraights;
    }

    boolean checkCriteria(int[] coefficients) {
        for (ICriterion criterion : criteriaNotPassed) {
            if (criterion.hasPassed(coefficients, amountOfStraights)) return false;
        }
        for (ICriterion criterion : criteriaPassed) {
            if (!criterion.hasPassed(coefficients, amountOfStraights)) return false;
        }
        return true;
    }
}
