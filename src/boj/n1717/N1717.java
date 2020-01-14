package boj.n1717;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N1717 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] parent = new int[n + 1];

        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int q = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (q == 0) {
                union(parent, a, b);
            } else {
                if(check(parent, a, b)){
                    System.out.println("YES");
                }else{
                    System.out.println("NO");
                }
            }
        }
    }


    static int getParent(int[] parent, int a) {
        if (parent[a] == a) {
            return a;
        }

        return parent[a] = getParent(parent, parent[a]);
    }

    static void union(int[] parent, int a, int b) {
        a = getParent(parent, a);
        b = getParent(parent, b);

        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    static boolean check(int[] parent, int a, int b) {
        a = getParent(parent, a);
        b = getParent(parent, b);

        return a == b;
    }

}
