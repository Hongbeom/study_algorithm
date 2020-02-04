package boj.n11438;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class N11438 {

    static int[] depth;
    static int[][] ac;
    static List<Integer>[] graph;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n + 1];

        StringTokenizer st;
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            if (graph[from] == null) {
                graph[from] = new ArrayList<>();
            }
            if (graph[to] == null) {
                graph[to] = new ArrayList<>();
            }
            graph[from].add(to);
            graph[to].add(from);
        }

        depth = new int[n + 1];
        depth[0] = -1;
        ac = new int[n + 1][(int) log2(n, 2) + 1];
        getTree(1, 0);

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (depth[a] != depth[b]) {
                if (depth[a] > depth[b]) {
                    // b를 올려주기 위해서
                    int tm = a;
                    a = b;
                    b = tm;
                }

                for (int id = ac[0].length - 1; id >= 0; id--) {
                    if (depth[a] <= depth[ac[b][id]]) {
                        b = ac[b][id];
                        if (depth[a] == depth[ac[b][id]]) {
                            break;
                        }
                    }
                }
            }
            int lca = a;
            if (a != b) {
                for (int id = ac[0].length - 1; id >= 0; id--) {
                    if (ac[a][id] != ac[b][id]) {
                        a = ac[a][id];
                        b = ac[b][id];
                    }
                    lca = ac[a][id];
                }
            }
            System.out.println(lca);

        }
    }


    static void getTree(int here, int parent) {
        // 먼저 depth 설정
        depth[here] = depth[parent] + 1;

        // 2^0 번째 조상을 바로위로 선정
        ac[here][0] = parent;

        // 조상들을 초기화 해줌.
        for (int i = 1; i < ac[0].length; i++) {
            int tmp = ac[here][i - 1];
            ac[here][i] = ac[tmp][i - 1];
        }


        // dfs로 트리를 계속 만든다.
        if (graph[here] != null) {
            for (int there : graph[here]) {
                if (there != parent) {
                    getTree(there, here);
                }
            }
        }
    }

    static double log2(double x, double base) {
        return Math.log10(x) / Math.log10(base);
    }


}
