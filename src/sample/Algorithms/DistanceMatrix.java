package sample.Algorithms;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class DistanceMatrix {
    public static double[][] getDistanceMatrix(String address) {//Pobierz dane miast
        //Najpierw wyznaczana jest ilosc miast
        //Nastepnie dla kazdej linijki dodaj miasto
        List<City> listaMiast = readTextToList(address);

        System.out.println("Ilosc miast:" + listaMiast.size());

        //obliczenie macierzy odleglosci wszystkich miast
        return countDistance(listaMiast);
    }

    private static List<City> readTextToList(String link) {
        List<City> listaMiast = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(link));
            listaMiast = readCities(br);
            System.out.println(listaMiast);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaMiast;
    }

    private static double[][] countDistance(List<City> listaMiast) {
        double[][] tmp = new double[listaMiast.size()][listaMiast.size()];
        for (int i = 0; i < listaMiast.size(); i++) {
            for (int j = i; j < listaMiast.size(); j++) {
                if (i == j) {
                    tmp[i][j] = 0;
                } else {
                    double res = Math.sqrt(Math.pow(listaMiast.get(i).getX() - listaMiast.get(j).getX(), 2) + Math.pow(listaMiast.get(i).getY() - listaMiast.get(j).getY(), 2));
                    tmp[i][j] = res;
                    tmp[j][i] = res;
                }


            }

        }
        return tmp;
    }

    public static List<City> readCities(BufferedReader br) {
        List<City> retList = new ArrayList<>();
        try {
            String ret = br.readLine();
            Scanner sc = new Scanner(ret);

            int inputCount = sc.nextInt();
            System.out.println(inputCount);
            while ((ret = br.readLine()) != null) {
                sc = new Scanner(ret);
                int x = sc.nextInt();
                int y = sc.nextInt();
                retList.add(new City(x, y));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return retList;
    }
}

