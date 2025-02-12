package org.example.Praktichna7_12_02_2025;

import java.util.Scanner;

public class Funcs_and_strings {

    public static String right_text() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter your special text: ");
            String input = scanner.nextLine().trim();
            scanner.nextLine();
            String[] words = input.split("\\s+");

            int validWordsCount = 0;
            for (String word : words) {
                if (word.length() >= 3) {
                    validWordsCount++;
                }
            }

            if (validWordsCount >= 2) {
                return input;
            } else {
                System.out.println("Wrong! enter a text, wich have at least 2 words of 3 letters.");
            }
        }
    }

    public static boolean is_valid_text() {
        return !right_text().isEmpty();
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
        boolean program_running = true;

        String special_text = right_text();

        if (is_valid_text()) {
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
}

