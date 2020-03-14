package boj.n3076;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N3076 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        StringBuilder fsb = new StringBuilder();
        StringBuilder ssb = new StringBuilder();

        for (int i = 0; i < c; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < b; j++) {
                    fsb.append("X");
                    ssb.append(".");
                }
            } else {
                for (int j = 0; j < b; j++) {
                    fsb.append(".");
                    ssb.append("X");
                }
            }
        }

        String first = fsb.toString();
        String second = ssb.toString();

        for (int i = 0; i < r; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < a; j++) {
                    System.out.println(first);
                }
            } else {
                for (int j = 0; j < a; j++) {
                    System.out.println(second);
                }
            }

        }

    }
}
