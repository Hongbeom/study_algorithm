package boj.n2805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N2805 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        long m = Long.parseLong(st.nextToken());

        long max = 0;
        long[] trees = new long[n];
        st = new StringTokenizer(br.readLine());


        for (int i = 0; i < n; i++) {
            trees[i] = Long.parseLong(st.nextToken());
            max = Math.max(max, trees[i]);
        }

        System.out.println(bs(0, max, trees, m));

    }

    static long bs(long start, long end, long[] trees, long target) {

        long middle = (start + end) / 2;
        long[] current = getValue(trees, middle);

        long m = current[0];
        long next = current[1];

        if (m < target) {
            return bs(start, middle, trees, target);
        } else {
            if (next < target) {
                return middle;
            } else {
                return bs(middle + 1, end, trees, target);
            }

        }
    }

    static long[] getValue(long[] trees, long h) {
        long m = 0;
        long next = 0;
        for (long tree : trees) {
            if (tree - h > 0) {
                m += tree - h;
                next += tree - h - 1;
            }
        }
        return new long[]{m, next};
    }
}
