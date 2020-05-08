package all.myCriteria;

import all.criteria.ICriterion;

public class CriteriumForFiniteField implements ICriterion {
    private int[] perfectFieldPointsAmount = {7,13,21,31,57};
    private int[] perfectField = {2,3,4,5,7};

    private int findPerfectField(int amountOfStraights) {
        int result = 0;
        while(amountOfStraights < perfectFieldPointsAmount[result]) {
            result++;
        }
        return result;
    }

    public boolean hasPassed(int[] coefficients, int amountOfStraights) {
        int index = findPerfectField(amountOfStraights);
        int nextPerfectDField = perfectField[index];
        int nextPerfectFieldPointsAmount = perfectFieldPointsAmount[index];
        int pointsAmount = 0;
        for (int coefficient : coefficients) {
            pointsAmount += coefficient;
        }
        int straightsAmountToDestroyPoint = nextPerfectDField;
        int actualStraightsAmount = nextPerfectFieldPointsAmount;
        for (int i=0; i<nextPerfectFieldPointsAmount-pointsAmount; i++) {
            actualStraightsAmount-=straightsAmountToDestroyPoint;
            if(amountOfStraights>actualStraightsAmount) return false;
            straightsAmountToDestroyPoint--;
        }
        return true;
    }
}
