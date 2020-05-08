package boj.n17485;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class N17485 {

    static int MAX = 100001;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        int[][][] memo = new int[n][m][3];
        int answer = MAX;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int j = 0; j < m; j++) {
            Arrays.fill(memo[0][j], map[0][j]);
            if (j == 0) {
                memo[0][j][2] = MAX;
            }
            if (j == m - 1) {
                memo[0][j][0] = MAX;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (j == 0) {
                    memo[i][j][0] = Math.min(memo[i - 1][j + 1][1], memo[i - 1][j + 1][2]) + map[i][j];
                    memo[i][j][2] = MAX;
                } else if (j == m - 1) {
                    memo[i][j][0] = MAX;
                    memo[i][j][2] = Math.min(memo[i - 1][j - 1][0], memo[i - 1][j - 1][1]) + map[i][j];
                } else {
                    memo[i][j][0] = Math.min(memo[i - 1][j + 1][1], memo[i - 1][j + 1][2]) + map[i][j];
                    memo[i][j][2] = Math.min(memo[i - 1][j - 1][0], memo[i - 1][j - 1][1]) + map[i][j];
                }
                memo[i][j][1] = Math.min(memo[i - 1][j][0], memo[i - 1][j][2]) + map[i][j];
                if (i == n - 1) {
                    answer = Math.min(answer, memo[i][j][0]);
                    answer = Math.min(answer, memo[i][j][1]);
                    answer = Math.min(answer, memo[i][j][2]);
                }
            }
        }
        System.out.println(answer);
    }
}
