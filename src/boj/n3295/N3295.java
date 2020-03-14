package boj.n3295;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class N3295 {

    static int[] MATCH;
    static List<Integer>[] GRAPH;
    static boolean[] VISITED;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int tc = 0; tc < TC; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            MATCH = new int[n];
            GRAPH = new ArrayList[n];
            VISITED = new boolean[n];

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                if (GRAPH[from] == null) {
                    GRAPH[from] = new ArrayList<>();
                }
                GRAPH[from].add(to);
            }

            Arrays.fill(MATCH, -1);
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                Arrays.fill(VISITED, false);
                if (dfs(i)) {
                    cnt++;
                }
            }
            System.out.println(cnt);
        }
    }

    static boolean dfs(int from) {
        if (GRAPH[from] != null) {
            for (int to : GRAPH[from]) {
                if (VISITED[to]) {
                    continue;
                }
                VISITED[to] = true;
                if (MATCH[to] == -1 || dfs(MATCH[to])) {
                    MATCH[to] = from;
                    return true;
                }
            }
        }
        return false;
    }
}
