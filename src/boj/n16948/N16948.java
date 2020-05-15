package boj.n16948;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N16948 {

    static int[] H = new int[]{-2, -2, 0, 0, 2, 2};
    static int[] W = new int[]{-1, 1, -2, 2, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int r1 = Integer.parseInt(st.nextToken());
        int c1 = Integer.parseInt(st.nextToken());

        int r2 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());

        System.out.println(bfs(n, r1, c1, r2, c2));
    }

    static int bfs(int n, int r1, int c1, int r2, int c2) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        int step = -1;
        queue.add(new int[]{r1, c1, 0});
        visited[r1][c1] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            int a = current[0];
            int b = current[1];
            int s = current[2];

            for (int i = 0; i < H.length; i++) {
                int na = a + H[i];
                int nb = b + W[i];

                if (na < 0 || nb < 0 || na >= visited.length || nb >= visited[0].length) {
                    continue;
                }
                if (na == r2 && nb == c2) {
                    if (step == -1) {
                        step = s + 1;
                    } else {
                        step = Math.min(step, s + 1);
                    }
                    continue;
                }

                if (visited[na][nb]) {
                    continue;
                }
                visited[na][nb] = true;

                queue.add(new int[]{na, nb, s + 1});
            }
        }
        return step;
    }
}
