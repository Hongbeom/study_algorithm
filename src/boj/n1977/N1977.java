package boj.n1977;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N1977 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int m = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());

        int sqrtM = (int) Math.sqrt(m);
        int sqrtN = (int) Math.sqrt(n);
        if (Math.pow(sqrtM, 2) < m) {
            sqrtM++;
        }

        int ans = sqrtN - sqrtM + 1;
        if (ans == 0) {
            System.out.println(-1);
        } else {
            int sum = 0;
            for (int i = sqrtM; i <= sqrtN; i++) {
                sum += (int) Math.pow(i, 2);
            }

            System.out.println(sum + "\n" + (int) Math.pow(sqrtM, 2));
        }

    }
}
