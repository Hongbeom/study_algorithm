package boj.n13136;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N13136 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        long hn;
        long wn;

        if (r % n == 0) {
            hn = r / n;
        } else {
            hn = r / n + 1;
        }

        if (c % n == 0) {
            wn = c / n;
        } else {
            wn = c / n + 1;
        }

        System.out.println(hn * wn);
    }
}
