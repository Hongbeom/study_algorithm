package boj.n9345;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N9345 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int Q = Integer.parseInt(st.nextToken());

            int[] dvd = new int[n];

            for (int i = 0; i < dvd.length; i++) {
                dvd[i] = i;
            }

            MaxMinSegTree tree = new MaxMinSegTree(n);

            for (int i = 0; i < Q; i++) {
                st = new StringTokenizer(br.readLine());
                int q = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());


                if (q == 1) {
                    int[] result = tree.query(1, 0, n - 1, a, b);

                    if (result[0] == a && result[1] == b) {
                        System.out.println("YES");
                    } else {
                        System.out.println("NO");
                    }
                } else {
                    int orinA = dvd[a];
                    int orinB = dvd[b];

                    dvd[a] = orinB;
                    dvd[b] = orinA;

                    tree.update(1, 0, n - 1, a, orinB);
                    tree.update(1, 0, n - 1, b, orinA);
                }


            }

        }

    }

    static class MaxMinSegTree {

        int[][] tree;

        MaxMinSegTree(int n) {
            tree = new int[n * 4][2];
            init(1, 0, n - 1);
        }


        int[] init(int node, int left, int right) {
            if (left == right) {
                tree[node][0] = left;
                tree[node][1] = left;
                return tree[node];
            }

            int mid = (left + right) / 2;

            int[] leftNode = init(node * 2, left, mid);
            int[] rightNode = init(node * 2 + 1, mid + 1, right);


            tree[node][0] = Math.min(leftNode[0], rightNode[0]);
            tree[node][1] = Math.max(leftNode[1], rightNode[1]);

            return tree[node];
        }

        int[] query(int node, int left, int right, int a, int b) {


            if (a > right || b < left) {
                return null;
            } else if (a <= left && right <= b) {
                return tree[node];
            }

            int mid = (left + right) / 2;

            int[] leftResult = query(node * 2, left, mid, a, b);
            int[] rightResult = query(node * 2 + 1, mid + 1, right, a, b);

            if (leftResult == null) {
                return rightResult;
            } else if (rightResult == null) {
                return leftResult;
            } else {
                int[] result = new int[2];

                result[0] = Math.min(leftResult[0], rightResult[0]);
                result[1] = Math.max(leftResult[1], rightResult[1]);

                return result;
            }

        }

        void update(int node, int left, int right, int id, int value) {

            if (id < left || id > right) {
                return;
            } else if (left == right) {
                tree[node][0] = value;
                tree[node][1] = value;
                return;
            }

            int mid = (left + right) / 2;
            update(node * 2, left, mid, id, value);
            update(node * 2 + 1, mid + 1, right, id, value);

            tree[node][0] = Math.min(tree[node * 2][0], tree[node * 2 + 1][0]);
            tree[node][1] = Math.max(tree[node * 2][1], tree[node * 2 + 1][1]);

        }

    }
}
