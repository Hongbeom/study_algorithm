package boj.n16939;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class N16939 {

    static List<Integer>[] GRAPH;
    static long MAX = (long) Math.pow(10, 18);
    static long[] INPUT;
    static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        HashMap<Long, List<Integer>> map = new HashMap<>();
        GRAPH = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            GRAPH[i] = new ArrayList<>();
        }
        INPUT = new long[N];
        for (int i = 0; i < N; i++) {
            long v = Long.parseLong(st.nextToken());
            INPUT[i] = v;
            List<Integer> list = map.getOrDefault(v, null);
            if (list == null) {
                list = new ArrayList<>();
                map.put(v, list);
            }
            list.add(i);
        }

        for (int i = 0; i < N; i++) {
            long v = INPUT[i];
            if (v % 3 == 0 && map.containsKey(v / 3)) {
                GRAPH[i].addAll(map.get(v / 3));
            }
            if (v * 2 < MAX && map.containsKey(v * 2)) {
                GRAPH[i].addAll(map.get(v * 2));
            }
        }

        for (int i = 0; i < N; i++) {
            int[] visited = new int[N];
            visited[i] = 1;
            dfs(i, 1, visited);
        }
    }

    static void dfs(int current, int c, int[] visited) {
        if (c == N) {
            long[] ans = new long[N];
            for (int i = 0; i < N; i++) {
                ans[visited[i] - 1] = INPUT[i];
            }
            for (int i = 0; i < N; i++) {
                System.out.print(ans[i] + " ");
            }
            System.exit(0);
        }

        for (int next : GRAPH[current]) {
            if (visited[next] != 0) {
                continue;
            }
            int[] visit = visited.clone();
            visit[next] = c + 1;
            dfs(next, c + 1, visit);
        }

    }
}
