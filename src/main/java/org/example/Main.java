package org.example;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
//test
public class Main {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        Random r = new Random();

        System.out.println("Введіть розмір масиву: ");
        int row_size = s.nextInt();

        int[][] array = new int[row_size][];

        for(int i = 0; i < row_size; ++i){
            array[i] = new int[i + 1];
            for (int j = 0; j < array[i].length; ++j){
                array[i][j] = r.nextInt();
            }
        }

        for(int[] row : array){
            System.out.println(Arrays.toString(row));
        }

        for(int row = array.length-1; row >= 0; --row){
            System.out.println(Arrays.toString(array[row]));
        }

    }
}