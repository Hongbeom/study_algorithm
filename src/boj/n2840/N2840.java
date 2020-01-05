package boj.n2840;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class N2840 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[26];

        char[] circle = new char[n];
        Arrays.fill(circle, '?');

        int current = 0;
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = (Integer.parseInt(st.nextToken())) % n;
            char c = st.nextToken().charAt(0);

            current = (current + (n - r)) % n;

            if (circle[current] != '?' && circle[current] != c) {
                System.out.println("!");
                return;
            }


            if (circle[current] == '?' && visited[(int) c - 65]) {
                System.out.println("!");
                return;
            }

            visited[(int) c - 65] = true;
            circle[current] = c;
        }

        for (int i = 0; i < circle.length; i++) {
            int id = (i + current) % n;
            System.out.print(circle[id]);
        }

    }
}
