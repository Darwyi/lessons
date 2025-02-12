package org.example.lesson_05022025;

import java.util.Scanner;

public class Password {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        boolean entering_password = true;

        while (entering_password) {
            System.out.println("Введіть ваш пароль: ");
            String password = s.nextLine();
            if (chek_password(password)) {
                System.out.println("Це не міцний пароль.");
                entering_password = false;
            } else {
                System.out.println("Напишіть пароль міцніше.");
            }

        }
    }

    public static boolean chek_password(String password) {
        boolean has_chars, has_nums, has_spec_symbols;

        if (password.length() >= 8) {
            for (char letter : password.toCharArray()) {
                if (Character.isLetter(letter)) {
                    has_chars = true;
                } else if (Character.isDigit(letter)) {
                    has_nums = true;
                } else if (password.charAt(letter) == 'i') {
                    has_spec_symbols = true;
                } else {
                    has_chars = false; has_nums = false; has_spec_symbols = false;
                }
            }
        } else {
            System.out.println("Пароль має бути якнайменше з 8 символів");
            return false;
        }

        return true;
    }
}
