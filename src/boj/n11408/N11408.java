package boj.n11408;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class N11408 {

    static int src;
    static int sink;
    static int size;
    static int[][] CAP;
    static int[][] FLOW;
    static int[][] COST;
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
        FLOW = new int[size][size];
        COST = new int[size][size];
        GRAPH = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            GRAPH[i] = new ArrayList<>();
        }


        for (int i = 1; i <= n; i++) {
            GRAPH[src].add(i);
            GRAPH[i].add(src);
            CAP[src][i] = 1;
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            for (int j = 0; j < s; j++) {
                int task = Integer.parseInt(st.nextToken()) + n;
                int money = Integer.parseInt(st.nextToken());
                GRAPH[i].add(task);
                GRAPH[task].add(i);

                CAP[i][task] = Integer.MAX_VALUE;

                COST[i][task] = money;
                COST[task][i] = -1 * money;
            }
        }
        for (int i = n + 1; i <= n + m; i++) {
            GRAPH[i].add(sink);
            GRAPH[sink].add(i);
            CAP[i][sink] = 1;
        }

        for (int ans : maxFlowMinCost(src, sink)) {
            System.out.println(ans);
        }
    }


    static int[] maxFlowMinCost(int start, int end) {
        int result = 0;
        int cnt = 0;

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
                            CAP[here][next] - FLOW[here][next] > 0 &&
                            distance[next] > distance[here] + COST[here][next]) {
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
                result += COST[visited[i]][i];
                FLOW[visited[i]][i] += flow;
                FLOW[i][visited[i]] -= flow;
            }
            cnt++;
        }

        return new int[]{cnt, result};

    }


}
