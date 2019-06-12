package sample.Algorithms;

import javafx.scene.chart.XYChart;

import java.util.Random;

public class HillClimbingAlgorithm extends AbstractTSPAlgorithm {


    public static AlgorithmSolution solveHillClimbing(double[][] distanceMatrix, XYChart.Series chart) {
        //Wybierz losową ścieżkę rozwiązania
        //sprawdz czy sąsiednie rozwiązania sa lepsze
        //Jesli nie to zwroc wynik
        //Jesli tak to wyznacz sciezke jako lepsza i ponow działanie


        long startTime = System.currentTimeMillis();

        int[] result = temporaryArray(distanceMatrix.length+1);


        //Obranie losowej sciezki i obliczenie jej odleglosci
        //tablica result jest tablica wynikowa, ktora przechowuje indexy miast
        int size = 0;
        int randIndex = randomIndex(result.length-1);
        int startIndex = randIndex;
        while (size < result.length) {

            if (!isIndexInResult(result, randIndex)) {
                result[size] = randIndex;
                size++;
            }
            else if(size==result.length-1){
                result[size] = startIndex;
                size++;
            }
            randIndex = randomIndex(result.length-1);
        }
        //Liczenie odleglosci podanej sciezki
        double distance = countRoute(distanceMatrix, result);

        int age = 1;

        for (int i = 0; i < result.length; i++) { //dla kazdego elementu w liscie
            for (int j = i + 1; j < result.length; j++) { //zamien z kolejnymi elementami i porownaj odleglosc dwoch list
                int[] tmpRes = result.clone();
                if (j < result.length - 2) {
                    int tmp = result[i];
                    result[i] = result[j];
                    result[j] = tmp;
                }

                //Sprawdzanie odleglosci sciezki sąsiedniej
                double tmpDist = countRoute(distanceMatrix, tmpRes);

                if (tmpDist < distance) {
                    result = tmpRes;
                    distance = tmpDist;
                    i = 0;
                }

            }
            chart.getData().add(new XYChart.Data(age, distance));
            age++;

        }
        long endTime = System.currentTimeMillis();
        return new AlgorithmSolution(distance, (endTime - startTime), result, chart);
    }

    private static int randomIndex(int len) {
        Random rand = new Random();
        return rand.nextInt(len);
    }
}
