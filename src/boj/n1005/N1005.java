package boj.n1005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class N1005 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int tc = 0; tc < TC; tc++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[] weight = new int[n];
            int[] time = new int[n];
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < n; i++) {
                time[i] = Integer.parseInt(st.nextToken());
            }
            List<Integer>[] graph = new ArrayList[n];

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken()) - 1;
                int to = Integer.parseInt(st.nextToken()) - 1;

                if (graph[from] == null) {
                    graph[from] = new ArrayList<>();
                }
                graph[from].add(to);
                weight[to]++;
            }

            int w = Integer.parseInt(br.readLine()) - 1;

            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < weight.length; i++) {
                if (weight[i] == 0) {
                    queue.add(i);
                }
            }

            long[] complete = new long[n];

            for (int i = 0; i < n; i++) {

                if (queue.isEmpty()) {
                    break;
                }

                int node = queue.poll();
                complete[node] = complete[node] + time[node];
                if (node == w) {
                    System.out.println(complete[node]);
                    break;
                }

                if (graph[node] != null) {
                    for (int next : graph[node]) {
                        complete[next] = Math.max(complete[next], complete[node]);
                        if (--weight[next] == 0) {
                            queue.add(next);
                        }
                    }
                }
            }

        }
    }
}
