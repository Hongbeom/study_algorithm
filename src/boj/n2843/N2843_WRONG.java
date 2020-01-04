package boj.n2843;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N2843_WRONG {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] vertex = new int[n];

        for (int i = 0; i < n; i++) {
            vertex[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        int q = Integer.parseInt(br.readLine());

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int question = Integer.parseInt(st.nextToken());
            int node = Integer.parseInt(st.nextToken()) - 1;

            if (question == 1) {

                int result = search(vertex, node);
                if (result == -1) {
                    System.out.println("CIKLUS");
                } else {
                    System.out.println(result + 1);
                }

            } else {
                vertex[node] = -1;
            }
        }


    }

    static int search(int[] vertex, int first) {
        boolean[] visited = new boolean[vertex.length];

        int current = first;
        visited[current] = true;
        while (true) {
            int next = vertex[current];

            if (next == -1) {
                return current;
            }

            if (visited[next]) {
                return -1;
            }

            visited[next] = true;
            current = next;
        }

    }
}
