package org.example.TA.HW_11_04;

import java.util.Arrays;

public class SortByFirstLetter {
    public static void main(String[] args) {
        String[] words = {"banana", "Apple", "cherry", "avocado"};

        Arrays.sort(words);

        System.out.println(Arrays.toString(words));
    }
}
