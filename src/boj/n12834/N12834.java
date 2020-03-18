package boj.n12834;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class N12834 {

    static List<int[]>[] GRAPH;
    static int A = 0;
    static int B = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        GRAPH = new ArrayList[v];

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken()) - 1;
        B = Integer.parseInt(st.nextToken()) - 1;

        int[] kist = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            kist[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int dist = Integer.parseInt(st.nextToken());

            if (GRAPH[from] == null) {
                GRAPH[from] = new ArrayList<>();
            }
            if (GRAPH[to] == null) {
                GRAPH[to] = new ArrayList<>();
            }
            GRAPH[from].add(new int[]{to, dist});
            GRAPH[to].add(new int[]{from, dist});
        }

        int ans = 0;

        for (int s : kist) {
            ans += dijkstra(s);
        }

        System.out.println(ans);
    }

    static int dijkstra(int start) {
        int d = 0;
        int[] distance = new int[GRAPH.length];
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
            int here = current[0];
            int dist = current[1];
            if (dist > distance[here]) {
                continue;
            }
            if (GRAPH[here] != null) {
                for (int[] nextNode : GRAPH[here]) {
                    int next = nextNode[0];
                    int nextDist = nextNode[1];
                    if (distance[here] != Integer.MAX_VALUE &&
                            distance[here] + nextDist < distance[next]) {
                        distance[next] = distance[here] + nextDist;
                        pq.add(new int[]{next, distance[next]});
                    }
                }
            }
        }

        if (distance[A] == Integer.MAX_VALUE) {
            d -= 1;
        } else {
            d += distance[A];
        }

        if (distance[B] == Integer.MAX_VALUE) {
            d -= 1;
        } else {
            d += distance[B];
        }

        return d;

    }
}
