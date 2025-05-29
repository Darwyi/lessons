package org.example.AP.Practice16_TicTacToe;

import java.util.Scanner;

import static org.example.AP.Practice16_TicTacToe.Data.*;
import static org.example.AP.Practice16_TicTacToe.UI.input;

public class Logic {
    static Scanner s = new Scanner(System.in);

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
            System.out.println("Current player: " + (current_player.equals("X") ? playerX : playerO));
            int row = get_hod("row", size);
            if (row == 10) {
                currentMenu = "main";
                break;
            }

            int col = get_hod("column", size);
            if (col == 10) {
                currentMenu = "main";
                break;
            }

            if (board[row * 2 - 1][col * 2 - 1].equals(" ")) {
                board[row * 2 - 1][col * 2 - 1] = current_player;
                moves++;
                if (check_win(board, size, current_player)) {
                    System.out.println("Player " + (current_player.equals("X") ? playerX : playerO) + " wins!\nFinal view of board:\n");
                    print_field(board);
                    Data.saveStatistics(current_player.equals("X") ? playerX : playerO);
                    break;
                }
                if (moves == size * size) {
                    System.out.println("It's a tie!\nFinal view of board:\n");
                    print_field(board);
                    Data.saveStatistics("Draw");
                    break;
                }
                current_player = current_player.equals("X") ? "O" : "X";
            } else {
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
}
