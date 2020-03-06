package kickstart.round_e_2019.cherriesmesh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CherriesMesh {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int tc = 1; tc <= T; tc++) {
            boolean check = false;
            st = new StringTokenizer(br.readLine());

            // number of cherries
            int n = Integer.parseInt(st.nextToken());

            // number of black strands
            int m = Integer.parseInt(st.nextToken());

            int[] parent = new int[n];

            for (int p = 0; p < n; p++) {
                parent[p] = p;
            }

            int bCount = 0;

            for (int i = 0; i < m; i++) {
                if (check) {
                    br.readLine();
                    continue;
                }
                st = new StringTokenizer(br.readLine());

                int from = Integer.parseInt(st.nextToken()) - 1;
                int to = Integer.parseInt(st.nextToken()) - 1;
                if (union(parent, from, to)) {
                    if (++bCount == n - 1) {
                        check = true;
                    }
                }

            }

            if (check) {
                ans(tc, n - 1);
                continue;
            }
            ans(tc, bCount + (n - 1 - bCount) * 2);

        }
    }

    static void ans(int tc, int n) {
        System.out.println("Case #" + tc + ": " + n);
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
