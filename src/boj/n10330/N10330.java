package boj.n10330;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N10330 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        boolean[] bit = new boolean[n];
        boolean[] visited = new boolean[32768];
        int initial = 0;
        int first = 0;
        int second = 0;
        int[] target = new int[m];
        for (int i = 0; i < n; i++) {
            if (Integer.parseInt(st.nextToken()) == 1) {
                bit[i] = true;
                initial += Math.pow(2, n - 1 - i);
            }
        }
        st = new StringTokenizer(br.readLine());
        int c = 0;
        for (int i = 0; i < m; i++) {
            target[i] = Integer.parseInt(st.nextToken());
            if (i % 2 == 0) {
                for (int e = c; e < c + target[i]; e++) {
                    first += Math.pow(2, n - 1 - e);
                }
            } else {
                for (int e = c; e < c + target[i]; e++) {
                    second += Math.pow(2, n - 1 - e);
                }
            }
            c += target[i];
        }
        if (initial == first || initial == second) {
            System.out.println(0);
            return;
        }

        visited[initial] = true;
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(bit, initial, 0));

        while (!queue.isEmpty()) {

            Node current = queue.poll();
            int num = current.num;
            boolean[] cbit = current.bit;
            int cnt = current.cnt;

            for (int i = 0; i < n - 1; i++) {
                if (cbit[i] == cbit[i + 1]) {
                    continue;
                }
                int nv;
                if (cbit[i]) {
                    nv = num - (int) Math.pow(2, n - 1 - i) + (int) Math.pow(2, n - 1 - (i + 1));
                } else {
                    nv = num + (int) Math.pow(2, n - 1 - i) - (int) Math.pow(2, n - 1 - (i + 1));
                }
                if (visited[nv]) {
                    continue;
                }

                if (nv == first || nv == second) {
                    System.out.println(cnt + 1);
                    return;
                }

                visited[nv] = true;
                boolean[] nbit = current.bit.clone();
                boolean tmp = nbit[i];
                nbit[i] = nbit[i + 1];
                nbit[i + 1] = tmp;
                queue.add(new Node(nbit, nv, cnt + 1));
            }

        }


    }

    static class Node {
        boolean[] bit;
        int num;
        int cnt;

        public Node(boolean[] bit, int num, int cnt) {
            this.bit = bit;
            this.num = num;
            this.cnt = cnt;
        }
    }
}
