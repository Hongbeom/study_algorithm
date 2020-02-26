package boj.n10469;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class N10469 {

    static final int[] H = new int[]{-1, 1, 0, 0, 1, 1, -1, -1};
    static final int[] W = new int[]{0, 0, -1, 1, 1, -1, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] board = new char[8][8];
        List<int[]> queens = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == '*') {
                    queens.add(new int[]{i, j});
                }
            }
        }

        if (queens.size() != 8) {
            System.out.println("invalid");
            return;
        }

        for (int[] queen : queens) {
            if (check(board, queen)) {
                System.out.println("invalid");
                return;
            }
        }

        System.out.println("valid");

    }


    static boolean check(char[][] board, int[] start) {
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < 8; i++) {
            queue.add(new int[]{start[0], start[1], i});
        }

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            current[0] += H[current[2]];
            current[1] += W[current[2]];


            if (current[0] < 0 || current[1] < 0 || current[0] >= 8 || current[1] >= 8) {
                continue;
            }

            if (board[current[0]][current[1]] == '*') {
                return true;
            }

            queue.add(current);
        }

        return false;
    }
}
