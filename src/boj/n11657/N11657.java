package boj.n11657;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class N11657 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<Edge> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());

            list.add(new Edge(from, to, weight));

        }
        int[] distance = Bellman(list, 0, n);

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

    static int[] Bellman(List<Edge> list, int start, int n) {
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        for (int i = 0; i < n; i++) {
            boolean check = false;
            for (Edge edge : list) {
                int from = edge.from;
                int to = edge.to;
                if (distance[from] != Integer.MAX_VALUE && distance[from] + edge.weight < distance[to]) {
                    distance[to] = distance[from] + edge.weight;
                    check = true;
                }
            }
            if (!check) {
                break;
            } else {
                if (i == n - 1) {
                    return null;
                }
            }
        }
        return distance;
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
