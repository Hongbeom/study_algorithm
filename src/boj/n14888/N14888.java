package boj.n14888;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N14888 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] numbers = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }


        st = new StringTokenizer(br.readLine());

        // Node를 배열로 표현하여 BFS -> +, -, *, /, number, step
        int[] node = new int[6];
        node[4] = numbers[0];
        for (int i = 0; i < 4; i++) {
            node[i] = Integer.parseInt(st.nextToken());
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(node);
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            int number = current[4];
            int step = current[5];

            if (step == numbers.length-1) {

                max = Math.max(max, number);
                min = Math.min(min, number);

                continue;
            }


            for (int i = 0; i < 4; i++) {
                int op = current[i];

                if (op > 0) {
                    int[] next = current.clone();
                    next[i]--;

                    switch (i) {
                        case 0:
                            next[4] += numbers[step + 1];
                            break;
                        case 1:
                            next[4] -= numbers[step + 1];
                            break;
                        case 2:
                            next[4] *= numbers[step + 1];
                            break;
                        case 3:
                            next[4] /= numbers[step + 1];
                            break;
                    }
                    next[5] = step + 1;
                    queue.add(next);

                }
            }
        }

        System.out.println(max);
        System.out.println(min);


    }

}
