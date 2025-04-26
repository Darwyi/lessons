package org.example.AP.Praktichna7;

import java.util.Random;
import java.util.Scanner;

public class zavdanya4 {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Завдання 4 практична 7");

        System.out.print("Введіть розмір матриці (квадратної): ");
        int size = scanner.nextInt();

        int[][] matrix = new int[size][size];
        System.out.println("\nЗгенерована матриця:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = random.nextInt(9) + 1; // Випадкові числа від 1 до 9
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        System.out.print("\nВведіть номер рядка для мінору (1-" + size + "): ");
        int rowToRemove = scanner.nextInt() - 1;
        System.out.print("Введіть номер стовпця для мінору (1-" + size + "): ");
        int colToRemove = scanner.nextInt() - 1;

        int newSize = size - 1;
        int[][] minor = new int[newSize][newSize];
        for (int i = 0, minorRow = 0; i < size; i++) {
            if (i == rowToRemove) continue;
            for (int j = 0, minorCol = 0; j < size; j++) {
                if (j == colToRemove) continue;
                minor[minorRow][minorCol] = matrix[i][j];
                minorCol++;
            }
            minorRow++;
        }

        System.out.println("\nМатриця мінору:");
        for (int i = 0; i < newSize; i++) {
            for (int j = 0; j < newSize; j++) {
                System.out.print(minor[i][j] + " ");
            }
            System.out.println();
        }

        double determinant = 0;
        if (newSize == 1) {
            determinant = minor[0][0];
        } else if (newSize == 2) {
            determinant = minor[0][0] * minor[1][1] - minor[0][1] * minor[1][0]; // Для 2x2
        } else {
            for (int j = 0; j < newSize; j++) {
                int sign = (j % 2 == 0) ? 1 : -1;

                int[][] subMinor = new int[newSize - 1][newSize - 1];
                for (int row = 1, subRow = 0; row < newSize; row++, subRow++) {
                    for (int col = 0, subCol = 0; col < newSize; col++) {
                        if (col == j) continue;
                        subMinor[subRow][subCol] = minor[row][col];
                        subCol++;
                    }
                }
                determinant += sign * minor[0][j] * (subMinor.length == 1
                        ? subMinor[0][0]
                        : (subMinor[0][0] * subMinor[1][1] - subMinor[0][1] * subMinor[1][0]));
            }
        }

        System.out.println("\nВизначник мінору: " + determinant);
    }
}
