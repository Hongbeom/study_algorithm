package boj.n1760;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class N1760 {

    static int[][] BOARD;
    static boolean[] VISITED;
    static int[] MATCH;
    static List<Integer>[] GRAPH;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        BOARD = new int[m][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                BOARD[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] idCheck = new int[m][n];
        int vid = 1;
        for (int j = 0; j < n; j++) {
            boolean check = false;

            for (int i = 0; i < m; i++) {
                if (BOARD[i][j] == 2) {
                    if (check) {
                        vid++;
                    }
                } else {
                    if (!check) {
                        check = true;
                    }
                    idCheck[i][j] = vid;
                    if (i == m - 1) {
                        vid++;
                    }
                }
            }
        }
        GRAPH = new ArrayList[vid];
        for (int i = 0; i < vid; i++) {
            GRAPH[i] = new ArrayList<>();
        }

        int hid = 1;
        for (int i = 0; i < m; i++) {
            boolean check = false;
            for (int j = 0; j < n; j++) {
                if (BOARD[i][j] == 2) {
                    if (check) {
                        hid++;
                    }
                } else {
                    if (!check) {
                        check = true;
                    }
                    if (BOARD[i][j] != 1) {
                        GRAPH[idCheck[i][j]].add(hid);
                    }
                    if (j == n - 1) {
                        hid++;
                    }
                }
            }
        }
        MATCH = new int[hid];
        int ans = 0;
        for (int i = 1; i < vid; i++) {
            VISITED = new boolean[hid];
            if (dfs(i)) {
                ans++;
            }
        }
        System.out.println(ans);
    }

    static boolean dfs(int from) {

        if (GRAPH[from] != null) {
            for (int to : GRAPH[from]) {
                if (VISITED[to]) {
                    continue;
                }
                VISITED[to] = true;

                if (MATCH[to] == 0 || dfs(MATCH[to])) {
                    MATCH[to] = from;
                    return true;
                }
            }
        }
        return false;
    }

}
