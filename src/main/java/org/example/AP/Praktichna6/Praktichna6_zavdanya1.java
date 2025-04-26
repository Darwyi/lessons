package org.example.AP.Praktichna6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Praktichna6_zavdanya1
{
    public static void main(String[] args){

    Scanner s = new Scanner(System.in);
    Random r = new Random();

    //Дано масив певного розміру.
    // Згенерувати усі елементи масиву випадковими
    // значеннями. Знайти кількість парних та непарних чисел.

    System.out.println("Введіть розмір масиву: ");
    int size = s.nextInt();

    int[] array = new int[size];

    for(int i = 0; i < array.length ;i++) {

        array[i] = r.nextInt();

    }

    System.out.println( "Масив має такі значення: " + Arrays.toString(array));

    ArrayList<Integer> even = new ArrayList<>();
    ArrayList<Integer> odd = new ArrayList<>();

    for (int num : array) {
        if (num % 2 == 0) {
            even.add(num);
        } else {
            odd.add(num);

        }
    }

    System.out.println("Парні числа: " + even);
    System.out.println("Непарні числа: " + odd);

}
}
