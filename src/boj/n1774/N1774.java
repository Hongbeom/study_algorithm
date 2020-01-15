package boj.n1774;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class N1774 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());


        double answer = 0;
        int[] parent = new int[n];
        int[][] spot = new int[n][2];

            for (int i = 0; i < n; i++) {
            parent[i] = i;
            st = new StringTokenizer(br.readLine());
            spot[i][0] = Integer.parseInt(st.nextToken());
            spot[i][1] = Integer.parseInt(st.nextToken());
        }


        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            union(parent, from, to);
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                double dist = getDistance(spot[i], spot[j]);
                pq.add(new Edge(i, j, dist));
            }
        }
        int cnt = 0;
        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            if (check(parent, current.from, current.to)) {
                continue;
            }

            answer += current.distance;
            union(parent, current.from, current.to);
            cnt++;

            if (cnt == n - 1 - m) {

                break;
            }
        }
        System.out.printf("%.2f", answer);



    }

    static double getDistance(int[] from, int[] to) {
        return Math.sqrt(Math.pow(from[0] - to[0], 2) + Math.pow(from[1] - to[1], 2));
    }

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        double distance;

        Edge(int from, int to, double distance) {
            this.from = from;
            this.to = to;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge o) {
            if (this.distance > o.distance) {
                return 1;
            } else if (this.distance < o.distance) {
                return -1;
            }
            return 0;
        }
    }


    static int getParent(int[] parent, int a) {
        if (parent[a] == a) {
            return a;
        }

        return parent[a] = getParent(parent, parent[a]);
    }

    static boolean check(int[] parent, int a, int b) {

        a = getParent(parent, a);
        b = getParent(parent, b);

        return a == b;

    }

    static void union(int[] parent, int a, int b) {

        a = getParent(parent, a);
        b = getParent(parent, b);

        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }

    }
}
