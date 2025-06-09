package org.example.TA.HW_18_05;

public class Bishops {
    static int N = 8;
    static int[][] board = new int[N][N];
    static int[] diag1 = new int[2 * N]; // x + y
    static int[] diag2 = new int[2 * N]; // x - y + (N - 1)
    static int placed = 0;

    public static void main(String[] args) {
        placeBishops(0, 0);
        printBoard();
    }

    static boolean placeBishops(int row, int count) {
        if (count == N) return true;
        if (row >= N) return false;

        for (int col = 0; col < N; col++) {
            int d1 = row + col;
            int d2 = row - col + (N - 1);

            if (diag1[d1] == 0 && diag2[d2] == 0) {
                board[row][col] = 1;
                diag1[d1] = 1;
                diag2[d2] = 1;

                if (placeBishops(row + 1, count + 1)) return true;

                board[row][col] = 0;
                diag1[d1] = 0;
                diag2[d2] = 0;
            }
        }

        if (placeBishops(row + 1, count)) return true;

        return false;
    }

    static void printBoard() {
        for (int[] row : board) {
            for (int cell : row) {
                System.out.print((cell == 1 ? "B " : ". "));
            }
            System.out.println();
        }
    }
}
