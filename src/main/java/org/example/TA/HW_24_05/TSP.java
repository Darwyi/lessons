package org.example.TA.HW_24_05;

import java.util.Arrays;

public class TSP {

    public static void main(String[] args) {
        int[][] adj = {
                {0, 10, 15, 20},
                {10, 0, 35, 25},
                {15, 35, 0, 30},
                {20, 25, 30, 0}
        };

        tsp(adj);
        System.out.printf("Максимальна вартість: %d%n", finalRes);
        System.out.print("Обраний маршрут: ");
        for (int i = 0; i <= N; i++) {
            System.out.printf("%d ", finalPath[i]);
        }
    }

    static final int N = 4;
    static int[] finalPath = new int[N + 1];
    static boolean[] visited = new boolean[N];
    static int finalRes = Integer.MIN_VALUE;

    static void tsp(int[][] adj) {
        int[] currPath = new int[N + 1];
        Arrays.fill(finalPath, -1);
        Arrays.fill(visited, false);

        visited[0] = true;
        currPath[0] = 0;

        tspRec(adj, 0, 1, currPath);
    }

    static void tspRec(int[][] adj, int currWeight, int level, int[] currPath) {
        if (level == N) {
            if (adj[currPath[level - 1]][currPath[0]] != 0) {
                int curRes = currWeight + adj[currPath[level - 1]][currPath[0]];

                if (curRes > finalRes) {
                    copyToFinal(currPath);
                    finalRes = curRes;
                }
            }
            return;
        }

        for (int i = 0; i < N; i++) {
            if (adj[currPath[level - 1]][i] != 0 && !visited[i]) {
                currPath[level] = i;
                visited[i] = true;

                tspRec(adj, currWeight + adj[currPath[level - 1]][i], level + 1, currPath);

                visited[i] = false;
            }
        }
    }

    static void copyToFinal(int[] currPath) {
        for (int i = 0; i < N; i++) {
            finalPath[i] = currPath[i];
        }
        finalPath[N] = currPath[0];
    }
}
