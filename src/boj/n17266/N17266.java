package boj.n17266;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N17266 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int range = Integer.MIN_VALUE;
        int before = 0;
        int first = 0;
        for (int i = 0; i < m; i++) {
            int x = Integer.parseInt(st.nextToken());
            if (i == 0) {
                before = x;
                first = x;
                continue;
            }
            range = Math.max(range, x - before);
            before = x;
        }
        int mid = range / 2;
        if (range % 2 != 0) {
            mid++;
        }
        System.out.println(max(mid, first, n - before));

    }

    static int max(int a, int b, int c) {
        if (a > b) {
            return Math.max(c, a);
        } else {
            return Math.max(c, b);
        }
    }
}
