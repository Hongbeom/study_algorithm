package boj.n1967;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class N1967 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Info>[] graph = new ArrayList[n];

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());

            if (graph[from] == null) graph[from] = new ArrayList<>();
            if (graph[to] == null) graph[to] = new ArrayList<>();
            graph[from].add(new Info(to, w));
            graph[to].add(new Info(from, w));
        }

        int[] distance = new int[n];
        boolean[] visited = new boolean[n];
        visited[0] = true;

        dfs(graph, 0, 0, distance, visited);

        int node = 0;
        int dist = 0;

        for (int i = 1; i < distance.length; i++) {
            if (distance[i] > dist) {
                node = i;
                dist = distance[i];
            }
        }

        distance = new int[n];
        visited = new boolean[n];
        visited[node] = true;

        dfs(graph, node, 0, distance, visited);

        int answer = 0;
        for (int d : distance)
            answer = Math.max(answer, d);

        System.out.println(answer);
    }

    static class Info {
        int node;
        int w;

        public Info(int node, int w) {
            this.node = node;
            this.w = w;
        }
    }

    static void dfs(List<Info>[] graph, int current, int dist, int[] distance, boolean[] visited) {
        List<Info> nextList = graph[current];
        if (nextList != null) {
            for (Info info : nextList) {
                int next = info.node;
                if (visited[next]) continue;
                visited[next] = true;
                distance[next] = dist + info.w;
                dfs(graph, next, distance[next], distance, visited);
            }
        }
    }

}
