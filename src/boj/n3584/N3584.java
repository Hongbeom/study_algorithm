package boj.n3584;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class N3584 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());


            int[] parent = new int[n + 1];
            for (int i = 0; i < n - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int p = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                parent[c] = p;
            }

            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());


            List<Integer> aPath = new ArrayList<>();
            List<Integer> bPath = new ArrayList<>();

            createPath(aPath, parent, a);
            createPath(bPath, parent, b);

            aP:
            for (int pa : aPath) {
                for (int pb : bPath) {
                    if (pa == pb) {
                        System.out.println(pa);
                        break aP;
                    }
                }
            }

        }
    }


    static void createPath(List<Integer> list, int[] parent, int a) {

        while (a != 0) {
            list.add(a);
            a = parent[a];
        }
    }
}
