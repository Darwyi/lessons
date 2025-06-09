package org.example.TA.HW_18_05;

public class Rooks {
    public static void main(String[] args) {
        int N = 8;
        int[][] board = new int[N][N];

        for (int i = 0; i < N; i++) {
            board[i][i] = 1;
        }

        printBoard(board);
    }

    static void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) {
                System.out.print((cell == 1 ? "R " : ". "));
            }
            System.out.println();
        }
    }
}
