package sw.sw2117;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW2117 {

    static final int[] H = new int[]{-1, 1, 0, 0};
    static final int[] W = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int tc = 1; tc <= TC; tc++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[][] city = new int[n][n];

            for (int i = 0; i < city.length; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < city[0].length; j++) {
                    city[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int answer = 0;
            for (int i = 0; i < city.length; i++) {
                for (int j = 0; j < city[0].length; j++) {
                    answer = Math.max(answer, bfs(city, i, j, m));
                }
            }

            System.out.println("#" + tc + " " + answer);
        }
    }

    static int bfs(int[][] city, int h, int w, int m) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{h, w, 1});

        int home = 0;
        int check = 0;
        int maxHome = 0;
        boolean[][] visited = new boolean[city.length][city[0].length];
        visited[h][w] = true;

        if (city[h][w] == 1) {
            home++;
        }

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            int k = current[2];

            if (check + 1 == k) {
                check++;
                if (getExpense(k) <= home * m) {
                    maxHome = Math.max(home, maxHome);
                }
            }

            int a = current[0];
            int b = current[1];

            for (int i = 0; i < 4; i++) {
                int na = a + H[i];
                int nb = b + W[i];

                if (na < 0 || nb < 0 || na >= city.length || nb >= city[0].length) {
                    continue;
                }

                if (visited[na][nb]) {
                    continue;
                }
                visited[na][nb] = true;
                if (city[na][nb] == 1) {
                    home++;
                }
                queue.add(new int[]{na, nb, k + 1});
            }
        }

        return maxHome;
    }

    static int getExpense(int k) {
        return k * k + (k - 1) * (k - 1);
    }


}

