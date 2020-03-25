package boj.n9952;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N9952 {

    static StringBuilder ANS = new StringBuilder();
    static char[] A;
    static char[] B;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = br.readLine().toCharArray();
        B = br.readLine().toCharArray();

        int h = 0;
        int w = 0;
        int[][] memo = new int[A.length + 1][B.length + 1];
        int length = 0;
        for (int i = 1; i < memo.length; i++) {
            for (int j = 1; j < memo[0].length; j++) {
                if (A[i - 1] == B[j - 1]) {
                    memo[i][j] = memo[i - 1][j - 1] + 1;
                    if (memo[i][j] > length) {
                        length = memo[i][j];
                        h = i;
                        w = j;
                    }
                } else {
                    memo[i][j] = Math.max(memo[i - 1][j], memo[i][j - 1]);
                }

            }
        }

        System.out.println(length);
        if (length != 0) {
            ANS.append(A[h - 1]);
            search(memo, h, w, length - 1);
            System.out.println(ANS.reverse().toString());
        }
    }

    static void search(int[][] memo, int h, int w, int target) {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (memo[i][j] == target) {
                    ANS.append(A[i - 1]);
                    if (target - 1 != 0) {
                        search(memo, i, j, target - 1);
                    }
                    return;
                }
            }
        }
    }
}
