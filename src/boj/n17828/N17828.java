package boj.n17828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N17828 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        int limit = n * 26;
        if (limit < x || x < n) {
            System.out.println('!');
            return;
        }

        StringBuilder sb = new StringBuilder();
        int left = limit - x;
        for (int i = 0; i < n; i++) {
            if (left - 25 >= 0) {
                sb.append('A');
                left -= 25;
            } else if (left > 0) {
                sb.append((char) (90 - left));
                left = 0;
            } else {
                sb.append('Z');
            }
        }
        System.out.println(sb.toString());
    }
}
