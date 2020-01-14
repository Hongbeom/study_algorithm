package boj.n1697;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N1697 {

    static int LIMIT;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{n, 0});

        if (n > k) {
            LIMIT = n + 1;
        } else {
            LIMIT = k + 2;
        }

        boolean[] visited = new boolean[LIMIT];
        visited[n] = true;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            int x = current[0];
            int step = current[1];

            if (x == k) {
                System.out.println(step);
                return;
            }


            if (check(x + 1, visited)) {
                visited[x + 1] = true;
                queue.add(new int[]{x + 1, step + 1});
            }

            if (check(x - 1, visited)) {
                visited[x - 1] = true;
                queue.add(new int[]{x - 1, step + 1});
            }

            if (check(x * 2, visited)) {
                visited[x * 2] = true;
                queue.add(new int[]{x * 2, step + 1});
            }

        }
    }

    static boolean check(int nx, boolean[] visited) {

        if (nx < 0 || nx >= visited.length) {
            return false;
        }

        return !visited[nx];

    }
}
