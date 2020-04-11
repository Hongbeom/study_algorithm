package boj.n1018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N1018 {
    static boolean[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        board = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                board[i][j] = tmp[j] == 'W';
            }
        }
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i <= n - 8; i++) {
            for (int j = 0; j <= m - 8; j++) {
                answer = Math.min(answer, check(i, j));
            }
        }
        System.out.println(answer);
    }

    static int check(int sh, int sw) {
        int c1 = 0;
        int c2 = 0;
        for (int i = 0; i < 8; i++) {
            int h = i + sh;
            boolean f;
            if (i % 2 == 0) {
                f = board[sh][sw];
            } else {
                f = !board[sh][sw];
            }
            for (int j = 0; j < 8; j++) {
                int w = j + sw;
                if (j % 2 == 0) {
                    if (board[h][w] != f) {
                        c1++;
                    } else {
                        c2++;
                    }
                } else {
                    if (board[h][w] == f) {
                        c1++;
                    } else {
                        c2++;
                    }
                }
            }
        }
        return Math.min(c1, c2);
    }
}
