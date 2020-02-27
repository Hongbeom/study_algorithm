package boj.n9763;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class N9763_WRONG {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] towns = new int[n][3];

        StringTokenizer st;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            towns[i][0] = Integer.parseInt(st.nextToken());
            towns[i][1] = Integer.parseInt(st.nextToken());
            towns[i][2] = Integer.parseInt(st.nextToken());
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {

                pq.add(new Edge(i, j, towns[i], towns[j]));
            }
        }


        int[] minDist = new int[n];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        Edge first = pq.poll();
        minDist[first.from] = first.distance;
        minDist[first.to] = first.distance;


        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            if (minDist[current.from] != Integer.MAX_VALUE || minDist[current.to] != Integer.MAX_VALUE) {
                System.out.println(Math.min(minDist[current.from], minDist[current.to]) + current.distance);
                return;
            }

            minDist[current.from] = Math.min(minDist[current.from], current.distance);
            minDist[current.to] = Math.min(minDist[current.to], current.distance);

        }


    }

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int distance;

        Edge(int from, int to, int[] x, int[] y) {
            this.from = from;
            this.to = to;
            this.distance = Math.abs(x[0] - y[0]) + Math.abs(x[1] - y[1]) + Math.abs(x[2] - y[2]);
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
}
