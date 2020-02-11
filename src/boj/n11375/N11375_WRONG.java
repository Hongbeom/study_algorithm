package boj.n11375;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class N11375_WRONG {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        PriorityQueue<List<Integer>> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.size() > o2.size()) {
                return 1;
            } else if (o1.size() < o2.size()) {
                return -1;
            }
            return 0;
        });


        boolean[] check = new boolean[m];
        int[] weight = new int[m];
        int answer = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            List<Integer> can = new ArrayList<>(size);
            for (int j = 0; j < size; j++) {
                int taskId = Integer.parseInt(st.nextToken()) - 1;
                can.add(taskId);
                weight[taskId]++;
            }
            pq.add(can);
        }

        while (!pq.isEmpty()) {
            List<Integer> can = pq.poll();

            can.sort((o1, o2) -> {
                if (weight[o1] > weight[o2]) {
                    return 1;
                } else if (weight[o1] < weight[o2]) {
                    return -1;
                }
                return 0;
            });

            for (int task : can) {
                if (!check[task]) {
                    answer++;
                    check[task] = true;
                    break;
                }
            }
        }

        System.out.println(answer);


    }

}
