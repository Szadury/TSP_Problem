package sample.Algorithms;

import java.util.List;

public abstract class AbstractTSPAlgorithm {

    protected static double countRoute(double[][] distanceMatrix, int[] result) {
        double res = 0;
        for (int i = 1; i < result.length; i++) {
            res += distanceMatrix[result[i-1]][result[i]];
        }
        return res;
    }

    protected static void displayResult(int[] tmp) {
        for (int i = 0; i < tmp.length; i++) {
            System.out.println(i + ". " + tmp[i]);
            System.out.println();
        }
    }

    protected static int[] temporaryArray(int length) {
        int[] tmp = new int[length];
        for (int i = 0; i < tmp.length; i++)
            tmp[i] = -1;

        return tmp;
    }

    protected static boolean isIndexInResult(int[] result, int index) {
        for (int tmp : result) {
            if (tmp == index)
                return true;
        }
        return false;
    }

}
