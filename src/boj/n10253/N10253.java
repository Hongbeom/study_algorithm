package boj.n10253;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N10253 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int tc = 0; tc < TC; tc++) {
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            System.out.println(solve(a, b));
        }
    }


    static long solve(long a, long b) {

        if (b % a == 0) {
            return b / a;
        }

        long n = b / a + 1;

        a = a * n - b;
        b = b * n;

        long gcd = gcd(b, a);

        a /= gcd;
        b /= gcd;
        return solve(a, b);
    }

    static long gcd(long a, long b) {

        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }
}
