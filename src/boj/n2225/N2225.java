package boj.n2225;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N2225 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] memo = new int[k + 1][n + 1];

        for (int i = 1; i < memo.length; i++) {
            for (int j = 0; j < memo[0].length; j++) {

                if (i == 1 || j == 0) {
                    memo[i][j] = 1;
                } else {
                    memo[i][j] = (memo[i - 1][j] + memo[i][j - 1]) % 1000000000;
                }
            }
        }

        System.out.println(memo[k][n]);
    }
}
