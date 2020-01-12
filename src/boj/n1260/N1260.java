package boj.n1260;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class N1260 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken()) - 1;

        List<Integer>[] edges = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;

            edges[from].add(to);
            edges[to].add(from);
        }

        for (int i = 0; i < n; i++) {
            Collections.sort(edges[i]);
        }

        boolean[] visited = new boolean[n];
        visited[v] = true;
        dfs(edges, v, visited);
        System.out.println();
        bfs(edges, v);

    }

    static void dfs(List<Integer>[] edges, int v, boolean[] visited){
        System.out.print((v + 1) + " ");

        for(int next : edges[v]){

            if(visited[next]){
                continue;
            }
            visited[next] = true;
            dfs(edges, next, visited);

        }
    }

    static void bfs(List<Integer>[] edges, int v) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[edges.length];
        visited[v] = true;
        queue.add(v);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print((current + 1) + " ");

            for (int next : edges[current]) {

                if (visited[next]) {
                    continue;
                }
                visited[next] = true;
                queue.add(next);
            }
        }
    }


}
