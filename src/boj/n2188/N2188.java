package boj.n2188;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class N2188 {

    static List<Integer>[] graph;
    static int[] matching;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n];
        matching = new int[m];
        visited = new boolean[m];
        Arrays.fill(matching, -1);

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            for (int j = 0; j < s; j++) {
                graph[i].add(Integer.parseInt(st.nextToken()) - 1);
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            Arrays.fill(visited, false);
            if (dfs(i)) {
                ans++;
            }
        }
        System.out.println(ans);
    }

    static boolean dfs(int current) {

        if (graph[current] != null) {
            for (int target : graph[current]) {

                if (visited[target]) {
                    continue;
                }
                visited[target] = true;

                if (matching[target] == -1 || dfs(matching[target])) {
                    matching[target] = current;
                    return true;
                }
            }
        }

        return false;
    }
}
