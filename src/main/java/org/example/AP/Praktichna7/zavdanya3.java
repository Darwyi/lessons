package org.example.AP.Praktichna7;

import java.util.Scanner;

public class zavdanya3 {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Завдання 3 практична 7");
        System.out.println("Розмір масиву буде 5 на 5");

        int[][] array = new int[5][5];

        for (int i = 0; i < 5; i++){
            for ( int j = 0; j < 5; j++){
                System.out.println("Введіть значення елементу ("+ i + "," + j + ") масиву");
                array[i][j] = s.nextInt();
            }
        }
        System.out.println("Масив з вашими данними");

        for(int[] row : array){
            for (int num : row){
                System.out.print(num + ", ");
            }
            System.out.println();
        }

        int viznachnik = 1;
        boolean swapRow = false;

        for (int i = 0; i < 5; i++) {
            if (array[i][i] == 0) {
                for (int k = i + 1; k < 5; k++) {
                    if (array[k][i] != 0) {
                        for (int j = 0; j < 5; j++) {
                            int temp = array[i][j];
                            array[i][j] = array[k][j];
                            array[k][j] = temp;
                        }
                        viznachnik *= -1;
                        swapRow = true;
                        break;
                    }
                }
                if (!swapRow) {
                    viznachnik = 0;
                    break;
                }
            }

            for (int j = i + 1; j < 5; j++) {
                int factor = array[j][i] / array[i][i];
                for (int k = i; k < 5; k++) {
                    array[j][k] -= factor * array[i][k];
                }
            }
        }

        // Добуток діагональних елементів
        for (int i = 0; i < 5; i++) {
            viznachnik *= array[i][i];
        }

        System.out.println("\nВизначник матриці: " + viznachnik);
    }
}
