package boj.n3973;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class N3973_WRONG {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int tc = 0; tc < TC; tc++) {
            int n = Integer.parseInt(br.readLine());
            int[] weight = new int[n];
            int center = -1;
            int maxWeight = 0;
            List<int[]>[] graph = new ArrayList[n];
            for (int i = 0; i < n - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                if (graph[from] == null) {
                    graph[from] = new ArrayList<>();
                }
                if (graph[to] == null) {
                    graph[to] = new ArrayList<>();
                }
                graph[from].add(new int[]{to, 1});
                graph[to].add(new int[]{from, 1});

                if (++weight[from] > maxWeight) {
                    maxWeight = weight[from];
                    center = from;
                }
                if (++weight[to] > maxWeight) {
                    maxWeight = weight[to];
                    center = to;
                }
            }
//            System.out.println("center : " + center);
            System.out.println(dijkstra(graph, center));
        }
    }

    static int dijkstra(List<int[]>[] graph, int start) {
        int[] distance = new int[graph.length];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;
        int maxDist = 0;
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
            int[] here = pq.poll();
            int currentNode = here[0];
            int dist = here[1];

            if (distance[currentNode] < dist) {
                continue;
            }

            if (graph[currentNode] != null) {
                for (int[] next : graph[currentNode]) {
                    int nextNode = next[0];
                    int nextDist = next[1];
                    if (nextDist + distance[currentNode] < distance[nextNode]) {
                        distance[nextNode] = nextDist + distance[currentNode];
                        maxDist = Math.max(maxDist, distance[nextNode]);
                        pq.add(new int[]{nextNode, nextDist + distance[currentNode]});
                    }
                }
            }
        }

//        System.out.println(Arrays.toString(distance));
        return maxDist;
    }
}
