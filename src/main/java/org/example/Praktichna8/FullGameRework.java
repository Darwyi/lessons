package org.example.Praktichna8;

import java.util.Scanner;

public class FullGameRework {

    public static Scanner s = new Scanner(System.in);
    public static String current_menu = "main";
    public static boolean is_running = true;
    public static int size = 3;

    public static int input() {
        while (!s.hasNextInt()) {
            System.out.println("Wrong input! Enter a number.");
            s.nextLine();
        }
        int input = s.nextInt();
        s.nextLine();
        return input;
    }


    public static void main_menu() {
        System.out.println("\t\tMain Menu\n\tPlay(1)\t\tSettings(2)\n\t\t\t\tExit(3)");
        int choice = input();
        switch (choice) {
            case 1 -> current_menu = "game";
            case 2 -> current_menu = "settings";
            case 3 -> is_running = false;
            default -> System.out.println("Wrong choice.");
        }
    }

    public static void settings() {
        System.out.println("Set Field Size(1)\t\tOther Settings(soon)\n\nBack(10)");
        int choice = input();
        if (choice == 1) fiels_size();
        else if (choice == 10) current_menu = "main";
        else System.out.println("Option not available.");
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

    public static void game() {
        int board_size = size * 2;
        String[][] board = new String[board_size + 1][board_size + 1];
        create_field(board, board_size);
        start(board, board_size);
    }

    public static void start(String[][] board, int boardSize) {
        String current_player = "X";
        int moves = 0;
        while (true) {
            print_field(board);
            System.out.println("Current player: " + current_player);
            int row = get_hod("row", size);
            if (row < 1 && row != 10 || row > size && row != 10) {
                System.out.println("Wrong input choose from 1 to " + size + ".");
                break;
            }else if (row == 10) {
                current_menu = "main";
                break;
            }

            int col = get_hod("column", size);
            if (col < 1 && col !=10 || col > size && col !=10){
                System.out.println("Wrong input choose from 1 to " + size + ".");
                break;
            } else if (col == 10) {
                current_menu = "main";
                break;
            }


            if (board[row * 2 - 1][col * 2 - 1].equals(" ")) {
                board[row * 2 - 1][col * 2 - 1] = current_player;
                moves++;
                if (check_win(board, size, current_player)) {
                    System.out.println("Player " + current_player + " wins!\nFinal view of board:\n");
                    print_field(board);
                    break;
                }
                if (moves == size * size) {
                    System.out.println("It's a tie!\nFinal view of board:\n");
                    print_field(board);
                    break;
                }
                current_player = (current_player.equals("X")) ? "O" : "X";
            } else if (board[row * 2 - 1][col *2 - 1] == "X" || board[row * 2 - 1][col *2 - 1] == "O"){
                System.out.println("Spot already taken. Choose another.");
            }
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

    public static void main(String[] args) {
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
