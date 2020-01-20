package boj.n1389;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class N1389 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] relation = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(relation[i], Integer.MAX_VALUE);
            relation[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            relation[a][b] = 1;
            relation[b][a] = 1;

        }

        System.out.println(floyd(relation));


    }

    static int[][] clone(int[][] src) {
        int[][] re = new int[src.length][src[0].length];

        for (int i = 0; i < src.length; i++) {
            System.arraycopy(src[i], 0, re[i], 0, src[i].length);
        }

        return re;
    }

    static int floyd(int[][] relation) {

        for (int k = 0; k < relation.length; k++) {
            from:
            for (int i = 0; i < relation.length; i++) {
                for (int j = 0; j < relation.length; j++) {
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
        int answer = Integer.MAX_VALUE;
        int id = -1;
        for (int i = 0; i < relation.length; i++) {
            int sum = 0;
            for (int j = 0; j < relation.length; j++) {
                if (relation[i][j] != Integer.MAX_VALUE) {
                    sum += relation[i][j];
                }
            }

            if (sum < answer) {
                answer = sum;
                id = i + 1;
            }
        }

        return id;
    }
}
