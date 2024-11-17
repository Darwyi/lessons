package org.example.Praktichna6;

import java.util.Random;
import java.util.Scanner;

public class zavdanya4 {
    public static void main(String[] args) {

        double[] sin_array = new double[91];

        for (int degrees = 0; degrees <= 90; degrees++) {

            double radians = degrees * Math.PI / 180;

            sin_array[degrees] += Math.sin(radians);
        }

        for (int i = 0; i < sin_array.length; i++) {

            System.out.print(String.format("%.4f", sin_array[i]) + ", ");

            if ((i + 1) % 10 == 0) {
                System.out.println();
            }
        }

    }
}
