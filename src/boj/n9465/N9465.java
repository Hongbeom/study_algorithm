package boj.n9465;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N9465 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int tc = 0; tc < TC; tc++) {
            int n = Integer.parseInt(br.readLine());
            int[][] input = new int[2][n];
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    input[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] memo = new int[2][n];
            int answer = 0;
            for (int j = 0; j < n; j++) {
                if (j == 0) {
                    memo[0][j] = input[0][j];
                    memo[1][j] = input[1][j];
                } else {
                    memo[0][j] = Math.max(memo[0][j-1], memo[1][j-1] + input[0][j]);
                    memo[1][j] = Math.max(memo[1][j-1], memo[0][j-1] + input[1][j]);

                    if( j == n-1){
                        answer = Math.max(memo[0][j], memo[1][j]);
                    }
                }
            }

            System.out.println(answer);
        }
    }
}
