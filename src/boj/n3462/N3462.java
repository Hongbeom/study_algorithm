package boj.n3462;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N3462 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int d = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int i = 0; i < d; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            long a = n - 1 - m / 2;
            long b = (m - 1) / 2;

            long c = a - b;

            long a2 = 0;
            while (a > 0) {
                a /= 2;
                a2 += a;
            }

            long bc2 = 0;
            while (b > 0) {
                b /= 2;
                bc2 += b;
            }

            while (c > 0) {
                c /= 2;
                bc2 += c;
            }
            if (a2 > bc2) {
                System.out.println(0);
            } else {
                System.out.println(1);
            }

        }

    }

}
