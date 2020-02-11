package boj.n1535;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N1535 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] hp = new int[n];
        int[] pleasure = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            hp[i] = Integer.parseInt(st.nextToken());

        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            pleasure[i] = Integer.parseInt(st.nextToken());

        }

        int[][] dp = new int[n + 1][100];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < 100; j++) {
                if (j - hp[i - 1] >= 0) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - hp[i - 1]] + pleasure[i - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        System.out.println(dp[n][99]);


    }
}
