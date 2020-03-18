package boj.n14575;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N14575 {
    static int[][] LIMIT;
    static int T;
    static int LOW_MAX;
    static int HIGH_MAX;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        LIMIT = new int[n][2];

        int lowSum = 0;
        int highSum = 0;

        LOW_MAX = Integer.MIN_VALUE;
        HIGH_MAX = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            LIMIT[i][0] = Integer.parseInt(st.nextToken());
            LIMIT[i][1] = Integer.parseInt(st.nextToken());
            lowSum += LIMIT[i][0];
            highSum += LIMIT[i][1];

            LOW_MAX = Math.max(LOW_MAX, LIMIT[i][0]);
            HIGH_MAX = Math.max(HIGH_MAX, LIMIT[i][1]);
        }

        if (T < lowSum || T > highSum) {
            System.out.println(-1);
            return;
        }
        System.out.println(bs(LOW_MAX, HIGH_MAX));

    }

    static int bs(int left, int right) {

        int mid = (left + right) / 2;

        int value = getValue(mid);

        if (value >= T) {
            if (mid == LOW_MAX || getValue(mid - 1) < T) {
                return mid;
            }
            return bs(left, mid);

        } else {
            return bs(mid + 1, right);
        }
    }

    static int getValue(int s) {
        int value = 0;
        for (int[] limit : LIMIT) {
            int high = limit[1];
            value += Math.min(high, s);
        }
        return value;
    }
}
