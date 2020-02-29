package all;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class MinimumFinder {
    private double minimum = 0;
    private int[] coefficientsOfMinimum;
    private SortedMap<Double, Set<int[]>> minimums = new TreeMap<>();
    private int amountOfStraights;

    MinimumFinder(int amountOfStraights) {
        this.amountOfStraights = amountOfStraights;
    }

    void findMinimum(int[] coefficients) {
        int pointsSum = 0;
        double result = Math.pow(amountOfStraights, 2);
        for (int i = 0; i < coefficients.length; i++) {
            pointsSum += coefficients[i];
            result -= Math.pow(i + 2, 2) * coefficients[i];
        }
        result /= pointsSum;
        //
        if (minimums.size() > 5) minimums.remove(minimums.lastKey());
        if (minimums.get(result) != null) minimums.get(result).add(coefficients.clone());
        else {
            Set<int[]> newSet = new HashSet<>();
            newSet.add(coefficients.clone());
            minimums.put(result, newSet);
        }
        if (result < minimum) {
            minimum = result;
            coefficientsOfMinimum = coefficients.clone();
        }
    }

    void printMinimums(String fileName, int howManyResults) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
        //System.out.println("Minimum is: " + minimum + "! " + Arrays.toString(coefficientsOfMinimum));
        Iterator<Map.Entry<Double, Set<int[]>>> iterator = minimums.entrySet().iterator();//
        if (iterator.hasNext()) {
            writer.write("Minimums for d = " + amountOfStraights);
            writer.newLine();
        }
        for (int i = 0; i < howManyResults; i++) {
            if (iterator.hasNext()) {
                Map.Entry<Double, Set<int[]>> entry = iterator.next();
                writer.write(entry.getKey() + ":");
                writer.newLine();
                for (Object coefficients : entry.getValue().toArray()) {
                    writer.write("\t" + Arrays.toString((int[]) coefficients));
                    writer.newLine();
                }
            }
        }
        writer.newLine();
        writer.close();
    }
}