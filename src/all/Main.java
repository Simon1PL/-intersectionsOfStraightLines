package all;

import all.criteria.*;
import all.myCriteria.*;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        //Kryteria chyba slabsze od pozostalych czyli nic nie lapiace: BasicMatricalTracesInequality
        //Kryteria dla ciał zespolonych: PokoraInequality
        //Kryteria dla ciał rzeczywistych: GeneralizedMelchiorInequality, MelchiorInequality
        ICriterion[] criteriaPassed = {/*new GeneralizedMelchiorInequality(), new MelchiorInequality(), new PokoraInequality(),*/ new RGonalInequality(), new TwoPencilsCriterion(), new BasicMatricalTracesInequality(), new EvennessCriterion(), new RectangleInequality(), new WhatIsIt(), new CriteriumForFiniteField()};

        ICriterion[] criteriaNotPassed = {/*new WhatIsIt()*/};

        for (int j = 43; j <= 43; j++) {

            int amountOfStraights = j;
            boolean print = false;
            String fileForResultName = "E:\\wyniki\\results-test.txt";
            boolean findMinimum = true;
            String fileForMinimumsName = "E:\\wyniki\\minimums-test.txt";


            long start = System.currentTimeMillis();
            OptionsFinder optionsFinder = new OptionsFinder(amountOfStraights, criteriaPassed, criteriaNotPassed, print, findMinimum, fileForResultName);
            long stop = System.currentTimeMillis();
            System.out.println("Czas wykonania: " + (stop - start) / 1000.0 + "s");
            if (findMinimum) optionsFinder.printMinimums(fileForMinimumsName, 5);
        }
    }
}