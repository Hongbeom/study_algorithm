package boj.n14499;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N14499 {

    static final int[] H = new int[]{0, 0, 0, -1, 1};
    static final int[] W = new int[]{0, 1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[][] dice = new int[4][3];
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());


        int[][] input = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                input[i][j] = Integer.parseInt(st.nextToken());
                if (i == a && j == b) {
                    dice[3][1] = input[i][j];
                }
            }
        }

        st = new StringTokenizer(br.readLine());

        for(int q = 0;  q < k; q++){
            int d = Integer.parseInt(st.nextToken());

            if (0 > a + H[d] || a + H[d] >= n || 0 > b + W[d] || b + W[d] >= m) {
                continue;
            } else {
                a += H[d];
                b += W[d];
            }
            int tm;
            switch (d) {
                case 1:
                    tm = dice[1][2];
                    System.arraycopy(dice[1], 0, dice[1], 1, 2);
                    dice[1][0] = dice[3][1];
                    dice[3][1] = tm;
                    break;

                case 2:
                    tm = dice[1][0];
                    System.arraycopy(dice[1], 1, dice[1], 0, 2);
                    dice[1][2] = dice[3][1];
                    dice[3][1] = tm;
                    break;
                case 3:
                    tm = dice[0][1];
                    for (int i = 1; i < 4; i++) {
                        dice[i - 1][1] = dice[i][1];
                    }
                    dice[3][1] = tm;
                    break;

                case 4:
                    tm = dice[3][1];
                    for (int i = 2; i >= 0; i--) {
                        dice[i + 1][1] = dice[i][1];
                    }
                    dice[0][1] = tm;
                    break;
            }
            if (input[a][b] == 0) {
                input[a][b] = dice[3][1];
            } else {
                dice[3][1] = input[a][b];
                input[a][b] = 0;
            }
            System.out.println(dice[1][1]);
        }

    }
}
