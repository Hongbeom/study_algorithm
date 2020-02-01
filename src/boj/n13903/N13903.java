package boj.n13903;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class N13903 {

    static int[] H;
    static int[] W;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[][] block = new int[r][c];
        List<int[]> firstRow = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                block[i][j] = Integer.parseInt(st.nextToken());
                if (i == 0 && block[i][j] == 1) {
                    firstRow.add(new int[]{i, j, 0});
                }
            }
        }

        int n = Integer.parseInt(br.readLine());

        H = new int[n];
        W = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            H[i] = Integer.parseInt(st.nextToken());
            W[i] = Integer.parseInt(st.nextToken());
        }

        int answer = bfs(block, firstRow);

        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }


    }

    static int bfs(int[][] block, List<int[]> init) {
        Queue<int[]> queue = new LinkedList<>(init);
        boolean[][] visited = new boolean[block.length][block[0].length];

        for (int[] start : init) {
            visited[start[0]][start[1]] = true;
        }

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            int a = current[0];
            int b = current[1];
            int s = current[2];

            for (int i = 0; i < H.length; i++) {
                int na = a + H[i];
                int nb = b + W[i];


                if (na < 0 || nb < 0 || na >= block.length || nb >= block[0].length) {
                    continue;
                }

                if (visited[na][nb]) {
                    continue;
                }

                if (block[na][nb] == 1) {
                    if (na == block.length - 1) {
                        return s + 1;
                    }
                    visited[na][nb] = true;
                    queue.add(new int[]{na, nb, s + 1});
                }

            }
        }

        return Integer.MAX_VALUE;
    }
}
