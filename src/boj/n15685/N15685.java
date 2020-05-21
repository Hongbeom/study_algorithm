package boj.n15685;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class N15685 {

    static final int[] H = new int[]{0, -1, 0, 1};
    static final int[] W = new int[]{1, 0, -1, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;

        boolean[][] map = new boolean[101][101];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            List<Integer> base = new ArrayList<>();
            base.add(d);
            map[h][w] = true;
            h += H[d];
            w += W[d];
            map[h][w] = true;

            for (int j = 1; j <= g; j++) {
                List<Integer> move = new ArrayList<>();
                for (int id = base.size() - 1; id >= 0; id--) {
                    int direct = (base.get(id) + 1) % 4;
                    h += H[direct];
                    w += W[direct];
                    map[h][w] = true;
                    move.add(direct);
                }
                base.addAll(move);
            }


        }

        System.out.println(check(map));

    }

    static int check(boolean[][] map) {
        int cnt = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (!map[i][j]) {
                    continue;
                }
                for (int k = 1; k <= Math.min(map.length - 1 - i, map[0].length - 1 - j); j++) {
                    if (map[i][j] && map[i + k][j] && map[i][j + k] && map[i + k][j + k]) {
                        cnt++;
                    }
                }
            }
        }

        return cnt;
    }
}
