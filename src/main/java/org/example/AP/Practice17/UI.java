package org.example.AP.Practice17;

import java.util.Scanner;

public class UI {
    private static Scanner s = new Scanner(System.in);

    public static int input() {
        while (!s.hasNextInt()) {
            System.out.println("Wrong input! Enter a number.");
            s.nextLine();
        }
        int input = s.nextInt();
        s.nextLine();
        return input;
    }

    public static void mainMenu() {
        System.out.println("\t\tMain Menu\n\tPlay(1)\t\tSettings(2)\n\tStatistics(3)\tExit(4)");
        int choice = input();
        switch (choice) {
            case 1 -> Data.currentMenu = "game";
            case 2 -> Data.currentMenu = "settings";
            case 3 -> Data.viewStatistics();
            case 4 -> Data.isRunning = false;
            default -> System.out.println("Wrong choice.");
        }
    }

    public static void settings() {
        System.out.println("Set Field Size(1)\t\tSet Player Names(2)\n\nBack(10)");
        int choice = input();
        if (choice == 1) fieldSize();
        else if (choice == 2) setPlayerNames();
        else if (choice == 10) Data.currentMenu = "main";
        else System.out.println("Option not available.");
        Data.saveSettings();
    }

    public static void fieldSize() {
        System.out.println("Choose Field Size:\n3x3(1)\t5x5(2)\t7x7(3)\t9x9(4)\n\t\t\t\t10. Back");
        switch (input()) {
            case 1 -> Data.settings.size = 3;
            case 2 -> Data.settings.size = 5;
            case 3 -> Data.settings.size = 7;
            case 4 -> Data.settings.size = 9;
            case 10 -> Data.currentMenu = "settings";
            default -> System.out.println("Invalid choice.");
        }
    }

    public static void setPlayerNames() {
        System.out.println("Enter name for Player X:");
        Data.settings.playerX = s.nextLine();
        System.out.println("Enter name for Player O:");
        Data.settings.playerO = s.nextLine();
    }
}
