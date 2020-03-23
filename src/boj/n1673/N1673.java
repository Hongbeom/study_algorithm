package boj.n1673;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N1673 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String in;
        StringTokenizer st;
        while ((in = br.readLine()) != null) {
            st = new StringTokenizer(in);
            long n = Long.parseLong(st.nextToken());
            long k = Long.parseLong(st.nextToken());
            n *= k;
            n -= k;
            k -= 1;
            System.out.println(n / k + 1);
        }
    }
}
