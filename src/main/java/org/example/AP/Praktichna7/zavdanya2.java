package org.example.AP.Praktichna7;

import java.util.Random;
import java.util.Scanner;

public class zavdanya2 {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        Random r = new Random();

        System.out.println("Завдання 2 практична 7");
        System.out.println("Введіть розмір двовимірного масиву: ");
        int row_size = s.nextInt();
        int column_size = s.nextInt();

        double[][] array = new double[row_size][column_size];

        for (int i = 0; i < row_size; i++){
            for (int j =0; j < column_size; j++) {
                array[i][j] = r.nextDouble();
            }
        }

        for (double[] row : array) {
            for (double num : row) {
                System.out.printf("%.4f\t", num);
            }
            System.out.println();
        }

        for (int i = 0; i < row_size; i++) {
            for (int j = 0; j < column_size; j++) {
                if (i % 2 != 0 || j % 2 != 0) {
                    array[i][j] = Math.sqrt(array[i][j]);
                }
            }
        }
        System.out.println("Дані масиву зі змінами: \n");

        for (double[] row : array) {
            for (double num : row) {
                System.out.printf("%.4f\t", num);
            }
            System.out.println();
        }
    }
}
