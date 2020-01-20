package boj.n3653;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N3653 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int tc = 0; tc < TC; tc++) {

            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[] arr = new int[n + m];
            int[] idx = new int[n];

            for (int i = 0; i < n; i++) {
                idx[i] = m + i;
            }

            for (int i = m; i < n + m; i++) {
                arr[i] = 1;
            }

            SegmentTree tree = new SegmentTree(arr);
            st = new StringTokenizer(br.readLine());
            int moveId = m - 1;
            for (int i = 0; i < m; i++) {
                int id = Integer.parseInt(st.nextToken()) - 1;
                System.out.print(tree.query(1, 0, n + m - 1, 0, idx[id] - 1) + " ");
                tree.update(1, 0, n + m - 1, idx[id], -1);
                tree.update(1, 0, n + m - 1, moveId, 1);
                idx[id] = moveId;
                moveId--;
            }
            System.out.println();
        }
    }

    static class SegmentTree {
        int[] tree;

        SegmentTree(int[] arr) {

            tree = new int[arr.length * 4];

            init(arr, 1, 0, arr.length - 1);

        }

        int init(int[] arr, int node, int left, int right) {
            if (left == right) {
                return tree[node] = arr[left];
            }
            int mid = (left + right) / 2;
            tree[node] += init(arr, node * 2, left, mid);
            tree[node] += init(arr, node * 2 + 1, mid + 1, right);

            return tree[node];
        }

        int query(int node, int left, int right, int a, int b) {
            if (a > right || b < left) {
                return 0;
            } else if (a <= left && right <= b) {
                return tree[node];
            }
            int mid = (left + right) / 2;
            return query(node * 2, left, mid, a, b)
                    + query(node * 2 + 1, mid + 1, right, a, b);

        }

        void update(int node, int left, int right, int id, int diff) {
            if (id > right || id < left) {
                return;
            }
            tree[node] += diff;
            if (left != right) {
                int mid = (left + right) / 2;
                update(node * 2, left, mid, id, diff);
                update(node * 2 + 1, mid + 1, right, id, diff);
            }
        }
    }
}
