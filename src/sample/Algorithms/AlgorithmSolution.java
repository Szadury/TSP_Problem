package sample.Algorithms;

import javafx.scene.chart.XYChart;

public class AlgorithmSolution {
    private double distance;
    private double time;
    private int[] indexRes;
    private XYChart.Series chart;


    public AlgorithmSolution(double distance, long time, int[] result, XYChart.Series chart) {
        this.distance = distance;
        this.time = time;
        this.indexRes = result;
        this.chart = chart;
    }

    public XYChart.Series getChart() {
        return chart;
    }

    public String toString() {
        return "Distance: " + distance + ", time: " + time;
    }

    public int[] getIndexRes() {
        return indexRes;
    }

    public double getDistance() {
        return distance;
    }

    public double getTime() {
        return time;
    }
}
