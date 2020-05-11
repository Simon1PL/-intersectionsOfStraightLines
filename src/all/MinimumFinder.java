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
    private int howManyResults = 3;

    MinimumFinder(int amountOfStraights) {
        this.amountOfStraights = amountOfStraights;
        Set<int[]> newSet = new HashSet<>();
        minimums.put(0.0, newSet);
    }

    void findMinimum(int[] coefficients) {
        int pointsSum = 0;
        double result = Math.pow(amountOfStraights, 2);
        for (int i = 0; i < coefficients.length; i++) {
            pointsSum += coefficients[i];
            result -= Math.pow(i + 2, 2) * coefficients[i];
        }
        result /= pointsSum;

        if(howManyResults!=1) {
            if (minimums.get(result) != null) minimums.get(result).add(coefficients.clone());
            else if (minimums.lastKey() > result || minimums.size() < howManyResults) {
                if(minimums.size()==howManyResults) minimums.remove(minimums.lastKey());
                Set<int[]> newSet = new HashSet<>();
                newSet.add(coefficients.clone());
                minimums.put(result, newSet);
            }
        }
        else {
            if (result < minimum) {
                minimum = result;
                coefficientsOfMinimum = coefficients.clone();
            }
        }
    }

    /*
    void findMinimum(int[] coefficients) {
        int pointsSum = 0;
        double result = Math.pow(amountOfStraights, 2);
        for (int i = 0; i < coefficients.length; i++) {
            pointsSum += coefficients[i];
            result -= Math.pow(i + 2, 2) * coefficients[i];
        }
        result /= pointsSum;

        if (minimums.size() > howManyResults) minimums.remove(minimums.lastKey());
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
    */

    void printMinimums(String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
        if(howManyResults==1) {
            writer.write("Minimums for d = " + amountOfStraights);
            writer.newLine();
            writer.write(minimum + ":");
            writer.newLine();
            writer.write("\t" + Arrays.toString(coefficientsOfMinimum));
            writer.newLine();
        }
        //System.out.println("Minimum is: " + minimum + "! " + Arrays.toString(coefficientsOfMinimum));
        else {
            Iterator<Map.Entry<Double, Set<int[]>>> iterator = minimums.entrySet().iterator();//
            if (iterator.hasNext()) {
                writer.write("Minimum for d = " + amountOfStraights);
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
        }
        writer.newLine();
        writer.close();
    }
}