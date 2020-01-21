package boj.n1613;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class N1613 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] relation = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(relation[i], Integer.MAX_VALUE);
            relation[i][i] = 0;
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken()) - 1;
            int from = Integer.parseInt(st.nextToken()) - 1;
            relation[from][to] = 1;
        }
        floyd(relation);

        int s = Integer.parseInt(br.readLine());

        for (int i = 0; i < s; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;

            if (relation[from][to] == Integer.MAX_VALUE
                    && relation[to][from] == Integer.MAX_VALUE) {
                System.out.println(0);
            } else if (relation[from][to] == Integer.MAX_VALUE) {
                System.out.println(-1);
            } else {
                System.out.println(1);
            }
        }
    }


    static void floyd(int[][] relation) {
        int n = relation.length;

        for (int k = 0; k < n; k++) {
            from:
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (relation[i][k] == Integer.MAX_VALUE) {
                        continue from;
                    }

                    if (relation[k][j] == Integer.MAX_VALUE) {
                        continue;
                    }

                    if (relation[i][k] + relation[k][j] < relation[i][j]) {
                        relation[i][j] = relation[i][k] + relation[k][j];
                    }
                }
            }
        }
    }
}
