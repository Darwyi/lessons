package org.example.Praktichna7_12_02_2025;

import java.util.Scanner;

public class Funcs_and_strings {

    public static boolean contains_3_letters(String text) {
        boolean has_3_letters;

        if (text.length() >= 3) {
            has_3_letters = true;
        } else {
            has_3_letters = false;
        }

        return has_3_letters;
    }

    public static String reverse_user_text(String specialText) {
        String reversed_text = "";

        for (int i = 0; i < specialText.length(); i++) {
            reversed_text = specialText.charAt(i) + reversed_text;
        }


        return reversed_text;
    }

    public static String reverse_user_text_by_words(String specialText) {

        String[] words = specialText.split("\\s+");
        String reversed_user_text_by_words = "";

        for (int i = 0; i < words.length; i++) {
            words[i] = reverseWords(words[i]);
        }

        reversed_user_text_by_words = String.join(" ", words);
        return reversed_user_text_by_words;
    }

    public static String reverseWords(String word) {
        char[] chars = word.toCharArray();
        String reversed = "";

        for (int i = chars.length - 1; i >= 0; i--) {
            reversed += chars[i];
        }
        return reversed;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        boolean enter_right_text = true;
        boolean program_running = false;
        String special_text = "";

        System.out.println("Enter your special text: ");

        while (enter_right_text) {
            special_text = s.nextLine();
            if (contains_3_letters(special_text)) {
                program_running = true;
                break;
            } else {
                enter_right_text = true;
                System.out.println("Enter text, wich contains at least 3 letters!");
            }
        }

        while (program_running) {
            System.out.println("""
                         Choose what you want to do with your text
                       \s
                         reverse(1)                 reverse every word(2)
                        \s
                                         exit program(3)
                   \s""");

            String choose = s.nextLine();

            switch (choose) {
                case "1":
                    System.out.println(reverse_user_text(special_text));
                    System.out.println("""
                            Want to do another text operations?
                            
                            yes(1)          exit the program(2)
                            """);

                    String another_operation = s.nextLine();

                    if (another_operation == "1") {
                        break;
                    }

                    if (another_operation == "2") {
                        program_running = false;
                        break;
                    }
                break;

                case "2" :
                    System.out.println(reverse_user_text_by_words(special_text));
                    System.out.println("""
                            Want to do another text operations?
                            
                            yes(1)          exit the program(2)
                            """);

                    String another_operation_1 = s.nextLine();

                    if (another_operation_1 == "1") {
                        break;
                    }

                    if (another_operation_1 == "2") {
                        program_running = false;
                        break;
                    }
                    break;

                case "3":
                    program_running = false;
                    break;

                default:
                    System.out.println("Enter a right choose!");
            }
        }
    }
}

