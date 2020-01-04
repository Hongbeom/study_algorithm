package boj.n2843;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N2843 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] vertex = new int[n];
        int ansCnt = 0;
        for (int i = 0; i < n; i++) {
            vertex[i] = Integer.parseInt(st.nextToken()) - 1;
        }
        int[] save = vertex.clone();

        int q = Integer.parseInt(br.readLine());

        int[][] queries = new int[q][2];

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int question = Integer.parseInt(st.nextToken());
            int node = Integer.parseInt(st.nextToken()) - 1;

            queries[i][0] = question;
            queries[i][1] = node;

            if (question == 2) {
                vertex[node] = -1;
            }else{
                ansCnt++;
            }
        }
        int[] answer = new int[ansCnt];
        ansCnt = 0;
        int[] parent = new int[n];
        boolean[] cycle = new boolean[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < n; i++) {
            if (vertex[i] != -1) {
                union(parent, i, vertex[i], cycle);
            }
        }

        for (int i = queries.length - 1; i >= 0; i--) {
            int[] query = queries[i];
            int question = query[0];
            int node = query[1];

            if (question == 1) {
                if (cycle[getParent(parent, node)]) {
                    answer[ansCnt++] = -1;
                } else {
                    answer[ansCnt++] = getParent(parent, node) + 1;
                }
            } else {
                union(parent, node, save[node], cycle);
            }
        }

        for(int i = answer.length - 1; i >=0; i--){
            if(answer[i] < 0){
                System.out.println("CIKLUS");
            }else{
                System.out.println(answer[i]);
            }
        }

    }

    static int getParent(int[] parent, int a) {
        if (parent[a] == a) {
            return a;
        }

        return parent[a] = getParent(parent, parent[a]);

    }

    static void union(int[] parent, int a, int b, boolean[] cycle) {
        a = getParent(parent, a);
        b = getParent(parent, b);

        if (a == b) {
            cycle[a] = true;
        } else {
            parent[a] = b;
        }
    }
}
