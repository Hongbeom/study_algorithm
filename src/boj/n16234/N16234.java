package boj.n16234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class N16234 {

    static final int[] H = new int[]{-1, 1, 0, 0};
    static final int[] W = new int[]{0, 0, -1, 1};
    static int L;
    static int R;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;

        while (true) {
            boolean[][] visited = new boolean[n][n];
            boolean check = false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i][j]) {
                        continue;
                    }
                    check = bfs(map, visited, new int[]{i, j}) || check;
                }
            }

            if (check) {
                answer++;
            } else {
                break;
            }
        }

        System.out.println(answer);


    }


    static boolean bfs(int[][] map, boolean[][] visited, int[] init) {
        Queue<int[]> queue = new LinkedList<>();
        visited[init[0]][init[1]] = true;
        int count = 1;
        int sum = map[init[0]][init[1]];
        List<int[]> change = new ArrayList<>();
        change.add(init);
        queue.add(init);

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int a = current[0];
            int b = current[1];

            int p = map[a][b];

            for (int i = 0; i < 4; i++) {

                int na = a + H[i];
                int nb = b + W[i];

                if (na < 0 || nb < 0 || na >= map.length || nb >= map[0].length || visited[na][nb]) {
                    continue;
                }

                int diff = Math.abs(p - map[na][nb]);
                if (L <= diff && diff <= R) {
                    count++;
                    sum += map[na][nb];
                    visited[na][nb] = true;
                    change.add(new int[]{na, nb});
                    queue.add(new int[]{na, nb});
                }

            }
        }

        if (count != 1) {
            for (int[] spot : change) {
                map[spot[0]][spot[1]] = sum / count;
            }
            return true;
        }
        return false;
    }
}
