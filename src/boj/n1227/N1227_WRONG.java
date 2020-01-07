package boj.n1227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N1227_WRONG {

    static final int[] H = new int[]{-1, 1, 0, 0};
    static final int[] W = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int b = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());


        boolean[][] visited = new boolean[2 * s + 1][2 * s + 1];

        visited[s][s] = true;

        for (int i = 0; i < b; i++) {

            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) + s;
            int y = Integer.parseInt(st.nextToken()) + s;
            visited[x][y] = true;

        }

        int[] init = new int[]{s, s, 0};
        int sanguen = 0;
        int jungin = 0;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(init);
        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            int x = current[0];
            int y = current[1];
            int step = current[2];

            int check = step % 2;

            if (step <= s) {
                if (check == 0) {
                    sanguen++;
                } else {
                    jungin++;
                }

                if (step == s) {
                    continue;
                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + H[i];
                int ny = y + W[i];

                if (visited[nx][ny]) {
                    continue;
                }
                visited[nx][ny] = true;
                queue.add(new int[]{nx, ny, step + 1});
            }
        }

        System.out.println(sanguen + " " + jungin);
    }
}
