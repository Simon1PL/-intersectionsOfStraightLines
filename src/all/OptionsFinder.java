package all;

import all.criteria.ICriterion;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import static java.lang.Math.max;
import static java.lang.Math.pow;

public class OptionsFinder {
    private int amountOfStraights;
    private int[] coefficientsTable;
    private int[] coefficients;
    private int amountOfResults = 0;
    private CriteriaChecker criteriaChecker;
    private MinimumFinder minimumFinder;
    private boolean print;
    private boolean findMinimum;
    private BufferedWriter resultsToFileWriter;

    OptionsFinder(int amountOfStraights, ICriterion[] criteriaPassed, ICriterion[] criteriaNotPassed, boolean print) throws IOException {
        this(amountOfStraights, criteriaPassed, criteriaNotPassed, print, false, "result.txt");
    }

    OptionsFinder(int amountOfStraights, ICriterion[] criteriaPassed, ICriterion[] criteriaNotPassed, boolean print, boolean findMinimum) throws IOException {
        this(amountOfStraights, criteriaPassed, criteriaNotPassed, print, findMinimum, "result.txt");
    }

    OptionsFinder(int amountOfStraights, ICriterion[] criteriaPassed, ICriterion[] criteriaNotPassed, boolean print, boolean findMinimum, String fileForResultsName) throws IOException {
        if (amountOfStraights < 2) {
            System.out.println("Error, there are no points intersection. You need at least 2 straights.");
            return;
        }
        this.amountOfStraights = amountOfStraights;
        this.print = print;
        if (print) resultsToFileWriter = new BufferedWriter(new FileWriter(fileForResultsName, true));
        this.findMinimum = findMinimum;
        this.coefficients = new int[amountOfStraights - 1];
        createCoefficientsTable();
        this.criteriaChecker = new CriteriaChecker(criteriaPassed, criteriaNotPassed, amountOfStraights);
        if (findMinimum) this.minimumFinder = new MinimumFinder(amountOfStraights);
        int estimatedTime = (amountOfStraights > 23) ? (int) (2.9 * pow(1.88, max(amountOfStraights - 24, 0))) : 0;
        System.out.print("d = " + amountOfStraights + "\t\tEstimated time: " + estimatedTime + "s\t");
        if (amountOfStraights > 29) System.out.print("maybe less :D\t");
        long amountOfOptions;
        amountOfOptions = findOptions(coefficientsTable[amountOfStraights - 2], amountOfStraights - 2);
        /*if (print && findMinimum)
            amountOfOptions = findOptions(coefficientsTable[amountOfStraights - 2], amountOfStraights - 2, new Runnable() {
                @Override
                public void run() {
                    try {
                        checkCriteriaWithAll();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        else if (print)
            amountOfOptions = findOptions(coefficientsTable[amountOfStraights - 2], amountOfStraights - 2, new Runnable() {
                @Override
                public void run() {
                    try {
                        checkCriteriaPrint();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        else if (findMinimum)
            amountOfOptions = findOptions(coefficientsTable[amountOfStraights - 2], amountOfStraights - 2, new Runnable() {
                @Override
                public void run() {
                    checkCriteriaMinimums();
                }
            });
        else
            amountOfOptions = findOptions(coefficientsTable[amountOfStraights - 2], amountOfStraights - 2, new Runnable() {
                @Override
                public void run() {
                    checkCriteriaEmpty();
                }
            });*/
        System.out.print("Ammount of options: " + amountOfOptions + "!\tAmmount of results: " + amountOfResults + "!\t");
        if (resultsToFileWriter != null) {
            resultsToFileWriter.write("d = " + amountOfStraights + "\t\tAmmount of options: " + amountOfOptions + "!\tAmmount of results: " + amountOfResults + "!\n\n");
            resultsToFileWriter.close();
        }
    }

    public static int choose(int n, int k) //symbol Newtona
    {
        int result = 1;
        for (int i = 1; i <= k; i++) {
            result = result * (n - i + 1) / i;
        }
        return result;
    }

    private void createCoefficientsTable() {
        coefficientsTable = new int[amountOfStraights - 1];
        int i = 2;
        while (i <= amountOfStraights) {
            coefficientsTable[i - 2] = choose(i, 2);
            i = i + 1;
        }
    }

     private void checkCriteria() throws IOException {
        if (criteriaChecker.checkCriteria(coefficients)) {
            amountOfResults++;
            if (findMinimum) minimumFinder.findMinimum(coefficients);
            if (print) {
                resultsToFileWriter.write(Arrays.toString(coefficients));
                resultsToFileWriter.newLine();
            }
        }
    }

    void printMinimums(String fileName) throws IOException {
        minimumFinder.printMinimums(fileName);
    }

    private long findOptions(int sum, int indexInCoefficientsTable) throws IOException {
        long result = 1;
        boolean isEnd = true;
        if (indexInCoefficientsTable > 0) {
            int j = 0;
            while (coefficientsTable[indexInCoefficientsTable] * j <= sum) {
                coefficients[indexInCoefficientsTable] = j;
                if (j == 0) {
                    result = 0;
                    isEnd = false;
                }
                result += findOptions(sum - coefficientsTable[indexInCoefficientsTable] * j, indexInCoefficientsTable - 1);
                j++;
            }
        }
        if (isEnd) {
            coefficients[0] = sum;
            checkCriteria();
        }
        return (result);
    }

    /*private void checkCriteriaWithAll() throws IOException {
        if (criteriaChecker.checkCriteria(coefficients)) {
            amountOfResults++;
            minimumFinder.findMinimum(coefficients);
            resultsToFileWriter.write(Arrays.toString(coefficients));
            resultsToFileWriter.newLine();
        }
    }

    private void checkCriteriaPrint() throws IOException {
        if (criteriaChecker.checkCriteria(coefficients)) {
            amountOfResults++;
            resultsToFileWriter.write(Arrays.toString(coefficients));
            resultsToFileWriter.newLine();
        }
    }

    private void checkCriteriaEmpty() {
        if (criteriaChecker.checkCriteria(coefficients)) {
            amountOfResults++;
        }
    }

    private void checkCriteriaMinimums() {
        if (criteriaChecker.checkCriteria(coefficients)) {
            amountOfResults++;
            minimumFinder.findMinimum(coefficients);
        }
    }

    private long findOptions(int sum, int indexInCoefficientsTable, Runnable checkCriteria) throws IOException {
        long result = 1;
        boolean isEnd = true;
        if (indexInCoefficientsTable > 0) {
            int j = 0;
            while (coefficientsTable[indexInCoefficientsTable] * j <= sum) {
                coefficients[indexInCoefficientsTable] = j;
                if (j == 0) {
                    result = 0;
                    isEnd = false;
                }
                result += findOptions(sum - coefficientsTable[indexInCoefficientsTable] * j, indexInCoefficientsTable - 1, checkCriteria);
                j++;
            }
        }
        if (isEnd) {
            coefficients[0] = sum;
            checkCriteria.run();
        }
        return (result);
    }*/
}