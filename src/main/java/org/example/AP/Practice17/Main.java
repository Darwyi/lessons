package org.example.AP.Practice17;

public class Main {
    public static void main(String[] args) {
        Data.loadSettings();
        while (Data.isRunning) {
            switch (Data.currentMenu) {
                case "main" -> UI.mainMenu();
                case "settings" -> UI.settings();
                case "game" -> Logic.game();
            }
        }
    }
}
