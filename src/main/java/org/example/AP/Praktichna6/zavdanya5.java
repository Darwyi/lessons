package org.example.AP.Praktichna6;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class zavdanya5 {
    public static void main(String[] args) {
        Random r = new Random();
        Scanner s = new Scanner(System.in);

        System.out.println("Введіть кількість елементів масиву: ");
        int array_size = s.nextInt();

        int[] array = new int[array_size];

        for (int i = 0; i < array.length; i++) {
            array[i] = r.nextInt();
        }

        System.out.println("Масив має значення" + Arrays.toString(array));


        System.out.println("Перевірити масив: зростання чи спадання: ");
        String review_choice = s.next();

        if (review_choice.equals("Зростання") || review_choice.equals("зростання"))
            for (int num = 0; num < array.length - 1; num++) {
                if (array[num] > array[num + 1]) {
                    System.out.println("Масив не є зростаючим.");
                }else {
                    System.out.println("Масив зростаючий.");
                }
            }
        if (review_choice.equals("Спадіння") || review_choice.equals("спадіння"))
            for (int num = 0; num > array.length - 1; num++) {
                if (array[num] < array[num + 1]) {
                    System.out.println("Масив не є спадаючим.");
                } else {
                    System.out.println("Масив спадає.");
                }
            }
    }
}