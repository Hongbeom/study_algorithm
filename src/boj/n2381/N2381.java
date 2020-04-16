package boj.n2381;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N2381 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int pMax = Integer.MIN_VALUE;
        int pMin = Integer.MAX_VALUE;

        int sMax = Integer.MIN_VALUE;
        int sMin = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            pMax = Math.max(pMax, x + y);
            pMin = Math.min(pMin, x + y);

            sMax = Math.max(sMax, x - y);
            sMin = Math.min(sMin, x - y);
        }

        System.out.println(Math.max(Math.abs(pMax - pMin), Math.abs(sMax - sMin)));

    }

}
