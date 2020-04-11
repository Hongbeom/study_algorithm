package boj.n14792;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class N14792 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        double max = Double.MIN_VALUE;
        PriorityQueue<Double> pq = new PriorityQueue<>(Collections.reverseOrder());
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            double v = Double.parseDouble(br.readLine());
            if (cnt < 7) {
                cnt++;
                pq.add(v);
                max = Math.max(max, v);
                continue;
            }
            if (max < v) {
                continue;
            }
            pq.add(v);
            pq.poll();
            max = pq.peek();
        }
        double[] answer = new double[7];
        int id = 0;
        while (!pq.isEmpty()) {
            answer[id++] = pq.poll();
        }
        for (int i = answer.length - 1; i >= 0; i--) {
            System.out.println(String.format("%.3f", answer[i]));
        }
    }
}
