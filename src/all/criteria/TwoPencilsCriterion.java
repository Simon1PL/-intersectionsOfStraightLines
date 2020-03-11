package all.criteria;

import java.util.Arrays;

import static java.lang.Math.ceil;
import static java.lang.Math.pow;

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

        //1. OLD A
        /*+ ceil((amountOfStraights + 1 - multP1 - multP2) / (double) (multP2 - 1))*/
        //2. OLD A
        /*int pieceOfA = amountOfStraights - multP1 * (multP2 - 1);
        double a = ceil((pow(pieceOfA, 2) - pieceOfA) / (double) (multP2 * (multP2 - 1)));
        if ((multP1 - 1) * (multP2 - 1) + a + 2 > pointsAmount && (multP1 - 1) * (multP2 - 1) + 2 <= pointsAmount) {
            System.out.println(Arrays.toString(coefficients) + "\ta= " + a);
        }*/

        //SPRAWDZANIE CZY A DUZO ZMIENIA
        /*if((multP1 - 1) * (multP2 - 1) + 2 - pointsAmount <1 && (multP1 - 1) * (multP2 - 1) + 2 - pointsAmount > -2)
            System.out.println(Arrays.toString(coefficients) + "\nlewa= " + ((multP1 - 1) * (multP2 - 1) + 2) + "\tprawa= " + pointsAmount);*/

        return ((multP1 - 1) * (multP2 - 1) + 2 <= pointsAmount);
    }
}