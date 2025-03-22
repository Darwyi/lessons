package org.example;

import java.util.Scanner;

public class ProcessingErrors {

    public static Scanner s = new Scanner(System.in);
    public static int Users = 0;
    public static String[] Usernames = new String[15];
    public static String[] Passwords = new String[15];

    public static String UserInput(String Text) {
        System.out.print(Text);
        return s.nextLine();
    }

    public static void RegisterUser() {
        if (Users >= 15) {
            System.out.println("You can't have more than 15 users!");
            return;
        }

        String Login = "";
        boolean RightLogin = false;

        while (!RightLogin) {
            Login = UserInput("\nEnter new user login (Login must be more than 5 symbols and haven't spaces): ");
            RightLogin = CheckLogin(Login);
        }

        String Password = "";
        boolean RightPassword = false;

        while (!RightPassword) {
            Password = UserInput("\nEnter user password (at least 10 symbols, 3 digit, 1 special symbol): ");
            RightPassword = CheckPassword(Password);
        }

        Usernames[Users] = Login;
        Passwords[Users] = Password;
        Users++;
        System.out.println("User added successfully!");
    }

    public static boolean CheckLogin(String Login) {
        return Login.length() >= 5 && !Login.contains(" ");
    }

    public static boolean CheckPassword(String Password) {
        String[] BannedPasswords = {"admin", "pass", "password", "qwerty", "ytrewq"};
        String AllowedSymbols = "qwertyuiop[]asdfghjkl;'zxcvbnm,./1234567890-=_+!@#$%^&*()<>{}:\\QWERTYUIOPASDFGHJKLZXCVBNM";
        int NumbersCount = 0;
        boolean HasSpecialChar = false;

        if (!(Password.length() >= 10) && Password.contains(" ")) {
            System.out.println("Password must have at least 10 symbols and no spaces!");
            return false;
        }

        for (String Banned : BannedPasswords) {
            if (Password.toLowerCase().contains(Banned)) {
                System.out.println("Password can't not so easy!");
                return false;
            }
        }

        char[] PasswordCharArr = Password.toCharArray();

        for (int i = 0; i < Password.length(); i++) {
            char[] AllowedSymbolsCharArray = AllowedSymbols.toCharArray();
            for (char c : AllowedSymbolsCharArray) {
                if (Password.charAt(i) == c) {
                    HasSpecialChar = true;
                    break;
                }
            }
        }

        for (char c : PasswordCharArr) {
            if (Character.isDigit(c)) {
                NumbersCount++;
            }
        }

        return NumbersCount >= 3 && HasSpecialChar;
    }


    public static void DeleteUser() {
        System.out.print("Enter username of user to delete: ");
        String Username = s.nextLine();

        for (int i = 0; i < Users; i++) {
            if (Usernames[i] != null && Usernames[i].equals(Username)) {
                Usernames[i] = Usernames[Users - 1];
                Passwords[i] = Passwords[Users - 1];

                Usernames[Users - 1] = null;
                Passwords[Users - 1] = null;

                Users--;
                System.out.println("User \"" + Username + "\" delete.");
                return;
            }
        }
        System.out.println("Error: User \"" + Username + "\" doesn't exist.");

    }

    public static boolean Authorize() {
        String Login = UserInput("Enter your login: ");
        String Password = UserInput("Enter your password: ");

        for (int i = 0; i < Users; i++) {
            if (Usernames[i] != null && Usernames[i].equals(Login) && Passwords[i].equals(Password)) {
                System.out.println("User authorized!");
                return true;
            }
        }
        System.out.println("Error: Wrong user login or password.");
        return false;
    }

    public static void SeeAllUsers() {

        if (Authorize()) {
            System.out.println("Users: ");
            for (int i = 0; i < Usernames.length; i++) {
                if (Usernames[i] == null){
                    System.out.print("");
                } else {
                    System.out.print(Usernames[i] + " ");
                }
            }
            System.out.println("\n");
        }
    }

    public static void main(String[] args) {
        boolean Running = true;
        while (Running) {
        System.out.println("""
                      Authorization menu
                
                authorizate new user(1)    delete user(2)
                See all users(3)                  exit(4)
                """);

        String MainMenuChoice = UserInput("Your choice: ");

        switch (MainMenuChoice) {
            case "1":
                RegisterUser();
                break;
            case "2":
                DeleteUser();
                break;
            case "3":
                SeeAllUsers();
                break;
            case "4":
                Running = false;
                break;
            default:
                System.out.println("Wrong input!");
        }
    }
    }
}
