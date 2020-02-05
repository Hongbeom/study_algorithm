package boj.n12899;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N12899 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int size = 2000001;
        SegmentTree tree = new SegmentTree(size);
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            if (q == 1) {
                tree.update(1, 0, size - 1, x, 1);
            } else {
                int k = tree.query(1, 0, size - 1, x);
                System.out.println(k);
                tree.update(1, 0, size - 1, k, -1);
            }

        }

    }

    static class SegmentTree {

        int[] tree;

        SegmentTree(int n) {
            tree = new int[n * 4];
        }

        int query(int node, int left, int right, int k) {
            if (left == right) {
                return left;
            }

            int mid = (left + right) / 2;
            if (k <= tree[node * 2]) {
                return query(node * 2, left, mid, k);
            } else {
                return query(node * 2 + 1, mid + 1, right, k - tree[node * 2]);
            }


        }

        void update(int node, int left, int right, int id, int value) {
            if (id < left || id > right) {
                return;
            }
            tree[node] += value;
            if (left != right) {
                int mid = (left + right) / 2;
                update(node * 2, left, mid, id, value);
                update(node * 2 + 1, mid + 1, right, id, value);
            }
        }

    }
}
