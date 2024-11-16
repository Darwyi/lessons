package org.example;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Praktichna6_zavdanya2 {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        Random r = new Random();

        System.out.println("Введіть кількість кутів: ");
        int corner_count = s.nextInt();

        if (corner_count < 3) {
            System.out.println("Багатокутник повинен мати не менше 3 кутів.");
            return;
        }

        int[] X = new int[corner_count];

        for (int i = 0; i < X.length; i++) {
            X[i] = r.nextInt(179) + 1;
        }

        System.out.println(Arrays.toString(X));

        int review = 180*(X.length-2);

        System.out.println("Сума кутів за теоремою: " + review + ".");

        int sum_corners = 0;

        for(int corners : X) {
            sum_corners += corners;
        }

        System.out.println("Сума кутів за додаванням їх з масиву: " + sum_corners + ".");

        if (sum_corners <= review){
            System.out.println("Многокутник може існувати!");
        }
        else {
            System.out.println("Многокутник не може існувати.");
        }

        if (X[corner_count - 1] <= 0 || X[corner_count - 1] >= 180) {
            System.out.println("Неможливо згенерувати багатокутник з такими кутами.");
        }

    }
}
