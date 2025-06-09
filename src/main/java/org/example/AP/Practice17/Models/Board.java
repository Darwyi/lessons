package org.example.AP.Practice17.Models;

public class Board {
    public String[][] board;
    public int size;

    public Board(int size) {
        this.size = size;
        int boardSize = size * 2;
        board = new String[boardSize + 1][boardSize + 1];
        createField();
    }

    private void createField() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
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

    public void print() {
        for (String[] row : board) {
            for (String cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }
}
