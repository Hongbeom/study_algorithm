package boj.n3050;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N3050 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        char[][] apart = new char[r][c];

        int[][] blocks = new int[r][c];

        for (int i = 0; i < r; i++) {
            apart[i] = br.readLine().toCharArray();
            for (int j = 0; j < c; j++) {
                if (apart[i][j] == 'X') {
                    blocks[i][j] = 1;
                } else {
                    blocks[i][j] = 0;
                }

                if (j != 0) {
                    blocks[i][j] += blocks[i][j - 1];
                }
            }

        }

        int answer = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(apart[i][j] != 'X'){
                    answer = Math.max(answer, check(blocks, apart, i, j));
                }
            }
        }

        System.out.println(answer);


    }

    static int check(int[][] blocks, char[][] apart, int h, int w) {

        int re = 3;
        int width = 1;
        int height = 1;
        for (int c = blocks[0].length - 1; c >= w; c--) {
            if (blocks[h][c] - blocks[h][w] == 0) {
                width = c - w + 1;
                break;
            }
        }
        re = Math.max(re, 2 * width + 2 * height - 1);
        for (int r = h + 1; r < blocks.length; r++) {
            if (apart[r][w] == 'X') {
                break;
            }

            for (int c = blocks[0].length - 1; c >= w; c--) {
                if (blocks[r][c] - blocks[r][w] == 0) {
                    width = Math.min(width, c - w + 1);
                    re = Math.max((r - h + 1) * 2 + 2 * width - 1, re);
                    break;
                }
            }
        }

        return re;


    }

}
