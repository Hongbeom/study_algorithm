package boj.n1783;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N1783 {

    private static final int[] H = new int[]{-2, 2, 1, -1};
    private static final int[] W = new int[]{1, 1, 2, 2};

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());


        if (n > 2 && m > 6) {
            System.out.println(m - 2);
            return;
        }

        int a = n - 1;
        int b = 0;
        int answer = 1;
        root:
        while (true) {
            for (int i = 0; i < 4; i++) {
                int na = a + H[i];
                int nb = b + W[i];

                if (na < 0 || na >= n) {
                    continue;
                }

                if (nb >= m) {
                    break root;
                }

                if (++answer == 4) {
                    break root;
                }
                a = na;
                b = nb;
                continue root;
            }
            break;
        }
        System.out.println(answer);
    }


}
