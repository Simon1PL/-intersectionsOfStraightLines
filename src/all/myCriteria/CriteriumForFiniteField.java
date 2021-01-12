package all.myCriteria;

import all.criteria.ICriterion;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class CriteriumForFiniteField implements ICriterion {
    private int[] perfectFieldPointsAmount = {7,13,21,31,57,73};
    private int[] perfectField = {2,3,4,5,7,8};

    private int findPerfectField(int amountOfStraights) {
        int result = 0;
        while(amountOfStraights > perfectFieldPointsAmount[result]) {
            result++;
        }
        return result;
    }

    public boolean hasPassed(int[] coefficients, int amountOfStraights) {
        int index = findPerfectField(amountOfStraights);
        int straightsAmountToDestroyPoint = perfectField[index];
        int nextPerfectFieldPointsAmount = perfectFieldPointsAmount[index];
        int pointsAmount = 0;
        for (int coefficient : coefficients) {
            pointsAmount += coefficient;
        }

        int pointsToDestroy = nextPerfectFieldPointsAmount - pointsAmount;
        if(pointsToDestroy<0) return false;
        //1. straightsAmountToDestroyPoint < pointsToDestroy:
        //straightsAmountToDestroyPoint + (straightsAmountToDestroyPoint-1) + ... + 1 = (straightsAmountToDestroyPoint+1)/2*straightsAmountToDestroyPoint
        //2. straightsAmountToDestroyPoint >= pointsToDestroy:
        //straightsAmountToDestroyPoint + (straightsAmountToDestroyPoint-1) + ... + (straightsAmountToDestroyPoint-pointsToDestroy+1) =
        //(straightsAmountToDestroyPoint+(straightsAmountToDestroyPoint-pointsToDestroy+1))/2*pointsToDestroy
        //1 & 2 =>
        int straightsToDestroy = (straightsAmountToDestroyPoint + max(1, straightsAmountToDestroyPoint-pointsToDestroy+1)) * min(pointsToDestroy, straightsAmountToDestroyPoint)/2;
        if(nextPerfectFieldPointsAmount-straightsToDestroy<amountOfStraights) return false;
        //if(pointsToDestroy>straightsAmountToDestroyPoint)
        return true;
    }
}
