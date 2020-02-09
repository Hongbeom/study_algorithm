package boj.n6416;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class N6416 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int tc = 1;
        TreeSet<Integer> node = new TreeSet<>();
        int[] parent = new int[100000];

        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        int[] tree = parent.clone();
        int edge = 0;
        root:
        while (true) {
            String in = br.readLine().trim();
            if (in.startsWith("-1")) {
                return;
            }

            st = new StringTokenizer(in);
            while (st.hasMoreTokens()) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());


                if (a == b && b == 0) {
                    boolean check;

                    if (edge == 0) {
                        check = true;
                    } else if (edge != node.size() - 1) {
                        check = false;
                    } else {
                        check = check(tree, node);
                    }

                    ans(tc, check);
                    tc++;

                    node.clear();
                    tree = parent.clone();
                    edge = 0;
                    continue root;
                } else {
                    edge++;
                    node.add(a);
                    node.add(b);
                    union(tree, a, b);
                }
            }

        }

    }

    static void ans(int tc, boolean check) {
        if (check) {
            System.out.println("Case " + tc + " is a tree.");
        } else {
            System.out.println("Case " + tc + " is not a tree.");
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

    static boolean rootCheck(int[] parent, int a, int b) {
        a = getParent(parent, a);
        b = getParent(parent, b);
        return a == b;
    }

    static boolean check(int[] parent, Set<Integer> node) {
        for (int a : node) {

            for (int b : node) {
                if (a == b) {
                    continue;
                }

                if (!rootCheck(parent, a, b)) {
                    return false;
                }
            }
            break;
        }
        return true;
    }

}
