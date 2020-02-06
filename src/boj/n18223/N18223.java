package boj.n18223;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class N18223 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken()) - 1;

        List<int[]>[] graph = new ArrayList[v];

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            if (graph[a] == null) {
                graph[a] = new ArrayList<>();
            }
            graph[a].add(new int[]{b, c});

            if (graph[b] == null) {
                graph[b] = new ArrayList<>();
            }
            graph[b].add(new int[]{a, c});
        }
        if (check(graph, 0, p)) {
            System.out.println("SAVE HIM");
        } else {
            System.out.println("GOOD BYE");
        }


    }

    static boolean check(List<int[]>[] graph, int start, int p) {
        int[] distance = new int[graph.length];
        boolean[] check = new boolean[graph.length];
        check[p] = true;
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[1] > o2[1]) {
                return 1;
            } else if (o1[1] < o2[1]) {
                return -1;
            }
            return 0;
        });

        pq.add(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();

            int node = current[0];
            int d = current[1];

            if (distance[node] < d) {
                continue;
            }

            if (graph[node] != null) {
                for (int[] next : graph[node]) {
                    int nxt = next[0];
                    int nd = next[1];

                    if (d + nd <= distance[nxt]) {
                        if (nxt == p || check[node]) {
                            check[nxt] = true;
                        } else if (d + nd != distance[nxt]) {
                            check[nxt] = false;
                        }
                        distance[nxt] = d + nd;
                        pq.add(new int[]{nxt, d + nd});
                    }
                }
            }
        }
        return check[graph.length - 1];
    }
}
