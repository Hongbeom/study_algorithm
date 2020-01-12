package boj.n1197;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class N1197 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        int[] parent = new int[v];

        for (int i = 0; i < v; i++) {
            parent[i] = i;
        }

        List<int[]> edges = new ArrayList<>();

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new int[]{Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken())});
        }

        Collections.sort(edges, (o1, o2) -> {
            if (o1[2] > o2[2]) {
                return 1;
            } else if (o1[2] < o2[2]) {
                return -1;
            }
            return 0;
        });

        int answer = 0;
        int cnt = 0;

        for (int[] edge : edges) {

            if (union(parent, edge[0], edge[1])) {
                answer += edge[2];
                cnt++;
            }

            if (cnt == v-1) {
                break;
            }
        }

        System.out.println(answer);
    }


    static int getParent(int[] parent, int a) {
        if (parent[a] == a) {
            return a;
        }

        return parent[a] = getParent(parent, parent[a]);
    }

    static boolean union(int[] parent, int a, int b) {
        a = getParent(parent, a);
        b = getParent(parent, b);

        if (a == b) {
            return false;
        }

        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }

        return true;
    }
}
