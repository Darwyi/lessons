package org.example.AP.Practice16_TicTacToe;

import java.io.*;
import java.time.LocalDateTime;

public class Data {
    public static boolean isRunning = true;
    public static String currentMenu = "main";
    public static int size = 3;
    public static String playerX = "Player X";
    public static String playerO = "Player O";
    public static final String SETTINGS_FILE = "settings.txt";
    public static final String STATS_FILE = "statistics.txt";

    public static void saveSettings() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(SETTINGS_FILE))) {
            bw.write(size + "\n");
            bw.write(playerX + "\n");
            bw.write(playerO + "\n");
        } catch (IOException e) {
            System.out.println("Error saving settings: " + e.getMessage());
        }
    }
    public static void loadSettings() {
        try (BufferedReader in = new BufferedReader(new FileReader(SETTINGS_FILE))) {
            size = Integer.parseInt(in.readLine());
            playerX = in.readLine();
            playerO = in.readLine();
        } catch (IOException | NumberFormatException e) {
            System.out.println("Using default settings.");
        }
    }
    public static void viewStatistics() {
        try (BufferedReader in = new BufferedReader(new FileReader(STATS_FILE))) {
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("No statistics found.");
        }
    }
    public static void saveStatistics(String winner) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(STATS_FILE, true))) {
            LocalDateTime now = LocalDateTime.now();
            bw.write(now + ", Winner: " + winner + ", Field Size: " + size + ", " + playerX + " vs " + playerO + "\n");
        } catch (IOException e) {
            System.out.println("Error saving statistics: " + e.getMessage());
        }
    }
}
