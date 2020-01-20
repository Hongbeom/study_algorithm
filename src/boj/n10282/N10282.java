package boj.n10282;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class N10282 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int tc = 0; tc < TC; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken()) - 1;

            List<int[]>[] edges = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                edges[i] = new ArrayList<>();
            }
            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                int s = Integer.parseInt(st.nextToken());

                edges[b].add(new int[]{a, s});
            }

            int[] distance = dijkstra(edges, c);
            int cnt = 0;
            int max = Integer.MIN_VALUE;
            for (int dist : distance) {
                if (dist != Integer.MAX_VALUE) {
                    cnt++;
                    max = Math.max(dist, max);
                }
            }

            System.out.println(cnt + " " + max);
        }

    }

    static int[] dijkstra(List<int[]>[] edges, int start) {
        int[] distance = new int[edges.length];
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

            for (int[] edge : edges[node]) {
                int next = edge[0];
                int nextDist = d + edge[1];

                if (nextDist < distance[next]) {
                    distance[next] = nextDist;
                    pq.add(new int[]{next, nextDist});
                }
            }
        }

        return distance;
    }

}
