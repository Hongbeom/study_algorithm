package boj.n11657;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class N11657_SPFA {

    static List<int[]>[] edges;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        edges = new ArrayList[n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            if (edges[from] == null) {
                edges[from] = new ArrayList<>();
            }
            edges[from].add(new int[]{to, weight});
        }

        int[] distance = SPFA(0, n);
        if (distance == null) {
            System.out.println(-1);
        } else {
            for (int i = 1; i < distance.length; i++) {
                if (distance[i] == Integer.MAX_VALUE) {
                    System.out.println(-1);
                } else {
                    System.out.println(distance[i]);
                }
            }
        }


    }

    static int[] SPFA(int start, int n) {
        int[] distance = new int[n];
        // 노드를 몇번 방문했는지 표기하는 배열
        int[] visited = new int[n];
        // 큐에 존재하는지 확인하는 배열
        boolean[] inQueue = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);
        inQueue[start] = true;
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;
        visited[start]++;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            inQueue[current] = false;

            if (edges[current] != null) {
                for (int[] edge : edges[current]) {
                    int next = edge[0];
                    int weight = edge[1];

                    if (distance[current] != Integer.MAX_VALUE && distance[current] + weight < distance[next]) {
                        distance[next] = distance[current] + weight;

                        if (!inQueue[next]) {

                            if (++visited[next] == n) {
                                return null;
                            }
                            queue.add(next);
                            inQueue[next] = true;
                        }
                    }
                }
            }
        }

        return distance;

    }
}
