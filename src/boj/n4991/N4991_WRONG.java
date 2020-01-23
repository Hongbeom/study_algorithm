package boj.n4991;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class N4991_WRONG {

    static final int[] H = new int[]{-1, 1, 0, 0};
    static final int[] W = new int[]{0, 0, -1, 1};


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {

            StringTokenizer st = new StringTokenizer(br.readLine().trim());

            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            if (w == 0 && h == 0) {
                return;
            }

            int[] start = new int[2];
            List<int[]> dustList = new ArrayList<>();
            char[][] map = new char[h][w];

            for (int i = 0; i < h; i++) {
                char[] tmp = br.readLine().toCharArray();
                for (int j = 0; j < w; j++) {
                    map[i][j] = tmp[j];

                    if (map[i][j] == 'o') {
                        start[0] = i;
                        start[1] = j;
                    } else if (map[i][j] == '*') {
                        dustList.add(new int[]{i, j});
                    }
                }
            }
            dustList.add(0, start);
            List<int[]> candidates = new ArrayList<>();
            getCandidate(candidates, new int[dustList.size()], new int[dustList.size()], 0);
            int answer = -1;

            candidate:
            for (int[] candidate : candidates) {
                int distance = 0;

                for (int i = 0; i < candidate.length - 1; i++) {
                    int[] p1 = dustList.get(candidate[i]);
                    int[] p2 = dustList.get(candidate[i + 1]);

                    int d = bfs(map, p1, p2);

                    if (d == -1) {
                        continue candidate;
                    }
                    distance += d;
                }

                if (answer == -1) {
                    answer = distance;
                } else {
                    answer = Math.min(answer, distance);
                }

            }

            System.out.println(answer);
        }


    }

    static int bfs(char[][] map, int[] p1, int[] p2) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[map.length][map[0].length];
        visited[p1[0]][p1[1]] = true;
        queue.add(new int[]{p1[0], p1[1], 0});

        while (!queue.isEmpty()) {

            int[] current = queue.poll();
            int a = current[0];
            int b = current[1];
            int step = current[2];

            for (int i = 0; i < 4; i++) {
                int na = a + H[i];
                int nb = b + W[i];

                if (na < 0 || nb < 0 || na >= map.length || nb >= map[0].length || visited[na][nb]) {
                    continue;
                }

                if (na == p2[0] && nb == p2[1]) {
                    return step + 1;
                }

                if (map[na][nb] != 'x') {
                    visited[na][nb] = true;
                    queue.add(new int[]{na, nb, step + 1});
                }

            }
        }

        return -1;

    }

    static void getCandidate(List<int[]> list, int[] tm, int[] check, int start) {

        if (start == tm.length) {
            list.add(tm);
        }

        if (start == 0) {
            check[start]--;
            tm[start] = 0;
            getCandidate(list, tm, check, start + 1);
        } else {
            for (int i = 0; i < tm.length; i++) {
                if (check[i] == 0) {
                    check[i]--;
                    tm[start] = i;
                    getCandidate(list, tm.clone(), check.clone(), start + 1);
                    check[i]++;
                }
            }
        }
    }
}
