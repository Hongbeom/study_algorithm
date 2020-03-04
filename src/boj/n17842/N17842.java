package boj.n17842;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class N17842 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer>[] graph = new ArrayList[n];
        StringTokenizer st;
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

        int answer = 0;
        for (List<Integer> g : graph) {
            if (g.size() == 1) {
                answer++;
            }
        }

        System.out.println((answer / 2) + (answer % 2));
    }
}
