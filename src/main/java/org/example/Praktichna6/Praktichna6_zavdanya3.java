package org.example.Praktichna6;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Praktichna6_zavdanya3 {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        Random r = new Random();

        System.out.println("Введіть кількість елементів масиву: ");
        int array_size = s.nextInt();

        int[] array = new int[array_size];

        for (int i = 0; i < array.length; i++) {
            array[i] = r.nextInt();
        }

        System.out.println("Масив містить: " + Arrays.toString(array));

        System.out.println("Виберіть елемент масиву: ");
        int array_index = s.nextInt();

        System.out.println("Вибраний елемент масиву містить значення: " + array[array_index]);

        System.out.println("Введіть значення на яке хочете його замінити(int): ");
        int replace_value_to_index = s.nextInt();

        array[array_index] = replace_value_to_index;

        System.out.println("Масив з зміненими данними: " + Arrays.toString(array));

    }
}
