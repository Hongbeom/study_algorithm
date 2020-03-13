package boj.n1575;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class N1575 {

    static int src;
    static int sink;
    static int size;
    static int target = 0;
    static int[][] CAP;
    static int[][] FLOW;
    static int[][] COST;
    static boolean[] taken;
    static List<Integer>[] GRAPH;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int p = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        taken = new boolean[101];
        for (int i = 0; i < p; i++) {
            taken[Integer.parseInt(st.nextToken())] = true;
        }

        int n = Integer.parseInt(br.readLine());
        src = 0;
        sink = n + 101;
        size = sink + 1;

        CAP = new int[size][size];
        FLOW = new int[size][size];
        COST = new int[size][size];
        GRAPH = new ArrayList[size];

        for (int i = 0; i < size; i++) {
            GRAPH[i] = new ArrayList<>();
        }
        for (int i = 101; i <= 100 + n; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            target += t;
            int lecN = Integer.parseInt(st.nextToken());
            GRAPH[src].add(i);
            GRAPH[i].add(src);
            CAP[src][i] = t;
            for (int j = 0; j < lecN; j++) {
                int lecture = Integer.parseInt(st.nextToken());
                GRAPH[i].add(lecture);
                GRAPH[lecture].add(i);
                CAP[i][lecture] = Integer.MAX_VALUE;

                if (taken[lecture]) {
                    COST[i][lecture] = Integer.MIN_VALUE;
                } else {
                    COST[i][lecture] = lecture;
                }
                COST[lecture][i] = -1 * COST[i][lecture];
            }
        }

        for (int i = 1; i < 101; i++) {
            CAP[i][sink] = 1;
            GRAPH[i].add(sink);
            GRAPH[sink].add(i);
        }

        List<Integer> ansList = maxFlowMinCost(src, sink);
        if (ansList == null) {
            System.out.println(-1);
        } else {
            StringBuilder sb = new StringBuilder();
            System.out.println(ansList.size());
            for (int ans : ansList) {
                sb.append(ans);
                sb.append(" ");
            }
            System.out.println(sb.toString().trim());
        }
    }

    static List<Integer> maxFlowMinCost(int start, int end) {
        int cnt = 0;
        int[] distance = new int[size];
        int[] visited = new int[size];
        boolean[] inQueue = new boolean[size];
        List<Integer> ansList = new ArrayList<>();
        while (true) {
            Arrays.fill(distance, Integer.MAX_VALUE);
            Arrays.fill(visited, -1);
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

                        if (end == next) {
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
                FLOW[visited[i]][i] += flow;
                FLOW[i][visited[i]] -= flow;
            }
            if (!taken[visited[end]]) {
                ansList.add(visited[end]);
            }
            cnt++;
        }

        if (cnt < target) {
            return null;
        } else {
            return ansList;
        }
    }
}
