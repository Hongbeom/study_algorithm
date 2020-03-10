package boj.n3187;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N3187 {

    static final int[] H = new int[]{-1, 1, 0, 0};
    static final int[] W = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        char[][] map = new char[r][c];
        int sheep = 0;
        int wolf = 0;
        for (int i = 0; i < r; i++) {
            map[i] = br.readLine().toCharArray();
        }
        boolean[][] visited = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (visited[i][j]) {
                    continue;
                }

                if (map[i][j] != '#') {
                    int[] result = bfs(map, visited, i, j);
                    sheep += result[0];
                    wolf += result[1];
                }
            }
        }

        System.out.println(sheep + " " + wolf);
    }


    static int[] bfs(char[][] map, boolean[][] visited, int h, int w) {

        Queue<int[]> queue = new LinkedList<>();

        int sheep = 0;
        int wolf = 0;

        if (map[h][w] == 'v') {
            wolf++;
        } else if (map[h][w] == 'k') {
            sheep++;
        }

        visited[h][w] = true;
        queue.add(new int[]{h, w});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int a = current[0];
            int b = current[1];

            for (int i = 0; i < 4; i++) {
                int na = a + H[i];
                int nb = b + W[i];

                if (na < 0 || nb < 0 || na >= map.length || nb >= map[0].length) {
                    continue;
                }

                if (visited[na][nb] || map[na][nb] == '#') {
                    continue;
                }

                if (map[na][nb] == 'v') {
                    wolf++;
                } else if (map[na][nb] == 'k') {
                    sheep++;
                }
                visited[na][nb] = true;
                queue.add(new int[]{na, nb});
            }
        }
        if (sheep > wolf) {
            return new int[]{sheep, 0};
        } else {
            return new int[]{0, wolf};
        }
    }

}
