package boj.n17213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N17213 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        if (n == m) {
            System.out.println(1);
            return;
        }

        int[][] memo = new int[n + 1][m - n + 1];

        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[0].length; j++) {
                if (i == 0 && j != 0) {
                    memo[i][j] = 0;
                } else if (j == 0) {
                    memo[i][j] = 1;
                } else {
                    memo[i][j] = memo[i][j - 1] + memo[i - 1][j];
                }
            }
        }
        System.out.println(memo[n][m - n]);

    }

}
