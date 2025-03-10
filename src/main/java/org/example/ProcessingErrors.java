package org.example;

import java.util.Currency;
import java.util.Scanner;

public class ProcessingErrors {

    public static Scanner s = new Scanner(System.in);
    public static int Users = 0;
    public static  String CurrentMenu = "Main";

    public static String UserInput() {
        String userInput = null;

        try {
            userInput = s.nextLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            return userInput;
        }
    }

    public static void main(String[] args) {

        System.out.println("""
                      Authorization menu
                
                authorizate new user(1)    delete user(2)
                                    exit(3)
                """);
        while (CurrentMenu.equals("Main")) {

            String MainMenuChoice = UserInput();

            switch (MainMenuChoice) {
                case "1":
                    CurrentMenu = "Authorize";
                    Authorize();
                    break;
                case "2":
                    CurrentMenu = "DeleteUser";
                    Authorize();
                    break;
                case "3":
                    break;
                default:
                    System.out.println("Wrong input!");
            }
        }
    }

    public static boolean LoginCheck(String login) {
        if (login.length() >= 5) {
            for (int i = 0; i < login.length(); i++) {
                if (login.charAt(i) == ' ') {
                    System.out.println("Логін не має містити пробілів!");
                }
            }
        } else {
            return false;
        }

        return true;
    }

    public static boolean PasswordCheck(String password) {


        return true;
    }

    public static void Authorize() {

        while (CurrentMenu.equals("Authorize")) {
            if (Users > 15) {
                System.out.println("You can't have more than 15 users!");
                CurrentMenu = "Main";
                break;
            }

            String UserLogin = UserInput();
            boolean RightLogin = LoginCheck(UserLogin);

            while (!RightLogin) {
                System.out.print("Authorize user \n\nLogin: ");
                UserLogin = UserInput();
                RightLogin = LoginCheck(UserLogin);
            }

                System.out.print("\nPassword: ");
                String UserPassword = UserInput();
        }
    }
}
