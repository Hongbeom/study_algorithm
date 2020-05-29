package boj.n3973;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class N3973 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int tc = 0; tc < TC; tc++) {
            int n = Integer.parseInt(br.readLine());
            List<Integer>[] graph = new ArrayList[n];
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

                graph[from].add(to);
                graph[to].add(from);
            }

            boolean[] visited = new boolean[graph.length];
            visited[0] = true;
            int[] leaf = getMaxHeightAndId(graph, 0, 0, visited);

            visited = new boolean[graph.length];
            visited[leaf[1]] = true;

            int length = search(graph, leaf[1], 0, visited);
            if(length % 2 == 1){
                length = length / 2 + 1;
            }else{
                length = length / 2;
            }
            System.out.println(length);
        }

    }

    static int[] getMaxHeightAndId(List<Integer>[] graph, int id, int height, boolean[] visited) {
        int[] result = new int[]{height, id};

        for (int next : graph[id]) {
            if (visited[next]) {
                continue;
            }
            visited[next] = true;

            int[] tmp = getMaxHeightAndId(graph, next, height + 1, visited);
            if(tmp[0] > result[0]){
                result = tmp;
            }
        }

        return result;
    }

    static int search(List<Integer>[] graph, int id, int step, boolean[] visited){
        int cnt = step;
        for(int next : graph[id]){
            if(visited[next]){
                continue;
            }
            visited[next] = true;
            cnt = Math.max(cnt, search(graph, next, step + 1, visited));
        }
        return cnt;
    }
}

