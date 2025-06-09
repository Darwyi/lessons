package org.example.AP.Practice17;

import org.example.AP.Practice17.Models.Settings;
import org.example.AP.Practice17.Models.Statistics;

import java.io.*;
import java.time.LocalDateTime;

public class Data {
    public static boolean isRunning = true;
    public static String currentMenu = "main";
    public static Settings settings = new Settings(3, "Player X", "Player O");
    public static final String SETTINGS_FILE = "settings.txt";
    public static final String STATS_FILE = "statistics.txt";

    public static void saveSettings() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(SETTINGS_FILE))) {
            bw.write(settings.size + "\n");
            bw.write(settings.playerX + "\n");
            bw.write(settings.playerO + "\n");
        } catch (IOException e) {
            System.out.println("Error saving settings: " + e.getMessage());
        }
    }
    public static void loadSettings() {
        try (BufferedReader in = new BufferedReader(new FileReader(SETTINGS_FILE))) {
            settings.size = Integer.parseInt(in.readLine());
            settings.playerX = in.readLine();
            settings.playerO = in.readLine();
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
        Statistics stat = new Statistics(winner, settings.size, settings.playerX, settings.playerO);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(STATS_FILE, true))) {
            bw.write(stat.toString() + "\n");
        } catch (IOException e) {
            System.out.println("Error saving statistics: " + e.getMessage());
        }
    }

}
