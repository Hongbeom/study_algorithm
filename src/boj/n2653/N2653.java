package boj.n2653;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class N2653 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] relations = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                relations[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[] visited = new boolean[n];
        List<List<Integer>> answer = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            List<Integer> can = new ArrayList<>();
            can.add(i + 1);
            visited[i] = true;
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                if (visited[j]) {
                    continue;
                }
                if (Arrays.equals(relations[i], relations[j])) {
                    visited[j] = true;
                    can.add(j + 1);
                }
            }
            if (can.size() == 1) {
                System.out.println(0);
                return;
            } else {
                answer.add(can);
            }
        }

        System.out.println(answer.size());
        for (List<Integer> list : answer) {
            for (int a : list) {
                System.out.print(a + " ");
            }
            System.out.println();
        }
    }


}
