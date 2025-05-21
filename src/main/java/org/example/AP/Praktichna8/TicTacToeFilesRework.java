package org.example.AP.Praktichna8;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Scanner;

public class TicTacToeFilesRework {

    public static Scanner s = new Scanner(System.in);
    public static String current_menu = "main";
    public static boolean is_running = true;
    public static int size = 3;
    public static String playerX = "Player X";
    public static String playerO = "Player O";

    public static String sett_file = "settings.txt";
    public static String stat_file = "statistics.txt";

    public static int input() {
        while (!s.hasNextInt()) {
            System.out.println("Wrong input! Enter a number.");
            s.nextLine();
        }
        int input = s.nextInt();
        s.nextLine();
        return input;
    }

    public static void fiels_size() {
        System.out.println("Choose Field Size:\n3x3(1)\t5x5(2)\t7x7(3)\t9x9(4)\n\t\t\t\t10. Back");
        switch (input()) {
            case 1 -> size = 3;
            case 2 -> size = 5;
            case 3 -> size = 7;
            case 4 -> size = 9;
            case 10 -> current_menu = "settings";
            default -> System.out.println("Invalid choice.");
        }
    }

    public static boolean check_win(String[][] board, int size, String current_player) {

        for (int i = 0; i < size; i++) {
            if (checkLine(board, size, i, true, current_player) || checkLine(board, size, i, false, current_player)) return true;
        }
        return checkDiagonal(board, size, current_player);
    }

    public static boolean checkLine(String[][] board, int size, int index, boolean isRow, String player) {
        for (int i = 0; i < size; i++) {
            int row = isRow ? index : i;
            int col = isRow ? i : index;
            if (!board[row * 2 + 1][col * 2 + 1].equals(player)) return false;
        }
        return true;
    }

    public static boolean checkDiagonal(String[][] board, int size, String player) {
        boolean mainDiagonal = true, antiDiagonal = true;
        for (int i = 0; i < size; i++) {
            if (!board[i * 2 + 1][i * 2 + 1].equals(player)) mainDiagonal = false;
            if (!board[i * 2 + 1][(size - i - 1) * 2 + 1].equals(player)) antiDiagonal = false;
        }
        return mainDiagonal || antiDiagonal;
    }

    public static void saveSettings() {
        try (PrintWriter out = new PrintWriter(new FileWriter(sett_file))) {
            out.println(size);
            out.println(playerX);
            out.println(playerO);
        } catch (IOException e) {
            System.out.println("Error saving settings: " + e.getMessage());
        }
    }

    public static void loadSettings() {
        try (BufferedReader in = new BufferedReader(new FileReader(sett_file))) {
            size = Integer.parseInt(in.readLine());
            playerX = in.readLine();
            playerO = in.readLine();
        } catch (IOException | NumberFormatException e) {
            System.out.println("Using default settings.");
        }
    }

    public static void saveStatistics(String winner) {
        try (PrintWriter out = new PrintWriter(new FileWriter(stat_file, true))) {
            LocalDateTime now = LocalDateTime.now();
            out.println(now + ", Winner: " + winner + ", Field Size: " + size + ", " + playerX + " vs " + playerO);
        } catch (IOException e) {
            System.out.println("Error saving statistics: " + e.getMessage());
        }
    }

    public static void viewStatistics() {
        try (BufferedReader in = new BufferedReader(new FileReader(stat_file))) {
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("No statistics found.");
        }
    }

    public static void main_menu() {
        System.out.println("\t\tMain Menu\n\tPlay(1)\t\tSettings(2)\n\tStatistics(3)\tExit(4)");
        int choice = input();
        switch (choice) {
            case 1 -> current_menu = "game";
            case 2 -> current_menu = "settings";
            case 3 -> viewStatistics();
            case 4 -> is_running = false;
            default -> System.out.println("Wrong choice.");
        }
    }

    public static void settings() {
        System.out.println("Set Field Size(1)\t\tSet Player Names(2)\n\nBack(10)");
        int choice = input();
        if (choice == 1) fiels_size();
        else if (choice == 2) setPlayerNames();
        else if (choice == 10) current_menu = "main";
        else System.out.println("Option not available.");
        saveSettings();
    }

    public static void setPlayerNames() {
        System.out.println("Enter name for Player X:");
        playerX = s.nextLine();
        System.out.println("Enter name for Player O:");
        playerO = s.nextLine();
    }

    public static void game() {
        int board_size = size * 2;
        String[][] board = new String[board_size + 1][board_size + 1];
        create_field(board, board_size);
        start(board, board_size);
    }

    public static void create_field(String[][] board, int board_size) {
        for (int i = 0; i < board_size + 1; i++) {
            for (int j = 0; j < board_size + 1; j++) {
                if (i == 0 && j == 0) {
                    board[i][j] = "  ";
                } else if (i == 0 && j % 2 == 1) {
                    board[i][j] = (j / 2) + 1 + " ";
                } else if (j == 0 && i % 2 == 1) {
                    board[i][j] = (i / 2) + 1 + " ";
                } else if (i % 2 == 1 && j % 2 == 1) {
                    board[i][j] = " ";
                } else if (i % 2 == 1) {
                    board[i][j] = "| ";
                } else if (j % 2 == 1) {
                    board[i][j] = "--";
                } else {
                    board[i][j] = " ";
                }
            }
        }
    }

    public static void start(String[][] board, int boardSize) {
        String current_player = "X";
        int moves = 0;
        while (true) {
            print_field(board);
            System.out.println("Current player: " + (current_player.equals("X") ? playerX : playerO));
            int row = get_hod("row", size);
            if (row == 10) {
                current_menu = "main";
                break;
            }

            int col = get_hod("column", size);
            if (col == 10) {
                current_menu = "main";
                break;
            }

            if (board[row * 2 - 1][col * 2 - 1].equals(" ")) {
                board[row * 2 - 1][col * 2 - 1] = current_player;
                moves++;
                if (check_win(board, size, current_player)) {
                    System.out.println("Player " + (current_player.equals("X") ? playerX : playerO) + " wins!\nFinal view of board:\n");
                    print_field(board);
                    saveStatistics(current_player.equals("X") ? playerX : playerO);
                    break;
                }
                if (moves == size * size) {
                    System.out.println("It's a tie!\nFinal view of board:\n");
                    print_field(board);
                    saveStatistics("Draw");
                    break;
                }
                current_player = current_player.equals("X") ? "O" : "X";
            } else {
                System.out.println("Spot already taken. Choose another.");
            }
        }
    }

    public static int get_hod(String type, int limit) {
        int move;
        do {
            System.out.println("Enter " + type + " (1-" + limit + ") or 10 to back:");
            move = input();
            if (move < 1 && move != 10 || move > size && move != 10) {
                System.out.println("Wrong input choose from 1 to " + size + ".");
            }
        } while (move < 1 || move > limit && move != 10);
        return move;
    }

    public static void print_field(String[][] board) {
        for (String[] row : board) {
            for (String cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        loadSettings();
        while (is_running) {
            switch (current_menu) {
                case "main":
                    main_menu();
                    break;
                case "settings":
                    settings();
                    break;
                case "game":
                    game();
                    break;
            }
        }
    }
}
