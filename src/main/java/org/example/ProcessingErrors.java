package org.example;

import java.util.Currency;
import java.util.Scanner;

public class ProcessingErrors {

    public static Scanner s = new Scanner(System.in);
    public static int Users = 0;
    public static  String CurrentMenu = "Main";

    public static void main(String[] args) {

        System.out.println("""
                      Authorization menu
                
                authorizate new user(1)    delete user(2)
                                    exit(3)
                """);
        while (CurrentMenu.equals("Main")) {

            String MainMenuChoice = s.nextLine();

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
        int spaces = 0;
        for (int i = 0; i < login.length(); i++) {
            if (login.charAt(i) == ' ') {
                spaces++;
            }
        }
        if (login.length() >= 5) {
            System.out.println("Login is must have at least 5 symbols");
        } else {
            return false;
        }
        if (spaces != 0) {
            System.out.println("Login can't have spaces!");
        } else {
            return false;
        }



        return true;
    }

    public static boolean PasswordCheck(String password) {
        String AllowedSymbols;

        return true;
    }

    public static void Authorize() {

        while (CurrentMenu.equals("Authorize")) {
            if (Users > 15) {
                System.out.println("You can't have more than 15 users!");
                CurrentMenu = "Main";
                break;
            }

            String UserLogin = s.nextLine();
            boolean RightLogin = LoginCheck(UserLogin);

            while (!RightLogin) {
                System.out.print("Authorize user \n\nLogin: ");
                UserLogin = s.nextLine();
                RightLogin = LoginCheck(UserLogin);
            }

                System.out.print("\nPassword: ");
                String UserPassword = s.nextLine();

                Users++;
                CurrentMenu = "Main";
                break;
        }
    }
}
