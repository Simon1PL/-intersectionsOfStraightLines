package all;

import all.criteria.*;
import all.myCriteria.*;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        //Kryteria chyba slabsze od pozostalych czyli nic nie lapiace: BasicMatricalTracesInequality
        //Kryteria dla ciał zespolonych: PokoraInequality
        //Kryteria dla ciał rzeczywistych: GeneralizedMelchiorInequality, MelchiorInequality
        //Kryteria dla ciał skończonych(z najniższą stałą harborna?): CriteriumForFiniteField
        //nwm czy cokolwiek łapią: RectangleInequality, WhatIsIt
        ICriterion[] criteriaPassed = {/*new GeneralizedMelchiorInequality(), new CriteriumForFiniteField(),*/ new MelchiorInequality(), new PokoraInequality(), new TwoPencilsCriterion(), new RGonalInequality(), new BasicMatricalTracesInequality(), new EvennessCriterion(), new RectangleInequality(), new WhatIsIt()};

        ICriterion[] criteriaNotPassed = {/*new WhatIsIt()*/};

        //How to chceck one sample:
        //int[] coefficients={1, 0, 0, 1, 3, 39, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        //System.out.println(new CriteriumForFiniteField().hasPassed(coefficients, 43));
        for (int j =30; j <= 30; j++) {

            int amountOfStraights = j;
            boolean print = false;
            String fileForResultName = "E:\\wyniki\\results-test.txt";
            boolean findMinimum = true;
            String fileForMinimumsName = "E:\\wyniki\\minimums-test.txt";


            long start = System.currentTimeMillis();
            OptionsFinder optionsFinder = new OptionsFinder(amountOfStraights, criteriaPassed, criteriaNotPassed, print, findMinimum, fileForResultName);
            long stop = System.currentTimeMillis();
            System.out.println("Czas wykonania: " + (stop - start) / 1000.0 + "s");
            if (findMinimum) optionsFinder.printMinimums(fileForMinimumsName);
        }
    }
}