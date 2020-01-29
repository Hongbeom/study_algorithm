package boj.n10448;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class N10448 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        boolean[] visited = new boolean[1001];
        root:
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());

            Queue<int[]> queue = new LinkedList<>();

            queue.add(new int[]{0, 0});
            visited[0] = true;
            int cnt = 0;

            while (!queue.isEmpty()) {
                int[] current = queue.poll();
                int value = current[0];
                int step = current[1];

                if (cnt + 1 == step) {
                    cnt++;
                    visited = new boolean[10001];
                }

                if (step == 3) {
                    if (value == n) {
                        System.out.println(1);
                        continue root;
                    }
                    continue;
                }

                int a = 1;
                int plus = 2;
                while (a < n) {
                    int nv = value + a;

                    if (nv > n) {
                        break;
                    }
                    if (!visited[nv]) {
                        visited[nv] = true;
                        queue.add(new int[]{nv, step + 1});
                    }

                    a += plus;
                    plus++;

                }
            }
            System.out.println(0);
        }
    }

}
