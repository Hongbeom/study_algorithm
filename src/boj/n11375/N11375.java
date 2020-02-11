package boj.n11375;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class N11375 {

    static List<Integer>[] graph;
    static int[] matching;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());


        graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            for (int j = 0; j < size; j++) {
                graph[i].add(Integer.parseInt(st.nextToken()));
            }

        }

        matching = new int[m + 1];
        int answer = 0;

        for (int i = 1; i <= n; i++) {
            visited = new boolean[n + 1];
            if (dfs(i)) {
                answer++;
            }
        }

        System.out.println(answer);


    }

    static boolean dfs(int node) {

        if (visited[node]) {
            return false;
        }

        visited[node] = true;

        for (int task : graph[node]) {

            if (matching[task] == 0 || dfs(matching[task])) {
                matching[task] = node;
                return true;
            }
        }

        return false;

    }


}
