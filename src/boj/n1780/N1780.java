package boj.n1780;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N1780 {

    static int PN = 0;
    static int P0 = 0;
    static int PP = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] paper = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] tmp = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                paper[i][j] = Integer.parseInt(tmp[j]);
            }
        }

        count(paper, 0, 0, n);
        System.out.println(PN);
        System.out.println(P0);
        System.out.println(PP);


    }

    static void count(int[][] paper, int h, int w, int n) {
        int init = paper[h][w];

        for (int i = h; i < h + n; i++) {

            for (int j = w; j < w + n; j++) {

                if (init != paper[i][j]) {
                    n /= 3;
                    count(paper, h, w, n);
                    count(paper, h, w + n, n);
                    count(paper, h, w + 2 * n, n);
                    count(paper, h + n, w, n);
                    count(paper, h + n, w + n, n);
                    count(paper, h + n, w + 2 * n, n);
                    count(paper, h + 2 * n, w, n);
                    count(paper, h + 2 * n, w + n, n);
                    count(paper, h + 2 * n, w + 2 * n, n);
                    return;
                }

            }
        }

        if (init == 1) {
            PP++;
        } else if (init == 0) {
            P0++;
        } else {
            PN++;
        }

    }
}
