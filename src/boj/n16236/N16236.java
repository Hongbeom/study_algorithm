package boj.n16236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N16236 {


    static final int[] H = new int[]{-1, 1, 0, 0};
    static final int[] W = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] map = new int[n][n];
        StringTokenizer st;
        BabyShark baby = null;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    baby = new BabyShark(i, j);
                    map[i][j] = 0;
                }
            }
        }

        while (bfs(map, baby)) ;

        System.out.println(baby.step);


    }


    static boolean bfs(int[][] map, BabyShark baby) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[map.length][map[0].length];
        visited[baby.i][baby.j] = true;

        int[] eat = null;
        queue.add(new int[]{baby.i, baby.j, 0});
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

                // 못먹는 상황
                if (map[na][nb] > baby.size) {
                    continue;
                }

                visited[na][nb] = true;

                // 먹을 수 있는 상황
                if (map[na][nb] > 0 && map[na][nb] < baby.size) {
                    if (eat == null) {
                        eat = new int[]{na, nb, step + 1};
                    } else {
                        if (eat[2] == step + 1) {
                            if (eat[0] > na) {
                                eat[0] = na;
                                eat[1] = nb;
                                eat[2] = step + 1;
                            } else if (eat[0] == na) {
                                if (eat[1] > nb) {
                                    eat[0] = na;
                                    eat[1] = nb;
                                    eat[2] = step + 1;
                                }
                            }
                        }
                    }
                    continue;
                }
                queue.add(new int[]{na, nb, step + 1});
            }
        }

        if (eat == null) {
            return false;
        }
        map[eat[0]][eat[1]] = 0;
        baby.eat(eat[0], eat[1], eat[2]);

        return true;
    }

    static class BabyShark {
        int i;
        int j;
        int size;
        int eaten;
        int step;

        BabyShark(int i, int j) {
            this.i = i;
            this.j = j;
            this.size = 2;
            this.eaten = 0;
            this.step = 0;
        }

        void eat(int i, int j, int step) {
            this.i = i;
            this.j = j;
            this.step += step;
            if (++eaten == size) {
                eaten = 0;
                size += 1;
            }
        }
    }
}
