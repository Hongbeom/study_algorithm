package boj.n12968;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N12968 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        if (r % 2 == 1 && c % 2 == 1) {
            if (k == 1) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        } else {
            System.out.println(1);
        }

    }
}
