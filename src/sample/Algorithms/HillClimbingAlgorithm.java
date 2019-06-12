package sample.Algorithms;

import javafx.scene.chart.XYChart;

import java.util.Random;

public class HillClimbingAlgorithm extends AbstractTSPAlgorithm {

//    public static AlgorithmSolution solveHillClimbing(List<City> lista, XYChart.Series chart){
//        //Wybierz losową ścieżkę rozwiązania
//        //sprawdz czy kazde sąsiednie rozwiązanie jest lepsze
//        //Jesli nie to zwroc wynik
//        //Jesli tak to wyznacz sciezke jako lepsza i ponow rozwiazanie
//
//        //Obranie losowej sciezki i obliczenie jej odleglosci
//        long startTime = System.nanoTime();
//        double distance;
//        //Sciezka losowa
//        List<City> route = new ArrayList<>(lista);
//
//        Collections.shuffle(route);
//        distance = countRoute(route);
//        System.out.println("dystans: " + distance);
//
//        for(int i=0; i<route.size(); i++){ //dla kazdego elementu w liscie
//            for (int j=i+1; j<route.size(); j++){ //zamien z kolejnymi elementami i porownaj odleglosc dwoch list
//                List<City> tmpList = new ArrayList<>(route);
//                if(j < route.size() -2 ) {
//                    Collections.swap(tmpList, j, j+1);
//                }
//
//                double tmpDist = countRoute(tmpList);
////                System.out.println("TMP DIST: " + tmpDist);
////                System.out.println("Route Dist: " + distance);
//                if(tmpDist < distance){
//                    System.out.println(tmpDist);
////                    System.out.println(i + " #" + " j : " + j);
//                    route = tmpList;
//                    distance = tmpDist;
////                    System.out.println(distance);
//                    i = 0;
//                }
//
//            }
//        }
//        long endTime = System.nanoTime();
//
//        return new AlgorithmSolution(distance, (endTime-startTime), route);
//    }

    public static AlgorithmSolution solveHillClimbing(double[][] distanceMatrix, XYChart.Series chart) {
        //Wybierz losową ścieżkę rozwiązania
        //sprawdz czy sąsiednie rozwiązania sa lepsze
        //Jesli nie to zwroc wynik
        //Jesli tak to wyznacz sciezke jako lepsza i ponow działanie


        long startTime = System.currentTimeMillis();

        int[] result = temporaryArray(distanceMatrix.length);


        //Obranie losowej sciezki i obliczenie jej odleglosci
        //tablica result jest tablica wynikowa, ktora przechowuje indexy miast
        int size = 0;
        while (size < distanceMatrix.length) {
            int j = randomIndex(distanceMatrix.length);

            if (!isIndexInResult(result, j)) {
                result[size] = j;
                size++;
            }
        }
        //Liczenie odleglosci podanej sciezki
        double distance = countRoute(distanceMatrix, result);

        int age = 1;

        for (int i = 0; i < result.length; i++) { //dla kazdego elementu w liscie
            for (int j = i + 1; j < result.length; j++) { //zamien z kolejnymi elementami i porownaj odleglosc dwoch list
                int[] tmpRes = result.clone();
                if (j < result.length - 2) {
                    int tmp = result[i];
                    result[i] = result[i + 1];
                    result[i + 1] = tmp;
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
