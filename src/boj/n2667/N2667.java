package boj.n2667;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class N2667 {

    static final int[] H = new int[]{-1, 1, 0, 0};
    static final int[] W = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());


        char[][] home = new char[n][n];
        for (int i = 0; i < n; i++) {
            home[i] = br.readLine().toCharArray();
        }

        boolean[][] visited = new boolean[n][n];
        int cnt = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j]) {
                    continue;
                }
                if (home[i][j] != '0') {
                    cnt++;
                    list.add(bfs(home, visited, i, j));
                }
            }
        }
        Collections.sort(list);
        System.out.println(cnt);
        for (int a : list) {
            System.out.println(a);
        }

    }

    static int bfs(char[][] home, boolean[][] visited, int h, int w) {

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{h, w});
        visited[h][w] = true;
        int cnt = 1;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int a = current[0];
            int b = current[1];

            for (int i = 0; i < 4; i++) {
                int na = a + H[i];
                int nb = b + W[i];

                if (na < 0 || nb < 0 || na >= home.length || nb >= home[0].length || visited[na][nb]) {
                    continue;
                }

                if (home[na][nb] != '0') {
                    cnt++;
                    visited[na][nb] = true;
                    queue.add(new int[]{na, nb});
                }
            }
        }

        return cnt;

    }
}
