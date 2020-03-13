package boj.n11405;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class N11405 {

    static int src;
    static int sink;
    static int size;
    static int[][] CAP;
    static int[][] COST;
    static int[][] FLOW;
    static List<Integer>[] GRAPH;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        src = 0;
        sink = n + m + 1;
        size = sink + 1;
        CAP = new int[size][size];
        COST = new int[size][size];
        FLOW = new int[size][size];

        GRAPH = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            GRAPH[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            GRAPH[i].add(sink);
            GRAPH[sink].add(i);
            CAP[i][sink] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = n + 1; i <= n + m; i++) {
            GRAPH[i].add(src);
            GRAPH[src].add(i);
            CAP[src][i] = Integer.parseInt(st.nextToken());
        }

        for (int i = n + 1; i <= n + m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                COST[i][j] = Integer.parseInt(st.nextToken());
                COST[j][i] = -1 * COST[i][j];
                // 서점과 사람의 간선은 순방향 간선만 용량을 max로 해준다.
                CAP[i][j] = Integer.MAX_VALUE;
                GRAPH[i].add(j);
                GRAPH[j].add(i);
            }
        }

        System.out.println(maxFlowMinCost(src, sink));
    }

    static int maxFlowMinCost(int start, int end) {
        int result = 0;
        int[] visited = new int[size];
        int[] distance = new int[size];
        boolean[] inQueue = new boolean[size];
        while (true) {
            Arrays.fill(visited, -1);
            Arrays.fill(distance, Integer.MAX_VALUE);
            Arrays.fill(inQueue, false);

            Queue<Integer> queue = new LinkedList<>();
            queue.add(start);
            inQueue[start] = true;
            distance[start] = 0;
            while (!queue.isEmpty()) {
                int here = queue.poll();
                inQueue[here] = false;
                for (int next : GRAPH[here]) {
                    if (distance[here] != Integer.MAX_VALUE &&
                            distance[here] + COST[here][next] < distance[next] &&
                            CAP[here][next] - FLOW[here][next] > 0) {
                        distance[next] = distance[here] + COST[here][next];
                        visited[next] = here;
                        if (next == end) {
                            break;
                        }
                        if (!inQueue[next]) {
                            queue.add(next);
                            inQueue[next] = true;
                        }
                    }
                }
            }
            if (visited[end] == -1) break;
            int flow = Integer.MAX_VALUE;
            for (int i = end; i != start; i = visited[i]) {
                flow = Math.min(flow, CAP[visited[i]][i] - FLOW[visited[i]][i]);
            }

            for (int i = end; i != start; i = visited[i]) {
                result += flow * COST[visited[i]][i];
                FLOW[visited[i]][i] += flow;
                FLOW[i][visited[i]] -= flow;
            }
        }
        return result;
    }
}
