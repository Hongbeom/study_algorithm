package boj.n2151;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class N2151 {

    static int[] H = new int[]{-1, 1, 0, 0};
    static int[] W = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[][] home = new char[n][n];

        Queue<int[]> queue = new LinkedList<>();
        int[] end = new int[2];
        for (int i = 0; i < n; i++) {
            home[i] = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                if (home[i][j] == '#') {
                    if (queue.isEmpty()) {
                        queue = new LinkedList<>();
                        for (int d = 0; d < 4; d++) {
                            queue.add(new int[]{i, j, d, 0});
                        }
                    } else {
                        end[0] = i;
                        end[1] = j;
                    }
                }
            }
        }
        System.out.println(bfs(home, queue, end));
    }

    static int bfs(char[][] home, Queue<int[]> queue, int[] end) {

        int[][][] visited = new int[home.length][home[0].length][4];
        for (int[][] ints : visited) {
            for (int j = 0; j < visited[0].length; j++) {
                Arrays.fill(ints[j], Integer.MAX_VALUE);
            }
        }
        int mirror = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int a = current[0];
            int b = current[1];
            int d = current[2];
            int m = current[3];

            int na = a + H[d];
            int nb = b + W[d];

            if (na < 0 || nb < 0 || na >= home.length || nb >= home[0].length) {
                continue;
            }

            if (na == end[0] && nb == end[1]) {
                mirror = Math.min(mirror, m);
                continue;
            }

            if (home[na][nb] == '*' || visited[na][nb][d] < m) {
                continue;
            }

            visited[na][nb][d] = m;
            queue.add(new int[]{na, nb, d, m});
            if (home[na][nb] == '!') {
                queue.add(new int[]{na, nb, convert(d, true), m + 1});
                queue.add(new int[]{na, nb, convert(d, false), m + 1});
            }

        }
        return mirror;
    }

    static int convert(int d, boolean md) {
        int nd = 0;
        switch (d) {
            case 0:
                nd = md ? 2 : 3;
                break;
            case 1:
                nd = md ? 3 : 2;
                break;
            case 2:
                nd = md ? 0 : 1;
                break;
            case 3:
                nd = md ? 1 : 0;
                break;
        }
        return nd;
    }

}
