package boj.n17142;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class N17142 {

    static final int[] H = new int[]{-1, 1, 0, 0};
    static final int[] W = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][n];
        List<int[]> virusList = new ArrayList<>();

        int cnt = 0;
        for (int i = 0; i < map.length; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    cnt++;
                } else if (map[i][j] == 2) {
                    virusList.add(new int[]{i, j, 0});
                }
            }
        }

        if (cnt == 0) {
            System.out.println(0);
            return;
        }

        List<int[]> candidates = new ArrayList<>();
        createCase(candidates, new int[m], 0, 0, virusList.size());

        int answer = Integer.MAX_VALUE;
        for (int[] candidate : candidates) {
            answer = Math.min(answer, bfs(virusList, candidate, map, cnt));
        }

        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }

        System.out.println(answer);
    }

    static int bfs(List<int[]> virusList, int[] candidate, int[][] map, int cnt) {
        boolean[][] visited = new boolean[map.length][map[0].length];
        Queue<int[]> queue = new LinkedList<>();
        for (int id : candidate) {
            int[] virus = virusList.get(id);
            queue.add(virus);
            visited[virus[0]][virus[1]] = true;
        }
        int time = Integer.MAX_VALUE;
        root:
        while (!queue.isEmpty()) {

            int[] current = queue.poll();
            int a = current[0];
            int b = current[1];
            int t = current[2];

            for (int i = 0; i < 4; i++) {
                int na = a + H[i];
                int nb = b + W[i];

                if (na < 0 || nb < 0 || na >= map.length || nb >= map[0].length) {
                    continue;
                }

                if (visited[na][nb] || map[na][nb] == 1) {
                    continue;
                }

                if (map[na][nb] == 0) {
                    if (--cnt == 0) {
                        time = t + 1;
                        break root;
                    }
                }
                visited[na][nb] = true;
                queue.add(new int[]{na, nb, t + 1});
            }
        }

        return time;
    }


    static void createCase(List<int[]> list, int[] tm, int step, int before, int m) {

        if (step == tm.length) {
            list.add(tm);
            return;
        }

        for (int i = before; i < m; i++) {
            tm[step] = i;
            createCase(list, tm.clone(), step + 1, i + 1, m);
        }
    }
}
