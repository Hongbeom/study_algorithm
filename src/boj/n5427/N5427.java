package boj.n5427;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N5427 {

    static final int[] H = new int[]{-1, 1, 0, 0};
    static final int[] W = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < TC; tc++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            char[][] map = new char[h][w];
            boolean[][] visited = new boolean[h][w];
            Queue<int[]> queue = new LinkedList<>();
            int sh = -1;
            int sw = -1;
            for (int i = 0; i < map.length; i++) {
                map[i] = br.readLine().toCharArray();
                for (int j = 0; j < map[0].length; j++) {
                    if (map[i][j] == '@') {
                        sh = i;
                        sw = j;
                        visited[i][j] = true;
                    } else if (map[i][j] == '*') {
                        // fire
                        queue.add(new int[]{i, j, 0, 0});
                    }
                }
            }
            // person
            queue.add(new int[]{sh, sw, 0, 1});

            int answer = -1;
            root:
            while (!queue.isEmpty()) {
                int[] current = queue.poll();

                int a = current[0];
                int b = current[1];
                int time = current[2];
                int isPerson = current[3];

                for (int i = 0; i < 4; i++) {
                    int na = a + H[i];
                    int nb = b + W[i];

                    if (na < 0 || nb < 0 || na >= map.length || nb >= map[0].length) {
                        if (isPerson == 1) {
                            answer = time + 1;
                            break root;
                        }
                        continue;
                    }

                    // 벽인 경우
                    if (map[na][nb] == '#' || map[na][nb] == '*') {
                        continue;
                    }

                    if (isPerson == 0) {
                        map[na][nb] = '*';
                    } else {
                        if (visited[na][nb]) {
                            continue;
                        }
                        visited[na][nb] = true;
                    }
                    queue.add(new int[]{na, nb, time + 1, isPerson});
                }
            }
            if (answer == -1) {
                System.out.println("IMPOSSIBLE");
            } else {
                System.out.println(answer);
            }

        }
    }
}
