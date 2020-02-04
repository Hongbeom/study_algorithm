package boj.n3584;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class N3584_LCA {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int tc = 0; tc < T; tc++) {
            int n = Integer.parseInt(br.readLine());
            List<Integer>[] graph = new ArrayList[n + 1];
            boolean[] check = new boolean[n + 1];
            for (int i = 0; i < n - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                check[to] = true;
                if (graph[from] == null) {
                    graph[from] = new ArrayList<>();
                }
                graph[from].add(to);

                if (graph[to] == null) {
                    graph[to] = new ArrayList<>();
                }
                graph[to].add(from);
            }

            int root = 0;
            for (int i = 1; i < check.length; i++) {
                if (!check[i]) {
                    root = i;
                    break;
                }
            }

            int[] depth = new int[n + 1];
            int[][] ac = new int[n + 1][(int) log2(n) + 1];
            depth[0] = -1;

            getTree(graph, ac, depth, root, 0);

            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (depth[a] != depth[b]) {
                if (depth[a] > depth[b]) {
                    int tm = a;
                    a = b;
                    b = tm;
                }
                // b를 올린다.
                for (int i = ac[0].length - 1; i >= 0; i--) {
                    if (depth[a] <= depth[ac[b][i]]) {
                        b = ac[b][i];
                    }
                }
            }

            int lca = a;
            if (a != b) {
                for (int i = ac[0].length - 1; i >= 0; i--) {
                    if (ac[a][i] != ac[b][i]) {
                        a = ac[a][i];
                        b = ac[b][i];
                    }
                    lca = ac[a][i];
                }
            }

            System.out.println(lca);

        }


    }

    static void getTree(List<Integer>[] graph, int[][] ac, int[] depth, int here, int parent) {

        depth[here] = depth[parent] + 1;

        ac[here][0] = parent;

        for (int i = 1; i < ac[0].length; i++) {
            int tm = ac[here][i - 1];
            ac[here][i] = ac[tm][i - 1];
        }

        if (graph[here] != null) {

            for (int there : graph[here]) {
                if (there != parent) {
                    getTree(graph, ac, depth, there, here);
                }
            }
        }

    }

    static double log2(double x) {
        return Math.log10(x) / Math.log10((double) 2);
    }

}
