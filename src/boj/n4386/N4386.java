package boj.n4386;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class N4386 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;

        double[][] spot = new double[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            spot[i][0] = Double.parseDouble(st.nextToken());
            spot[i][1] = Double.parseDouble(st.nextToken());
        }
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                pq.add(new Vertex(i, j, getDistance(spot[i], spot[j])));
            }
        }

        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        double answer = 0;
        int cnt = 0;
        while (!pq.isEmpty()) {
            Vertex v = pq.poll();

            if (union(parent, v.from, v.to)) {
                answer += v.distance;
                cnt++;
            }
            if (cnt == n) {
                break;
            }
        }
        System.out.println(answer);
    }


    static boolean union(int[] parent, int a, int b) {
        a = getParent(parent, a);
        b = getParent(parent, b);
        if (a != b) {
            if (a < b) {
                parent[b] = a;
            } else {
                parent[a] = b;
            }
            return true;
        } else {
            return false;
        }

    }

    static int getParent(int[] parent, int a) {
        if (parent[a] == a) {
            return a;
        }

        return parent[a] = getParent(parent, parent[a]);
    }

    static double getDistance(double[] a, double[] b) {

        return Math.sqrt(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2));
    }

    static class Vertex implements Comparable<Vertex> {
        int from;
        int to;
        double distance;

        Vertex(int from, int to, double distance) {
            this.from = from;
            this.to = to;
            this.distance = distance;
        }

        @Override
        public int compareTo(Vertex o) {
            if (this.distance > o.distance) {
                return 1;
            } else if (this.distance < o.distance) {
                return -1;
            }

            return 0;
        }
    }
}
