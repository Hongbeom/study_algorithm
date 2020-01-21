package boj.n1865;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class N1865 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringTokenizer st;
        root:
        for (int tc = 0; tc < TC; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            List<Edge> edges = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken()) - 1;
                int to = Integer.parseInt(st.nextToken()) - 1;
                int weight = Integer.parseInt(st.nextToken());
                edges.add(new Edge(from, to, weight));
                edges.add(new Edge(to, from, weight));
            }

            for (int i = 0; i < w; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken()) - 1;
                int to = Integer.parseInt(st.nextToken()) - 1;
                int weight = Integer.parseInt(st.nextToken());
                edges.add(new Edge(from, to, weight * -1));
            }


            for (int i = 0; i < n; i++) {
                if (Bellman(edges, i, n) < 0) {
                    System.out.println("YES");
                    continue root;
                }
            }
            System.out.println("NO");

        }
    }

    static int Bellman(List<Edge> edges, int start, int n) {
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        for (int i = 0; i < n; i++) {
            boolean check = true;

            for (Edge edge : edges) {
                // relaxation
                int from = edge.from;
                int to = edge.to;

                if (distance[from] != Integer.MAX_VALUE
                        && distance[from] + edge.weight < distance[to]) {
                    distance[to] = distance[from] + edge.weight;
                    check = false;
                }
            }

            if (check) {
                break;
            }
        }


        return distance[start];

    }

    static class Edge {
        int from;
        int to;
        int weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}
