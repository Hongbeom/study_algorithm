package boj.n1516;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class N1516 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] weight = new int[n];
        int[] time = new int[n];
        int[] answer = new int[n];

        List<Integer>[] edges = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }

        String[] tmp;
        for (int i = 0; i < n; i++) {
            tmp = br.readLine().split(" ");
            for (int j = 0; j < tmp.length - 1; j++) {
                if (j == 0) {
                    time[i] = Integer.parseInt(tmp[j]);
                } else {
                    int from = Integer.parseInt(tmp[j]) - 1;
                    edges[from].add(i);
                    weight[i]++;
                }
            }
        }
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (weight[i] == 0) {
                queue.add(i);
            }
        }

        for (int i = 0; i < n; i++) {
            int current = queue.poll();

            answer[current] += time[current];

            for (int next : edges[current]) {
                if (--weight[next] == 0) {
                    queue.add(next);
                }
                answer[next] = Math.max(answer[next], answer[current]);
            }
        }


        for (int t : answer) {
            System.out.println(t);
        }

    }
}
