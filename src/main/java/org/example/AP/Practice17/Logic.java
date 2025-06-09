package org.example.AP.Practice17;

import java.util.Scanner;
import org.example.AP.Practice17.Models.Board;

public class Logic {
    static Scanner s = new Scanner(System.in);

    public static void game() {
        Board board = new Board(Data.settings.size);
        start(board);
    }

    public static void start(Board board) {
        String current_player = "X";
        int moves = 0;
        while (true) {
            board.print();
            System.out.println("Current player: " + (current_player.equals("X") ? Data.settings.playerX : Data.settings.playerO));
            int row = get_hod("row", Data.settings.size);
            if (row == 10) {
                Data.currentMenu = "main";
                break;
            }

            int col = get_hod("column", Data.settings.size);
            if (col == 10) {
                Data.currentMenu = "main";
                break;
            }

            if (board.board[row * 2 - 1][col * 2 - 1].equals(" ")) {
                board.board[row * 2 - 1][col * 2 - 1] = current_player;
                moves++;
                if (check_win(board, Data.settings.size, current_player)) {
                    System.out.println("Player " + (current_player.equals("X") ? Data.settings.playerX : Data.settings.playerO) + " wins!\nFinal view of board:\n");
                    board.print();
                    Data.saveStatistics(current_player.equals("X") ? Data.settings.playerX : Data.settings.playerO);
                    break;
                }
                if (moves == Data.settings.size * Data.settings.size) {
                    System.out.println("It's a tie!\nFinal view of board:\n");
                    board.print();
                    Data.saveStatistics("Draw");
                    break;
                }
                current_player = current_player.equals("X") ? "O" : "X";
            } else {
                System.out.println("Spot already taken. Choose another.");
            }
        }
    }

    public static boolean check_win(Board board, int size, String current_player) {
        for (int i = 0; i < Data.settings.size; i++) {
            if (checkLine(board, size, i, true, current_player) || checkLine(board, size, i, false, current_player)) return true;
        }
        return checkDiagonal(board, Data.settings.size, current_player);
    }

    public static boolean checkLine(Board board, int size, int index, boolean isRow, String player) {
        for (int i = 0; i < size; i++) {
            int row = isRow ? index : i;
            int col = isRow ? i : index;
            if (!board.board[row * 2 + 1][col * 2 + 1].equals(player)) return false;
        }
        return true;
    }

    public static boolean checkDiagonal(Board board, int size, String player) {
        boolean mainDiagonal = true, antiDiagonal = true;
        for (int i = 0; i < size; i++) {
            if (!board.board[i * 2 + 1][i * 2 + 1].equals(player)) mainDiagonal = false;
            if (!board.board[i * 2 + 1][(size - i - 1) * 2 + 1].equals(player)) antiDiagonal = false;
        }
        return mainDiagonal || antiDiagonal;
    }

    public static int get_hod(String type, int limit) {
        int move;
        do {
            System.out.println("Enter " + type + " (1-" + limit + ") or 10 to back:");
            move = UI.input();
            if (move < 1 && move != 10 || move > Data.settings.size && move != 10) {
                System.out.println("Wrong input choose from 1 to " + Data.settings.size + ".");
            }
        } while (move < 1 || move > limit && move != 10);
        return move;
    }
} 
