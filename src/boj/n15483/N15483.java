package boj.n15483;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N15483 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] change = br.readLine().toCharArray();
        char[] target = br.readLine().toCharArray();

        int[][] memo = new int[change.length + 1][target.length + 1];

        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[0].length; j++) {
                if (i == 0) {
                    memo[i][j] = j;
                    continue;
                }
                if (j == 0) {
                    memo[i][j] = i;
                    continue;
                }
                if (change[i - 1] == target[j - 1]) {
                    memo[i][j] = memo[i - 1][j - 1];
                } else {
                    memo[i][j] = min3(memo[i - 1][j] + 1, memo[i - 1][j - 1] + 1, memo[i][j - 1] + 1);
                }
            }
        }

        System.out.println(memo[change.length][target.length]);

    }

    static int min3(int a, int b, int c) {
        if (a < b) {
            return Math.min(a, c);
        } else {
            return Math.min(b, c);
        }
    }

}
