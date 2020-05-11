package all.criteria;

import all.OptionsFinder;

import java.util.ArrayList;

import static java.lang.Math.min;

public class RGonalInequality implements ICriterion {

    private int left(int[] coefficients, int r) {
        int result = 0;
        int index = coefficients.length - 1;
        int toFind = r;
        while (toFind != 0 && index >= 0) {
            result += min(coefficients[index], toFind) * (index + 2);
            toFind -= min(coefficients[index], toFind);
            index = index - 1;
        }
        return result;
    }

    public boolean hasPassed(int[] coefficients, int amountOfStraights) {
        int a=0;
        for (int i=coefficients.length-1; i>=0; i--) {
            if (coefficients[i]!=0) a+=coefficients[i];
        }
        for (int r = 3; r < a; r++) {
            if (left(coefficients, r) > amountOfStraights + OptionsFinder.choose(r, 2)) return false;
        }
        return true;
    }

   /* private int left(int r, ArrayList<Integer> multP) {
        int result = 0;
        for (int i = 0; i < r; i++) result += multP.get(i);
        return result;
    }

    public boolean hasPassed(int[] coefficients, int amountOfStraights) {
        ArrayList<Integer> multP = new ArrayList<>();
        for (int i = coefficients.length - 1; i >= 0; i--) {
            int howMany = coefficients[i];
            while (howMany > 0) {
                multP.add(i + 2);
                howMany--;
            }
        }
        for (int r = 3; r < multP.size(); r++) {
            if (left(r, multP) > amountOfStraights + OptionsFinder.choose(r, 2)) return false;
        }
        return true;
    }*/

   /*private int left(int[] multP, int r) {
        int result = 0;
        for (int i=0; i<r; i++) {
            result+=multP[multP.length-i-1];
        }
        return result;
    }

    public boolean hasPassed(int[] coefficients, int amountOfStraights) {
        int pointsAmount=0, multPIndex=0;
        int[] multP=new int[OptionsFinder.choose(amountOfStraights,2)];
        for (int i=coefficients.length-1; i>=0; i--) {
            if (coefficients[i]!=0) pointsAmount+=coefficients[i];
            int howMany=coefficients[i];
            while(howMany>0){
                multP[multPIndex]=i+2;
                howMany--;
            }
        }

        for (int r = 3; r < pointsAmount; r++) {
            if (left(multP, r) > amountOfStraights + OptionsFinder.choose(r, 2)) return false;
        }
        return true;
    }*/
}
