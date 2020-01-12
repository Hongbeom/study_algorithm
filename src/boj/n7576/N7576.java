package boj.n7576;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N7576 {

    static final int[] H = new int[]{-1, 1, 0, 0};
    static final int[] W = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[][] box = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> queue = new LinkedList<>();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());

                if (box[i][j] == 1) {
                    queue.add(new int[]{i, j, 0});
                    visited[i][j] = true;
                } else if (box[i][j] == -1) {
                    visited[i][j] = true;
                } else {
                    cnt++;
                }
            }
        }
        if (cnt == 0) {
            System.out.println(0);
            return;
        }
        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            int a = current[0];
            int b = current[1];
            int step = current[2];

            for (int i = 0; i < 4; i++) {
                int na = a + H[i];
                int nb = b + W[i];

                if (na < 0 || nb < 0 || na >= box.length || nb >= box[0].length) {
                    continue;
                }

                if (visited[na][nb]) {
                    continue;
                }

                visited[na][nb] = true;
                cnt--;

                if (cnt == 0) {
                    System.out.println(step + 1);
                    return;
                }

                queue.add(new int[]{na, nb, step + 1});
            }
        }

        System.out.println(-1);

    }
}
