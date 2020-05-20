package boj.n15683;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class N15683 {

    static final int[] H = new int[]{-1, 1, 0, 0};
    static final int[] W = new int[]{0, 0, -1, 1};
    static List<int[]> CCTV;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        CCTV = new ArrayList<>();
        int[][] map = new int[n][m];

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] > 0 && map[i][j] < 6) {
                    CCTV.add(new int[]{i, j, map[i][j]});
                } else if (map[i][j] == 0) {
                    cnt++;
                }
            }
        }

        int answer = cnt;

        List<int[]> candidates = new ArrayList<>();


        createCase(candidates, new int[CCTV.size()], 0);

        for (int[] candidate : candidates) {

            Queue<int[]> queue = new LinkedList<>();
            for (int i = 0; i < candidate.length; i++) {
                int[] cctv = CCTV.get(i);
                int angle = candidate[i];
                int h = cctv[0];
                int w = cctv[1];
                switch (cctv[2]) {
                    case 1:
                        if (angle == 0) {
                            queue.add(new int[]{h, w, 3});
                        } else if (angle == 1) {
                            queue.add(new int[]{h, w, 1});
                        } else if (angle == 2) {
                            queue.add(new int[]{h, w, 2});
                        } else {
                            queue.add(new int[]{h, w, 0});
                        }
                        break;
                    case 2:
                        if (angle == 0) {
                            queue.add(new int[]{h, w, 2});
                            queue.add(new int[]{h, w, 3});
                        } else {
                            queue.add(new int[]{h, w, 0});
                            queue.add(new int[]{h, w, 1});
                        }
                        break;

                    case 3:
                        if (angle == 0) {
                            queue.add(new int[]{h, w, 0});
                            queue.add(new int[]{h, w, 3});
                        } else if (angle == 1) {
                            queue.add(new int[]{h, w, 3});
                            queue.add(new int[]{h, w, 1});
                        } else if (angle == 2) {
                            queue.add(new int[]{h, w, 1});
                            queue.add(new int[]{h, w, 2});
                        } else {
                            queue.add(new int[]{h, w, 2});
                            queue.add(new int[]{h, w, 0});
                        }
                        break;
                    case 4:
                        if (angle == 0) {
                            queue.add(new int[]{h, w, 2});
                            queue.add(new int[]{h, w, 0});
                            queue.add(new int[]{h, w, 3});
                        } else if (angle == 1) {
                            queue.add(new int[]{h, w, 0});
                            queue.add(new int[]{h, w, 3});
                            queue.add(new int[]{h, w, 1});
                        } else if (angle == 2) {
                            queue.add(new int[]{h, w, 3});
                            queue.add(new int[]{h, w, 1});
                            queue.add(new int[]{h, w, 2});
                        } else {
                            queue.add(new int[]{h, w, 1});
                            queue.add(new int[]{h, w, 2});
                            queue.add(new int[]{h, w, 0});
                        }
                        break;
                    case 5:
                        queue.add(new int[]{h, w, 0});
                        queue.add(new int[]{h, w, 1});
                        queue.add(new int[]{h, w, 2});
                        queue.add(new int[]{h, w, 3});
                        break;
                }
            }

            answer = Math.min(answer, bfs(queue, map, cnt));

        }

        System.out.println(answer);
    }

    static int bfs(Queue<int[]> queue, int[][] map, int cnt) {
        boolean[][] visited = new boolean[map.length][map[0].length];

        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            int na = current[0] + H[current[2]];
            int nb = current[1] + W[current[2]];

            if (na < 0 || nb < 0 || na >= map.length || nb >= map[0].length) {
                continue;
            }

            if (map[na][nb] == 6) {
                continue;
            }

            if (map[na][nb] == 0) {
                if (!visited[na][nb]) {
                    cnt--;
                    visited[na][nb] = true;
                }
            }

            queue.add(new int[]{na, nb, current[2]});
        }

        return cnt;
    }

    static void createCase(List<int[]> list, int[] tm, int step) {

        if (step == tm.length) {
            list.add(tm);
            return;
        }

        switch (CCTV.get(step)[2]) {
            case 2:
                tm[step] = 0;
                createCase(list, tm.clone(), step + 1);
                tm[step] = 1;
                createCase(list, tm, step + 1);
                break;
            case 1:
            case 3:
            case 4:
                tm[step] = 0;
                createCase(list, tm.clone(), step + 1);
                tm[step] = 1;
                createCase(list, tm.clone(), step + 1);
                tm[step] = 2;
                createCase(list, tm.clone(), step + 1);
                tm[step] = 3;
                createCase(list, tm, step + 1);
                break;
            case 5:
                tm[step] = -1;
                createCase(list, tm, step + 1);
                break;
        }


    }
}

