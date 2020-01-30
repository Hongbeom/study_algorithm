package boj.n9274;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N9274_WRONG {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String in;
        StringTokenizer st;
        while ((in = br.readLine()) != null) {
            st = new StringTokenizer(in);
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            char[][] map = new char[n][m];
            boolean[][] checked = new boolean[n][m];

            for (int i = 0; i < n; i++) {
                map[i] = br.readLine().toCharArray();
            }

            int answer = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == '.') {
                        continue;
                    }

                    if (checked[i][j]) {
                        continue;
                    }

                    check(checked, i, j, h, w);
                    answer++;
                }
            }
            System.out.println();
            for (boolean[] cc : checked) {
                for (boolean c : cc) {
                    if (c) {
                        System.out.print("X");
                    } else {
                        System.out.print(".");
                    }
                }
                System.out.println();
            }
            System.out.println(answer);

        }
    }

    static void check(boolean[][] checked, int a, int b, int h, int w) {

        int hEnd;
        int wEnd;

        if (a + h > checked.length) {
            hEnd = checked.length;
        } else {
            hEnd = a + h;
        }

        if (b + w > checked[0].length) {
            wEnd = checked[0].length;
        } else {
            wEnd = b + w;
        }


        for (int i = a; i < hEnd; i++) {
            for (int j = b; j < wEnd; j++) {
                checked[i][j] = true;
            }
        }
    }

}
