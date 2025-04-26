package org.example.AP.Praktichna7;

import java.util.Random;
import java.util.Scanner;

public class zavdanya5 {
    public static void main ( String[] args ) {
        System.out.println("Завдання 5 практична 7");

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Введіть розмір матриці (квадратної): ");
        int size = scanner.nextInt();

        int[][] matrix = new int[size][size];
        System.out.println("\nЗгенерована матриця:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = random.nextInt(10);
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        int[][] transposedMatrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                transposedMatrix[i][j] = matrix[j][i];
            }
        }

        System.out.println("\nТранспонована матриця:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(transposedMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
