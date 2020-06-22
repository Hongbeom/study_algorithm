package boj.n2073;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N2073 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int d = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        int[][] pipes = new int[p][2];

        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            pipes[i][0] = Integer.parseInt(st.nextToken());
            pipes[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] memo = new int[p][d + 1];

        memo[0][pipes[0][0]] = pipes[0][1];

        for (int i = 1; i < p; i++) {
            int l = pipes[i][0];
            int c = pipes[i][1];

            for (int j = 1; j < memo[0].length; j++) {

                if (j - l < 0) {
                    memo[i][j] = Math.max(memo[i][j], memo[i - 1][j]);
                } else if (j - l == 0) {
                    memo[i][j] = Math.max(memo[i - 1][j], c);
                } else {
                    memo[i][j] = Math.max(memo[i - 1][j], Math.min(c, memo[i - 1][j - l]));
                }
            }
        }

        System.out.println(memo[p - 1][d]);
    }
}
