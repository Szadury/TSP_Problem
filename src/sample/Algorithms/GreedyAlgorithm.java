package sample.Algorithms;

import javafx.scene.chart.XYChart;

import java.util.Random;

public class GreedyAlgorithm extends AbstractTSPAlgorithm {

    public static AlgorithmSolution solveGreedy(double[][] distanceMatrix, XYChart.Series chart) {

        long startTime = System.currentTimeMillis();

        int[] result = temporaryArray(distanceMatrix.length);
        int index = randomIndex(distanceMatrix.length);
        int resSize = 0;
        result[resSize] = index;
        resSize++;
        double distance = 0;
        while (resSize != distanceMatrix.length) {
            double minDistance = -1;
            int minIndex = -1;

            for (int i = 0; i < distanceMatrix[index].length; i++) {
                if (!isIndexInResult(result, i)) {
                    double current = distanceMatrix[index][i];
                    if (minDistance == -1 || current < minDistance) {

                        minDistance = current;
                        minIndex = i;

                    }

                }
            }
            distance += minDistance;
            chart.getData().add(new XYChart.Data(resSize, distance));
            index = minIndex;
            result[resSize] = index;
            resSize++;

        }
        distance = countRoute(distanceMatrix, result);
        long endTime = System.currentTimeMillis();
        return new AlgorithmSolution(distance, (endTime - startTime), result, chart);
    }

    private static int randomIndex(int len) {
        Random rand = new Random();
        return rand.nextInt(len);
    }

}