package boj.n16397;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N16397 {

    static final int MAX = 100000;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        int g = Integer.parseInt(st.nextToken());

        if (n == g) {
            System.out.println(0);
            return;
        }

        boolean[] visited = new boolean[100000];
        visited[n] = true;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{n, 0});

        int answer = -1;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int a = current[0];
            int cnt = current[1];

            if (a == g) {
                answer = cnt;
                break;
            }

            if (cnt >= t) {
                continue;
            }

            if (a + 1 < MAX && !visited[a + 1]) {
                visited[a + 1] = true;
                queue.add(new int[]{a + 1, cnt + 1});
            }

            if (a != 0 && a * 2 < MAX) {
                int next = buttonB(a * 2);
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(new int[]{next, cnt + 1});
                }
            }
        }

        if (answer == -1) {
            System.out.println("ANG");
        } else {
            System.out.println(answer);
        }
    }

    static int buttonB(int value) {
        int e = -1;
        for (int i = 4; i >= 0; i--) {
            int compare = (int) Math.pow(10, i);
            if (value >= compare) {
                e = i;
                break;
            }
        }

        return value - (int) Math.pow(10, e);
    }
}
