package org.example.TA.HW_04_04;

public class ReplaceMaxByMin {
    public static void main(String[] args) {
        int[] array = {1, 5, 3, 56, 56, 34, 56};

        int min = array[0];
        int max = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) min = array[i];
            if (array[i] > max) max = array[i];
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i] == max) {
                array[i] = min;
            }
        }

        System.out.print("Після заміни: ");
        for (int num : array) {
            System.out.print(num + " ");
        }
    }
}
