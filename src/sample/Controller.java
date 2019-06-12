package sample;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import sample.Algorithms.AlgorithmSolution;
import sample.Algorithms.DistanceMatrix;
import sample.Algorithms.GreedyAlgorithm;
import sample.Algorithms.HillClimbingAlgorithm;

public class Controller {
    public LineChart LineChartHill;
    public LineChart<Number, Number> LineChartGreedy;
    public Label timeHill;
    public Label timeGreedy;
    public Label scoreHill;
    public Label scoreGreedy;
    public Label timeDifference;
    public Label scoreDifference;

    XYChart.Series seriesHill = new XYChart.Series();
    XYChart.Series seriesGreedy = new XYChart.Series();


    public void initialize() {
        AlgorithmSolution HillClimbing;
        AlgorithmSolution Greedy;
        double[][] distances;

        //Wyznaczenie macierzy odległości z punktów podanych w pliku
        distances = DistanceMatrix.getDistanceMatrix("miasta.txt");
//        displayMatrix(distances);

        //Obliczenie algorytmem wspinaczkowym
        HillClimbing = HillClimbingAlgorithm.solveHillClimbing(distances, seriesHill);

        LineChartHill.setTitle("HillClimbing");
        LineChartHill.getData().add(HillClimbing.getChart());

        //Obliczenie algorytmem zachłannym
        Greedy = GreedyAlgorithm.solveGreedy(distances, seriesGreedy);

        LineChartGreedy.setTitle("Greedy");
        LineChartGreedy.getData().add(Greedy.getChart());

        System.out.println("Algorytm zachlanny " + Greedy);
        System.out.println("Algorytm wspinaczkowy " + HillClimbing);

        timeHill.setText(timeHill.getText() + HillClimbing.getTime());
        timeGreedy.setText(timeGreedy.getText() + Greedy.getTime());

        scoreGreedy.setText(scoreGreedy.getText() + Greedy.getDistance());
        scoreHill.setText(scoreHill.getText() + HillClimbing.getDistance());

        timeDifference.setText(timeDifference.getText() + (HillClimbing.getTime() - Greedy.getTime()));
        scoreDifference.setText(scoreDifference.getText() + (HillClimbing.getDistance() - Greedy.getDistance()));

    }

    private void displayMatrix(double[][] tmp) {
        for (double[] array : tmp) {
            for (double number : array)
                System.out.print(number + " ");
            System.out.println();
        }
    }
}