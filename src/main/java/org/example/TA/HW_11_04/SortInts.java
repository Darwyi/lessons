package org.example.TA.HW_11_04;

import java.util.Arrays;

public class SortInts {
        public static void main(String[] args) {
            int[] array = {5, 2, 8, 3, 1, 4};

            int[] evens = Arrays.stream(array)
                    .filter(n -> n % 2 == 0)
                    .sorted()
                    .toArray();

            int evenIndex = 0;
            for (int i = 0; i < array.length; i++) {
                if (array[i] % 2 == 0) {
                    array[i] = evens[evenIndex++];
                }
            }

            System.out.println("Масив після сортування парних: " + Arrays.toString(array));
        }
}
