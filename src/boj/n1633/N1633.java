package boj.n1633;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class N1633 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String in;
        StringTokenizer st;
        List<Integer> white = new ArrayList<>();
        List<Integer> black = new ArrayList<>();
        while ((in = br.readLine()) != null) {
            st = new StringTokenizer(in);
            white.add(Integer.parseInt(st.nextToken()));
            black.add(Integer.parseInt(st.nextToken()));
        }

        int[][][] dp = new int[white.size() + 1][16][16];


        for (int i = 1; i <= white.size(); i++) {
            for (int w = 0; w < 16; w++) {
                for (int b = 0; b < 16; b++) {
                    if (w == 0 && b == 0) {
                        dp[i][w][b] = 0;
                    } else if (w == 0) {
                        dp[i][w][b] = Math.max(dp[i - 1][w][b], dp[i - 1][w][b - 1] + black.get(i - 1));
                    } else if (b == 0) {
                        dp[i][w][b] = Math.max(dp[i - 1][w][b], dp[i - 1][w - 1][b] + white.get(i - 1));
                    }else{
                        dp[i][w][b] = max(dp[i - 1][w][b], dp[i - 1][w - 1][b] + white.get(i - 1), dp[i - 1][w][b - 1] + black.get(i - 1));
                    }
                }
            }
        }

        System.out.println(dp[white.size()][15][15]);

    }

    static int max(int a, int b, int c) {
        if (a > b) {
            if (c > a) {
                return c;
            } else {
                return a;
            }
        } else {
            if (c > b) {
                return c;
            } else {
                return b;
            }
        }
    }
}