package boj.n4435;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N4435 {

    static final int[] GANDALF = new int[]{1, 2, 3, 3, 4, 10};
    static final int[] SAURON = new int[]{1, 2, 2, 2, 3, 5, 10};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int tc = 1; tc <= T; tc++) {
            int g = 0;
            st = new StringTokenizer(br.readLine());
            for (int value : GANDALF) {
                g += Integer.parseInt(st.nextToken()) * value;
            }
            int s = 0;
            st = new StringTokenizer(br.readLine());
            for (int value : SAURON) {
                s += Integer.parseInt(st.nextToken()) * value;
            }

            if (g > s) {
                ans(tc, -1);
            } else if (g < s) {
                ans(tc, 1);
            } else {
                ans(tc, 0);
            }

        }
    }

    static void ans(int n, int f) {
        switch (f) {
            case -1:
                System.out.println("Battle " + n + ": Good triumphs over Evil");
                break;
            case 0:
                System.out.println("Battle " + n + ": No victor on this battle field");
                break;
            case 1:
                System.out.println("Battle " + n + ": Evil eradicates all trace of Good");
                break;
        }
    }
}


