package all;

import all.criteria.*;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        ICriterion[] criteriaPassed = {/*new GeneralizedMelchiorInequality(), new MelchiorInequality(), new PokoraInequality(),*//* new RGonalInequality(), new TwoPencilsCriterion(), new BasicMatricalTracesInequality(), new EvennessCriterion(), new RectangleInequality(), new WhatIsIt()*/ new TwoPencilsCriterion()};

        ICriterion[] criteriaNotPassed = {/*new WhatIsIt()*/};

        for (int j = 15; j <= 15; j++) {

            int amountOfStraights = j;
            boolean print = true;
            String fileForResultName = "E:\\wyniki\\results-test.txt";
            boolean findMinimum = false;
            String fileForMinimumsName = "E:\\wyniki\\minimums-test.txt";


            long start = System.currentTimeMillis();
            OptionsFinder optionsFinder = new OptionsFinder(amountOfStraights, criteriaPassed, criteriaNotPassed, print, findMinimum, fileForResultName);
            long stop = System.currentTimeMillis();
            System.out.println("Czas wykonania: " + (stop - start) / 1000.0 + "s");
            if (findMinimum) optionsFinder.printMinimums(fileForMinimumsName, 5);
        }
    }
}