package boj.n2140;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N2140 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] board = new int[n][n];
        int answer = 0;

        for (int i = 0; i < n; i++) {
            char[] in = br.readLine().toCharArray();
            if (i == 0 || i == n - 1) {
                for (int j = 0; j < n; j++) {
                    board[i][j] = in[j] - '0';
                }
            } else {
                board[i][0] = in[0] - '0';
                board[i][n - 1] = in[n - 1] - '0';
            }
        }

        for (int j = 1; j < n - 2; j++) {
            int current = 0;
            if (j == 1) {
                if (board[0][0] == 1) {
                    board[1][1] = 1;
                    answer++;
                    current++;
                }
                if (board[0][j] - current > 0) {
                    board[1][j + 1] = 1;
                    answer++;
                }
            } else {

                if (board[1][j - 1] == 1) {
                    current++;
                }

                if (board[1][j] == 1) {
                    current++;
                }

                if (board[0][j] - current > 0) {
                    board[1][j + 1] = 1;
                    answer++;
                }
            }
        }

        for (int j = 1; j < n - 2; j++) {
            int current = 0;
            if (j == 1) {

                if (board[n - 1][0] == 1) {
                    board[n - 2][1] = 1;
                    answer++;
                    current++;
                }

                if (board[n - 1][j] - current > 0) {
                    board[n - 2][j + 1] = 1;
                    answer++;
                }

            } else {

                if (board[n - 2][j - 1] == 1) {
                    current++;
                }

                if (board[n - 2][j] == 1) {
                    current++;
                }

                if (board[n - 1][j] - current > 0) {
                    board[n - 2][j + 1] = 1;
                    answer++;
                }
            }
        }

        for (int i = 1; i < n - 2; i++) {
            int current = 0;
            if (i == 1) {
                if (board[0][0] == 1) {
                    board[1][1] = 1;
                    current++;
                }

                if (board[i][0] - current > 0) {
                    board[i + 1][1] = 1;
                    answer++;
                }

            } else {

                if (board[i - 1][1] == 1) {
                    current++;
                }

                if (board[i][1] == 1) {
                    current++;
                }

                if (board[i][0] - current > 0) {
                    board[i + 1][1] = 1;

                    if (i + 1 != n - 2) {
                        answer++;
                    }
                }
            }
        }

        for (int i = 1; i < n - 2; i++) {
            int current = 0;
            if (i == 1) {
                if (board[0][n - 1] == 1) {
                    board[1][n - 2] = 1;
                    current++;
                }

                if (board[i][n - 1] - current > 0) {
                    board[i + 1][n - 2] = 1;
                    answer++;
                }

            } else {

                if (board[i - 1][n - 2] == 1) {
                    current++;
                }

                if (board[i][n - 2] == 1) {
                    current++;
                }

                if (board[i][n - 1] - current > 0) {
                    board[i + 1][n - 2] = 1;
                    if (i + 1 != n - 2) {
                        answer++;
                    }
                }
            }
        }
        if(n == 3 && board[0][0] == 1){
            answer = 1;
        }

        if (n <= 4) {
            System.out.println(answer);
        } else {
            System.out.println((int) (answer + Math.pow(n - 4, 2)));
        }
    }
}
