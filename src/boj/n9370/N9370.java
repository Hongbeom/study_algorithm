package boj.n9370;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class N9370 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken()) - 1;
            int h = Integer.parseInt(st.nextToken()) - 1;

            List<int[]>[] edges = new ArrayList[n];

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                int d = Integer.parseInt(st.nextToken());

                if (edges[a] == null) {
                    edges[a] = new ArrayList<>();
                }
                edges[a].add(new int[]{b, d});

                if (edges[b] == null) {
                    edges[b] = new ArrayList<>();
                }
                edges[b].add(new int[]{a, d});

            }

            int[] candidates = new int[t];
            for (int i = 0; i < t; i++) {
                candidates[i] = Integer.parseInt(br.readLine()) - 1;
            }

            boolean[] check = check(edges, s - 1, g, h);

            for (int i = 0; i < check.length; i++) {
                if (check[i]) {
                    for (int c : candidates) {
                        if (c == i) {
                            System.out.print((i + 1) + " ");
                        }
                    }
                }
            }
            System.out.println();


        }
    }

    static boolean[] check(List<int[]>[] edges, int start, int g, int h) {

        int[] d = new int[edges.length];
        boolean[] check = new boolean[edges.length];
        Arrays.fill(d, Integer.MAX_VALUE);
        d[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[1] > o2[1]) {
                return 1;
            } else if (o1[1] < o2[1]) {
                return -1;
            }
            return 0;
        });

        pq.add(new int[]{start, 0});

        while (!pq.isEmpty()) {

            int[] current = pq.poll();

            int node = current[0];
            int dist = current[1];

            if (d[node] < dist) {
                continue;
            }

            for (int[] edge : edges[node]) {

                int to = edge[0];
                int nd = edge[1];

                if (nd + dist <= d[to]) {

                    if ((node == g && to == h) || (node == h && to == g) || check[node]) {
                        check[to] = true;
                    } else if (nd + dist != d[to]) {
                        check[to] = false;
                    }

                    d[to] = nd + dist;
                    pq.add(new int[]{to, d[to]});
                }

            }
        }

        return check;

    }

}
