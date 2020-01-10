package boj.n1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N1010 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());

        int[][] memo = new int[31][31];

        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[0].length; j++) {
                if (i == 0 || j == 0) {
                    memo[i][j] = 0;
                } else if(i == 1){
                    memo[i][j] = j;
                }else {
                    memo[i][j] = memo[i][j - 1] + memo[i - 1][j - 1];
                }
            }
        }


        StringTokenizer st;
        for (int tc = 0; tc < TC; tc++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            System.out.println(memo[n][m]);
        }
    }
}
