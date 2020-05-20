package boj.n14503;

import java.io.*;
import java.util.*;

public class N14503 {

    static final int[] H = new int[] {-1, 0, 1, 0};
    static final int[] W = new int[] {0, 1, 0, -1};

    static final int[] BH = new int[] {1, 0, -1, 0};
    static final int[] BW = new int[] {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        int answer = 1;

        boolean[][] cleaned = new boolean[n][m];
        cleaned[r][c] = true;

        for(int i = 0; i < map.length; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < map[0].length; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        root:
        while(true) {

            for(int i = 0; i < 4; i++) {
                d = (d + 3) % 4;

                int nr = r + H[d];
                int nc = c + W[d];


                if(nr < 0 || nc < 0 || nr >= map.length || nc >= map[0].length) {
                    continue;
                }

                if(cleaned[nr][nc] || map[nr][nc] == 1) {
                    continue;
                }

                r = nr;
                c = nc;
                cleaned[r][c] = true;
                answer++;
                continue root;
            }

            // 후진 검사 여기서 진행.
            int bh = r + BH[d];
            int bw = c + BW[d];

            if(bh < 0 || bw < 0 || bh >= map.length || bw >= map[0].length || map[bh][bw] == 1) {
                break;
            }

            r = bh;
            c = bw;
        }

        System.out.println(answer);
    }
}

