package boj.n15686;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class N15686 {

    static int[] H = new int[]{-1, 1, 0, 0};
    static int[] W = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<int[]> home = new ArrayList<>();
        List<int[]> chicken = new ArrayList<>();

        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    home.add(new int[]{i, j, 0});
                } else if (map[i][j] == 2) {
                    chicken.add(new int[]{i, j});
                }
            }
        }

        List<int[]> candidates = new ArrayList<>();
        candidates(candidates, new int[m], 0, 0, chicken.size());
        int answer = Integer.MAX_VALUE;
        for (int[] can : candidates) {
            int[][] nmap = new int[n][n];

            for (int id : can) {
                // 치킨집 표시
                int[] spot = chicken.get(id);
                nmap[spot[0]][spot[1]] = 2;
            }
            int distance = 0;
            for (int[] h : home) {
                distance += bfs(nmap, h);
            }

            answer = Math.min(distance, answer);

        }

        System.out.println(answer);


    }

    static int bfs(int[][] map, int[] init) {
        boolean[][] visited = new boolean[map.length][map[0].length];
        visited[init[0]][init[1]] = true;
        Queue<int[]> queue = new LinkedList<>();

        queue.add(init);
        int distance = 0;
        root:
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int a = current[0];
            int b = current[1];
            int step = current[2];

            for (int i = 0; i < 4; i++) {
                int na = a + H[i];
                int nb = b + W[i];

                if (na < 0 || nb < 0 || na >= map.length || nb >= map[0].length) {
                    continue;
                }

                if (visited[na][nb]) {
                    continue;
                }

                if (map[na][nb] == 2) {
                    distance = step + 1;
                    break root;
                }

                visited[na][nb] = true;
                queue.add(new int[]{na, nb, step + 1});
            }
        }
        return distance;
    }

    static void candidates(List<int[]> list, int[] tm, int start, int value, int max) {


        if (start == tm.length) {
            list.add(tm);
            return;
        }

        if (value == max) {
            return;
        }


        tm[start] = value;
        candidates(list, tm.clone(), start + 1, value + 1, max);
        candidates(list, tm.clone(), start, value + 1, max);
    }
}
