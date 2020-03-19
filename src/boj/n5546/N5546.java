package boj.n5546;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N5546 {

    static int[] PLAN;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        PLAN = new int[n + 1];
        int[][] memo = new int[4][n + 1];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            PLAN[d] = p;
        }

        if (PLAN[1] == 0) {
            memo[1][1] = 1;
            memo[2][1] = 1;
            memo[3][1] = 1;
        } else {
            memo[PLAN[1]][1] = 1;
        }

        for (int j = 2; j <= n; j++) {
            for (int i = 1; i <= 3; i++) {
                if (PLAN[j] != 0 && PLAN[j] != i) {
                    continue;
                }

                int sum = (memo[1][j - 1] + memo[2][j - 1] + memo[3][j - 1]) % 10000;
                if (j != 2 && memo[i][j - 1] != 0 && memo[i][j - 2] != 0) {
                    // 빼주는 경우.
                    if (j - 3 == 0) {
                        sum -= 1;
                    } else {
                        int b1 = i % 3 + 1;
                        int b2 = b1 % 3 + 1;
                        long sb = memo[b1][j - 3] + memo[b2][j - 3];
                        sum -= sb;
                    }
                }

                while (sum < 0) {
                    sum += 10000;
                }

                memo[i][j] = sum;
            }
        }


        int ans = (memo[1][n] + memo[2][n] + memo[3][n]) % 10000;
        System.out.println(ans);

    }
}
