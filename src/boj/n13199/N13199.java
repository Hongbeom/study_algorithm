package boj.n13199;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N13199 {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int tc = 0; tc < TC; tc++) {
            st = new StringTokenizer(br.readLine());

            int p = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            int moneyChicken = m / p;

            int coupon = moneyChicken * c;

            int doYoung = moneyChicken + (coupon / f);

            int sangUn = moneyChicken;

            if (coupon - f >= 0) {
                sangUn += (coupon - f) / (f - c) + 1;
            }

            System.out.println(sangUn - doYoung);

        }
    }
}
