package boj.n1766;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class N1766 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] weight = new int[n];

        List<Integer>[] vertex = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            vertex[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;

            vertex[from].add(to);
            weight[to]++;

        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < weight.length; i++) {
            if (weight[i] == 0) {
                pq.add(i);
            }
        }

        while (!pq.isEmpty()) {
            int current = pq.poll();
            System.out.print((current + 1) + " ");
            for (int to : vertex[current]) {
                if (--weight[to] == 0) {
                    pq.add(to);
                }
            }
        }

    }
}
